<template>
  <div>
      <my-breadcrumb :model="['用户管理', '用户管理']"/>

      <el-card class="add_card">
            <Upload v-model="info.avatar" @change="up"/>

            <el-form :model="info" ref="addForm" :rules="rules" class="input">
                <el-form-item prop="account" label="账户名">
                    <el-input v-model="info.account"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码">
                    <el-input type="password" v-model="info.password"></el-input>
                </el-form-item>
                <el-form-item prop="nickname" label="昵称">
                    <el-input v-model="info.nickname"></el-input>
                </el-form-item>
                <el-form-item prop="email" label="邮箱">
                    <el-input v-model="info.email"></el-input>
                </el-form-item>
                <el-form-item prop="summary" label="简介">
                    <el-input type="textarea" v-model="info.summary"></el-input>
                </el-form-item>
                <el-button type="primary" @click="save" style="margin-top: 10px">添加用户</el-button>
            </el-form>
        </el-card>

      <el-card>
        <el-table :data="userList" border stripe>
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column label="头像" width="150">
            <template slot-scope="scope">
                <el-image :src="scope.row.avatar"></el-image>
            </template>
          </el-table-column>
          <el-table-column label="账户" prop="account" width="100"></el-table-column>
          <el-table-column label="昵称" prop="nickname" width="100"></el-table-column>
          <el-table-column label="注册时间" width="170">
            <template slot-scope="scope">
                {{scope.row.createDate | dateFormat}}
            </template>
          </el-table-column>
          <el-table-column label="最近登录" width="170">
            <template slot-scope="scope">
                {{scope.row.lastLogin | dateFormat}}
            </template>
          </el-table-column>
          <el-table-column label="邮箱地址" prop="email"  width="230"></el-table-column>
          <el-table-column label="状态">
            <template slot-scope="scope">
                <el-tag type="danger" v-if="scope.row.deleted">已删除</el-tag>
                <el-tag v-else>正常</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="145">
              <template slot-scope="scope">
                  <el-button type="primary" size="mini" @click="showChange(scope.row)">修改</el-button>
                  <el-button type="warning" v-if="scope.row.deleted" size="mini" @click="cancelDelete(scope.row.id)">恢复</el-button>
                  <el-button type="danger" v-else size="mini" @click="deleteUser(scope.row.id)">删除</el-button>
              </template>
          </el-table-column>
        </el-table>
      </el-card>

      <el-dialog
        title="修改用户"
        :visible.sync="dialogVisible"
        width="70%"
        @close="handleClose">
        <el-form :model="info" ref="editForm" :rules="rules" label-width="70px">
            <el-form-item prop="avatar" label="头像">
              <Upload v-model="info.avatar" @change="up"/>
            </el-form-item>
            <el-form-item prop="account" label="账户名">
                <el-input v-model="info.account"></el-input>
            </el-form-item>
            <el-form-item prop="nickname" label="昵称">
                <el-input v-model="info.nickname"></el-input>
            </el-form-item>
            <el-form-item prop="email" label="邮箱">
                <el-input v-model="info.email"></el-input>
            </el-form-item>
            <el-form-item prop="summary" label="简介">
                <el-input type="textarea" v-model="info.summary"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click.native="changeUser">确 定</el-button>
        </span>
        </el-dialog>

      <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.pageNum"
      :page-sizes="[7, 10]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import Upload from '@/components/ImageUpLoad'

import { getUserList, addUser, editUser, deleteUser, cancelDelete } from '@/api/user'

export default {
    name: 'User',
    data() {
      return {
        page: {
          pageNum: 0,
          pageSize: 7
        },
        total: 0,
        userList: [],
        info: {
          avatar: "",
          account: '',
          password: '',
          nickname: '',
          email: '',
          summary: '',
        },
        dialogVisible: false,
        rules: {
          account: [
            { required: true, message: '请输入账户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 8, max: 20, message: '密码在8到20字符之间', trigger: 'blur' },
          ],
          nickname: [
            { required: true, message: '请输入昵称', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      getUserList() {
        getUserList(this.page).then(res => {
          this.userList = res.data.data
          this.total = res.data.total
        })
      },
      save() {
        this.$refs.addForm.validate(vaild => {
          if (!vaild) return
          addUser(this.info).then(() => {
            this.getUserList()
            this.$message.success("添加成功")
          })
        })
      },
      showChange(info) {
        this.dialogVisible = true
        this.info = JSON.parse(JSON.stringify(info))
      },
      changeUser() {
        console.log("@")
        this.$refs.editForm.validate(vaild => {
          if (!vaild) return
          editUser(this.info).then(() => {
            this.getUserList()
            this.$message.success("修改成功")
          })
        })
        this.dialogVisible = false
      },
      deleteUser(id) {
        this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
          deleteUser(id).then(() => {
              this.$message.success("删除成功")
              this.getUserList()
          }).catch(err => {
              this.$message.error(err)
          })
        }).catch(err => {
            this.$message.info('已取消')
        })
      },
      cancelDelete(id) {
        cancelDelete(id).then(res => {
          this.getUserList()
          this.$message.success("恢复成功")
        })
      },
      handleSizeChange(newSize) {
          this.page.pageSize = newSize
          this.getUserList()
      },
      handleCurrentChange(newNum) {
          this.page.pageNum = newNum
          this.getUserList()
      },
      handleClose() {
        this.$refs.editForm.resetFields()
        this.info= {
          avatar: "",
          account: '',
          password: '',
          nickname: '',
          email: '',
          summary: '',
        }
      },
      up(avatar) {
          this.info.avatar = avatar
      }
    },
    created() {
      this.getUserList()
    },
    components: {Upload}
}
</script>

<style scoped lang="less">
  .el-card {
        margin-bottom: 20px;
    }

  /deep/ .el-card__body {
    display: flex;
    align-items: center;

    .input {
      margin-left: 40px;
      width: 70%;

      .el-form-item {
          margin: 0;
      }

      .el-input {
          margin-bottom: 10px;
      }
    }
  }
</style>