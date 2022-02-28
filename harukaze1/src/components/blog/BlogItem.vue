<template>
    <ul class="blog_list">
        <li class="blog_item" v-for="item in articleList" :key="item.id">
            <router-link :to="`/blog/${item.id}`" class="blog">
                <div class="cover" :style="`background-image: url(${item.cover})`"></div>
                <div class="content">
                    <div class="avatar">
                        <el-avatar size="large" :src="item.author.avatar"></el-avatar>
                        <span style="font-size:13px; margin-left:7px; margin-bottom:5px;font-style: italic;">@{{item.author.nickname}}</span>
                        <div class="blog_date" style="font-size:13px; margin-left:10px; margin-bottom:5px">
                            <svg-icon icon-class="time" style="font-size: 1.2em;"></svg-icon>
                            <span style="font-size:0.7em">{{item.createDate | blogDateFormat}}</span>
                        </div>
                    </div>
                    <h3>{{item.title}}</h3>
                </div>
            </router-link>
        </li>
    </ul>
</template>

<script>
export default {
    name: "BlogItem",
    props: ["articleList"]
}
</script>

<style scoped lang="less">
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        width: 68%;

        li {
            margin: 0;
            padding: 0;
            margin-bottom: 20px;

            a {
                text-decoration: none;
                color: #000;
            }
        }

        .blog {
            box-sizing: border-box;
            height: 250px;
            background-color: #fff;
            border: 1px solid #EBEEF5;
            box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
            border-radius: 4px;
            display: flex;
            padding: 10px;
            position: relative;

            .cover {
                width: 60%;
                height: 100%;
                border-radius: 4px;
                background-position: center;
                background-size: cover;
                background-repeat: no-repeat;
                transition: .5s;
            }

            .content {
                padding: 10px;

                .avatar {
                    display: flex;
                    align-items: flex-end;
                }

                h3:hover {
                    color: red;
                }
            }

            .content::after {
                content: '';
                position: absolute;
                top: 0;
                right: 0;
                background-color: pink;
                width: 4px;
                height: 0px;
                border-radius: 2px;
                transition: .5s;
            }
        }
    }

    .blog:hover {
        cursor: pointer;

        .content::after {
            height: 250px;
            box-shadow: #ffc0cb 0px 0px 6px;
        }

        .cover {
            transform: scale(1.05);
            transition: all 0.5s ease-in-out;
        }
    }

    .blog_list li:nth-child(even) {
        .blog .cover {
            order: 2;
        } 

        .blog .content::after {
            left: 0px;
        }
    }
</style>