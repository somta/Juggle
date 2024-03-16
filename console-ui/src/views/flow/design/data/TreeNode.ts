export class TreeNode {
  private parent: TreeNode | null = null;
  private children: TreeNode[] = [];

  public getParent(): TreeNode | null {
    return this.parent;
  }

  public getChildren(): TreeNode[] {
    return [...this.children];
  }

  public addChild(node: TreeNode): void {
    const currentParent = node.getParent();
    if (currentParent) {
      currentParent.removeChild(node);
    }
    node.parent = this;
    this.children.push(node);
  }

  public removeChild(node: TreeNode): void {
    const index = this.children.indexOf(node);
    if (index !== -1) {
      node.parent = null;
      this.children.splice(index, 1);
    }
  }

  public delete(): void {
    while (this.children.length > 0) {
      this.children[0].delete();
    }

    if (this.parent) {
      this.parent.removeChild(this);
    }
  }

  public insertChild(node: TreeNode, index: number): void {
    if (index < 0 || index > this.children.length) {
      this.addChild(node);
      return;
    }
    const currentParent = node.getParent();
    if (currentParent) {
      currentParent.removeChild(node);
    }
    node.parent = this;
    this.children.splice(index, 0, node);
  }

  public insertAfter(node: TreeNode, after: TreeNode): void {
    const index = this.children.indexOf(after);
    if (index === -1) {
      this.addChild(node);
    } else {
      this.insertChild(node, index + 1);
    }
  }
}
