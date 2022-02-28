<template>
    <div class="container">
        <!-- <canvas-bg/> -->
        <section class="body">
            <user-pane class="user-pane"/>

            <el-card class="blog_content">
                <div class="blog_header">
                    <span>技术文章</span>
                </div>
                <tr class="blog" v-for="item in articleList" :key="item.id">
                    <div class="avatar">
                        <el-avatar size="large" :src="item.author.avatar"></el-avatar>
                    </div>
                    <div class="content first">
                        <div class="meta">
                            <span style="font-size:13px">@{{item.author.nickname}}</span>
                            <div class="blog_date" style="font-size:13px">
                                <svg-icon icon-class="time" style="font-size: 1.2em;"></svg-icon>
                                <span>{{item.createDate | dateFormat()}}</span>
                            </div>
                        </div>
                        <router-link :to="`/blog/${item.id}`" class="title">{{item.title}}</router-link>
                        <p>{{item.summary | ellipsis(100)}}</p>
                        <router-link :to="`/blog/${item.id}`" class="img" :style="`background-image: url(${item.cover})`"></router-link>
                        <div class="blog_footer">
                            <div>
                                <i class="el-icon-view"><span>{{item.viewCounts}}</span></i>
                                <i class="el-icon-chat-line-round"><span>{{item.commentCounts}}</span></i>
                            </div>
                        </div>
                    </div>
                </tr>
            </el-card>

            <div class="card">
                <date-picker v-model="date" style="width: 300px; margin-bottom: 20px"/>
                <tag-pane ref="tag" class="pane"/>
                <category-pane ref="category" class="pane"/>
            </div>
            
        </section>
        
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
    name: 'TechBlog',
    data() {
        return {
            page: {
                categoryId: 2,
                pageNum: 0,
                pageSize: 5
            },
            total: 0,
            articleList: [],
            date: new Date()
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
}
</script>

<style scoped lang="less">
    .container {
        width: 90%;
        margin: auto;
        position: relative;
    }

    .body {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
    }

    .user-pane, .card {
        margin-top: 60px;
    }

    .card {
        width: 300px;
        padding: 0;
    }

    .pane {
        margin-bottom: 20px;
    } 

    .el-card {
        background-color: rgba(255,255,255,0.9);
        overflow: visible;
    }

    /deep/ .el-card__body {
        padding: 0 !important;
        overflow: visible;
    }

    .blog_content {
        width: 700px;
        padding: 10px;
        margin-top: 60px;
        position: relative;
    }

    .blog_header {
        font-size: 1.3em;
        position: absolute;
        top: -50px;
        left: 0;

        span {
            font-size: 1.1em;
            font-weight:bold;
        }
    }

    .blog {
        padding: 10px 30px;
        border-top: solid gray 1px;
        width: 100%;
        transform: translateX(5px);
        overflow: visible;

        .avatar {
            position: absolute;
            top: 5px;
            width: 20px;
        }

        div.content {
            box-sizing: border-box;
            width: 92%;
            margin-left: 48px;
            margin-bottom: 20px;
            padding-top: 10px;
            padding-right: 20px;
            border-top: solid 1px gray;

            .meta {
                .blog_date {
                    display: inline-block;
                    margin-left: 15px;
                }

                span {
                    font-style: italic;
                    font: 0.1em;
                }
            }

            .title {
                text-decoration: none;
                font-size: 1.6em;
                color: black;
                line-height: 40px;
                word-wrap:break-word;
                margin-bottom: 30px;
                font-weight: bold;
            }

            .title:hover {
                color: red;
            }
        } 

        .img {
            display: block;
            height: 260px;
            min-width: 624px;
            background-position: center;
            background-size: cover;
            transform: scale(1);
            transition: all 0.35s ease-in-out;
            margin-bottom: 10px;
            box-shadow: 0px 10px 15px #2416573d;
            border-radius: 5px;   
        }

        .img:hover {
            transform: scale(1.1);
            transition: all 0.35s ease-in-out;
            cursor: pointer;
        }

        
    }

    .blog_footer {
        display: flex;
        align-items: center;
    }

    .blog_footer span {
        font-size: 10px;
        margin-left: 5px !important;
    }

    .blog_footer i{
        margin-left: 15px;
    }

    .el-pagination {
        margin-top: 20px;
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