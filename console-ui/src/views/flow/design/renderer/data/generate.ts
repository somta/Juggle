import { FlowContext } from '../../hooks/flow-data';
import { ElementType } from '../../types';
import { DataNode, DataRootNode, DataBranchNode } from './DataNode';

export function generateDataTree(flowContext: FlowContext) {
  const datas = flowContext.getFlowNodes();
  const start = datas.find(data => data.elementType === ElementType.START);
  if (!start) throw new Error('start node not found');
  const root = new DataRootNode(flowContext);
  root.out = start.key;
  generateBranch(root, flowContext);
  return root;
}

function generateBranch(branch: DataRootNode | DataBranchNode, flowContext: FlowContext) {
  let current = flowContext.getFlowNode(branch.out);
  while (current) {
    const parent = branch.getParent();
    if (parent && parent.out === current.key) {
      break;
    }
    const node = new DataNode(flowContext, current.key);
    branch.addChild(node);
    if (current.elementType === ElementType.CONDITION) {
      current.conditions?.forEach((_, index) => {
        const branch = new DataBranchNode(flowContext, current!, index);
        node.addChild(branch);
        generateBranch(branch, flowContext);
      });
    }
    const outgoing = flowContext.getFlowNode(node.out);
    current = outgoing;
  }
}

export function setDataNodeMap(node: DataNode, dataNodemap: Map<string, DataNode>) {
  dataNodemap.set(node.key, node);
  node.getChildren().forEach(child => {
    setDataNodeMap(child, dataNodemap);
  });
  return dataNodemap;
}
