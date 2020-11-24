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
        articleList:[
          {
            id:1,
            imgSrc:require("../assets/logo.png"),
            title:"Python之父，现在成为微软打工人",
            nickname:"小明",
            avatarSrc:require("../assets/logo.png"),
            releaseTime:"11-13",
            likes:"232",
            commentsNum:"56",
            introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
            content:"Python之父加入微软干什么？\n" +
              "几个小时前，Guido Van Rossum本人发推宣布了这个消息，他说：「退休生活太无聊了，我决定加入微软的开发者部门！」"
          },
          {
            id:2,
            imgSrc:require("../assets/logo.png"),
            title:"如何看待马云 4 月 11 日在内外直播中将 996 称为「修来的福报」？",
            nickname:"小红",
            avatarSrc:require("../assets/logo.png"),
            releaseTime:"11-10",
            likes:"60",
            commentsNum:"12",
            introduction:"4月11日内外直播中，在被问及如何看待996.icu事件时，马云主要提出了一下三个观点： 马云谈996 1. 能996是你们的幸福 ",
            content:"1. 能996是你们的幸福\n" +
              "\n" +
              "2. 我本人从很久开始就不止996，简直007\n" +
              "\n" +
              "3. 不要扯法律什么的，法律也没规定公司要给你们提供这么好的工作环境这么高的工资，法律也没规定公司要给你们股票，但是我不也给你们了吗（阿里巴巴对外发布的演讲稿中未提及该条，真实性存疑）"
          },
          {
            id:3,
            imgSrc:require("../assets/logo.png"),
            title:"如何看待 Python 之父 Guido 加盟微软？？？",
            nickname:"李华",
            avatarSrc:require("../assets/logo.png"),
            releaseTime:"11-9",
            likes:"3",
            commentsNum:"1",
            introduction:"Python 之父 Guido van Rossum 在 Python 邮件组里发邮称，他将退出 Python 核心决策层，而转居幕后。",
            content: "Python如今的地位，尤其是在人工智能领域的地位，完全不需要任何赘述。\n" +
              "\n" +
              "世界上有好几百种编程语言，流行的也就20多种。但能做到应用广泛、优雅简洁、“老少皆宜”的，只有Python。\n" +
              "\n" +
              "Guido大神的职业生涯，也基本都跟Python紧密相连。"
          },
          {
            id:4,
            imgSrc:require("../assets/logo.png"),
            title:"Python之父，现在成为微软打工人",
            nickname:"小明",
            avatarSrc:require("../assets/logo.png"),
            releaseTime:"11-13",
            likes:"232",
            commentsNum:"56",
            introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
            content:"Python之父加入微软干什么？\n" +
              "几个小时前，Guido Van Rossum本人发推宣布了这个消息，他说：「退休生活太无聊了，我决定加入微软的开发者部门！」"
          },
          {
            id:5,
            imgSrc:require("../assets/logo.png"),
            title:"如何看待马云 4 月 11 日在内外直播中将 996 称为「修来的福报」？",
            nickname:"小红",
            avatarSrc:require("../assets/logo.png"),
            releaseTime:"11-10",
            likes:"60",
            commentsNum:"12",
            introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
            content:"Python之父加入微软干什么？\n" +
              "几个小时前，Guido Van Rossum本人发推宣布了这个消息，他说：「退休生活太无聊了，我决定加入微软的开发者部门！」"
          },
          {
            id:6,
            imgSrc:require("../assets/logo.png"),
            title:"如何看待 Python 之父 Guido 加盟微软？",
            nickname:"李华",
            avatarSrc:require("../assets/logo.png"),
            releaseTime:"11-9",
            likes:"3",
            commentsNum:"1",
            introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
            content:"Python之父加入微软干什么？\n" +
              "几个小时前，Guido Van Rossum本人发推宣布了这个消息，他说：「退休生活太无聊了，我决定加入微软的开发者部门！」"
          },
        ],
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

    }
  }
</script>

<style scoped>

  #article{
    height: 100%;
  }

  /*左侧分类选择菜单*/
  #articleTags{
    width: 15%;
    position: fixed;
  }

  /deep/ .el-col-12 {
    width: 100%;
  }

  /*右侧文章列表*/
  #articleContainer{
    /*background: rgb(246 246 246);*/
    width: 85%;
    height: 100%;
    float: right;
    list-style-type: none;
    margin-right: 15px;
  }

</style>
