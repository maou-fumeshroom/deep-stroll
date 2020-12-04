<template>
  <div id = "article">

    <!--    左侧分类选择菜单-->
    <div id="articleTags">
      <el-col :span="12">
        <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
          <el-submenu index="1">
            <template slot="title">
              <span>导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组1</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="2">
            <span slot="title">导航二</span>
          </el-menu-item>
          <el-menu-item index="3">
            <span slot="title">导航三</span>
          </el-menu-item>
          <el-menu-item index="4">
            <span slot="title">导航四</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </div>

    <!--    右侧文章列表-->
    <div id="articleContainer">
      <article-box :articleList="articleList" :page="page"/>
<!--      <article-box :articleList="testList" :page="page"/>-->
    </div>

    <div class="pagination">
      <el-pagination layout="prev, pager, next" :page-count=totalPage @current-change="currentChange" :hide-on-single-page="totalPage === 1"/>
    </div>

  </div>
</template>

<script>
  import articleBox from "../components/articleBox";
  export default {
    name: 'Article',
    components:{
      articleBox,
    },
    data () {
      return {
        page:"article",
        // articleList:[
        //   {
        //     id:1,
        //     cover:require("../assets/logo.png"),
        //     title:"Python之父，现在成为微软打工人",
        //     nickname:"小明",
        //     avatar:require("../assets/logo.png"),
        //     dateTime:"2020-11-13",
        //     likeNum:232,
        //     comment:56,
        //     introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
        //     status:0,
        //     classifyName:"互联网",
        //     // mdSrc:"http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481592000.md"
        //   },
        //   {
        //     id:2,
        //     cover:require("../assets/logo.png"),
        //     title:"如何看待马云 4 月 11 日在内外直播中将 996 称为「修来的福报」？",
        //     nickname:"小红",
        //     avatar:require("../assets/logo.png"),
        //     dateTime:"2020-11-10",
        //     likeNum:60,
        //     comment:12,
        //     introduction:"4月11日内外直播中，在被问及如何看待996.icu事件时，马云主要提出了一下三个观点： 马云谈996 1. 能996是你们的幸福 ",
        //     status:0,
        //     classifyName:"互联网",
        //     // mdSrc:"http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481596000.md"
        //   },
        //   {
        //     id:3,
        //     cover:require("../assets/logo.png"),
        //     title:"如何看待 Python 之父 Guido 加盟微软？？？",
        //     nickname:"李华",
        //     avatar:require("../assets/logo.png"),
        //     dateTime:"2020-11-9",
        //     likeNum:3,
        //     comment:1,
        //     introduction:"Python 之父 Guido van Rossum 在 Python 邮件组里发邮称，他将退出 Python 核心决策层，而转居幕后。",
        //     status:0,
        //     classifyName:"互联网",
        //     // mdSrc: "http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481592000.md",
        //   },
        //   {
        //     id:4,
        //     cover:require("../assets/logo.png"),
        //     title:"Python之父，现在成为微软打工人",
        //     nickname:"小明",
        //     avatar:require("../assets/logo.png"),
        //     dateTime:"2020-11-13",
        //     likeNum:232,
        //     comment:56,
        //     introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
        //     status:0,
        //     classifyName:"互联网",
        //     // mdSrc:"http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481596000.md"
        //   },
        //   {
        //     id:5,
        //     cover:require("../assets/logo.png"),
        //     title:"如何看待马云 4 月 11 日在内外直播中将 996 称为「修来的福报」？",
        //     nickname:"小红",
        //     avatar:require("../assets/logo.png"),
        //     dateTime:"2020-11-10",
        //     likeNum:60,
        //     comment:12,
        //     introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
        //     status:0,
        //     classifyName:"互联网",
        //     // mdSrc:"http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481592000.md",
        //   },
        //   {
        //     id:6,
        //     cover:require("../assets/logo.png"),
        //     title:"如何看待 Python 之父 Guido 加盟微软？",
        //     nickname:"李华",
        //     avatar:require("../assets/logo.png"),
        //     dateTime:"2020-11-9",
        //     likeNum:3,
        //     comment:1,
        //     introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
        //     status:0,
        //     classifyName:"互联网",
        //     // mdSrc:"http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481596000.md",
        //   }
        // ],
        articleList:[],
        totalPage:2,
        currentPage:1,
        // testList:[],
        classifys:{}
      }
    },
    methods: {
      //展开指定的分类
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      //收起指定的分类
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      //当前页
      currentChange(val){
        if(this.currentPage+1 < this.totalPage){
          this.currentPage += 1;
          this.getArticles();
        }

        // let curNum = (val-1)*10;
        // let len = this.articleList.length;
        // let temp = len - curNum;
        // let current = 0;
        // if(temp >= 10){
        //   current = curNum + 10;
        //   for(let i=curNum,j=0;i<current;i++,j++){
        //     let obj = this.articleList[i];
        //     this.$set(this.testList,j,obj)
        //   }
        // }
        // else{
        //   current = curNum + temp;
        //   for(let i=curNum,j=0;i<current;i++,j++){
        //     let obj = this.articleList[i];
        //     this.$set(this.testList,j,obj)
        //   }
        //   // for(let i=len-current,j=temp;i<len;i++,j++){
        //   //   let obj = {};
        //   //   this.$set(this.testList,j,obj)
        //   // }
        // }
        // console.log("2 test: "+this.testList[0].title);
      },
      getArticles(){
        let that = this;
        //获取文章列表
        this.$http.get('/api/article/search',{
          params:{
            key:"",
            classify:"",
            page:that.currentPage,
            status:0,
          }
        }).then(function(res){
          // console.log("！！： "+res);
            // console.log("！！： "+JSON.stringify(res));
            that.articleList = res.data.articles;
        that.totalPage = res.data.totalPage;
          // console.log("！！： "+that.articleList[0].title);
          console.log("！！： "+that.articleList.length);
          }).catch(function(){
          console.log("服务器异常");
        });
      }
    },
    created() {
      let that = this;
      //获取文章分类
      this.$http.get('/api/article/classify')
        .then(function(res){
          // console.log("！！： "+JSON.stringify(res));
          that.msg = res.data;
        }).catch(function(){
        console.log("服务器异常");
      });

      that.getArticles();
    },
    mounted() {
      // 监听滑动条
      window.addEventListener('scroll',()=>{
        // console.log("65555555555555555")
        // console.log("len = " + this.len);
        //变量scrollTop是滚动条滚动时，距离顶部的距离
        var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
        //变量windowHeight是可视区的高度
        var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
        //变量scrollHeight是滚动条的总高度
        var scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;
        //滚动条到底部的条件
        //这里加1，是因为不知道为什么滑动条到底了，但是scrollTop + windowHeight总是比scrollHeight少0.2，所以加1，判断滑动条是否到底
        if(scrollTop + windowHeight + 1 > scrollHeight){
          // console.log("距顶部"+scrollTop+"可视区高度"+windowHeight+"滚动条总高度"+scrollHeight);
          // console.log("!!!!!!!!  len = " + this.len);
          // 滑动条到底更新len，给len加8，会多显示8张照片
          // this.len = this.len + 8;
          // $(".pagination").css('top', scrollHeight-30);
          // console.log("OKOKOKOKOK!!!!!!!!");
        }
        // console.log("=========================");
      })
    },
    watch:{
      testList: {
        handler(newValue, oldValue) {
          console.log("baba1: "+newValue)
          for (let i = 0; i < newValue.length; i++) {
            if (oldValue[i] !== newValue[i]) {
              console.log("baba2: "+newValue)
            }
          }
        },
      },
    }
  }
</script>

<style scoped>
  #article{
    height: 98.9%;
    width: 76%;
    margin: 0 12% 0 12%;
    background-color: #fff;
    position: absolute;
  }
  /*左侧分类选择菜单*/
  #articleTags{
    width: 13%;
    position: fixed;
    padding-top: 5%;
  }
  /deep/ .el-col-12 {
    width: 100%;
  }
  /*右侧文章列表*/
  #articleContainer{
    padding-top: 5%;
    width: 82%;
    height: 85%;
    float: right;
    list-style-type: none;
    margin-right: 15px;
    overflow: auto;
  }
  .pagination{
    position: absolute;
    /* top: 800px; */
    left: 52%;
    bottom: 10px;
  }
</style>
