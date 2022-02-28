<template>
  <el-upload
    title="上传头像"
    class="avatar-uploader"
    action="fake"
    ref="uploadAvatar"
    :auto-upload="false"
    :http-request="upload"
    :on-change="handleChange"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload">
    <img v-if="avatar" :src="avatar" class="avatar">
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
</template>

<script>
import { upload } from '@/api/upload'
export default {
    data() {
        return {
           
        }
    },
    props: ["value"],
    computed: {
        avatar: {
            get() {
                return this.value
            },
            set(value) {
                // console.log()
                this.$emit('change', value)
            }
        }
    },
    methods: {
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
        upload(data) {
                let params = new FormData()
                params.append('image', data.file)
                upload(params).then(res => {
                    this.avatar = res.data.data
                })
            },
        handleChange(file) {
            // console.log(file)
            if (file) this.$refs.uploadAvatar.submit()
        }
    },
    // deactivated() {
    //     this.avatar = ''
    // }
}
</script>

<style lang="less" scoped>
    .avatar-uploader {
        width: 100px;
        height: 100px;
    }

    /deep/ .el-upload {
        border: 1px dashed gray !important;
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
        width: 100px;
        height: 100px;
        line-height: 100px;
        text-align: center;
    }
    .avatar {
        width: 100px;
        height: 100px;
        display: block;
    }
</style>