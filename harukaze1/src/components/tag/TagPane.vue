<template>
    <el-card class="tag_content">
        <div class="title">
            <svg-icon icon-class="tag" style="font-size: 1.3em;"></svg-icon>
            <span>Tags</span>
        </div>
        <div class="content">
            <router-link :to="`/search?tag=${item.id}`" v-for="item in tagList" :key="item.id" style="margin: 10px 10px 10px 0">{{item.tagName}}</router-link>
        </div>
    </el-card> 
</template>

<script>
export default {
    name: 'TagPane',
    data() {
        return {
            tagList: []
        }
    },
    props: ['data'],
    methods: {
        setTags(data) {
            this.tagList = data
        }
    },
    
}
</script>

<style scoped lang="less">
    .tag_content {
        box-sizing: border-box;
        width: 300px;
        padding: 10px;
        margin: 0;
        margin-bottom: 20px;
    }

    .title {
        border-bottom: solid 1px gray;
        padding-bottom: 5px;

       span {
           color: rgb(53, 52, 52);
           margin-left: 5px;
       }
    }

    .content {
        box-sizing: border-box;
        padding: 0 30px;
        margin: auto;
        display: flex;
        flex-wrap: wrap;

        a {
            text-decoration: none;
        }
    }
</style>