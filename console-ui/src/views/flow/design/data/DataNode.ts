import { TreeNode } from './TreeNode';
import { ElementType, RawData } from '../types';

/**
 * 数据节点
 */
export class DataNode extends TreeNode {

  public static context: {
    getRaw: (key: string) => RawData;
    getType: (key: string) => ElementType;
    getIn: (key: string) => string;
    setIn: (key: string, val: string) => void;
    getOut: (key: string) => string;
    setOut: (key: string, val: string) => void;
    setBranchOut: (key: string, branchIndex: number, val: string) => void;
    addRaw: (raw: RawData) => void;
    deleteRaw: (key: string) => void;
  };

  public static DataNodeMap = new Map();

  constructor(key: string) {
    super();
    this._key = key;
    if (key) {
      DataNode.DataNodeMap.set(key, this);
    }
  }

  private _key: string;

  get key () {
    return this._key;
  }

  get raw() {
    return DataNode.context.getRaw(this.key);
  }

  get type() {
    return DataNode.context.getType(this.key);
  }

  get in () {
    return DataNode.context.getIn(this.key);
  }

  set in (val: string) {
    DataNode.context.setIn(this.key, val);
  }

  get out () {
    return DataNode.context.getOut(this.key);
  }

  set out (val: string) {
    DataNode.context.setOut(this.key, val);
    // 条件节点 - 额外工作
    if (this.type === ElementType.CONDITION) {
      this.getChildren().forEach((child: any) => {
        const branch = child as DataBranch;
        const branchChildren = branch.getChildren();
        // 如果分支存在子节点，则将末尾节点的 out 设置为 val
        if (branchChildren.length > 0) {
          const last = branchChildren[branchChildren.length - 1];
          last.out = val;
        } else {
          // 否则把分支节点的out设置为 val
          branch.out = val;
        }
      });
    }
  }

  public getParent() {
    return super.getParent() as DataNode | null;
  }

  public getChildren() {
    return super.getChildren() as DataNode[];
  }

  public setBranchOut (branchIndex: number, val: string) {
    DataNode.context.setBranchOut(this.key, branchIndex, val);
  }

  removeChild (node: DataNode) {
    super.removeChild(node);
    DataNode.DataNodeMap.delete(node.key);
    DataNode.context.deleteRaw(node.key);
  }
}

/**
 * 分支
 */
export class DataBranch extends DataNode {

  private _branchIndex: number;

  constructor(branchIndex: number) {
    super(null as unknown as string);
    this._branchIndex = branchIndex;
  }

  get condition () {
    return this.getParent() as DataNode;
  }

  get key () {
    if (!this.condition) {
      return 'root';
    }
    return `${this.condition.key}-${this.branchIndex}`;
  }

  get type() {
    return ElementType.BRANCH;
  }

  get raw () {
    return {
      key: this.key,
      name: this.branch?.conditionName,
      outgoings: [this.out],
      incomings: [this.in],
      elementType: ElementType.BRANCH,
    } as RawData;
  }

  get branchIndex () {
    return this._branchIndex;
  }

  get branch() {
    const parent = this.getParent() as DataNode;
    return parent?.raw.conditions?.[this._branchIndex];
  }

  get in () {
    const parent = this.getParent() as DataNode;
    return parent?.key;
  }

  set in (_val: string) {}

  get out () {
    return this.branch?.outgoing as string;
  }

  set out (val) {
    this.condition.setBranchOut(this._branchIndex, val);
  }

  public getParent() {
    return super.getParent() as DataNode;
  }

  public getChildren() {
    return super.getChildren() as DataNode[];
  }
}
