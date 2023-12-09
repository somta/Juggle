import { TreeNode } from '../utils/TreeNode';
import { ElementType, RawData } from '../../types';

/**
 * 数据节点
 */
export class DataNode extends TreeNode {
  constructor(raw: RawData) {
    super();
    this._raw = raw;
  }

  private _raw: RawData;

  get raw() {
    return this._raw;
  }

  get key() {
    return this.raw.key;
  }

  get type() {
    return this.raw.elementType;
  }

  set in(val: string) {
    this.raw.incomings = [val];
  }

  get in() {
    return this.raw.incomings?.[0];
  }

  set out(val: string) {
    this.raw.outgoings = [val];
  }

  get out() {
    return this.raw.outgoings?.[0];
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
  constructor(condition: RawData, branchIndex: number) {
    const branch = condition.conditions?.[branchIndex];
    if (!branch) throw new Error('condition branch not found');
    const raw: RawData = {
      key: condition.key + '_' + branchIndex,
      name: branch.conditionName,
      incomings: [],
      outgoings: [],
      elementType: ElementType.BRANCH,
    };
    super(raw);
    this.out = branch.outgoing;
    this._branchIndex = branchIndex;
  }

  private _branchIndex: number;

  get branch() {
    const parent = this.getParent() as DataNode;
    return parent.raw.conditions?.[this._branchIndex];
  }
}

/**
 * 根节点
 */
export class DataRootNode extends DataNode {
  constructor() {
    super({
      key: 'root',
      name: 'root',
      incomings: [],
      outgoings: [],
      elementType: ElementType.ROOT,
    });
  }
}
