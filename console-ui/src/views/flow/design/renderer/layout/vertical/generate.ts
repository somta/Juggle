import { ElementType } from '../../../types';
import { DataNode } from '../../../data';
import { LayoutNode } from '../LayoutNode';

export const box = {
  width: 180,
  height: 40,
  headerHeight: 26,
  marginBottom: 80,
  marginRight: 60,
  conditionWidth: 140,
  conditionHeight: 40,
};

const heightAndMargin = box.height + box.marginBottom;
const branchTop = box.height + box.marginBottom / 2;

export function layoutTree(node: DataNode) {
  const root = new LayoutNode({
    left: 0,
    top: 0,
    width: 0,
    height: 0,
    data: node,
    linesTo: [],
  });
  return layoutBranch(root);
}

function layoutBranch(branch: LayoutNode) {
  let prev: LayoutNode = branch;
  branch.data.getChildren().forEach(child => {
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
  // 子节点居中
  branch.getChildren().forEach(child => {
    alignItemCenter(child);
  });
  // 分支节点偏移
  if (branch.data.key !== 'root') {
    offsetBranch(branch);
  }
  // 条件节点连线
  if (condition && condition.data.type === ElementType.CONDITION) {
    // 条件节点 -> 分支节点
    condition.line(branch.data.key);
    // 分支节点最末 -> 条件节点出口
    prev.line(condition.data.out);
  }
  return branch;
}

function layoutNormal(prev: LayoutNode, node: DataNode) {
  let left = prev.left;
  let top = prev.top + prev.height + box.marginBottom;
  // prev是父节点时，是第一个节点
  if (node.getParent() === prev.data) {
    left = 0;
    top = heightAndMargin;
  }
  const layout = new LayoutNode({
    left,
    top,
    width: box.width,
    height: box.height,
    data: node,
    linesTo: [],
  });
  layout.setContentBox({
    width: box.width,
    height: box.height,
    left: 0,
    top: 0,
  });
  if ([ElementType.START, ElementType.END].includes(node.type)) {
    layout.setSize(50, 50);
    layout.setContentBox({
      width: 50,
      height: 50,
      left: 0,
      top: 0,
    });
  }
  return layout;
}

function layoutCondition(prev: LayoutNode, node: DataNode) {
  const condition = layoutNormal(prev, node);
  condition.line(node.out);
  let prevChild: LayoutNode;
  node.getChildren().forEach(child => {
    let left = 0;
    if (prevChild) {
      left = prevChild.left + prevChild.width / 2 + box.marginRight;
    }
    const branch = new LayoutNode({
      left: left,
      top: branchTop,
      width: box.width,
      height: box.height,
      data: child,
      linesTo: [],
    });
    condition.addChild(branch);
    layoutBranch(branch);
    prevChild = branch;
  });
  // 设置节点尺寸
  setConditionBox(condition);
  return condition;
}

function setBranchBox(node: LayoutNode) {
  const children = node.getChildren() || [];
  const width = Math.max(node.width, ...node.getChildren().map(child => child.width));
  const height =
    heightAndMargin +
    children.reduce((sum, child) => {
      return sum + child.height + box.marginBottom;
    }, 0);
  node.setSize(width, height);
  node.setContentBox({
    width: box.width,
    height: box.height,
    left: -box.width / 2,
    top: 0,
  });
}

function setConditionBox(node: LayoutNode) {
  const children = node.getChildren() || [];
  if (children.length === 0) {
    return;
  }
  const first = children[0];
  const last = children[children.length - 1];
  // branch的left为中点
  const width = last.left + last.width / 2 - (first.left - first.width / 2);
  const height = branchTop + Math.max(...node.getChildren().map(child => child.height));
  node.setSize(width, height);
  node.setContentBox({
    width: box.conditionWidth,
    height: box.conditionHeight,
    left: width / 2 - box.conditionWidth / 2,
    top: 0,
  });
}

function alignItemCenter(node: LayoutNode) {
  node.setRelative(node.left - node.width / 2, node.top);
}

function offsetBranch(node: LayoutNode) {
  node.setRelative(node.left + node.width / 2, node.top);
}

export function setLayoutToMap(node: LayoutNode, dataNodeMap: Map<string, LayoutNode>) {
  dataNodeMap.set(node.data.key, node);
  node.getChildren().forEach(child => {
    setLayoutToMap(child, dataNodeMap);
  });
  return dataNodeMap;
}
