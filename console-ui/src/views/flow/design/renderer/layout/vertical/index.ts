import * as d3 from 'd3';
import { ElementType, D3Element } from '../../../types';
import { LayoutNode } from '../LayoutNode';
import { FlowRenderer } from '../../index';
import { DataNode } from '../../../data';
import { layoutTree, setLayoutToMap } from './generate';
import {DrawNodeHelper} from "./DrawNodeHelper.ts";

type D3Point = [number, number];

export class VerticalLayout {
  constructor(renderer: FlowRenderer) {
    // 居中
    renderer.container.attr('transform', `translate(${renderer.boundary.width / 2}, 0)`);
    this.lineContainer = renderer.container.append('g');
    this.nodeContainer = renderer.container.append('g');
    this.drawNodeHelper = new DrawNodeHelper(renderer);
  }

  private nodeContainer: D3Element;

  private lineContainer: D3Element;

  private drawNodeHelper: DrawNodeHelper;

  readonly layoutNodeMap: Map<string, LayoutNode> = new Map();

  private line = d3.line();

  _layoutRoot: LayoutNode | null = null;

  _dataRoot: DataNode | null = null;

  /**
   * 分析
   */

  analysis(dataRoot: DataNode) {
    this._dataRoot = dataRoot;
    this._layoutRoot = layoutTree(dataRoot);
    this.layoutNodeMap.clear();
    setLayoutToMap(this._layoutRoot, this.layoutNodeMap);
  }

  public draw() {
    this.drawLines();
    this.drawNodeHelper.drawNode(this.nodeContainer, this._layoutRoot!);
  }

  /**
   * 画线
   */

  drawLines() {
    const lines: { from: string; to: string }[] = [];
    this.layoutNodeMap.forEach(node => {
      const { linesTo, data } = node;
      if (node.data.key === 'root') {
        return;
      }
      linesTo.forEach(to => {
        const line = {
          from: data.key,
          to,
        };
        lines.push(line);
      });
    });
    const flowLines = this.lineContainer.selectAll('.flow-line').data(lines, (d: any) => `${d.from}-${d.to}`);

    // 更新
    flowLines.filter('path').attr('d', d => this.getPathD(d));

    // 新增
    flowLines
      .enter()
      .append('path')
      .attr('d', d => this.getPathD(d))
      .attr('class', 'flow-line')
      .attr('fill', 'none')
      .attr('stroke', '#aaa');
    // 删除
    flowLines.exit().remove();
  }

  getPathD(d: { from: string; to: string }) {
    const fromNode = this.layoutNodeMap.get(d.from);
    const toNode = this.layoutNodeMap.get(d.to);
    if (fromNode && toNode) {
      const polyLine = this.getPolyline(fromNode, toNode);
      return this.line(polyLine);
    }
    return '';
  }

  getPolyline(fromNode: LayoutNode, toNode: LayoutNode): D3Point[] {
    const from: D3Point = fromNode.getContentBoxCenter();
    const to: D3Point = toNode.getContentBoxCenter();
    // 最后一个分支节点
    const parent = fromNode.getParent();
    const children = parent.getChildren();
    const lastChild = children[children.length - 1];
    const isLastChild = fromNode === lastChild;
    // 条件节点
    if (fromNode.data.type === ElementType.CONDITION) {
      if (toNode.data.type === ElementType.BRANCH) {
        return [from, [to[0], from[1]], to];
      } else if (isLastChild) {
        const bottomCenter = fromNode.getBottomCenter();
        const condition = parent.getParent();
        const parentBottomCenter = condition.getBottomCenter();
        return [bottomCenter, [bottomCenter[0], parentBottomCenter[1]], parentBottomCenter];
      } else {
        const bottomCenter = fromNode.getBottomCenter();
        return [bottomCenter, to];
      }
    }
    // 空分支节点
    if (fromNode.data.type === ElementType.BRANCH && fromNode.getChildren().length === 0) {
      const bottomCenter = parent.getBottomCenter();
      return [from, [from[0], bottomCenter[1]], bottomCenter];
    }
    // 最后一个分支节点
    if (parent.data.type === ElementType.BRANCH && isLastChild) {
      const condition = parent.getParent();
      const bottomCenter = condition.getBottomCenter();
      return [from, [from[0], bottomCenter[1]], bottomCenter];
    }
    // 其他节点
    return [from, to];
  }

}
