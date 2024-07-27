import {D3Element, ElementType} from "@/views/flow/design";
import {LayoutNode} from "@/views/flow/design/renderer/layout";
import {nodeMap} from "@/views/flow/design/config";
import {box} from "@/views/flow/design/renderer/layout/vertical/generate";
import {FlowRenderer} from "@/views/flow/design/renderer";
import * as d3 from "d3";

export class DrawNodeHelper {

    constructor(renderer: FlowRenderer) {
        this.renderer = renderer;
    }

    private renderer: FlowRenderer;

    /**
     * 画节点
     */
    public drawNode(container: D3Element, node: LayoutNode, type: string = 'update') {
        switch (node.data.type) {
            case ElementType.START:
                this.drawStartNode(container, node, type);
                break;
            case ElementType.METHOD:
            case ElementType.CODE:
            case ElementType.MYSQL:
            case ElementType.AI:
                this.drawNormal(container, node, type);
                break;
            case ElementType.CONDITION:
                this.drawCondition(container, node, type);
                break;
            case ElementType.BRANCH:
                this.drawBranch(container, node, type);
                break;
            case ElementType.END:
                this.drawEndNode(container);
                break;
            default:
                break;
        }
    }

    private drawNormal(container: D3Element, node: LayoutNode, type: string) {
        const { width, height, data } = node;
        if (type === 'enter') {
            const flowNode = container.append('g').attr('class', 'flow-node');
            this.drawNormalCard(flowNode, node, width, height);

            this.drawHoverButtons(flowNode, node);
        } else {
            const flowNode = container.selectChild('.flow-node');
            flowNode.select('text').text(data.raw.name);
        }
        if ([ElementType.METHOD, ElementType.CODE, ElementType.MYSQL].includes(data.type)) {
            this.drawAddIcon(container, node, type);
        }
    }

    private drawCondition(container: D3Element, node: LayoutNode, type: string) {
        if (type === 'enter') {
            container.append('rect');

            const conditionBox = node.contentBox;
            const conditionStart = container.append('g').attr('class', 'flow-node').attr('transform', `translate(${conditionBox.left}, 0)`);

            this.drawNormalCard(conditionStart, node, conditionBox.width, conditionBox.height);
            this.drawHoverButtons(conditionStart, node);
        } else {
            container.selectChild('.flow-node').attr('transform', `translate(${node.contentBox.left}, 0)`);
            container.selectChild('.sup-rect').attr('width', node.width).attr('height', node.height);
            const conditionStart = container.selectChild('.flow-node');
            conditionStart.select('text').text(node.data.raw.name);
        }
        this.drawAddIcon(container, node, type);

        const children = node.getChildren();
        container
            .selectChildren('.branch-wrap')
            .data(children, (d: any) => d.data.key)
            .join(
                enter =>
                    enter.append('g').each((d, i, nodes) => {
                        const g = d3.select(nodes[i]);
                        this.drawBranch(g, d, 'enter');
                    }),
                update =>
                    update.each((d, i, nodes) => {
                        const g = d3.select(nodes[i]);
                        this.drawBranch(g, d, 'update');
                    })
            )
            .attr('class', 'branch-wrap')
            .attr('transform', d => `translate(${d.left}, ${d.top})`);
    }

    private drawBranch(container: D3Element, branch: LayoutNode, type: string) {
        if (branch.data.key !== 'root' && branch.data.type === ElementType.BRANCH) {
            if (type === 'enter') {
                const conditionBox = branch.contentBox;
                const branchStart = container.append('g').attr('class', 'flow-node').attr('transform', `translate(${conditionBox.left}, 0)`);

                branchStart
                    .append('rect')
                    .attr('width', conditionBox.width)
                    .attr('height', conditionBox.height)
                    .attr('fill', '#fff')
                    .attr('stroke', '#aaa')
                    .attr('stroke-width', 1)
                    .attr('rx', 4)
                    .attr('ry', 4);
                branchStart
                    .append('text')
                    .attr('text-anchor', 'middle')
                    .attr('dominant-baseline', 'middle')
                    .attr('x', conditionBox.width / 2)
                    .attr('y', conditionBox.height / 2)
                    .text(branch.data.raw.name);

                this.drawHoverButtons(branchStart, branch);
            } else {
                container.selectChild('.sup-rect').attr('width', branch.width).attr('height', branch.height);
                const branchStart = container.selectChild('.flow-node');
                branchStart.attr('transform', `translate(${branch.contentBox.left}, 0)`);
                branchStart.selectChild('text').text(branch.data.raw.name);
            }
            this.drawAddIcon(container, branch, type);
        }

        const nodes = branch.getChildren();
        container
            .selectChildren('.node-wrap')
            .data(nodes, (d: any) => d.data.key)
            .join(
                enter =>
                    enter.append('g').each((d, i, nodes) => {
                        const g = d3.select(nodes[i]);
                        this.drawNode(g, d, 'enter');
                    }),
                update =>
                    update.each((d, i, nodes) => {
                        const g = d3.select(nodes[i]);
                        this.drawNode(g, d);
                    })
            )
            .attr('class', 'node-wrap')
            .attr('transform', d => `translate(${d.left}, ${d.top})`);
    }

    private drawAddIcon(container: D3Element, node: LayoutNode, type: string) {
        let { width, height } = node.contentBox;
        if (node.data.type === ElementType.CONDITION) {
            width = node.width;
            height = node.height;
        }
        if (node.data.type === ElementType.BRANCH) {
            width = 0;
        }
        const btn_radius = 16;
        if (type === 'enter') {
            const addButton = container
                .append('g')
                .attr('class', 'flow-btn flow-btn-add')
                .attr('transform', `translate(${width / 2}, ${height - box.headerHeight / 2 + box.marginBottom / 2})`)
                .on('click', (_, d) => {
                    this.renderer.options.onAdd?.(d);
                });
            addButton.append('circle').attr('cx', 0).attr('cy', 0).attr('r', btn_radius).attr('fill', '#409eff');
            addButton.append('use').attr('href', '#icon-plus').attr('width', 16).attr('height', 16).attr('x', -8).attr('y', -8).attr('fill', '#fff');
        } else {
            container.select('.flow-btn-add').attr('transform', `translate(${width / 2}, ${height - box.headerHeight / 2 + box.marginBottom / 2})`);
        }
    }

    private drawHoverButtons(container: D3Element, node: LayoutNode) {
        const { width } = node.contentBox;
        const { data } = node;
        let btns = ['delete', 'edit'];
        if ([ElementType.START, ElementType.END].includes(data.type)) {
            btns = [];
        }
        if ([ElementType.BRANCH].includes(data.type)) {
            btns = ['edit'];
        }
        btns.forEach((btn, i) => {
            const flowBtns = container
                .append('g')
                .attr('class', `flow-btn flow-btn-${btn}`)
                .attr('transform', `translate(${width - i * 36}, ${ElementType.BRANCH === data.type ? 0 : -box.headerHeight})`)
                .on('click', (_, d) => {
                    if (btn === 'edit') {
                        this.renderer.options.onEdit?.(d);
                    }
                    if (btn === 'delete') {
                        this.renderer.options.onDelete?.(d);
                    }
                });
            flowBtns.append('circle').attr('cx', 0).attr('cy', 0).attr('r', '15').attr('fill', '#fff').attr('stroke', '#777');

            flowBtns.append('use').attr('href', `#icon-${btn}`).attr('width', 16).attr('height', 16).attr('x', -8).attr('y', -8).attr('fill', '#777');
        });
    }

    private drawNormalCard(flowNode: any, node: LayoutNode, width: number, height: number) {
        const { data } = node;
        const nodeType = node.data.type as ElementType;
        const nodeCard = nodeMap[nodeType as keyof typeof nodeMap];

        flowNode
            .append('rect')
            .attr('width', width)
            .attr('height', height)
            .attr('fill', '#fff')
            .attr('stroke', '#aaa')
            .attr('stroke-width', 1)
            .attr('rx', 1)
            .attr('ry', 4);

        flowNode
            .append('text')
            .attr('text-anchor', 'middle')
            .attr('dominant-baseline', 'middle')
            .attr('x', width / 2)
            .attr('y', height / 2)
            .text(data.raw.name);

        flowNode
            .append('rect')
            .attr('width', width)
            .attr('height', box.headerHeight)
            .attr('fill', nodeCard.nodeHeaderColor)
            .attr('stroke', '#aaa')
            .attr('stroke-width', 1)
            .attr('rx', 4)
            .attr('ry', 1)
            .attr('transform', `translate(0, -${box.headerHeight})`);

        flowNode
            .append('text')
            .attr('text-anchor', 'middle')
            .attr('dominant-baseline', 'middle')
            .attr('x', width / 2)
            .attr('y', height / 2 - 33)
            .text(nodeCard.nodeName);
    }

    private drawStartNode(container: D3Element, node: LayoutNode, type: string){
        container.append('g')
            .attr('transform', `translate(8, -2)`)
            .append('use').attr('href', `#icon-start`).attr('width', 50).attr('height', 50).attr('x', -8).attr('y', -8).attr('fill', '#02CA83');
        this.drawAddIcon(container, node, type);
    }

    private drawEndNode(container: D3Element){
        container.append('g')
            .attr('transform', `translate(6, -2)`)
            .append('use').attr('href', `#icon-end`).attr('width', 57).attr('height', 57).attr('x', -8).attr('y', -8).attr('fill', '#777');
    }
}
