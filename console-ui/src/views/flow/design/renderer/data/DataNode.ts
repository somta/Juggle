import { TreeNode } from '../utils/TreeNode';
import { ElementType, RawData } from '../../types';
import { FlowContext } from '../../hooks/flow-data';

/**
 * 数据节点
 */
export class DataNode extends TreeNode {
  constructor(flowContext: FlowContext, key: string) {
    super();
    this.context = flowContext;
    this.key = key;
  }

  public context: FlowContext;

  public key: string;

  get raw() {
    return this.context.getFlowNode(this.key) as RawData;
  }

  get type() {
    return this.raw?.elementType as ElementType;
  }

  set in(val: string) {
    this.context.updateFlowNode(this.key, { incomings: [val] });
  }

  get in() {
    return this.raw?.incomings?.[0] as string;
  }

  set out(val: string) {
    this.context.updateFlowNode(this.key, { outgoings: [val] });
  }

  get out() {
    return this.raw?.outgoings?.[0] as string;
  }

  public getParent() {
    return super.getParent() as DataNode | null;
  }

  public getChildren() {
    return super.getChildren() as DataNode[];
  }
}

/**
 * 分支节点
 */
export class DataBranchNode extends DataNode {

  private _raw: RawData;
  
  get raw() {
    return this._raw;
  }

  constructor(flowContext: FlowContext, condition: RawData, branchIndex: number) {
    const branch = condition.conditions?.[branchIndex];
    if (!branch) throw new Error('condition branch not found');
    const _raw: RawData = {
      key: condition.key + '_' + branchIndex,
      name: branch.conditionName,
      incomings: [],
      outgoings: [],
      elementType: ElementType.BRANCH,
    };
    super(flowContext, _raw.key);
    this._branchIndex = branchIndex;
    this._raw = _raw;
  }

  private _branchIndex: number;

  get branch() {
    const parent = this.getParent() as DataNode;
    return parent.raw?.conditions?.[this._branchIndex];
  }

  get in () {
    const parent = this.getParent() as DataNode;
    return parent.key;
  }

  set in (_val: string) {}

  get out () {
    return this.branch?.outgoing as string;
  }

  set out(val: string) {
    const parent = this.getParent() as DataNode;
    this.context.update(draft => {
      const parentRaw = draft.flowContent.find((item) => item.key === parent.key);
      const branch = parentRaw?.conditions?.[this._branchIndex];
      if (branch) {
        branch.outgoing = val;
      }
    });
  }
}

/**
 * 根节点
 */
export class DataRootNode extends DataNode {

  private _raw: RawData;

  get raw() {
    return this._raw;
  }

  constructor(flowContext: FlowContext) {
    const _raw = {
      key: 'root',
      name: 'root',
      incomings: [],
      outgoings: [],
      elementType: ElementType.ROOT,
    }
    super(flowContext, _raw.key);
    this._raw = _raw;
  }

  get in() {
    return this.raw?.incomings?.[0] as string;
  }

  set in(val: string) {
    this._raw.incomings = [val];
  }

  get out() {
    return this.raw?.outgoings?.[0] as string;
  }

  set out(val: string) {
    this._raw.outgoings = [val];
  }
}
