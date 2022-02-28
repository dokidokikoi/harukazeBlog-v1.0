<template>
  <div class="container">
      <my-breadcrumb :model="['文章管理', '发表文章']"/>
      <el-card>
            <el-form :model="articleForm" ref="articleForm" :rules="rules">
                <el-form-item>
                    <el-input resize="none"
                      type="textarea"
                      autosize
                      v-model="articleForm.title"
                      placeholder="请输入标题"
                      class="me-write-input">
                    </el-input>
                </el-form-item>

                <el-form-item label="文章分类" prop="category">
                    <el-select v-model="articleForm.category" value-key="id" placeholder="请选择文章分类">
                    <el-option 
                    v-for="c in categoryList" 
                    :key="c.id" 
                    :label="c.categoryName" 
                    :value="c"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="文章标签" prop="tags">
                    <el-checkbox-group v-model="articleForm.tags">
                        <el-checkbox v-for="t in tags" :key="t.id" :label="t.id" name="tags">
                            {{t.tagName}}
                        </el-checkbox>
                    </el-checkbox-group>
                </el-form-item>

                <el-form-item label="文章封面" prop="cover">
                    <el-upload
                    ref="uploadCover"
                    class="avatar-uploader"
                    action="fake"
                    :auto-upload="false"
                    :http-request="upload"
                    :on-change="handleChange"
                    :show-file-list="false"
                    :before-upload="beforeAvatarUpload"
                    :multiple="false">
                        <div v-if="this.articleForm.cover" :style="`background-image: url(${this.articleForm.cover})`" class="avatar"></div>
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>

                <el-form-item prop="summary">
                    <el-input type="textarea"
                        v-model="articleForm.summary"
                        :rows="4"
                        placeholder="请输入摘要">
                    </el-input>
                </el-form-item>
                
                <el-form-item>
                    <!-- <MarkdownEditor :editor="articleForm.body"/> -->
                    <TinyMceEditor :value="articleForm.body"/>
                </el-form-item>
            </el-form>
            <div class="footer">
                <el-button @click="rest">取 消</el-button>
                <el-button type="primary" @click="publish">发布</el-button>
            </div>
      </el-card>
  </div>
</template>

<script>
import TinyMceEditor from '@/components/TinymceEditor'
import { getCategoryList } from '@/api/category'
import { publishArticle } from '@/api/article'
import { getTags } from '@/api/tag'
import { upload } from '@/api/upload'

export default {
    name: "WriteArticle",
    data() {
        return {
            articleForm:{
                title: '',
                summary: '',
                cover: '',
                body: {
                    content: '',
                    contentHtml: ' '
                },
                category: '',
                tags: []
            },
            categoryList: [],
            tags: [],
            rules: {
                summary: [
                    {required: true, message: '请输入摘要', trigger: 'blur'},
                    {max: 80, message: '不能大于80个字符', trigger: 'blur'}
                ],
                category: [
                    {required: true, message: '请选择文章分类', trigger: 'change'}
                ],
                tags: [
                    {type: 'array', required: true, message: '请选择标签', trigger: 'change'}
                ],
                cover: [
                    {required: true, message: '请上传文章封面', trigger: 'change'}
                ]
            },
            isIndeterminate: false,
            cover: ''
        }
    },
    props: ['articleId'],
    methods: {
        getCategoryList() {
            getCategoryList().then(res => {
                this.categoryList = res.data.data
            }).catch(err => {
                this.$message.error(err)
            })
        },
        getTags() {
            getTags().then(res => {
                this.tags = res.data.data
            }).catch(err => {
                this.$message.error(err)
            })
        },
        publish() {
            if (!this.articleForm.cover) return this.$message.error("请上传封面")
            this.$refs.articleForm.validate(vaild => {
                if (!vaild) return
                this.$confirm('此操作将发布该文章, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let tags = this.articleForm.tags.map(function (item) {
                        return {id: item};
                    });
                    //发布文章
                    let article = {
                        title: this.articleForm.title,
                        summary: this.articleForm.summary,
                        category: this.articleForm.category,
                        cover: this.articleForm.cover,
                        tags: tags,
                        body: {
                            content: this.articleForm.body.content,
                            contentHtml: this.articleForm.body.contentHtml
                        }
                    }
                    console.log('--------------')
                    publishArticle(article).then(() => {
                        this.$message.success("发布成功")
                    }).catch(err => {
                        console.log(err)
                        // this.$message.error(err)
                    })
                    
                }).catch(err => {
                    this.$message.info(err)
                })   
            })
        },
        rest() {
            this.$confirm('此操作将清空所有数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.articleForm = {
                        title: '',
                        summary: '',
                        body: {
                            content: '',
                            contentHtml: ''
                        },
                        category: '',
                        tags: []
                    }
                })
        },
        beforeAvatarUpload(file) {
            let isJPG = file.type === 'image/jpeg'
            if (!isJPG) {
                isJPG = file.type === 'image/png'
            }
            const isLt5M = file.size / 1024 / 1024 < 5;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt5M) {
                this.$message.error('上传头像图片大小不能超过 5MB!')
            }
            return isJPG && isLt5M;
        },
        upload(data) {
            if (data.file.name == this.cover) return 
            let params = new FormData()
            params.append('image', data.file)
            console.log("@")
            upload(params).then(res => {
                this.articleForm.cover = res.data.data
                console.log(data)
            })
        },
        handleChange(file) {
            // console.log(file)
            if (file) {
                this.$refs.uploadCover.submit()
                this.cover = file.name
                console.log(file)
            }
            
        }
    },
    created() {
        this.getCategoryList()
        this.getTags()
    },
    components: {TinyMceEditor}
}
</script>

<style scoped lang='less'>
    .avatar-uploader {
        width: 700px;
        height: 400px;
        margin: auto;
    }

    /deep/ .el-upload.el-upload--text {
        width: 700px;
        height: 400px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        border: 1px dashed #555353;
        border-radius: 6px;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 700px;
        height: 400px;
        transform: translateY(50%);
        text-align: center;
    }
    .avatar {
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
        display: block;
        height: 100%;
        z-index: 100;
    }

    .footer .el-button{
        float: right !important;
        margin: 0 20px 20px 0;
    }
</style>