<template>
  <div id="app" @scroll="handleScroll()">
    <canvas-bg/>
    <loading v-if="isLoading"/>
    <router-view/>
    <transition name="showup" appear>
      <GoTop class="gotop" v-show="isShowTog" @click.native="goTop"/>
    </transition>
  </div>
</template>

<script>
import GoTop from '@/components/layout/GoTop'

export default {
  name: 'App',
  data() {
    return {
      scrollTop: 0,
      windowHeight: 0,
      scrollHeight: 0,
      scrollHeightDown: 0,
      isShowTog: false,
    }
  },
  computed: {
    cover() {
      return this.$store.state.bgi
    },
    isLoading() {
      return this.$store.state.isLoading
    } 
  },
  watch: {
    scrollTop(val) {
      if (val > 500) this.isShowTog = true;
      else this.isShowTog = false;
    }
  },
  components: { GoTop },
  methods: {
    handleScroll() {
      this.scrollTop = document.querySelector('html').scrollTop;
      this.windowHeight = document.querySelector('html').clientHeight;
      this.scrollHeight = document.querySelector('html').scrollHeight;
      this.scrollHeightDown = this.scrollHeight-20;
      
    },
    goTop() {
      this.$store.dispatch('toTop')
    }
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  destroyed() {
      window.removeEventListener("scroll", this.handleScroll);
  }
}
</script>

<style scoped>
  .showup-enter-active{
		animation: atguigu 1s linear;
	}

	.showup-leave-active{
		animation: atguigu .5s linear reverse;
	}

	@keyframes atguigu {
		from{
			transform: translateY(100%);
		}
		to{
			transform: translateY(0);
		}
	}

</style>

