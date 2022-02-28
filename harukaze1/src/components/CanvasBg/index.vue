<template>
  <canvas id="canvas"></canvas>
</template>

<script>
import { showBg } from './main'

export default {
    name: 'CanvasBg',
    mounted() {
        showBg()
    }
}
</script>

<style scoped lang="less">
    #canvas {
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        z-index: -1;
        // background: url(../../assets/bgImg.jpg);
        // background-position: 0 0;
        // background-size: cover;
        // background-color: #A2E1D5;
        background-color: rgb(255, 251, 245);
    }
</style>