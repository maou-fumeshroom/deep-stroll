<template lang="pug">
 div
  user-header(v-if="$route.meta.keepAlive")
  user-background(v-if="$route.meta.keepAlive" :activeMode="activeMode")
  .app-main-container(ref="element")
    router-view(v-if="isRouterAlive" @changeActive="changeActive")
</template>

<script>
  import userHeader from './components/userHeader'
  import userBackground from './components/userBackground'
export default {
  provide () {
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isRouterAlive: true,
      isLogin:false,
      user:null,
      activeMode:'none',
      // user:{
      //   avatar: require('../assets/logo.png'),
      //   nickname:'baozilong'
      // },
    }
  },
  components: {
    userHeader,
    userBackground
  },
  watch: {
    $route: {
      handler (val, oldVal) {
        this.$root.mine = !((val.path).indexOf('mine') === -1)
      },
      immediate: true
    }
  },
  created () {
    if(localStorage.getItem("activeMode")){
      this.activeMode = localStorage.getItem("activeMode")
    }
  },
  methods: {
    reload () {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    },
    changeActive(val){
      this.activeMode = val
    }
  }
}
</script>

<style scoped>
  .app-header-container{
    top: 0;
    width:100%;
    background-color: #fff;
    position: fixed;
    z-index: 9;
    box-shadow: 0 2px 6px rgba(173, 172, 172, 0.363);
  }
  .app-main-container {
    margin: 0 auto;
    padding: 0;
    min-height: calc(100vh - 62px);
  }
</style>
