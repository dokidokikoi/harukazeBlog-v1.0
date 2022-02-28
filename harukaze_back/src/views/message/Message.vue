<template>
  <div>
      <my-breadcrumb :model="['消息管理', '消息管理']"/>
      <SubComment :articleId="0" />
  </div>
</template>

<script>
import SubComment from '@/components/comment/SubComment'
export default {
    name: 'MyMessage',
    components: {SubComment}
}
</script>

<style>

</style>