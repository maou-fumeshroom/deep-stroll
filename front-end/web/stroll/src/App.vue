<template lang="pug">
 div
  user-header(v-if="$route.meta.keepAlive" :menus="menus" :user="user")
  user-background(v-if="$route.meta.keepAlive" :activeMode="activeMode" :imgSrc="imgSrc" :audioSrc="audioSrc")
  .app-main-container(ref="element")
    router-view(v-if="isRouterAlive" :key="key" @changeActive="changeActive" @onSetTheme="setTheme" @onSetMenus="setMenus")
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
      menus:[],
      imgSrc:'',
      audioSrc:'',
      key:''
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
    if(localStorage.getItem("bg")){
      this.imgSrc = localStorage.getItem("bg")
    }
    if(localStorage.getItem("bgm")){
      this.audioSrc = localStorage.getItem("bgm")
    }
    if (localStorage.getItem("menus")){
      this.menus = JSON.parse(localStorage.getItem("menus"))
    }
  },
  mounted(){
    this.getUser()
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
    },
    setTheme(val){
      localStorage.setItem("bgm", val.bgm);
      localStorage.setItem("bg", val.background);
      this.audioSrc = val.bgm
      this.imgSrc = val.background
    },
    setMenus(val){
      this.menus = val
      localStorage.setItem("menus",JSON.stringify(this.menus))
      this.getUser()
    },
    getUser(){
      let that = this
      //得到个人基本信息
      this.$http.get('/api/person/basic')
        .then(function(res){
          if (res.result.code === 1) {
            that.user = res.data;
          }
        }).catch(function(){
        console.log("服务器异常");
      });
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
