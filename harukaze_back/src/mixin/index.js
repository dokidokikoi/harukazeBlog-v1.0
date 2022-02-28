export const navHighLight = {
    activated() {
        this.$store.commit('nav/SET_URI',this.$route.path) 
    },
    mounted() {
        this.$store.commit('nav/SET_URI',this.$route.path) 
    },
}