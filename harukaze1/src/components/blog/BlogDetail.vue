<template>
  <div class="container">
       <div class="blog_title">
            <div class="avatar">
                <el-avatar size="large" :src="article.author.avatar"></el-avatar>
            </div>
              <div class="content">
                <h2>{{article.title}}</h2>
                <div class="meta">
                    <span>@{{article.author.nickname}}</span>
                    <div class="blog_date">
                        <i class="el-icon-date"></i>
                        <span>{{article.createDate | dateFormat}}</span>
                    </div>
                    <div>
                        <i class="el-icon-view"></i>
                        <span>{{article.viewCounts}}</span>
                    </div>
                    <div>
                        <i class="el-icon-chat-line-round"></i>
                        <span>{{article.commentCounts}}</span>
                    </div>        
                </div>
              </div>
          </div>
          <div class="cover" :style="`background-image: url(${article.cover})`"></div>
          <div class="content_body">
              <div style="width: 73%;">
                <!-- <Markdown :content="body" style="margin-bottom: 20px"/> -->
                <div class="blog_body" v-html="body.content"></div>
                <SubComment :articleId="$route.params.id"/>
              </div>
              <div class="card">
                  <div class="blog_nav"></div>
                  <user-pane/>
                  <tag-pane ref="tag" :data="article.tags"/>
                  <category-pane ref="category" :data="article.category"/>
              </div>
          </div>
  </div>
</template>

<script>
import SubComment from '@/components/comment/SubComment'
import { getArticleById } from '@/api/article'
import Prism from 'prismjs'

export default {
    name: "BlogDetail",
    data() {
        return {
            article: {
                id: '',
                title: '',
                summary: '',
                author: {},
                acticleBody: {
                    id: '',
                    content: 'ss',
                    contentHtml: ''
                },
                category: '',
                tags: []
            },
            body: {
                content:''
            },
        }
    },
    methods: {
        getArticleById() {
            this.$store.commit('LOADING')
            if (this.$route.params.id) {
                getArticleById(this.$route.params.id).then(res => {
                    this.article = res.data.data
                    this.body.content = res.data.data.articleBody.contentHtml
                    this.$refs.tag.setTags(this.article.tags)
                    this.$refs.category.setCategory([this.article.category])
                    this.insertNav(this.body.content)
                    setTimeout(() => {
                        Prism.highlightAll()
                    },0)
                }).catch(err => {
                    this.$message.error(err)
                })
            }
            this.$store.commit('COMPLETE')
            this.$store.dispatch('toTop')
        },
        insertNav(content) {
            const reg1 = /(?<=h1>).*?(?=<)/g
            // let arr = content.match(reg1)
            
        }
    },
    created() {
        this.getArticleById()
    },
    mounted() {
        
    },
    components: {SubComment}
}
</script>

<style scoped lang="less">
    .container {
        width: 1200px;
        margin: auto;
        margin-top: 60px;

        .blog_title {
            background-color: #fff;
            padding: 10px 0;
            margin-bottom: 10px;
            position: relative;
            box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);

            .avatar {
                position: absolute;
                // top: 50%;
                // transform: translateY(-50%);
                bottom: 10px;
                left: 20px;
                .el-avatar {
                    width: 60px;
                    height: 60px;
                }
            }

            .content {
                margin-left: 110px;
                .meta {
                    display: flex;
                    justify-content: space-between;
                    width: 270px;
                    font-style: italic;
                    font-size: 0.7em;
                    color: gray;
                }
            }
        }

        .cover {
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            height: 500px;
            width: 100%;
            margin-bottom: 10px;
        }

        .blog_body {
            background-color: #fff;
            width: 876px;
            overflow: hidden;
            padding: 0 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
        }

        .content_body {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 20px;
        }

        .card {
            width: 300px;
        }
    }
    h2 {
        font-size: 2em !important;
    }
</style>