<template>
    <div class="my-breadcrumb">
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-for="(item,index) in model" :key="index">{{item}}</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
</template>

<script>
export default {
    name: 'MyBreadcrumb',
    props: ['model']
}
</script>

<style scoped lang='less'>
    /deep/ .el-breadcrumb__inner {
        color: #fff;
    }
</style>