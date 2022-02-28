<template>
  <div class="container">
      <h3 class="tag_name">{{tag.tagName}}</h3>
      <p class="tag_desc"></p>
  </div>
</template>

<script>
export default {
    name: "TagIndex",
    props: ['tag'],
}
</script>

<style scoped lang="less">
    div.container {
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
        background-color: rgba(255, 255, 255, 0.9);
        padding: 20px;
        margin-bottom: 20px;
    }
</style>