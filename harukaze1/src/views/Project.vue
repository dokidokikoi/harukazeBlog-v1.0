<template>
    <div class="container">
        <el-card class="bread">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>项目</el-breadcrumb-item>
            </el-breadcrumb>
        </el-card>
        <div class="bannar">
            <div class="mask"></div>
            <h1 style="margin-top: 0;">项目</h1>
        </div>
        
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
import BlogItem from '@/components/blog/BlogItem'

import { getArticleList } from '@/api/article'
import { getCategoryList } from '@/api/category'
import { getTags } from '@/api/tag'

export default {
    name: 'Project',
    data() {
        return {
            articleList: [],
            page: {
                categoryId: 1,
                pageNum: 0,
                pageSize: 5
            },
            total: 0,
        }
    },
    methods: {
        getArticleList() {
            this.$store.commit('LOADING')
            getArticleList(this.page).then(res => {
                this.articleList = res.data.data
                this.total = res.data.total
                this.$store.commit('COMPLETE')
                this.$store.dispatch('toTop')
            })
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
        toDetail(id) {
            this.$router.push(`blog/${id}`)  
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
        this.getArticleList()
        this.getTags()
        this.getCategoryList()
    },
    mounted() {
        this.$store.dispatch('toTop')
    },
    components: {BlogItem}
}
</script>

<style scoped lang="less">
    .container {
        width: 1000px;
        margin: auto;
        box-sizing: border-box;
    }

    .bannar {
        margin-top: 10px;
        margin-bottom: 20px;
        height: 400px;
        background: url("../assets/projectBannar.png") no-repeat; 
        background-position-x: 0;
        background-size: cover;
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

    .bread {
        margin-top: 60px;
        padding: 0px;
    }

    .main {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
    }

    @media screen and (max-width: 1040px) {
        .container {
            width: 100%;
        }
    }

    @media screen and (max-width: 900px) {
        .container, .blog_content {
            width: 600px;
        }

        
    }
</style>