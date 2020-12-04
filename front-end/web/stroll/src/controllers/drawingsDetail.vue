<template>
  <div id="drawingsDetail">
    <!--    文章详情页面上半部分，文章的信息-->
    <i class="el-icon-arrow-left" @click="back"/>
    <div id="message">
      <h2 class="title">{{drawingsMsg.title}}</h2>
      <p class="introduction">{{drawingsMsg.introduction}}</p>
      <img :src = "drawingsMsg.avatarSrc" class="avatar"/>
      <span class="nickname">{{drawingsMsg.nickname}}</span>
      <span class="time">{{drawingsMsg.releaseTime}}</span>
      <hr/>

      <drawing-array :drawings-src="drawingsMsg.imgSrc"></drawing-array>

    </div>

  </div>
</template>

<script>
  import drawingArray from '../components/drawingArray'
  export default {
    data() {
      return{
        drawingsId: this.$route.query.drawingsId,
        drawingsMsg:{
          id:"111",
          imgSrc:[require("../assets/logo.png"),require(`../assets/1.jpg`),require(`../assets/2.jpg`),require(`../assets/3.jpg`)],
          title:"1111",
          nickname:"1111",
          avatarSrc:require("../assets/logo.png"),
          releaseTime:"111",
          likes:"111",
          commentsNum:"111",
          introduction:"111",
          // content:""
          mdSrc:"111"
        },
        // drawingsMsg:{
        //   author:{},
        // },
      }
    },
    components: {
      drawingArray
    },
    methods:{
      back(){
        // this.$router.push({
        //   // 返回点入的父页面
        //   path:'/' + this.backPage,
        // })
      }
    },
    created() {
      let that = this;
      // console.log("id: "+ that.drawingsId);
      //获取手绘详情
      this.$http.get('/api/drawing/detail',{
        params:{
          // id:that.drawingsId,
          id:1,
        }
      }).then(function(res){
        // console.log("！！： "+JSON.stringify(res));
        // that.drawingsMsg = res.data;
      }).catch(function(){
        console.log("服务器异常");
      });
    },
  };
</script>

<style scoped>
  #drawingsDetail{
    margin: 62px 15% 0 15%;
    /*background:#e5e6ee26;*/
    height: 100%;
    position:relative;
  }
  .el-icon-arrow-left{
    cursor: pointer;
  }
  /*文章详情页面上半部分，文章的信息*/
  #message{
    padding: 10px 0;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
    border-radius: 4px;
    overflow: hidden;
    background: #ffffff;
  }
  .title{
    text-align: center;
  }
  .introduction{
    text-align: center;
    font-size: 0.8em;
    color: #3031336e;
    margin: 5px;
  }
  .avatar{
    width: 25px;
    height: 25px;
    border-radius: 100%;
    margin-left: 40px;
  }
  .time{
    float: right;
    margin-right: 40px;
  }
  hr{
    margin-top: 1px;
  }
</style>
