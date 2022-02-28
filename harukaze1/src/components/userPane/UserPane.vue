<template>
    <el-card class="host">
        <div class="avatar" @click="toAbout">
            <div class="spinner"></div>
            <div class="img" :style="`background-image: url(${info.avatar})`"></div>
        </div>
        <p>The life still going on</p>
        <p>很荣幸，能在这里与你相见</p>
        <div class="report">
            <a href="#">
                <span class="category">文章</span>
                <span class="num">{{articleCount}}</span>
            </a>
            <a href="#">
                <span class="category">标签</span>
                <span class="num">{{tagCount}}</span>
            </a>
            <a href="#">
                <span class="category">分类</span>
                <span class="num">{{categoryCount}}</span>
            </a>
        </div>
        <button class="about" @click="toAbout">
            <span>
                <svg-icon icon-class="home" style="font-size: 2em;"/>
                个人空间
            </span>
        </button>

        <div class="contact">
            <a href="#">
                <svg-icon icon-class="qq" style="font-size: 1.6em;"/>
            </a>
            <a href="#">
                <svg-icon icon-class="github" style="font-size: 1.6em;"/>
            </a>
            <a href="#">
                <svg-icon icon-class="gitee" style="font-size: 1.5em;"/>
            </a>
            <a href="mailto:2397351356@qq.com">
                <svg-icon icon-class="email" style="font-size: 1.8em;"/>
            </a>
        </div>
        
    </el-card>
</template>

<script>
import { getArtilceCounts } from '@/api/article'
import { getTagCounts } from '@/api/tag'
import { getCategoryCounts } from '@/api/category'
import { getAbout } from '@/api/about'

export default {
    name: 'UserPane',
    data() {
        return {
            articleCount: 0,
            tagCount: 0,
            categoryCount: 0,
            info: {}
        }
    },
    methods: {
        toAbout() {
            this.$router.push('about')
        },
        getAbout() {
            getAbout().then(res => {
                this.info = res.data.data
            })
        },
        getArtilceCounts() {
            getArtilceCounts().then(res => {
                this.articleCount = res.data.data
            })
        },
        getTagCounts() {
            getTagCounts().then(res => {
                this.tagCount = res.data.data
            })
        },
        getCategoryCounts() {
            getCategoryCounts().then(res => {
                this.categoryCount = res.data.data
            })
        }
    },
    created() {
        this.getArtilceCounts()
        this.getTagCounts()
        this.getCategoryCounts()
        this.getAbout()
    }
}
</script>

<style scoped lang="less">
    .host {
        width: 300px;
        margin-bottom: 20px;

        .avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            overflow: hidden;
            margin: auto;
            margin-top: 10px;
            cursor: pointer;

            .img {
                width: 100%;
                height: 100%;
                // background: url('../../assets/avatar.png');
                background-position: center;
                background-size: cover;
            }
        }

        p {
            text-align: center;
            font-size: 1.2em;
            font-weight: bold;
        }

        p:nth-child(3) {
            font-size: 1em;
            font-weight: normal;
        }

        .report {
            display: flex;
            justify-content: space-between;
            width: 200px;
            margin: auto;
            margin-top: 25px;
            margin-bottom: 15px;
            a {
                display: block;
                cursor: pointer;
                text-decoration: none;
                color: black;

                span {
                    display: block;
                    text-align: center;
                    font-weight: bold;
                }
            }
        }

        .about {
            width: 180px;
            height: 40px;
            color: #000;
            border-radius: 5px;
            padding: 10px 25px;
            font-family: 'Lato', sans-serif;
            font-weight: 500;
            background: transparent;
            cursor: pointer;
            transition: all 0.3s ease;
            position: relative;
            display: block;
            box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
            7px 7px 20px 0px rgba(0,0,0,.1),
            4px 4px 5px 0px rgba(0,0,0,.1);
            outline: none;

            background: linear-gradient(0deg, #4EDEC2 0%, #17E8BF 100%);
            line-height: 42px;
            padding: 0;
            border: none;
            margin: auto;

            &::before, &::after {
                position: absolute;
                content: "";
                right: 0;
                top: 0;
                background: #4EDEC2;
                transition: all 0.3s ease;
            }

            &::before {
                height: 0%;
                width: 2px;
            }
            
            &::after {
                width: 0%;
                height: 2px;
            }

            .svg-icon {
                margin-right: 5px;
            }

            &:hover {
                background: transparent;
                box-shadow: none;

                &::before {
                    height: 100%;
                }

                &::after {
                    width: 100%;
                }

                span {
                    color: #002B22;
                }
            }

            span {
                display: flex;
                align-items: center;
                justify-content: center;
            }

            span:before,
            span:after {
                position: absolute;
                content: "";
                left: 0;
                bottom: 0;
                background: #4EDEC2;
                transition: all 0.3s ease;
            }

            span::before {
                width: 2px;
                height: 0%;
            }

            span::after {
                width: 0%;
                height: 2px;
            }
            span:hover:before {
                height: 100%;
            }
            span:hover:after {
                width: 100%;
            }
        }

        .contact {
            width: 200px;
            margin: 20px auto;
            display: flex;
            justify-content: space-between;
            align-items: center;

            a {
                text-decoration: none;
                color: black;
            }
        }
    }
</style>