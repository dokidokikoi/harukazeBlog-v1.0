<template>
    <div class="container">
        <div>
            <p style="margin-top: 0">&copy; {{new Date().getFullYear()}} harukaze</p>
            <p style="color: white;">互联网ICP备案：<a href="https://beian.miit.gov.cn/">湘ICP备2021019471号-1</a></p>
            <p>Powered by myself | Theme DEMO</p>
        </div>
        <div>
            <p class="time">本站已安全运行{{time}}</p>
        </div>
    </div>
</template>

<script>
import { getAbout } from '@/api/about'

export default {
    name: 'MyFooter',
    data() {
        return {
            createDate: '',
            time: ''
        }
    },
    methods: {
        async getTime() {
            let {data:res} = await getAbout()
            this.createDate = res.data.createDate
        },
        format() {
            setInterval(() => {
                let dt = new Date(new Date().getTime() - this.createDate)
                let d = Math.floor(dt.getTime()/1000/60/60/24)
                let hh = (dt.getHours() + '').padStart(2, '0')
                let mm = (dt.getMinutes() + '').padStart(2, '0')
                let ss = (dt.getSeconds() + '').padStart(2, '0')
                this.time = `${d}天${hh}小时${mm}分${ss}秒`
            }, 1000)
        }
    },
    created() {
        this.getTime()
        this.format()
    }
}
</script>

<style scoped lang="less">
    .container {
        width: 1000px;
        padding: 10px;
        margin: auto;
        display: flex;
        justify-content: space-between;
    }

    div p a {
        text-decoration: none;
        color: antiquewhite;

        &:hover {
            color: aqua;
        }
    }

    p {
        font-size: .8em;
    }

    .time {
        font-size: 1.2em;
    }
</style>