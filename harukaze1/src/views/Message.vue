<template>
    <div class="container">
        <el-card class="bread">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>留言板</el-breadcrumb-item>
            </el-breadcrumb>
        </el-card>

        <div class="bannar">
            <div class="mask"></div>
            <h1 style="margin-top: 0;">留言板</h1>
        </div>

        <el-card>
            <h3 style="margin-top: 0;">注意</h3>
            <ul>
                <li>自由发言</li>
                <li>禁止人身攻击</li>
                <li>不能出现脏话</li>
            </ul>
        </el-card>

        <SubComment :articleId="0"/>

    </div>
</template>

<script>
import SubComment from '@/components/comment/SubComment'

export default {
    name: 'Message',
    data() {
        return {
            url: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
            
        }
    },
    mounted() {
        this.$store.dispatch('toTop')
    },
    components: {SubComment}
}
</script>

<style scoped lang="less">
    .container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin: auto;
        width: 1000px;
    }

    .el-card.bread {
        margin-top: 60px;
        margin-bottom: 10px;
    }

    .bannar {
        margin-bottom: 20px;
        height: 400px;
        background: url("../assets/messageBannar.png") no-repeat; 
        background-size: cover;
        background-position-y: -50px;
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

    .el-card {
        margin-bottom: 20px;
        background-color: rgba(255,255,255,0.9);
    }

    @media screen and (max-width: 800px) {
        .container {
            width: 600px;
        }
    }
</style>