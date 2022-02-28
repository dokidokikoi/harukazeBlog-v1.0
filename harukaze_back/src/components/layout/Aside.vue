<template>
  <div class="container">
      <el-menu
      :default-active="$route.path"
      unique-opened
      background-color="#304156"
      text-color="#fff"
      active-text-color="aqua"
      :collapse-transition='false'
      :collapse="isCollapse"
      router>
      <div class="hamburger" @click="toggleCollapse">
          <i :class="isCollapse? 'el-icon-s-unfold':'el-icon-s-fold'"></i>
      </div>
      <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
        <template slot="title">
          <i :class="item.icon"></i>
          <span>{{item.name}}</span>
        </template>
          <el-menu-item :index="i.path" v-for="i in item.childrens" :key="i.id">
              <template slot="title">
            <i :class="i.icon"></i>
            <span>{{i.name}}</span>
            </template>
          </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import {getMenus} from '@/api/menu'
export default {
    name: 'Aside',
    data() {
        return {
            menuList: [],
            isCollapse: false,
        }
    },
    
    methods: {
        // async getMenuList() {
        //     const res = await getMenus()
        //     console.log(res.data.data)
        // }
        getMenuList() {
            getMenus().then(res => {
                this.menuList = res.data.data
            }).catch(err => {
                this.$message.error(err)
            })   
        },
        toggleCollapse() {
            this.isCollapse = !this.isCollapse
        }
    },
    created() {
        this.activeUri = this.$route.path
        this.getMenuList()
    }
}
</script>

<style scoped lang="less">
    .container {
        height: 100%;
        overflow: hidden;
    }

    .hamburger {
        background-color: #263342;
        color: white;
        display: flex;
        justify-content: center;
        i {
            font-size: 1.3em;
            display: inline-block;
            margin: 10px;
        }
    }

    .el-menu {
        height: 100%;
        border-right: none;
    }

    .hamburger:hover {
        cursor: pointer;
    }

</style>