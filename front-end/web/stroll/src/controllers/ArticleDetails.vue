<template>
  <div id="articleDetails" v-if="loadingOK">
    <!--    文章详情页面上半部分，文章的信息-->
    <i class="el-icon-arrow-left" @click="back"/>
    <i v-if="userId == articleMsg.author.id" class="el-icon-delete delButton" @click="deleteArticle"/>
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
    <!--<div id="comment">-->
      <!--<el-input class="inputCom" type="textarea" placeholder="请输入评论" v-model="userComment" rows="5" maxlength="150" show-word-limit/>-->
      <!--<el-button class="publish" type="primary">发布</el-button>-->
    <!--</div>-->
  </div>
</template>

<script>
  import {client} from "../utils/alioss"
  const axios = require('axios');
  import VueMarkdown from "vue-markdown";
  import { Loading } from 'element-ui';
  export default {
    name: "ArticleDetails",
    components:{
      VueMarkdown,
    },
    data () {
      return {
        articleMsg:{
          author:{}
        },
        userComment:"",
        backPage:"",
        htmlMD:"",
        loadingOK:false,
        articleID:"",
        userId:0,
      }
    },
    methods: {
      back(){
        this.$router.push({
          // 返回点入的父页面
          path:'/' + this.backPage,
        })
      },
      deleteArticle(){
        //只有作者本人才能删除
        let _this = this
          //删除文章
          console.log("idididi: "+ this.articleID)
          this.$http.post('/api/person/works/delete',{
            id:this.articleID,
            type:0,
          },{emulateJSON: true})
            .then(function(res){
              if (res.result.code === 1) {
                //删除文章链接
                let temp1 = _this.articleMsg.fileUrl.split("/");
                console.log(temp1[3])
                let urlName1 = temp1[3];
                client().delete(urlName1).then(
                  result=>{
                    console.log("1"+result)
                  }
                );

                //删除封面链接
                let temp2 = _this.articleMsg.cover.split("/");
                console.log(temp2[3])
                let urlName2 = temp2[3];
                client().delete(urlName2).then(
                  result=>{
                    console.log("2"+result)
                  }
                );
                _this.$notify({
                  title: '成功',
                  message: '删除文章成功！',
                  type: 'success',
                  duration:1500
                });
                _this.$router.push({
                  // 返回点入的父页面
                  path:'/' + _this.backPage,
                })
              }
            }).catch(function(){
            console.log("服务器异常");
          });
      },
      getArticleContent(){
        let that = this;
        fetch(that.articleMsg.fileUrl,{
          method:'GET',
          mode:'cors'
        }).then(function (response) {
            // console.log(response);
            let text = response.text();
            // console.log("text: "+text)
            return text;
          }).then(function (data) {
            // console.log(data)
            that.htmlMD = data;
            that.loadingOK = true;
          })
      }
    },
    created() {
      //因为加载云上文章太慢所以加个遮罩层至加载完成
      let loadingInstance = Loading.service({
        fullscreen:true,
        lock:true,
        text:"加载一下马上就好😊",
        spinner:'el-icon-loading',
        background:'rgba(0, 0, 0, 0.8)'
      });
      //接收到传来的文章详情信息
      this.articleID = this.$route.query.id;
      //接收到传来的返回路径
      this.backPage = this.$route.query.backpage;
      console.log(this.backPage);

      let that = this;
      //获取文章详情
      this.$http.get('/api/article/detail',{
        params:{
          // id:that.articleMsg.id,
          id:that.articleID,
        }
      }).then(function(res){
        if (res.result.code === 1) {
          that.articleMsg = res.data;
          that.getArticleContent();
          that.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
            loadingInstance.close();
          });
        }
      }).catch(function(){
        console.log("服务器异常");
      });

      if (localStorage.getItem("userId")){
        this.userId = localStorage.getItem("userId")
      }
    },
  }
</script>

<style scoped>
  #articleDetails{
    margin: 62px 15% 0 15%;
    /*background-color: #fff;*/
    background-color: #ffffffa8;
    min-height: calc(100vh - 62px);
    position:relative;
  }
  .el-icon-arrow-left{
    cursor: pointer;
    margin: 5px 10px 0;
  }
  .delButton{
    float: right;
    margin: 5px 15px 0;
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
    padding:5px 0;
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
