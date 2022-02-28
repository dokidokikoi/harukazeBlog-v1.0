<template>
  <div class="container">
      <my-breadcrumb :model="['文章管理','删除记录']"/>
    <el-card>
        <el-row>
            <el-col :span="3">
                <el-select v-model="page.categoryId" clearable  placeholder="请选择文章分类">
                    <el-option
                    v-for="item in categorys"
                    :key="item.id"
                    :label="item.categoryName"
                    :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="3">
                <el-select v-model="page.tagId" clearable  placeholder="请选择文章标签">
                    <el-option
                    v-for="item in tags"
                    :key="item.id"
                    :label="item.tagName"
                    :value="item.id">
                    </el-option>
                </el-select>
            </el-col>
            <el-col :span="8">
                <el-input 
                placeholder="请输入内容" 
                v-model="page.query" 
                @keydown.enter.native="getArticleList" 
                clearable @clear="getArticleList()">
                    <el-button slot="append" icon="el-icon-search" @click="getArticleList()"></el-button>
                </el-input>
            </el-col>
            <el-col :span="4">
                <el-button type="primary" @click="writeArticle">发表文章</el-button>
            </el-col>
        </el-row>

        <el-table
        :data="articleList"
        style="width: 100%"
         border stripe>
            <el-table-column type="expand">
                <template slot-scope="scope">
                    <el-row style="padding: 0 50px;">
                        <el-col :span="6">
                            Category
                            <i class="el-icon-caret-right" style="margin-right: 20px"></i>
                            <el-tag v-if="scope.row.category.categoryName" type="primary">{{scope.row.category.categoryName}}</el-tag>
                        </el-col>
                        <el-col :span="14">
                            <el-row>
                                <el-col>
                                    Tags
                                    <i class="el-icon-caret-right" style="margin-right: 20px"></i>
                                    <el-tag type="success" v-for="item in scope.row.tags" :key="item.id" style="margin-right: 20px">
                                        {{item.tagName}}
                                    </el-tag>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </template>
            </el-table-column>
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column prop="title" label="标题" :width="150"></el-table-column>
            <el-table-column label="描述" :width="190">
                <template slot-scope="scope">
                    {{scope.row.summary | ellipsis}}                     
                </template>
            </el-table-column>
            <el-table-column  label="创建日期" :width="160">
                <template slot-scope="scope">
                    {{scope.row.createDate | dateFormat}}                     
                </template>
            </el-table-column>
            <el-table-column label="修改日期" :width="160">
                <template slot-scope="scope">
                    {{scope.row.updateDate | dateFormat}}                     
                </template>
            </el-table-column>
            <el-table-column prop="viewCounts" label="阅读数量" :width="90"></el-table-column>
            <el-table-column prop="commentCounts" label="评论数量" :width="90"></el-table-column>
            <el-table-column label="作者" :width="120">
                <template slot-scope="scope">
                    {{scope.row.author.nickname}}
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button icon="el-icon-edit"  type="primary" size="mini" @click="editArticle(scope.row.id)">修改</el-button>
                    <el-button icon="el-icon-delete" type="info" size="mini" @click="recoverArticle(scope.row.id)">恢复</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>

     <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.pageNum"
      :page-sizes="[5, 10]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import { getDeleted,recoverArticle } from '@/api/article'
import { getCategoryList } from '@/api/category'
import { getTags } from '@/api/tag'
import { navHighLight } from '@/mixin'
export default {
    name: 'Deleted',
    data() {
        return {
            articleList: [],
            page: {
                pageNum: 1,
                pageSize: 7,
                categoryId: '',
                query: '',
                tagId: ''
            },
            total: 0,
            categorys: [],
            tags: []
        }
    },
    methods: {
        handleSizeChange(newSize) {
            this.page.pageSize = newSize
            this.getArticleList()
        },
        handleCurrentChange(newNum) {
            this.page.pageNum = newNum
            this.getArticleList()
        },
        getArticleList() {
            getDeleted(this.page).then(res => {
                this.articleList = res.data.data
                this.total = res.data.total
                // this.$message.success("获取文章列表成功")
            }).catch(err => {
                this.$message.error(err)
            })
        },
        writeArticle() {
            this.$router.push('/write')
        },
        editArticle(id) {
            this.$router.push({path:'/edit?id='+id})
        },
        recoverArticle(id) {
             this.$confirm('此操作将恢复该文章, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    recoverArticle(id).then(() => {
                        this.$message.success("恢复成功")
                        this.getArticleList()
                    }).catch(err => {
                        this.$message.error(err)
                    })
                }).catch(err => {
                    this.$message.info('已取消')
                })
        },
        getCategoryList() {
            getCategoryList().then(res => {
                this.categorys = res.data.data
            })
        },
        getTags() {
            getTags().then(res => {
                this.tags = res.data.data
            })
        }
    },
    created() {
        this.getArticleList()
        this.getCategoryList()
        this.getTags()
    },
    mixins: [navHighLight]
}
</script>

<style scoped lang="less">
    .el-col {
        margin-right: 10px;
    }
</style>