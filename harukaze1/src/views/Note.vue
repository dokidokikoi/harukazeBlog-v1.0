<template>
    <div class="container">
        <el-card class="bread">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>随笔</el-breadcrumb-item>
            </el-breadcrumb>
        </el-card>

        <div class="bannar">
            <div class="mask"></div>
            <h1 style="margin-top: 0;">随笔</h1>
        </div>

        <div class="main">
            <ul class="blog_list">
                <li class="note" v-for="item in articleList" :key="item.id" @click="toDetail(item.id)">
                    <div class="img" :style="`background-image: url(${item.cover})`"></div>
                    <router-link class="bc" :to="`blog/${item.id}`"></router-link>
                    <h2>{{item.title}}</h2>
                    <div class="note_content">
                        <p class="describe">{{item.summary}}</p>
                        <div class="attach">
                            <svg-icon icon-class="time" style="font-size: .9em; margin-right:3px"></svg-icon><span style="font-size: 13px; margin-right: 10px">{{item.createDate | dateFormat}}</span>
                            <i class="el-icon-view"></i><span style="font-size: 13px; margin-right: 10px">{{item.viewCounts}}</span>
                            <i class="el-icon-chat-line-round"></i><span style="font-size: 13px; margin-right: 10px">{{item.commentCounts}}</span>
                        </div>
                    </div>
                </li>
            </ul>

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
import { getArticleList } from '@/api/article'
import { getCategoryList } from '@/api/category'
import { getTags } from '@/api/tag'


export default {
    name: 'Note',
    data() {
        return {
            articleList: [],
            page: {
                categoryId: 3,
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
            this.$router.push(`/blog/${id}`)  
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
        this.$store.dispatch('toTop')
        this.getArticleList()
        this.getTags()
        this.getCategoryList()
    },
    mounted() {
    }
}
</script>

<style scoped lang="less">
    .container {
        width: 1000px;
        margin: auto;
    }

    .el-card.bread {
        margin-top: 60px;
        margin-bottom: 10px;
    }

    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;

        li {
            overflow: hidden;
            margin-bottom: 20px;
        }
    }

    .main {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;

        .card {
            width: 300px;
        }
    }

    .blog_list {
        width: 68%;
    }

    .bannar {
        margin-bottom: 20px;
        height: 400px;
        background: url("../assets/noteBannar.png") no-repeat; 
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

    .note {
        width: 100%;
        height: 300px;
        color: white;
        border: none;
        position: relative;
        border-radius: 4px;

        .img {
            width: 100%;
            height: 300px;
            background-repeat: no-repeat; 
            background-position: center;
            background-size: cover;
            transform: scale(1);
            transition: all 0.35s ease-in-out;
            z-index: -10;
        }

        .note_content, h2 {
            position: absolute;
            left: 0;
            right: 0;
            margin: 0 30px;
            transform: translateY(0);
            transition: all 0.35s ease-in-out;
        }

        h2 {
            position: absolute;
            top: 40%;
            text-align: center;
        }

        .note_content {
            opacity: 0;
            color: white;
            display: flex;
            flex-direction: column;
            bottom: 20px;

            .describe {
                text-align: left; 
                margin-bottom: 0;
                padding-bottom: 10px;
                border-bottom: solid 1px white;
                font-size: 0.9em;
            }

            .attach {
                i {
                    margin-right: 3px;
                    font-size: 0.7em;
                }
            }
        }

        .bc {
            display: block;
            position: absolute;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(2, 2, 2, .2);
            z-index: 0;
        }
    }

    .note:hover {
        background-size: 120%;
        cursor: pointer;

        .note {
            opacity: 0.5;
        }
        
        .note_content, h2 {
            transition: all 0.35s ease-in-out;
        }

        h2 {
            transform: translateY(10px);
            z-index: 10;
        }

        .note_content {
            opacity: 1;
            transform: translateY(-10px);
            z-index: 10;
        }

        .img {
            transform: scale(1.1);
            transition: all 0.35s ease-in-out;
            z-index: -1;
        }

        .bc {
            background-color: rgba(2, 2, 2, .5);
        }        
    }
</style>