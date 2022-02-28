<template>
  <div class="container">
      <h3>HARUKAZE</h3>
      <el-dropdown class="avatar" trigger="click">
          <el-avatar shape="square" :size="size" :src="$store.state.user.avatar"></el-avatar>
          <i class="el-icon-caret-bottom"></i>
          <el-dropdown-menu>
              <el-dropdown-item>
                  <router-link to="/personal" style="text-decoration: none !important;color: black !important;">Profile</router-link>
              </el-dropdown-item>
              <el-dropdown-item>
                  <a href="https://github.com/dokidokikoi" target="_blank" style="text-decoration: none !important;color: black !important;">Github</a>
              </el-dropdown-item>
              <hr style="border: soild .5px gray; margin:0;padding:0;">
              <el-dropdown-item>
                  <a href="#" @click.prevent="logout" style="text-decoration: none !important;color: black !important;">Logout</a>
              </el-dropdown-item>
          </el-dropdown-menu>
      </el-dropdown>
  </div>
</template>

<script>
export default {
    name: 'Header',
    data() {
        return {
            imgUrl: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
            size: 40
        }
    },
    methods: {
        logout() {
            this.$store.dispatch('user/logout').then(() => {
                this.$router.push('/login')
                this.$message.success("已退出")
            }).catch(err => {
                this.$message.error(err)
            })
        }
    }
}
</script>

<style scoped lang='less'>
    .container {
        height: 100%;
        width: 100%;
        background-color: blueviolet;
        position: relative;

        h3 {
            display: inline-block;
            margin-top: 12px;
            margin-left: 20px;
            padding: 0;
            font-size: 1.5em;
            color: pink;
        }

        .avatar {
            position: absolute;
            top: 8px;
            right: 20px;
        }

        .el-dropdown:hover {
            cursor: pointer;
        }
    }
</style>