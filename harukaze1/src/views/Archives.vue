<template>
        <div class="container">
            <el-card class="bread">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>归档</el-breadcrumb-item>
                </el-breadcrumb>
            </el-card>
            <div class="bannar">
                <div class="mask"></div>
                <h1 style="margin-top: 0;">归档</h1>
            </div>
            <el-card class="archives_content">
                <div class="header">
                    <span>时间轴</span>
                </div>
                <TimeStamp/>
            </el-card>
        </div>
</template>

<script>
import TimeStamp from '@/components/TimeStamp'

export default {
    name: 'Archives',
    data() {
        return {
            items: [
                {
                    date: '2020-09-30',
                    msg: 'test'
                },
                {
                    date: '2020-09-30',
                    msg: 'test2'
                }
            ]
        }
    },
    mounted() {
        this.$store.dispatch('toTop')
    },
    components: {TimeStamp}
}
</script>

<style scoped lang="less">
    .container {
        margin: auto;
        width: 1000px;
        min-width: 100px;
    }

    .bannar {
        margin-bottom: 20px;
        height: 400px;
        background: url("../assets/archivesBannar.png") no-repeat; 
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

    .el-card {
        background-color: rgba(255,255,255,0.9);
        margin-bottom: 20px;
    }

    .header {
        display: flex;
        flex-direction: row;
        border-bottom: dashed gray 1px;
        padding-bottom: 15px;
        margin-bottom: 20px;
    }

    .header span{
        font-size: 1.6em;
    }

    .content {
        border-radius: 15px;
        width: 70%;
        margin-left: 20px;
    }

</style>