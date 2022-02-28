<template>
    <div class="container">
        <div class="bannar">
            <div style="height:60px;"></div>
            <div class="content_header">
                <div class="inner">
                    <h2 style="display: inline-block; position: relative;">个人简介</h2>
                </div>
                <!-- 头像 -->
                <div class="ih-item circle effect10 top_to_bottom"><a href="#">
                    <div class="img"><img :src="data.avatar" alt="img"></div>
                    <div class="info">
                        <h3>Haru <br/>kaze</h3>
                    </div>
                </a></div>
                <div style="margin-left: 180px">
                    <span class="hostName">{{data.nickname}}</span>
                    <p class="about_text">{{data.summary}}</p>
                </div>
            </div>
        </div>       
        
        <div class="more">
            <!-- part1 -->
            <h4 class="title">个人标签</h4>
            <ul class="part1">
                <li><p class="desc">常用昵称</p><p class="name">DOKIDOKIKOI</p></li>
                <li><p class="desc">爱好</p><p class="name">ACG</p></li>
            </ul>

            <h4 class="title">出没地点</h4>
            <ul class="part2">
                <li><p class="name"><a href="https://github.com/dokidokikoi" target="_blank">github</a></p></li>
                <li><p class="name"><a href="https://gitee.com/" target="_blank">gitee</a></p></li>
                <li><p class="name"><a href="#">QQ</a></p></li>
                <li><p class="name"><a href="mailto:2397351356@qq.com">email</a></p></li>
            </ul>
        </div>
        
    </div>
</template>

<script>
import { getAbout } from '@/api/about'

export default {
    name: 'About',
    data() {
        return {
            data: {}
        }
    },
    methods: {
        getAbout() {
            getAbout().then(res => {
                this.data = res.data.data
            })
        }
    },
    created() {
        this.getAbout()
    },
    mounted() {
        this.$store.dispatch('toTop')
    }
}
</script>

<style scoped lang="less">
    .container {
        position: relative;
    }

    .bannar {
        // position: absolute;
        // top: 0;
        height: 400px;
        width: 100%;
        background: url("../assets/aboutBannar.png") no-repeat; 
        // background-position-y: -720px;
        background-size: cover;
        margin-bottom: 20px;
    }

    .content_header {
        width: 65%;
        margin: auto;

        .inner {
            margin-bottom: 20px;
        }
    }

    .ih-item * {
        box-sizing: border-box;
    }
    
    .ih-item img {
        width: 100%;
        height: 100%;
    }

    .ih-item {
        position: relative;
        width: 150px;
        height: 150px;
        border-radius: 50%;
        overflow: hidden;
        float: left;
    }

    .ih-item.circle .img {
        position: relative;
        width: 150px;
        height: 150px;
        border-radius: 50%;
    }

    .ih-item.circle .img:before {
        position: absolute;
        display: block;
        content: '';
        width: 100%;
        height: 100%;
        border-radius: 50%;
        box-shadow: inset 0 0 0 5px rgba(255, 255, 255, 0.6), 0 1px 2px rgba(0, 0, 0, 0.3);
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
    }

    .ih-item.circle .img img {
        border-radius: 50%;
    }

    .ih-item.circle .info {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        /* width: 100%; */
        text-align: center;
        border-radius: 50%;
        -webkit-backface-visibility: hidden;
        /* backface-visibility: hidden; */
        z-index: 1;
    }

    .ih-item.circle.effect10 .img {
        z-index: 11;
        /* -webkit-transform: scale(1);
        -moz-transform: scale(1);
        -ms-transform: scale(1);
        -o-transform: scale(1); */
        transform: scale(1);
        /* -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out; */
        transition: all 0.35s ease-in-out;
    }

    /* .ih-item.circle.effect10.colored .info {
        background: #1a4a72;
    } */

    .ih-item.circle.effect10 .info {
        background: #333333;
        opacity: 1;
        /* -webkit-transform: scale(0);
        -moz-transform: scale(0);
        -ms-transform: scale(0);
        -o-transform: scale(0); */
        transform: scale(0);
        /* -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out; */
        transition: all 0.35s ease-in-out;
    }

    .ih-item.circle.effect10 .info h3 {
        color: #fff;
        text-transform: uppercase;
        position: relative;
        letter-spacing: 2px;
        font-size: 22px;
        font-family: "Lustria", Georgia, "Times New Roman", Times, serif;
        line-height: 23px;
        border-bottom: solid 1px white;
        margin: 0;
        padding: 0;
        text-shadow: 0 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.3);
    }

    .ih-item.circle.effect10 a:hover .info {
        /* visibility: visible; */
        /* opacity: 1; */
        /* -webkit-transform: scale(1);
        -moz-transform: scale(1);
        -ms-transform: scale(1);
        -o-transform: scale(1); */
        transform: scale(1);
    }

    .ih-item.circle.effect10.top_to_bottom .info h3 {
        margin: 0 30px;
        padding: 25px 0 0 0;
        height: 78px;
    }

    .ih-item.circle.effect10.top_to_bottom a:hover .img {
        /* -webkit-transform: translateY(50px) scale(0.5);
        -moz-transform: translateY(50px) scale(0.5);
        -ms-transform: translateY(50px) scale(0.5);
        -o-transform: translateY(50px) scale(0.5); */
        transform: translateY(40px) scale(0.45);
        transition: all 0.35s ease-in-out;
    }



    .container h2::after {
        content: " ";
        display: block;
        background: url("../assets/line_wave_g.png") repeat-x left center;
        width: 100%;
        height: 7px;
        position: absolute;
        left: 0px;
        bottom: -15px;
        z-index: 0;
    }

    .hostName {
        color: #fff;
        font-size: 1.6em;
    }

    .label {
        font-size: 1.3em;
    }

    .about_text {
        width: 70%;
        color: #fff;
        word-wrap: break-word;
        line-height: 25px;
    }

    .more {
        padding-top: 50px;
        width: 65%;
        margin: auto;
    }

    ul {
        box-sizing: border-box;
        list-style-type: none;
        margin: 0;
        padding: 40px 0;
    }

    ul::after {
        clear: both;
        display: block;
        content: '';
    }

    li {
        float: left;
        width: 200px;
        margin: 20px;
        padding: 0;
    }

    .title {
        font-size: 1.2em;
        position: relative;
        margin: 0;

        &::after {
            position: absolute;
            bottom: -4px;
            content: '';
            display: block;
            width: 100px;
            height: 3px;
            background-color: pink;
        }
    }

    .part1 {
        .name {
            margin: 0;
            font-size: 1.4em;
            color: black;
        }

        .desc {
            margin-bottom: 5px;
            border-bottom: solid 1px black;
            font-size: 0.5em;
        }
    }

    .part2 {
        // display: flex;
        // justify-content: space-between;
        // flex-wrap: wrap;

        .name {
            border: solid 1px black;
            border-radius: 30px;
            text-align: center;
            margin: 0 -1%;
            zoom: 1;
            transition: .8s;

            a {
                display: block;
                padding: 5px 0;
                text-decoration: none;
                font-size: 1.1em;
                color: black;
            }

            &:hover {
                background-color: #2C394B;
                a {
                    color: #fff;
                }
            }
        }
    }


</style>