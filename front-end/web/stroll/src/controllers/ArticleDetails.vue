<template>
  <div id="articleDetails" v-if="loadingOK">
    <!--    文章详情页面上半部分，文章的信息-->
    <i class="el-icon-arrow-left" @click="back"/>
    <div id="message">
      <h2 class="title">{{articleMsg.title}}</h2>
      <p class="introduction">{{articleMsg.introduction}}</p>
      <img :src="articleMsg.author.avatar" class="avatar"/>
      <span class="nickname">{{articleMsg.author.nickname}}</span>
      <span class="time">{{articleMsg.dateTime}}</span>
      <hr/>
    </div>

    <!--文章详情页面中部，文章的内容-->
    <div id="container">
      <!--      <p class="content">{{articleMsg.content}}</p>-->
      <VueMarkdown :source="htmlMD"/>
      <!--      <div v-html="htmlMD"></div>-->
    </div>

    <!--文章详情页面下半部分，评论-->
    <div id="comment">
      <el-input class="inputCom" type="textarea" placeholder="请输入评论" v-model="userComment" rows="5" maxlength="150" show-word-limit/>
      <el-button class="publish" type="primary">发布</el-button>
    </div>
  </div>
</template>

<script>
  const axios = require('axios');
  import VueMarkdown from "vue-markdown";
  export default {
    name: "ArticleDetails",
    components:{
      VueMarkdown,
    },
    data () {
      return {
        // articleMsg:{
        //   id:"",
        //   autor:{
        //     id:"1",
        //     nickname:"李华",
        //     avatar:require("../assets/logo.png"),
        //   },
        //   title:"如何看待 Python 之父 Guido 加盟微软？？？",
        //   introduction:"Python 之父 Guido van Rossum 在 Python 邮件组里发邮称，他将退出 Python 核心决策层，而转居幕后。",
        //   fileUrl:"http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481592000.md",
        //   likeNum:3,
        //   isLike:0,
        //   isCollect:1,
        //   labels:["python","程序员"],
        //   type:0,
        //   dateTime:"2020-11-20",
        //   status:0,
        //   classifyName:"互联网"
        // },
        articleMsg:{
          author:{}
        },
        // authorMsg:{},
        userComment:"",
        backPage:"",
        htmlMD:"",
        loadingOK:false
      }
    },
    methods: {
      back(){
        this.$router.push({
          // 返回点入的父页面
          path:'/' + this.backPage,
        })
      }
    },
    created() {
      //接收到传来的文章详情信息
      this.articleMsg.id = this.$route.query.id;
      // console.log(" 初始化id："+this.articleMsg.id);
      //接收到传来的返回路径
      this.backPage = this.$route.query.backpage;
      console.log(this.backPage);

      let that = this;
      //获取文章详情
      this.$http.get('/api/article/detail',{
        params:{
          id:that.articleMsg.id,
        }
      }).then(function(res){
        // console.log("！！： "+JSON.stringify(res));
        // console.log("！！： "+JSON.stringify(res.data.author.avatar));
        // console.log("！！： "+JSON.stringify(res.data.author.nickname));
        that.articleMsg = res.data;
        // console.log("nickname: "+ that.articleMsg.author.nickname)
        // console.log("url: "+ that.articleMsg.fileUrl)
        that.$http.get(that.articleMsg.fileUrl).then((response) => {
          that.htmlMD = response;
          console.log("this.htmlMD: "+ that.htmlMD)
          that.loadingOK = true;
        });
      }).catch(function(){
        console.log("服务器异常");
      });
    },
  }
</script>

<style scoped>
  #articleDetails{
    margin: 62px 15% 0 15%;
    background-color: #fff;
    height: 100%;
    position:relative;
  }
  .el-icon-arrow-left{
    cursor: pointer;
  }
  /*文章详情页面上半部分，文章的信息*/
  #message{
    padding-top: 10px;
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
    margin-right: 10px;
  }
  .nickname{
    position: absolute;
  }
  .time{
    float: right;
    margin-right: 40px;
  }
  hr{
    margin-top: 1px;
  }
  /*文章详情页面中部，文章的内容*/
  #container{
    margin: 0 5%;
  }
  /*文章详情页面下半部分，评论*/
  #comment{
    height: 180px;
  }
  .inputCom{
    margin-left: 2.5%;
    width: 95%;
  }
  /*/deep/ .el-textarea__inner{*/
  /*  min-height: 120px;*/
  /*}*/
  .publish{
    position: absolute;
    right: 25px;
    bottom: 20px;
  }
</style>
