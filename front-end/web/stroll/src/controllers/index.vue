<template lang="pug">
 .background
  .content_box
   .main_content
    .main_item(:class="itemIndex==0?'active':''" @mouseenter="enter(0)" @click="gotoArticle()")
      .hidden_box
        .hidden_div
          p 文章是什么，文章是你我心灵交织的港湾
      .item_title 文章
    .main_item(:class="itemIndex==1?'active':''" @mouseenter="enter(1)" @click="gotoDrawing()")
      .hidden_box
        .hidden_div
          p 用画笔书写情感，绘制碌碌人群心中净土
      .item_title 手绘
    .main_item(:class="itemIndex==2?'active':''" @mouseenter="enter(2)" @click="gotoLogin()")
      .hidden_box
        .hidden_div
          p 登陆注册，加入闲亭分享彼此柔软笔触
      .item_title 登录注册
</template>

<script>
    export default {
      data () {
        return {
          itemIndex:2,
        }
      },
      methods:{
        enter(index){
          this.itemIndex=index;
        },
        gotoArticle(){
          this.$router.push({
            path:'/article'
          })
        },
        gotoDrawing(){
          this.$router.push({
            path:'/drawings'
          })
        },
        gotoLogin(){
          this.$router.push({
            path:'/login'
          })
        },
      }
    }
</script>

<style lang="scss" scoped>
  *{
    transition:all 0.5s;
  }
  .background{
    height:100vh;
    width: 100%;
    display: flex;
    flex-direction: column;
    flex: 1 1 0%;
    background: url("../../static/firstbg.png") center center / auto 100% rgb(245, 245, 245);
    background-position: center center;
  }
  .content_box{
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    flex: 1 1 0%;
  }
  .main_content{
    height:300px;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    margin-top: -40px;
  }
  .main_item{
    opacity: 0.7;
    cursor: pointer;
    text-decoration: none;
    margin: 0px 1em;
    overflow: hidden;
    border-radius: 6px;
    transition: transform 500ms ease 0s, opacity 500ms ease 0s;
    display: flex;
    height: 300px;
    background: hsla(0, 0%, 100%, .75);
    box-shadow: 3px 3px 6px 3px rgba(0, 0, 0, .3);
    -webkit-backdrop-filter: blur(5px);
    backdrop-filter: blur(5px);
  }
  .main_item.active{
    opacity: 1;
    .hidden_box{
      width:400px;
    }
  }
  .item_title{
    box-sizing: border-box;
    width: 100px;
    height: 100%;
    display: flex;
    -webkit-writing-mode: vertical-rl;
    writing-mode: vertical-rl;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    font-size: 22px;
    letter-spacing: 0.5em;
    font-weight: bold;
    white-space: nowrap;
    margin: 0px;
    padding: 1em;
  }
  .hidden_box{
    box-sizing: border-box;
    position: relative;
    width: 0px;
    height: 100%;
    overflow: hidden;
    margin: 0px;
    transition: width 500ms ease 0s;
  }
  .hidden_div{
    width: 400px;
    height: 100%;
    position: relative;
  }
  .hidden_div p{
    position: absolute;
    bottom: 2em;
    left: 2em;
    line-height: 2;
    white-space: pre;
    font-weight: bold;
    letter-spacing: 1px;
    margin: 0px;
  }
</style>
