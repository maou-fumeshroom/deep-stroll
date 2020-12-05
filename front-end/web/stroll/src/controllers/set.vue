<template lang="pug">
  .content_box
    .theme_box
      .theme_item(v-for="(theme ,index) in themes" :key="index" @click="settheme(theme.id)") {{theme.name}}
      .theme_item 自定义
    .theme_box
      .theme_item(@click="changeActive('none')") 默认
      .theme_item(@click="changeActive('sun')") 阳光
      .theme_item(@click="changeActive('rain')") 雷雨
      .theme_item(@click="changeActive('snow')") 下雪
</template>

<script>
  export default {
    name: "set",
    data () {
      return {
        itemIndex:2,
        themes:[]
      }
    },
    methods:{
      getTheme(){
        let _this = this
        this.$http.get('/api/config/themeList').then(function(res){
          console.log(res);
          _this.themes = res.data.themes
        }).catch(function(){
          console.log("服务器异常");
        });
      },
      changeActive(mode){
        this.$emit('changeActive',mode);
        localStorage.setItem("activeMode", mode);
      },
      settheme(id){
        let that = this
        this.$http.get('/api/config/theme',{
          params:{
            id:id
          }
        }).then(function(res){
          let theme = {
            bgm:res.data.bgmUrl,
            background:res.data.background
          }
          localStorage.setItem("bgm", theme.bgm);
          localStorage.setItem("bg", theme.background);
          that.$emit('onSetTheme',theme);
        }).catch(function(){
          console.log("服务器异常");
        });
      }
    },
    created() {
      let that = this;
      this.getTheme()
    },
  }
</script>

<style scoped>
  .content_box{
    max-width:800px;
    margin:auto;
    display: flex;
    align-items: center;
    justify-content: center;
    height:100vh;
    flex-direction: column;
    flex: 1 1 0%;
    position:relative;
    z-index:2;
  }
  .theme_box{
    height:50px;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    border-radius: 6px;
    box-shadow: 3px 3px 6px 3px rgba(0, 0, 0, .3);
    overflow: hidden;
    margin-bottom:40px;
  }
  .theme_item{
    cursor: pointer;
    text-decoration: none;
    padding: 0px 1.5em;
    overflow: hidden;
    transition: transform 500ms ease 0s, opacity 500ms ease 0s;
    display: flex;
    height:50px;
    line-height:50px;
    -webkit-backdrop-filter: blur(5px);
    backdrop-filter: blur(5px);
    border-right:1px solid rgba(0, 0, 0, .3);
  }
</style>

