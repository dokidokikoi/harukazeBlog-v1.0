<template>
  <div>
      test
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'Test',
    methods: {
       async test() {
         const data = await axios.get("http://localhost:8080/blog")
         console.log(data)
      }
    },
    mounted() {
      this.test()
    }
}
</script>

<style>

</style>