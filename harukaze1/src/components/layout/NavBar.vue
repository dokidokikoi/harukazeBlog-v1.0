<template>
    <header id="header">
        <h2><router-link to="/">Harukaze</router-link></h2>
        <el-menu
        class="fall_menu"
        mode="horizontal"
        :default-active="$route.path"
        
        text-color="#fff"
        active-text-color="rgb(129, 245, 119)"
        router>
        <el-menu-item :index="'/'+item.path" v-for="item in navTitle" :key="item.id">
            <template style="position: relative;">
                <i :class="navIcon[item.id+'']" style="color: white"></i>
                <span>{{item.authName}}</span>
                <div class="bar"></div>
            </template>
        </el-menu-item>
        </el-menu>        

        <Search class="search"/>

        <div class="burger btn" @click="handleNavVisable"></div>
        <transition name="fold_menu" appear>
            <div class="fold_menu btn" v-if="navVisable">
                <div class="fold_close" @click="handleNavVisable"></div>
                <ul class="fold_nav">
                    <li v-for="item in navTitle" :key="item.id">
                        <router-link @click.native="handleNavVisable" :to="'/'+item.path">{{item.authName}}</router-link>
                    </li>
                </ul>
            </div>
        </transition>
        
    </header>
</template>

<script>
import Search from '@/components/search/Search'

export default {
    name: 'NavBar',
    data() {
        return {
            activeIndex: 1,
            navVisable: false,
            navTitle: [
                {
                    id: 1,
                    authName: '首页',
                    path: 'index'
                },
                {
                    id: 2,
                    authName: '项目',
                    path: 'project'
                },
                {
                    id: 3,
                    authName: '归档',
                    path: 'archives'
                },
                {
                    id: 4,
                    authName: '随笔',
                    path: 'note'
                },
                {
                    id: 5,
                    authName: '留言板',
                    path: 'message'
                },
                {
                    id: 6,
                    authName: '友链',
                    path: 'link'
                },
                {
                    id: 7,
                    authName: '关于我',
                    path: 'about'
                }
            ],
            navIcon: {
                '1': 'el-icon-s-home',
                '2': 'el-icon-s-opportunity',
                '3': 'el-icon-s-promotion',
                '4': 'el-icon-edit',
                '5': 'el-icon-chat-dot-round',
                '6': 'el-icon-paperclip',
                '7': 'el-icon-user'
            }
        }
    },
    methods: {
        handleNavVisable() {
            this.navVisable = !this.navVisable 
        }
    },
    mounted() {
        window.addEventListener("scroll", function() {
            var header = document.querySelector("#header")
            header.classList.toggle("show", window.scrollY > 0)
        })
    },
    components: {Search}
}
</script>

<style lang="less" scoped>
    header .el-menu {
      background: none;
    }

    .el-menu--horizontal>.el-menu-item:not(.is-disabled):focus {
        background: none;
    }

    .show {
        background-color: #78808B
    }

    header:hover {
        background-color: rgb(65, 139, 105, 0);
    }

    header {
        padding-left: 20px;
        // display: flex;
        // justify-content: space-around;
        // align-items: center;
        width: 100%;
        transition: 0.5s;
        background: none;
        position: relative;
  }

   h2 {
        margin: 0 40px;
        padding: 15px 0;
    a {
        text-decoration: none;
        color: pink;
    }
  }

  .el-menu {
      position: absolute;
      top: 0;
      left: 300px;
  }

  .el-menu li{
      padding-left: 10px;

      .bar {
          position: absolute;
          height: 5px;
          bottom: -3px;
          left: 0;
          right: 0;
          background-color: white;
          transform: translateY(5px);
          transition: all 0.2s ease-in-out;
      }

      i {
          transform: rotate(20deg);
          transition: all 0.2s ease-in-out;
      }

    &.is-active {
        background-color: rgba(0,0,0,0) !important;
    }

      &.is-active i{
        transform: rotate(0) !important;
        transition: all 0.2s ease-in-out;
    }
  }

  .el-menu .el-menu-item:hover {
      background-color: rgba(0,0,0,0) !important;

    .bar {
        transform: translateY(0px);
        transition: all 0.2s ease-in-out;
    }

    i {
        transform: rotate(0);
        transition: all 0.2s ease-in-out;
      }
  }

  header .search {
      position: absolute;
      top: 0;
      right: 60px;
      margin: 15px 0;
  }

  .btn:hover {
      cursor: pointer;
  }

    .burger {
        width: 25px;
        height: 25px;
        margin-right: 5px;
        background: url("../../assets/menu_cd_black.png") no-repeat;
        background-size: 100%;
        display: none;
    }

    .fold_close {
        width: 25px;
        height: 25px;
        margin-right: 5px;
        background: url("../../assets/fold_close.png") no-repeat;
        background-size: 100%;
        position: fixed;
        top: 10px;
        right: 10px;
    }

  .fold_menu {
      display: block;
      position: fixed;
      background-color: black;
      height: 100%;
      width: 70%;
      top: 0;
      right: 0;
      z-index: 100;
      padding: 50px 0;
  }

  .fold_nav {
      font-size: 1.2em;
      list-style-type: none;
      margin: 0;
      padding: 0;

      li {
          padding: 10px 20px;
          a {
              display: block;
              text-decoration: none;
              color: white;
          }
      }

      li:hover {
          background-color: aqua;
          color: black;
          a {
              color: black;
          }
      }
  }

  .fold_menu-enter-active {
      animation: showNav 0.5s linear;
  }

  .fold_menu-leave-active {
      animation: showNav 0.25s linear reverse;
  }

  @keyframes showNav {
    from{
        transform: translateX(100%);
    }
    to{
        transform: translateX(0);
    }
  }

    @media screen and (max-width: 1065px) {
        header {
            justify-content: space-between;
        }

      .fall_menu {
          display: none;
      }

      .burger {
          display: block;
          margin-right: 20px;
      }
    } 

</style>