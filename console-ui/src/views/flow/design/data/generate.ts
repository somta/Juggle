import { FlowContext } from '../hooks/flow-data';
import { ElementType, MyOptional, RawData } from '../types';
import { DataNode, DataBranch } from './DataNode';

export function generateDataTree(flowContext: FlowContext) {
  // 初始化数据关联
  DataNode.context = {
    getRaw: key => flowContext.getFlowNode(key),
    getType: key => flowContext.getFlowNode(key)?.elementType,
    getIn: key => flowContext.getFlowNode(key)?.incomings?.[0],
    setIn: (key, val) => {
      flowContext.updateFlowNode(key, { incomings: [val] });
    },
    getOut: key => flowContext.getFlowNode(key)?.outgoings?.[0],
    setOut: (key, val) => {
      flowContext.updateFlowNode(key, { outgoings: [val] });
    },
    setBranchOut: (key, branchIndex, val) => {
      flowContext.update(draft => {
        const parentRaw = draft.flowContent.find(item => item.key === key);
        const branch = parentRaw?.conditions?.[branchIndex];
        if (branch) {
          branch.outgoing = val;
        }
      });
    },
    addRaw: raw => {
      flowContext.update(draft => {
        const item = draft.flowContent.find(item => item.key === raw.key);
        if (!item) {
          draft.flowContent.push(raw);
        }
      });
    },
    deleteRaw: key => {
      flowContext.update(draft => {
        const index = draft.flowContent.findIndex(item => item.key === key);
        if (index !== -1) {
          draft.flowContent.splice(index, 1);
        }
      });
    },
  };
  DataNode.DataNodeMap = new Map();

  const datas = flowContext.getFlowNodes();
  const start = datas.find(data => data.elementType === ElementType.START);
  if (!start) throw new Error('start node not found');
  // 初始分支
  const branch = new DataBranch(0);
  const startNode = new DataNode(start.key);
  branch.addChild(startNode);
  generateBranch(flowContext, branch);
  // 清理无用的节点
  if (datas.length > DataNode.DataNodeMap.size) {
    flowContext.update(draft => {
      draft.flowContent = draft.flowContent.filter(item => {
        return DataNode.DataNodeMap.has(item.key);
      });
    });
  }
  return branch;
}

function generateBranch(flowContext: FlowContext, branch: DataBranch) {
  let out = branch.out;
  if (branch.key === 'root') {
    out = branch.getChildren()?.[0].out;
  }
  let current = flowContext.getFlowNode(out);
  while (current) {
    const parent = branch.getParent() as DataNode;
    if (parent && parent.out === current.key) {
      break;
    }
    const node = new DataNode(current.key);
    branch.addChild(node);
    if (current.elementType === ElementType.CONDITION) {
      current.conditions?.forEach((_, index) => {
        const branch = new DataBranch(index);
        node.addChild(branch);
        generateBranch(flowContext, branch);
      });
    }
    const outgoing = flowContext.getFlowNode(node.out);
    current = outgoing;
  }
}

// 生成节点key
function generateNodeKey(type: ElementType) {
  return `${type.toLowerCase()}_${generateRandomKey()}`;
}

// 生成8位随机字符串
function generateRandomKey() {
  return Math.random().toString(36).substring(2, 10);
}

// 初始化节点
export function initNewNode(info: MyOptional<RawData, 'name' | 'elementType'>, next: DataNode) {
  // 创建节点
  const currentRaw: RawData = {
    key: generateNodeKey(info.elementType),
    outgoings: [],
    incomings: [],
    ...info,
  };
  let current: DataNode;
  if (info.elementType === ElementType.CONDITION) {
    current = new DataNode(currentRaw.key);
    // 默认分支
    if (!currentRaw.conditions || currentRaw.conditions.length === 0) {
      currentRaw.conditions = [
        {
          conditionName: '条件1',
          conditionType: 'CUSTOM',
          expression: '',
          outgoing: next.key,
          conditionExpressions: [],
        },
        {
          conditionName: '默认else分支',
          conditionType: 'DEFAULT',
          expression: '',
          outgoing: next.key,
          conditionExpressions: [],
        },
      ];
    }
    currentRaw.conditions?.forEach((_, index) => {
      const branch = new DataBranch(index);
      current.addChild(branch);
    });
  } else {
    current = new DataNode(currentRaw.key);
  }
  DataNode.context.addRaw(currentRaw);
  return current;
}

export function rebuildCondition(flowContext: FlowContext, node: DataNode, oldData: RawData) {
  if (node.type !== ElementType.CONDITION) {
    return;
  }
  const oldChilds: string[] = [];
  oldData.conditions?.forEach(oldCondition => {
    if (oldCondition.outgoing !== node.out) {
      oldChilds.push(oldCondition.outgoing);
    }
  });
  node.getChildren().forEach(child => {
    if (oldChilds.includes(child.out)) {
      node.removeChild(child);
    } else {
      child.delete();
    }
  });
  node.raw.conditions?.forEach((_, index) => {
    const branch = new DataBranch(index);
    node.addChild(branch);
    generateBranch(flowContext, branch);
  });
}
