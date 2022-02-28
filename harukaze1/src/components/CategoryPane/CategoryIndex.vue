<template>
  <div class="container">
      <h3 class="category_name">{{category.categoryName}}</h3>
      <p class="category_desc">{{category.description}}</p>
  </div>
</template>

<script>
export default {
    name: 'CategooryIndex',
    props: ["category"]
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