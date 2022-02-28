<template>
  <div class="container">
      <el-card class="bread">
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>友链</el-breadcrumb-item>
        </el-breadcrumb>
    </el-card>
    <div class="bannar">
        <div class="mask"></div>
        <h1 style="margin-top: 0;">友链</h1>
    </div>

    <div class="card">
        <h3>欢迎交换友链</h3>
        <span>请先将本站添加到贵站的友链中，</span>
        <span>并在<router-link to="/message">留言板</router-link>或发送<a href="mailto:2397351356@qq.com">邮件(推荐)</a>告诉我你的: </span>
        <ol>
            <li>站点名称</li>
            <li>站点介绍 (尽量精简到一句话)</li>
            <li>主页地址 (https)</li>
            <li>头像地址 (https)</li>
        </ol>
        <span>※</span>

        <span>格式如下:</span>
        <ul>
            <li>Name: harukaze</li>
            <li>desc: </li>
            <li>URL: https://harukaze.top</li>
            <li>Avatar: ...</li>
        </ul>
    </div>

    <div class="card">
        <h2 class="title">友链</h2>

        <ul class="link">
            <li class="card" v-for="item in links" :key="item.id">
                <a :href="item.url" target="_blanck">
                    <el-avatar :src="item.avatar"></el-avatar>
                    <div class="info">
                        <p>{{item.name}}</p>
                        <p style="font-size: .8em;">{{item.siteDesc}}</p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
  </div>
</template>

<script>
import { getLink } from '@/api/link'

export default {
    name: 'Link',
    data() {
        return {
            links: [],
        }
    },
    methods: {
        getLink() {
            getLink().then(res => {
                this.links = res.data.data
            })
        }
    },
    created() {
        this.getLink()
    },
    mounted() {
        this.$store.dispatch('toTop')
    }
}
</script>

<style lang="less" scoped>
    .container {
        margin: auto;
        width: 1000px;
        min-width: 100px;
    }

    .bannar {
        margin-bottom: 20px;
        height: 400px;
        background: url("../assets/linkBannar.png") no-repeat; 
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

    .el-card.bread {
        margin-top: 60px;
        margin-bottom: 10px;
    }

    .card {
        background-color: rgba(255, 255, 255, 0.9);
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
        padding: 20px;
        margin-bottom: 20px;
        border-radius: 4px;

        a {
            text-decoration: none;
            color: pink;
        }
    }

    ol {
        margin-top: 10px;
        margin-bottom: 20px;
    }

    ul {
        margin-top: 10px;
        margin-bottom: 20px;
    }

    .title {
        display: inline-block;
        position: relative;
        margin-top: 0;
    }

    .title::after {
        content: " ";
        display: block;
        background: url("../assets/line_wave_g.png") repeat-x left center;
        background-position-X: 5px;
        width: 100%;
        height: 7px;
        position: absolute;
        left: 0px;
        bottom: -10px;
        z-index: 10;
    }

    .link {
        box-sizing: border-box;
        list-style-type: none;
        margin: 0;
        padding: 0;

        &::after {
            clear: both;
            display: block;
            content: "";
        }

        li {
            padding: 10px;
            float: left;
            width: 30%;
            margin-left: 10px;

            a {
                text-decoration: none;
                color: #000;
            }

            .el-avatar {
                float: left;
            }

            .info {
                margin-left: 50px;

                p {
                    margin: 0;
                }

                p:nth-child(1) {
                    border-bottom: dashed 1px black;
                    margin-bottom: 5px;
                }
            }

            &::after {
                content: '';
                clear: both;
                display: block;
            }
        }
    }
</style>