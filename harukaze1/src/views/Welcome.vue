<template>
    <div class="home_container">
      <el-header>
        <NavBar/>
      </el-header>
      
      <div class="el-container">
        <el-main>
          <router-view :key="new Date().getTime()"></router-view>
        </el-main>
        <el-footer style="height:auto">
          <MyFooter/>
        </el-footer>
      </div>
    </div>
</template>

<script>
import NavBar from '../components/layout/NavBar'
import MyFooter from '../components/layout/MyFooter'

export default {
  name: 'Welcome',
  mounted() {
    this.$store.dispatch('toTop')
  },
  components: {
    NavBar,
    MyFooter
  },
}
</script>

<style scoped lang="less">
  .home_container {
    width: 100%;
    position: relative;
  }

  .el-header {
    margin: 0;
    padding: 0;
    overflow: hidden;
    width: 100%;
    display: flex;
    position: fixed;
    top: 0;
    z-index: 999;
  }

  .el-container {
      display: flex;
     
  }

  .el-main {
    min-height: 100vh;
    background-color: rgba(0,0,0,0) !important;
    margin: 0;
    padding: 0;
    margin-bottom: 100px !important;
  }

  .el-footer {
    bottom: 0;
    position: absolute;
    width: 100%;
    color: #eee;
    font-size: 14px;

    background-color: #545c64;
  }
</style>
