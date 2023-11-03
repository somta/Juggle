<script setup lang="ts">
import { onMounted, ref } from 'vue';
import ZoomTool from './design/ZoomTool.vue';
import * as d3 from 'd3';

const scaleExtent: [number, number] = [0.5, 5]; // Limit the scale between 0.5x and 5x

const scale = ref(1);

const d3Vars = {
  svg: null as any,
  pan: null as any,
  root: null as any,
  boundary: null as any,
  zoom: null as any,
};

function init () {
  const svg = d3.select('.flow-canvas')
    .append('svg')
    .attr('width', '100%')
    .attr('height', '100%');

  const boundary = svg.node()!.getBoundingClientRect();
  const pan = svg.append('g');
  const root = pan.append('g')
    .attr('transform', `translate(${boundary.width / 2}, 60)`);

  root.append('rect')
    .attr('x', 0)
    .attr('y', 0)
    .attr('width', 100)
    .attr('height', 100)
    .attr('fill', 'blue');

  const zoom = d3.zoom()
    .scaleExtent(scaleExtent) 
    .on('zoom', (event) => {
      pan.attr('transform', event.transform);
      scale.value = event.transform.k;
    });

  svg.call(zoom as any);
  
  d3Vars.svg = svg;
  d3Vars.pan = pan;
  d3Vars.root = root;
  d3Vars.boundary = boundary;
  d3Vars.zoom = zoom;
}

function onZoomToolChange (value: number) {
  if (value < scaleExtent[0]) {
    scale.value = scaleExtent[0];
    return;
  }
  if (value > scaleExtent[1]) {
    scale.value = scaleExtent[1];
    return;
  }
  scale.value = value;
  const oldTransform = d3Vars.pan.attr('transform');
  if (oldTransform) {
    d3Vars.pan.attr('transform', oldTransform.replace(/scale\([0-9.]+\)/, `scale(${value})`));
  } else {
    d3Vars.pan.attr('transform', `scale(${value})`);
  }
}

onMounted(() => {
  init();
});

</script>

<template>
  <div class="page-flow-design">
    <div class="flow-canvas">
      <ZoomTool :scale="scale" @change="onZoomToolChange" />
    </div>
  </div>
</template>

<style lang="less" scoped>
.page-flow-design {
  .flow-canvas {
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: relative;
  }
}
</style>