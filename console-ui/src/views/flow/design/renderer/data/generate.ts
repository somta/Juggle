import { ElementType, RawData } from '../../types';
import { DataNode, DataRootNode, DataBranchNode } from './DataNode';

export function generateDataTree (datas: RawData[]) {
  const dataMap = new Map<string, RawData>();
  datas.forEach(data => {
    dataMap.set(data.key, data);
  });
  const start = datas.find(data => data.elementType === ElementType.START);
  if (!start) throw new Error('start node not found');
  const root = new DataRootNode();
  root.out = start.key;
  generateBranch(root, dataMap);
  return root;
}

function generateBranch (branch: DataRootNode | DataBranchNode, dataMap: Map<string, RawData>) {
  let current = dataMap.get(branch.out);
  while (current) {
    const parent = branch.getParent();
    if (parent && parent.out === current.key) {
      break;
    }
    const node = new DataNode(current);
    branch.addChild(node);
    if (current.elementType === ElementType.CONDITION) {
      current.conditions?.forEach((_, index) => {
        const branch = new DataBranchNode(current!, index);
        node.addChild(branch);
        generateBranch(branch, dataMap);
      });
    }
    const outgoing = dataMap.get(node.out);
    current = outgoing;
  }
}
