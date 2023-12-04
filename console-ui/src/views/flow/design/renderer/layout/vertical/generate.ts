import { ElementType } from '../../../types';
import { DataNode } from '../../data';
import { LayoutNode } from '../LayoutNode';

export const box = {
  width: 200,
  height: 40,
  marginBottom: 20,
  marginRight: 20,
};

export function layoutTree (node: DataNode) {
  const root = new LayoutNode({
    left: 0,
    top: 0,
    width: 0,
    height: 0,
    data: node,
    linesTo: [],
  });
  return layoutBranch(root, node);
}

function layoutBranch (branch: LayoutNode, node: DataNode) {
  let prev: LayoutNode = branch;
  node.getChildren().forEach(child => {
    let childLayout: LayoutNode;
    if (child.type === ElementType.CONDITION) {
      childLayout = layoutCondition(prev, child);
    } else {
      childLayout = layoutNormal(prev, child);
    }
    branch.addChild(childLayout);
    // 普通节点连线
    if (prev.data.type !== ElementType.CONDITION) {
      prev.line(child.key);
    }
    prev = childLayout;
  });
  const condition = branch.getParent();
  // 设置节点尺寸
  setBranchBox(branch);
  // 条件节点连线
  if (condition && condition.data.type === ElementType.CONDITION) {
    // 条件节点 -> 分支节点
    condition.line(branch.data.key);
    // 分支节点最末 -> 条件节点出口
    prev.line(condition.data.out);
    // 分支节点居中
    alignBranchCenter(branch);
  }
  return branch;
}

function layoutNormal (prev: LayoutNode, node: DataNode) {
  const layout = new LayoutNode({
    left: prev.left,
    top: prev.top + prev.height + box.marginBottom,
    width: box.width,
    height: box.height,
    data: node,
    linesTo: [],
  });
  alignItemCenter(layout);
  return layout;
}

function layoutCondition (prev: LayoutNode, node: DataNode) {
  const condition = layoutNormal(prev, node);
  let prevChild: LayoutNode;
  node.getChildren().forEach(child => {
    const branch = new LayoutNode({
      left: prevChild ? prevChild.right + box.marginRight : 0,
      top: 0,
      width: box.width,
      height: box.height,
      data: child,
      linesTo: [],
    });
    condition.addChild(branch);
    layoutBranch(branch, child);
    prevChild = branch;
  });
  // 设置节点尺寸
  setConditionBox(condition);
  return condition;
}

function setBranchBox (node: LayoutNode) {
  const children = node.getChildren() || [];
  if (children.length === 0) {
    return;
  }
  const first = children[0];
  const last = children[children.length - 1];
  const width = Math.max(...node.getChildren().map(child => child.width));
  const height = last.bottom - first.top;
  node.setSize(width, height);
}

function setConditionBox (node: LayoutNode) {
  const children = node.getChildren() || [];
  if (children.length === 0) {
    return;
  }
  const first = children[0];
  const last = children[children.length - 1];
  const width = last.right - first.left;
  const height = Math.max(...node.getChildren().map(child => child.height));
  node.setSize(width, height);
}

function alignItemCenter (node: LayoutNode) {
  node.setRelative(node.left - node.width / 2, node.top);
}

function alignBranchCenter (node: LayoutNode) {
  node.setRelative(node.left + node.width / 2, node.top);
}
