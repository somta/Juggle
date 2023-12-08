import { ElementType } from '../../types'
import { DataNode } from '../data'
import { TreeNode } from '../utils/TreeNode'
import { box } from './vertical/generate'

export class LayoutNode extends TreeNode {
  constructor(params: { left: number; top: number; width: number; height: number; data: DataNode; linesTo?: string[] }) {
    super()
    this._data = params.data
    if (params.linesTo) {
      this._linesTo = params.linesTo
    }
    this.setSize(params.width, params.height)
    this.setRelative(params.left, params.top)
  }

  private _data: DataNode

  private _linesTo: string[] = []

  private _width = 0

  private _height = 0

  private _top = 0

  private _left = 0

  get data() {
    return this._data
  }

  get linesTo() {
    return this._linesTo
  }

  get width() {
    return this._width
  }

  get height() {
    return this._height
  }

  get top() {
    return this._top
  }

  get left() {
    return this._left
  }

  get bottom() {
    return this.top + this.height
  }

  get right() {
    return this.left + this.width
  }

  get x() {
    const parent = this.getParent()
    const parentX: number = parent?.x || 0
    return this.left + parentX
  }

  get y() {
    const parent = this.getParent()
    const parentY: number = parent?.y || 0
    return this.top + parentY
  }

  setSize(width: number, height: number) {
    this._width = width
    this._height = height
  }

  setRelative(left: number, top: number) {
    this._left = left
    this._top = top
  }

  public getChildren() {
    return super.getChildren() as LayoutNode[]
  }

  public getParent() {
    return super.getParent() as LayoutNode
  }

  public line(node: string) {
    this._linesTo.push(node)
  }

  get linePoint(): [number, number] {
    if (this.data.type === ElementType.CONDITION) {
      return [this.x + this.width / 2, this.y + box.height / 2]
    }
    if (this.data.type === ElementType.BRANCH) {
      return [this.x, this.y + box.height]
    }
    return [this.x + this.width / 2, this.y + this.height / 2]
  }
}
