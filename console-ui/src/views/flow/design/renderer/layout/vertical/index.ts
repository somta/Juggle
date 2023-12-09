import * as d3 from 'd3';
import { ElementType, D3Element } from '../../../types';
import { LayoutNode } from '../LayoutNode';
import { FlowRenderer } from '../../renderer';
import { DataNode } from '../../data';
import { layoutTree, box, setLayoutToMap } from './generate';

type D3Point = [number, number];

export class VerticalLayout {
  constructor(renderer: FlowRenderer) {
    // 居中
    renderer.container.attr('transform', `translate(${renderer.boundary.width / 2}, 0)`);
    this.lineContainer = renderer.container.append('g');
    this.nodeContainer = renderer.container.append('g');
    this.renderer = renderer;
  }

  private renderer: FlowRenderer;

  private nodeContainer: D3Element;

  private lineContainer: D3Element;

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
    this.drawNode(this.nodeContainer, this._layoutRoot!);
  }

  /**
   * 画线
   */

  drawLines() {
    const lines: { from: string; to: string }[] = [];
    this.layoutNodeMap.forEach(node => {
      const { linesTo, data } = node;
      if (node.data.type === ElementType.ROOT) {
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
    const from: D3Point = fromNode.linePoint;
    const to: D3Point = toNode.linePoint;
    if (from[0] === to[0]) {
      return [from, to];
    }
    let turningPoint;
    if (fromNode.data.type === ElementType.CONDITION) {
      turningPoint = from[1];
    } else {
      turningPoint = toNode.y - box.marginBottom;
    }
    return [from, [from[0], turningPoint], [to[0], turningPoint], to];
  }

  /**
   * 画节点
   */

  private drawNode(container: D3Element, node: LayoutNode, type: string = 'update') {
    switch (node.data.type) {
      case ElementType.START:
      case ElementType.END:
      case ElementType.METHOD:
        this.drawNormal(container, node, type);
        break;
      case ElementType.CONDITION:
        this.drawCondition(container, node);
        break;
      case ElementType.BRANCH:
      case ElementType.ROOT:
        this.drawBranch(container, node);
        break;
      default:
        break;
    }
  }

  drawNormal(container: D3Element, node: LayoutNode, type: string) {
    const { width, height, data } = node;
    if (type === 'enter') {
      const flowNode = container.append('g').attr('class', 'flow-node');

      flowNode
        .append('rect')
        .attr('width', width)
        .attr('height', height)
        .attr('fill', '#fff')
        .attr('stroke', '#aaa')
        .attr('stroke-width', 1)
        .attr('rx', 4)
        .attr('ry', 4);

      flowNode
        .append('text')
        .attr('text-anchor', 'middle')
        .attr('dominant-baseline', 'middle')
        .attr('x', width / 2)
        .attr('y', height / 2)
        .text(data.raw.name);

      this.drawHoverButtons(flowNode, node);

      if ([ElementType.START, ElementType.METHOD].includes(data.type)) {
        this.drawAddIcon(container, node, type);
      }
    }
  }

  drawCondition(container: D3Element, node: LayoutNode) {
    if (container.selectChild('.flow-node').size() === 0) {
      // container.append('rect')
      //   .attr('width', node.width)
      //   .attr('height', node.height)
      //   .attr('fill', '#fff')
      //   .attr('stroke', '#aaa')
      //   .attr('stroke-width', 1)
      //   .attr('rx', 4)
      //   .attr('ry', 4);

      const conditionStart = container
        .append('g')
        .attr('class', 'flow-node')
        .attr('transform', `translate(${node.width / 2}, 0)`);

      conditionStart
        .append('rect')
        .attr('width', box.width)
        .attr('height', box.height)
        .attr('x', -box.width / 2)
        .attr('fill', '#fff')
        .attr('stroke', '#aaa')
        .attr('stroke-width', 1)
        .attr('rx', 4)
        .attr('ry', 4);
      conditionStart
        .append('text')
        .attr('text-anchor', 'middle')
        .attr('dominant-baseline', 'middle')
        .attr('y', box.height / 2)
        .text(node.data.raw.name);

      this.drawHoverButtons(conditionStart, node);
      this.drawAddIcon(container, node, 'enter');
    }

    const children = node.getChildren();
    container
      .selectChildren('.branch-wrap')
      .data(children, (d: any) => d.data.key)
      .join(
        enter =>
          enter.append('g').each((d, i, nodes) => {
            const g = d3.select(nodes[i]);
            this.drawBranch(g, d);
          }),
        update =>
          update.each((d, i, nodes) => {
            const g = d3.select(nodes[i]);
            this.drawBranch(g, d);
          })
      )
      .attr('class', 'branch-wrap')
      .attr('transform', d => `translate(${d.left}, ${d.top})`);
  }

  drawBranch(container: D3Element, branch: LayoutNode) {
    if (branch.data.type === ElementType.BRANCH) {
      if (container.selectChild('.flow-node').size() === 0) {
        const branchStart = container.append('g').attr('class', 'flow-node');
        // container.append('rect')
        //   .attr('width', branch.width)
        //   .attr('height', branch.height)
        //   .attr('fill', '#fff')
        //   .attr('stroke', '#aaa')
        //   .attr('stroke-width', 1)
        //   .attr('rx', 4)
        //   .attr('ry', 4)
        //   .attr('x', -branch.width / 2);

        branchStart
          .append('rect')
          .attr('width', box.width)
          .attr('height', box.height)
          .attr('x', -branch.width / 2)
          .attr('fill', '#fff')
          .attr('stroke', '#aaa')
          .attr('stroke-width', 1)
          .attr('rx', 4)
          .attr('ry', 4);
        branchStart
          .append('text')
          .attr('text-anchor', 'middle')
          .attr('dominant-baseline', 'middle')
          .attr('x', 0)
          .attr('y', box.height / 2)
          .text(branch.data.raw.name);

        this.drawHoverButtons(branchStart, branch);
        this.drawAddIcon(container, branch, 'enter');
      }
    }

    const nodes = branch.getChildren();
    container
      .selectChildren('.node-wrap')
      .data(nodes, (d: any) => d.data.key)
      .join(
        enter =>
          enter.append('g').each((d, i, nodes) => {
            const g = d3.select(nodes[i]);
            this.drawNode(g, d, 'enter');
          }),
        update =>
          update.each((d, i, nodes) => {
            const g = d3.select(nodes[i]);
            this.drawNode(g, d);
          })
      )
      .attr('class', 'node-wrap')
      .attr('transform', d => `translate(${d.left}, ${d.top})`);
  }

  drawAddIcon(container: D3Element, node: LayoutNode, type: string) {
    let { width, height } = node;
    if (node.data.type === ElementType.BRANCH) {
      width = 0;
      height = box.height;
    }
    if (type === 'enter') {
      const btn_radius = 16;
      const addButton = container
        .append('g')
        .attr('class', 'flow-btn flow-btn-add')
        .attr('transform', `translate(${width / 2}, ${height + box.marginBottom / 2})`)
        .on('click', (_, d) => {
          this.renderer.options.onAdd?.(d);
        });
      addButton.append('circle').attr('cx', 0).attr('cy', 0).attr('r', btn_radius).attr('fill', '#fff').attr('stroke', '#777');

      addButton.append('use').attr('href', '#icon-plus').attr('width', 24).attr('height', 24).attr('x', -12).attr('y', -12).attr('fill', '#777');
    } else {
      container.select('.flow-btn-add').attr('transform', `translate(${width / 2}, ${height + box.marginBottom / 2})`);
    }
  }

  drawHoverButtons(container: D3Element, node: LayoutNode) {
    let { width } = node;
    const { data } = node;
    if (data.type === ElementType.BRANCH || data.type === ElementType.CONDITION) {
      width = box.width / 2;
    }
    let btns = ['delete', 'edit'];
    if ([ElementType.START, ElementType.END, ElementType.BRANCH].includes(data.type)) {
      btns = ['edit'];
    }
    btns.forEach((btn, i) => {
      const flowBtns = container
        .append('g')
        .attr('class', `flow-btn flow-btn-${btn}`)
        .attr('transform', `translate(${width - i * 36}, 0)`)
        .on('click', (_, d) => {
          if (btn === 'edit') {
            this.renderer.options.onEdit?.(d);
          }
          if (btn === 'delete') {
            this.renderer.options.onDelete?.(d);
          }
        });
      flowBtns.append('circle').attr('cx', 0).attr('cy', 0).attr('r', '15').attr('fill', '#fff').attr('stroke', '#777');

      flowBtns.append('use').attr('href', `#icon-${btn}`).attr('width', 16).attr('height', 16).attr('x', -8).attr('y', -8).attr('fill', '#777');
    });
  }
}
