<template>
  <div>
      <my-breadcrumb :model="['个人空间', '个人空间']"/>
      <div>
        <el-card class="header">
            <el-upload
            title="更换头像"
            class="avatar-uploader"
            action="fake"
            ref="uploadAvatar"
            :auto-upload="false"
            :http-request="upload"
            :on-change="handleChange"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="info.avatar" :src="info.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-form :model="info" ref="editForm" :rules="rules" class="content">
                <el-form-item prop="nickname">
                    <el-input v-model="info.nickname"></el-input>
                </el-form-item>
                <el-form-item prop="summary">
                    <el-input type="textarea" v-model="info.summary"></el-input>
                </el-form-item>
                <el-button type="primary" @click="save">保存更改</el-button>
                <el-button type="info" @click="isEditPwd = !isEditPwd">修改密码</el-button>
            </el-form>
        </el-card>

        <el-card v-show="isEditPwd">
            <el-form :model="passwordParam" ref="editPwdForm" :rules="rules" label-width="70px">
                <el-form-item prop="oldPassword" label="原密码">
                    <el-input type="password" v-model="passwordParam.oldPassword" clearable></el-input>
                </el-form-item>
                <el-form-item prop="newPassword" label="新密码">
                    <el-input type="password" v-model="passwordParam.newPassword" clearable></el-input>
                </el-form-item>
                <el-button type="primary" @click="changePwd">提交</el-button>
            </el-form>
        </el-card>
      </div>
      
  </div>
</template>

<script>
import { getUserInfo, saveUserInfo, editPwd } from '@/api/user'
import { upload } from '@/api/upload'

export default {
    name: 'Personal',
    data() {
        return {
            info: {
                nickname: '',
                avatar: '',
                summary: ''
            },
            passwordParam: {
                oldPassword: '',
                newPassword: ''
            },
            isEditPwd: false,
            rules: {
                nickname: [
                    { required: true, message: '请输入昵称名', trigger: 'blur' }
                ],
                oldPassword: [
                    { required: true, message: '请输入原密码', trigger: 'blur' }
                ],
                newPassword: [
                    { required: true, message: '请输入新密码', trigger: 'blur' },
                    { min:8, max:20, message: '密码长度在8到20之间', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        getUserInfo() {
            getUserInfo().then(res => {
                this.info = res.data.data
            })
        },
        handleAvatarSuccess(res, file) {
            this.info.avatar = res.data
            console.log(res)
        },
        beforeAvatarUpload(file) {
            let isJPG = file.type === 'image/jpeg';
            if (!isJPG) {
                isJPG = file.type === 'image/png'
            }
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        save() {
            saveUserInfo(this.info).then(() => {
                this.$message.success("修改成功")
            })
        },
        changePwd() {
            this.$refs.editPwdForm.validate(valid => {
                if (!valid) return
                editPwd(this.passwordParam).then(() => {
                    this.$message.success("修改成功")
                })
            })
        },
        upload(data) {
            let params = new FormData()
            params.append('image', data.file)
            upload(params).then(res => {
                this.info.avatar = res.data.data
            })
        },
        handleChange(file) {
            // console.log(file)
            if (file) this.$refs.uploadAvatar.submit()
        }
    },
    created() {
        this.getUserInfo()
    },
}
</script>

<style scoped lang="less">
    .header {
        padding: 20px;

        /deep/ .el-card__body {
            padding: 0;
        }

        .avatar-uploader {
            float: left;
        }
    }

    .content {
        margin-left: 220px;

        .el-form-item {
            height: 50px;

            /deep/ .el-form-item__content{
                height: 50px;
            }
        }
    }

    .el-card {
        margin-bottom: 20px;
    }

    /deep/ .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>