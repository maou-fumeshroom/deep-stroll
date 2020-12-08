<template>
  <div id="app">
    <template>
      <div style="min-width: 1000px;">
        <el-container class="main-con">

          <el-header  class='main-header'>
            <div class="main-img-box">
              <span>闲亭管理后台</span>
            </div>
          </el-header>

          <el-container>
            <el-aside style="width:151px;overflow: hidden" v-show="$route.meta.keepAlive">
              <el-menu class="el-menu-vertical-demo" router :collapse-transition="true" :unique-opened='true' :collapse="false" background-color="#24292e">
                <div v-for="(route,index) in routes" :key="index">
                  <el-menu-item :index="route.path" v-if="!route.sonMenu">
                    <span slot="title" >{{route.name}}</span>
                  </el-menu-item>
                  <el-submenu :index="route.id+''" v-else>
                    <span slot="title">{{route.name}}</span>
                    <div v-for="(menu,index) in route.sonMenu" :key="index">
                      <el-menu-item :index="menu.path" v-if="menu.id === 4 || menu.id === 5|| menu.id ===6">
                        <span slot="title" style="font-size:12px;">{{menu.name}}</span>
                      </el-menu-item>
                    </div>
                  </el-submenu>
                </div>
              </el-menu>
            </el-aside>

            <el-container>
              <router-view v-if="isRouterAlive" @onSetRoute="setRoute"/>
            </el-container>

          </el-container>

        </el-container>

      </div>
    </template>

  </div>
</template>

<script>
export default {
  provide () {
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isRouterAlive: true,
      routes:[]
    }
  },
  watch: {
    $route: {
      handler (val, oldVal) {
        this.$root.mine = !((val.path).indexOf('mine') === -1)
      },
      immediate: true
    }
  },
  mounted(){
    if (sessionStorage.getItem('routes')){
      this.routes = JSON.parse(sessionStorage.getItem('routes'))
    }
  },
  methods:{
    reload () {
      this.isRouterAlive = false
      console.log('reload')
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    },
    setRoute(e){
      sessionStorage.setItem('routes',JSON.stringify(e))
      this.routes = e;
      console.log(e)
      if (this.routes[0].sonMenu===null) {
        this.$router.push({
          path: this.routes[0].path
        })
      } else {
        this.$router.push({
          path: this.routes[0].sonMenu[0].path
        })
      }
    }
  }
}
</script>

<style>
* {
  margin:0;
}

.main-con{
  min-height:100vh;
  background-color: #EFEEEE;
}
.main-header {
  display: flex;
  justify-content: center;
  padding: 0;
  height: 68px !important;
  background-color: #24292e;
}

.main-img-box {
  height: 68px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size:20px;
  color:#ffffff;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 151px;
  min-height: 100% ;
  text-align: center;
}

.el-menu-item{
  color:#ffffff;
  margin:1px 0;
}

.el-menu-item.is-active {

}

.el-submenu__title{
  color:#ffffff;
}

.el-submenu .el-menu-item {
  min-width: 151px;
}

</style>
