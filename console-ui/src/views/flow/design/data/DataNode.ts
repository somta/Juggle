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

  get key() {
    return this._key;
  }

  get raw() {
    return DataNode.context.getRaw(this.key);
  }

  get type() {
    return DataNode.context.getType(this.key);
  }

  get in() {
    return DataNode.context.getIn(this.key);
  }

  set in(val: string) {
    DataNode.context.setIn(this.key, val);
  }

  get out() {
    return DataNode.context.getOut(this.key);
  }

  set out(val: string) {
    // 条件节点 - 额外工作
    if (this.type === ElementType.CONDITION) {

      // 当前操作node存在的分支
      let branchIndex = -1;
      // 当前操作node在分支中位置
      let branchNodeIndex = -1;
      this.getChildren().some((child: any, childIndex: number) => {
        const grandChildIndex = child.getChildren().findIndex((grandChild: any) => grandChild.key === val);

        if (grandChildIndex != -1) {
          branchIndex = childIndex;
          branchNodeIndex = grandChildIndex;
          return true; // 找到后退出 some 循环
        }
        return false;
      })

      // 如果操作的节点不在分支中，则设置condition的out
      if (branchIndex == -1) {
        DataNode.context.setOut(this.key, val);
      }

      this.getChildren().some((child: any, childIndex: number) => {
        const branch = child as DataBranch;
        const branchChildren = branch.getChildren();

        if (branchNodeIndex == 0 && branchIndex == childIndex) {
          // 如果val是分支的第一个元素，则设置 branch 的 out
          // 只需要设置当前分支的out, 必须终止循环，以防在其他分支上设置错误的out
          branch.out = val;
          return true; // 找到并设置 branch.out 后终止循环
        } else if (branchIndex == -1 && branchChildren.length == 0) {
          // 如果val不是分支上的元素，并且分支上没有元素，则设置 branch 的 out，且继续循环
          branch.out = val;
          return false;
        } else if (branchChildren.length > 0) {
          const last = branchChildren[branchChildren.length - 1];
          last.out = val;
        }

        return false; // 继续循环
      });
    } else {
      DataNode.context.setOut(this.key, val);
    }
  }

  public getParent() {
    return super.getParent() as DataNode | null;
  }

  public getChildren() {
    return super.getChildren() as DataNode[];
  }

  public setBranchOut(branchIndex: number, val: string) {
    DataNode.context.setBranchOut(this.key, branchIndex, val);
  }

  removeChild(node: DataNode) {
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

  get condition() {
    return this.getParent() as DataNode;
  }

  get key() {
    if (!this.condition) {
      return 'root';
    }
    return `${this.condition.key}-${this.branchIndex}`;
  }

  get type() {
    return ElementType.BRANCH;
  }

  get raw() {
    return {
      key: this.key,
      name: this.branch?.conditionName,
      outgoings: [this.out],
      incomings: [this.in],
      elementType: ElementType.BRANCH,
    } as RawData;
  }

  get branchIndex() {
    return this._branchIndex;
  }

  get branch() {
    const parent = this.getParent() as DataNode;
    return parent?.raw.conditions?.[this._branchIndex];
  }

  get in() {
    const parent = this.getParent() as DataNode;
    return parent?.key;
  }

  set in(_val: string) {}

  get out() {
    return this.branch?.outgoing as string;
  }

  set out(val) {
    this.condition.setBranchOut(this._branchIndex, val);
  }

  public getParent() {
    return super.getParent() as DataNode;
  }

  public getChildren() {
    return super.getChildren() as DataNode[];
  }
}
