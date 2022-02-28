<template>
  <el-card class="category_content">
        <div class="title">
            <svg-icon icon-class="folder" style="font-size: 1.3em;"></svg-icon>
            <span>Category</span>
        </div>
        <div class="content">
            <router-link :to="`/search?category=${item.id}`" v-for="item in categoryList" :key="item.id" style="margin: 10px 10px 10px 0">{{item.categoryName}}</router-link>
        </div>
    </el-card> 
</template>

<script>
export default {
    name: 'CategoryPane',
    data() {
        return {
            categoryList: []
        }
    },
    props: ['data'],
    methods: {
        setCategory(data) {
            this.categoryList = data
        }
    },
    mounted() {
    }
}
</script>

<style scoped lang="less">
    .category_content {
        padding: 10px;
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