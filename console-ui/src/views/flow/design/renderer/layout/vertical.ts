import { ElementType } from '../../types';
import { DataNode } from '../data';
import { LayoutNode } from './LayoutNode';

const box = {
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
    // 创建布局
    let childLayout: LayoutNode;
    if (child.type === ElementType.CONDITION) {
      childLayout = layoutCondition(prev, child);
    } else {
      childLayout = layoutNormal(prev, child);
    }
    branch.addChild(childLayout);
    // 连线
    
    prev = childLayout;
  });

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
  return layout;
}

function layoutCondition (prev: LayoutNode, node: DataNode) {
  const condition = layoutNormal(prev, node);
  let prevChild: LayoutNode;
  node.getChildren().forEach(child => {
    const branch = new LayoutNode({
      left: (prevChild ? prevChild.right : 0) + box.marginRight,
      top: 0,
      width: 0,
      height: 0,
      data: child,
      linesTo: [],
    });
    layoutBranch(branch, child);
    condition.addChild(branch);
    prevChild = branch;
  });
  return condition;
}
