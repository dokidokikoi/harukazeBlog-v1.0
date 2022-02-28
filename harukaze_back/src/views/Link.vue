<template>
  <div>
      <my-breadcrumb :model="['友链管理', '友链管理']"/>
        <el-card>
            <Upload v-model="info.avatar" @change="up"/>

            <el-form :model="info" ref="addForm" :rules="rules" class="input">
                <el-form-item prop="name" label="站点名称">
                    <el-input v-model="info.name"></el-input>
                </el-form-item>
                <el-form-item prop="url" label="站点地址">
                    <el-input v-model="info.url"></el-input>
                </el-form-item>
                <el-form-item prop="siteDesc" label="站点描述">
                    <el-input v-model="info.siteDesc"></el-input>
                </el-form-item>
                <el-button type="primary" @click="save" style="margin-top: 10px">添加友链</el-button>
            </el-form>
        </el-card>

        <el-card>
            <el-table :data="links" border stripe>
                <el-table-column type="index" label="#"></el-table-column>
                <el-table-column label="头像" width="200">
                    <template slot-scope="scope">
                        <el-image :src="scope.row.avatar"></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="站点名称" prop="name" width="150"></el-table-column>
                <el-table-column label="站点地址" prop="url">
                    <template slot-scope="scope">
                        <a :href="scope.row.url" target="_blank">{{scope.row.url}}</a>
                    </template>
                </el-table-column>
                <el-table-column label="站点描述" prop="siteDesc"></el-table-column>
                <el-table-column label="操作" width="145">
                    <template slot-scope="scope">
                        <el-button type="primary" size="mini" @click="showChange(scope.row)">修改</el-button>
                        <el-button type="danger" size="mini" @click="deleteLink(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>

        <el-dialog
        title="修改友链"
        :visible.sync="dialogVisible"
        width="70%"
        @close="handleClose">
        <el-form :model="info" ref="editForm" :rules="rules" label-width="80px">
            <el-form-item prop="avatar" label="头像">
                <Upload v-model="info.avatar" @change="up"/>
            </el-form-item>
            <el-form-item prop="name" label="站点名称">
                <el-input v-model="info.name"></el-input>
            </el-form-item>
            <el-form-item prop="url" label="站点地址">
                <el-input v-model="info.url"></el-input>
            </el-form-item>
            <el-form-item prop="siteDesc" label="站点描述">
                <el-input v-model="info.siteDesc"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="changeLink">确 定</el-button>
        </span>
        </el-dialog>
  </div>
</template>

<script>
import Upload from '@/components/ImageUpLoad'

import { getLinks, saveLink, changeLink, deleteLink } from '@/api/link'

export default {
    name: 'Link',
    data() {
        return {
            info: {
                avatar: '',
                name: '',
                url: '',
                siteDesc: ''
            },
            rules: {
                name: [
                    { required: true, message: '请输入站点名', trigger: 'blur' }
                ],
                url: [
                    { required: true, message: '请输入站点网址', trigger: 'blur' },
                ],
                siteDesc: [
                    { required: true, message: '请输入描述', trigger: 'blur' }
                ]
            },
            links: [],
            dialogVisible: false
        }
    },
    methods: {
        getLinks() {
            getLinks().then(res => {
                this.links = res.data.data
            })
        },
        save() {
            this.$refs.addForm.validate(valid => {
                if (!valid) return
                saveLink(this.info).then(() => {
                    this.getLinks()
                    this.handleClose()
                    this.$message.success("添加成功")
                })
            })
        },
        showChange(info) {
            this.info = JSON.parse(JSON.stringify(info))
            this.dialogVisible = true
        },
        changeLink() {
            this.$refs.addForm.validate(valid => {
                if (!valid) return
                changeLink(this.info).then(res => {
                    this.getLinks()
                    this.dialogVisible = false
                    this.$message.success("修改成功")
                })
            })
        },
        deleteLink(id) {
            this.$confirm('此操作将删除该友链, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deleteLink(id).then(() => {
                        this.$message.success("删除成功")
                        this.getLinks()
                    }).catch(err => {
                        this.$message.error(err)
                    })
                }).catch(err => {
                    this.$message.info('已取消')
                })
        },
        handleClose() {
            this.info = {
                avatar: '',
                name: '',
                url: '',
                siteDesc: ''
            }
        },
        up(avatar) {
            this.info.avatar = avatar
        }
    },
    created() {
        this.getLinks()
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