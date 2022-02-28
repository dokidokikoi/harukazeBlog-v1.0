<template>
  <el-container class="home-container">
    <div class="stars">
        <div v-for="(item, index) in starsCount" :key="index" class="star" ref="star"></div>
    </div>


      <el-header>
        <Header/>
      </el-header>
    <el-container class="se-container">
      <el-aside width="200px">
        <Aside/>
      </el-aside>
      <el-main>
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Header from '@/components/layout/Header'
import Aside from '@/components/layout/Aside'
export default {
    name: 'Home',
    data() {
      return {
        starsCount: 800,
        distance:  800
      }
    },
    components: {
      Header,
      Aside
    },
    mounted() {
      let starArr = this.$refs.star
        starArr.forEach(item => {
            let speed = 0.2 + (Math.random() * 1); 
            let thisDistance = this.distance + (Math.random() * 300);
            item.style.transformOrigin = `0 0 ${thisDistance}px`;
            item.style.transform = `
                translate3d(0,0,-${thisDistance}px) 
                rotateY(${Math.random() * 360}deg) 
                rotateX(${Math.random() * -50}deg) 
                scale(${speed},${speed})`;
                })
    }
}
</script>

<style scoped lang="less">
  .home-container{
    height: 100% !important;
  }

  .el-header {
    margin: 0;
    padding: 0 !important;
  }

  .se-container {
    height: 1000px;
    overflow: hidden;
  }

  .el-aside {
    height: 100% !important;
  }



  @keyframes rotate{
        0%{
            transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(0);
        }
        100%{
            transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(-360deg);
        }
    }

    .stars{
        transform: perspective(500px);
        transform-style: preserve-3d;
        position: absolute;
        perspective-origin: 50% 100%;
        left: 50%;
        animation: rotate 90s infinite linear;
        z-index: -1;
        bottom: 0;
        height: 0;
    }

    .star{
        width: 2px;
        height: 2px;
        background: #f7f7b8;
        position: absolute;
        top: 0;
        left: 0;
        backface-visibility: hidden;
    }

</style>