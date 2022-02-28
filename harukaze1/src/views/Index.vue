<template>
    <div>
        <section class="banner">
            <el-card>
                <h3 class="good_words">{{showWords}}</h3>
                <h4 class="writer">{{showWriter}}</h4>
            </el-card>
            <div id="index" class="bounce down" @click="startRead">
                <i class="el-icon-arrow-down" style="color: white"></i>
            </div>
        </section>
        <TechBlog/>
    </div>
</template>

<script>
import TechBlog from '@/components/blog/TechBlog'
import { getWord } from '@/api/goodWord'

export default {
    name: 'Index',
    data() {
        return {
            showWords: '',
            showWriter: '',
            goodWords: "",
            writer: ""
        }
    },
    methods: {
        startRead() {
            this.$nextTick(() => {
                document.getElementById('index').scrollIntoView({
                behavior: 'smooth',
                block: 'start',
                // inline: 'nearest'
                });
            })
        },
        typeWrods() {
            let index = 0
            let len = this.goodWords.length
            let timer = setInterval(() => {
                if (len == 0) {
                    len = this.goodWords.length
                }
                this.showWords = this.goodWords.slice(0, index++) + "_"
                if (index == len) {
                    this.showWords = this.goodWords
                    this.showWriter = "————"+this.writer
                    clearInterval(timer)
                    console.log("@")
                }
            }, 100)
        },
        getWord() {
            getWord().then(res => {
                this.goodWords = res.data.data.content
                this.writer = res.data.data.writer
            })
        }
    },
    components: {TechBlog},
    created() {
        this.getWord()
    },
    mounted() {
        this.$store.commit('SET_BGI', 'images/bc1.jpg')
        this.typeWrods()
    }
}
</script>

<style scoped lang="less">
    .banner {
        height: 100vh;
        background: url("../assets/bc1.jpg") no-repeat center center fixed; 
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;

        display: flex;
        justify-content: center;
        align-items: center;
        position: relative;

        .el-card {
            box-sizing: border-box;
            position: relative;
            width: 80%;
            height: 50%;
            border: 20px solid rgba(129, 128, 128, 0.4);
            background: rgba(173, 172, 172, .2);

            .good_words, .writer {
                color: #fff;
                margin: 0;
            }

            .good_words {
                font-size: 2.4em;
                line-height: 1.5em;
            }

            .writer {
                position: absolute;
                bottom: 10px;
                right: 10px;
                font-size: 1.5em;
            }
        }

        .down {
            animation: bounce 2s infinite;
            animation-duration: 3s;
            font-size: 25px;
            position: absolute;
            bottom: 5px;
            left: 50%;
            transform: translateX(-50%);
            display: flex;
            justify-content: center;
            align-items: center;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            border: 2px solid #fff;
        }

        .down:hover {
            animation: none;
            cursor: pointer;
            box-shadow: 0 0 20px 0 white;
            transition: all .2s;
        }
    }

    @keyframes bounce {
        0%, 20%, 50%, 80%, 100% {
        transform: translate(-50%, 0);
        }
        40% {
        transform: translate(-50%, -30px);
        }
        60% {
        transform: translate(-50%, -15px);
        }
    }

</style>