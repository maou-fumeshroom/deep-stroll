<template lang="pug">
 div(id="deepStroll")
  user-header(v-show="$route.meta.keepAlive")
    <!--template(slot="logo"): router-link(to="/")-->

    <!--template(slot="nav")-->
      <!--li.link-->
        <!--router-link(to="/article", exact) 文章-->
      <!--li.link-->
        <!--router-link(to="/essay") 随笔-->
      <!--li.link-->
        <!--router-link(to="/drawings") 手绘-->
    <!--template(slot="tool")-->
      <!--a(href="javascript:void(0)", @click="$root.login()", v-if="user") 登录-->
      <!--template(v-else)-->
        <!--router-link.twt&#45;&#45;header-user.twt-m-r-1(to="/mine")-->
          <!--img.twt&#45;&#45;header-user-avatar(:src="user.avatar")-->
          <!--| {{user.realname}}-->
        <!--a.twt-hidden-md-down(href='javascript:void(0)', @click="$root.logout()") 退出-->
    <!--el-row.app-header-box(type="flex", justify="space-between")-->
      <!--div(v-show="!$root.manage")-->
        <!--router-link(to="/article", exact, tag="li", class="header-item") 文章-->
        <!--router-link(to="/essay", tag="li", class="header-item") 随笔-->
        <!--router-link(to="/drawings", tag="li", class="header-item") 手绘-->
      <!--.header-admin(v-if="$root.managePage")-->
  .app-main-container(ref="element")
    //- transition(name="el-zoom-in-center")
    router-view(v-if="isRouterAlive")
</template>

<script>
  import userHeader from './components/userHeader'
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
      // user:{
      //   avatar: require('../assets/logo.png'),
      //   nickname:'baozilong'
      // },
    }
  },
  components: {
    userHeader
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
    localStorage.setItem('token','1111111')
  },
  methods: {
    reload () {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    },
  }
}
</script>

<style>
  html,body{
    height: 100%;
  }

  #deepStroll{
    height: 100%;
  }
</style>

<style scoped>
  .app-header-container{
    top: 0;
    width:100%;
    height: 100%;
    background-color: #fff;
    position: fixed;
    z-index: 9;
    box-shadow: 0 2px 6px rgba(173, 172, 172, 0.363);
  }
  .app-main-container {
    margin: 0 auto;
    /*padding-top: 70px;*/
    padding-top: 62px;
    min-height: 100vh;
    height: 100%;
  }
</style>
