<template>
  <div class="top" @click="say"></div>
</template>

<script>
export default {
    name: "GoTop",
    data() {
        return {
            hoverImg: ''
        }
    },
    methods: {
        say(e) {
            // var word = document.createElement('span')
            // word.innerText = "烦死特了"
            // document.querySelector('.top').appendChild(word)
            // let x = e.pageX
            // let y = e.pageY
            // console.log(x,y)
            // word.style.zIndex = 999
            // word.style.position = 'absolute'
            // word.style.top = `${y - 20} !important`
            // word.style.left = x
            // word.style.fontWeight = 'bold'
            // word.style.color = '#000'
            // word.style.userSelect = 'none'

            // word.animate({
            //     top: y -180,
            //     opacity: 0
            // }, 2000, function() {
            //     document.removeChild('span')
            // })
        }
    },
    created() {
        this.hoverImg = require("@/assets/shiroha_syouku.png")
        console.log(this.hoverImg)
    },
    mounted() {
        document.querySelector(".top").onmouseover = () => {
            document.querySelector(".top").style.backgound = "#fff"
        }
        // let image = new Image()
        // image.onload = () => {

        // }
        // image.src = this.hoverImg
        // document.querySelector('div').appendChild(image)
    }
}
</script> 

<style scoped lang="less">
    .top {
        height: 270px;
        width: 250px;
        position: fixed;
        bottom: 0px;
        right: 0px;
        border-start-start-radius: 190px;
        border-start-end-radius: 190px;
        background-color: aqua;
        cursor: pointer;
        text-align: center;
        background: url("../../assets/shiroha.png") no-repeat;
        background-position-y: 10px;
        background-position-x: -40px;
        background-size: 130%; 
        z-index: 1000;
    }

    .top:hover {
        background-image: url("../../assets/shiroha_syouku.png");
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover; 
    }
</style>