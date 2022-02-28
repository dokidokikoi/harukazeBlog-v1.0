<template>
  <div class="login-container">
    <el-form :model="loginForm" :rules="loginRules" ref="loginForm">
        <div class="title-container">
            <h3 class="title">Login Form</h3>
        </div>

      <el-form-item prop="username">
        <el-input
          prefix-icon="el-icon-user-solid"
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      
    <el-form-item prop="password">
        <el-input
        :key="passwordType"
        ref="password"
        v-model="loginForm.password"
        :type="passwordType"
        placeholder="Password"
        name="password"
        tabindex="2"
        />
    </el-form-item>

      <el-button type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="login">Login</el-button>

      <div style="position:relative">
        <div class="tips">
          <span>Username : visitor</span>
          <span>Password : 123</span>
        </div>

        <el-button class="reset-button" type="primary" @click="reset">
          Reset
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import cryptoAES from "@/utils/cryptoAES"
export default {
    name: 'Login',
    data() {
        return {
            loginForm:{
                username:'',
                password:''
            },
            loginRules: {
                username: [{ required: true, trigger: 'blur', message: "用户名不能为空" }],
                password: [{ required: true, trigger: 'blur', message: "密码不能为空" }]
            },
            passwordType: 'password',
            capsTooltip: false,
            loading: false,
            showDialog: false,
            redirect: undefined,
            otherQuery: {}
        }
    },
    methods: {
        login() {
            this.$refs.loginForm.validate(valid => {
                if (!valid) return 
                let form = {
                    username: this.loginForm.username,
                    password: cryptoAES.encrypt(this.loginForm.password)
                }
                this.$store.dispatch('user/login', form).then(() => {
                    this.$router.push('/')
                    this.$message.success("登录成功");
                }).catch((err) => {
                    if (err !== 'error') {
                        this.$message.error(err);
                    }
                })
                // console.log(form)
            })    
        },
        reset() {
            this.$refs.loginForm.resetFields()
        }
    }
}
</script>

<style scoped lang="less">
@bg:#283443;
@light_gray:#fff;
@dark_gray:#889aa4;
@cursor: #fff;
    .login-container {
        height: 100%;
        background-color: @bg;
        .el-form {
            margin: auto;
            width: 420px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -60%);
            color: @light_gray;

            h3 {
                font-size: 1.5em;
                text-align: center;
                margin-bottom: 40px;
            }
            
            .el-form-item {
                margin-bottom: 30px !important;
                /deep/ .el-input__inner {
                    height: 47px !important;
                    background-color: #283443;
                    border: solid 1px @dark_gray;
                    color: @light_gray;
                }
            }

            .tips {
                position: absolute;
                left: 0;
                span {
                    margin-right: 20px;
                }
            }
            .reset-button{
                position: absolute;
                width: 100px;
                right: 0;
                top: -10px;
            }

        }
    }
</style>