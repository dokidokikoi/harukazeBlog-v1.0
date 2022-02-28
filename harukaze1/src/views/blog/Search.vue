<template>
  <div class="container">
        <el-card class="bread">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>检索</el-breadcrumb-item>
            </el-breadcrumb>
        </el-card>

        <div class="bannar">
            <div class="mask"></div>
            <h1 style="margin-top: 0;">检索</h1>
        </div>

      <CategoryIndex v-if="$route.query.category" :category="category"/>
      <TagIndex v-if="$route.query.tag" :tag="tag"/>
      <div class="main">
          <BlogItem :articleList="articleList"/>

            <div class="card" style="width:300px">
                <tag-pane ref="tag"/>
                <category-pane ref="category"/>
            </div>
      </div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="page.pageNum"
        :page-size="page.pageSize"
        layout="prev, pager, next, jumper"
        background
        :total="total">
        </el-pagination>
  </div>
</template>

<script>
import TagIndex from '@/components/tag/TagIndex'
import CategoryIndex from '@/components/CategoryPane/CategoryIndex'
import BlogItem from '@/components/blog/BlogItem'

import { getTag, getTags } from '@/api/tag'
import { getCategory, getCategoryList } from '@/api/category'
import { getArticleList } from '@/api/article'

export default {
    name: "Search",
    data() {
        return {
            tag: {},
            category: {},
            articleList: [],
            page: {
                pageNum: 0,
                pageSize: 5,
                categoryId: '',
                tagId: '',
                query: ''
            },
            total: 0
        }
    },
    methods: {
        getTag() {
            if (this.$route.query.tag) {
                getTag(this.$route.query.tag).then(res => {
                    this.tag = res.data.data
                }).catch(err => {
                    this.$message("参数错误")
                })
            }
        },
        getCategory() {
            if (this.$route.query.category) {
                getCategory(this.$route.query.category).then(res => {
                    this.category = res.data.data
                }).catch(err => {
                    this.$message.error("参数错误")
                })
            }
        },
        getTags() {
            getTags().then(res => {
                this.$refs.tag.setTags(res.data.data)
            })
        },
        getCategoryList() {
            getCategoryList().then(res => {
                this.$refs.category.setCategory(res.data.data)
            })
        },
        getArticleList() {
            this.$store.commit('LOADING')
            this.page.categoryId = this.$route.query.category
            this.page.tagId = this.$route.query.tag
            this.page.query = this.$route.query.query
            getArticleList(this.page).then(res => {
                this.articleList = res.data.data
                this.total = res.data.total
                this.$store.commit('COMPLETE')
                this.$store.dispatch('toTop')
            })
        },
        handleCurrentChange(newNum) {
            this.page.pageNum = newNum
            this.getArticleList(this.page)
        },
        handleSizeChange(newSize) {
            this.page.pageSize = newSize
            this.getArticleList(this.page)
        }
    },
    created() {
        this.getTag()
        this.getCategory()
        this.getCategoryList()
        this.getTags()
        this.getArticleList()
        this.$store.dispatch('toTop')
    },
    components: {TagIndex,CategoryIndex,BlogItem}
}
</script>

<style scoped lang="less">
    * {
        box-sizing: border-box;
    }

    .el-card.bread {
        margin-top: 60px;
        margin-bottom: 10px;
    }
    
    .container {
        margin: 0 auto;
        margin-top: 60px;
        width: 1000px;
    }

    .bannar {
        margin-bottom: 20px;
        height: 400px;
        background: url("../../assets/searchBannar.png") no-repeat; 
        background-size: cover;
        background-position-y: -50px;
        display: flex;
        align-items: center;
        justify-content: center;
        position: relative;

        .mask {
            position: absolute;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,.2);
            top: 0;
        }

        h1 {
            color: white;
            text-align: center;
            font-size: 2em;
            font-weight: normal;
            z-index: 2;
        }
    }

    .main {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
    }
</style>