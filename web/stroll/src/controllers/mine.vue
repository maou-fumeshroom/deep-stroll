<template>
  <div id = "myPage">
<!--    页面左半部分，个人信息-->
    <div id="myInformation">
      <div id="inforBox">
        <img :src=msg.avatarSrc class="myAvatar"/>
        <p class="myName">{{msg.nickname}}</p>
        <div class="allNum">
          <span>文章数</span>
          <span class="vertical">{{msg.worksNum}}</span>
          <span>手绘数</span>
          <span class="vertical">{{msg.paintNum}}</span>
          <span>点赞数</span>
          <span>{{msg.likes}}</span>
        </div>
        <p class="myIntroduce">{{msg.introduce}}</p>
        <el-button class="edit" @click="$router.push('/editInformation')">编辑</el-button>
      </div>
    </div>

<!--    页面右半部分，作品、收藏、消息-->
    <div id="myContainer">

<!--      导航栏-->
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-submenu index="1">
          <template slot="title">我的作品</template>
          <el-menu-item index="1-1">文章</el-menu-item>
          <el-menu-item index="1-2">手绘</el-menu-item>
        </el-submenu>

        <el-submenu index="2">
          <template slot="title">我的收藏</template>
          <el-menu-item index="2-1">文章</el-menu-item>
          <el-menu-item index="2-2">手绘</el-menu-item>
<!--          <el-submenu index="2-3">-->
<!--            <template slot="title">选项3</template>-->
<!--            <el-menu-item index="2-3-1">选项1</el-menu-item>-->
<!--          </el-submenu>-->
        </el-submenu>
        <el-menu-item index="3">消息</el-menu-item>
      </el-menu>

<!--      子组件，显示我的作品-文章-->
      <!--我的作品-->
      <article-box v-if="tagPath === '1-1'" :articleList="articleList" :page="page"/>
      <p v-if="tagPath  === '1-2'">我的作品手绘页，希望龙哥写了手绘组件</p>

      <!--我的收藏-->
      <article-box v-if="tagPath === '2-1'" :articleList="articleList" :page="page"/>
      <p v-if="tagPath === '2-2'">我的收藏手绘页，希望龙哥写了手绘组件</p>

      <!--消息-->
      <message-box v-if="tag === '3'" :messageList="messageList"/>

    </div>

    <img src="../assets/logo.png" id="addButton" @click="$router.push('/release')"/>
  </div>
</template>

<script>
  import articleBox from "../components/articleBox";
  import messageBox from "../components/messageBox";
    export default {
      name: 'mine',
      components:{
        articleBox,
        messageBox
      },
      data () {
        return {
          page:"mine",
          msg:{
            id:"0",
            nickname:"王明明",
            avatarSrc:require("../assets/logo.png"),
            worksNum:"26",
            paintNum:"5",
            likes:"31",
            introduce:"好好学习，天天向上"
          },
          activeIndex: '1',
          articleList:[
            {
              id:0,
              imgSrc:require("../assets/logo.png"),
              title:"Python之父，现在成为微软打工人!",
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
              id:1,
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
          ],
          len: 8,
          tag: 1,
          tagPath: "1-1",
          messageList:[
            {
              id:0,
              imgSrc:require("../assets/logo.png"),
              title:"Python之父，现在成为微软打工人!",
              nickname:"小明",
              avatarSrc:require("../assets/logo.png"),
              releaseTime:"11-13",
              comment:"这是一个好倡议",
              mode:"1-1"
            },
            {
              id:1,
              imgSrc:require("../assets/logo.png"),
              title:"如何看待马云 4 月 11 日在内外直播中将 996 称为「修来的福报」？",
              nickname:"小红",
              avatarSrc:require("../assets/logo.png"),
              releaseTime:"11-10",
              comment:"大大真棒！！！",
              mode:"2-2"
            },
          ],
        }
      },
      methods: {
        handleSelect(key, keyPath) {
          this.tag = keyPath[0] ;
          this.tagPath = keyPath[1] ;
          // console.log(key, keyPath);
          // console.log(this.tag);
        },
      }
    }
</script>

<style scoped>
  #myPage{
    height: 100%;
  }

  /*页面左半部分，个人信息*/
  #myInformation{
    float: left;
    height: 100%;
    width: 30%;
    text-align: center;
    /*border-right: 1px solid #cccccc;*/
  }

  #inforBox{
    display: inline-block;
    text-align: center;
    margin-top: 10%;
  }

  .myAvatar{
    border-radius: 100%;
    border: 1px solid #000;
    width: 280px;
    height: 280px;
  }

  .myName{
    font-size: 1.5em;
    margin-top: 10px;
    font-family: cursive;
  }

  .vertical{
    border-right: 1.5px solid #cccccc;
    padding-right: 5px;
    margin-right: 5px;
  }

  .myIntroduce{
    margin-top: 10px;
  }

  .edit{
    width: 280px;
    cursor: pointer;
    background: #f8f9fa;
  }


  /*页面右半部分*/
  #myContainer{
    float: right;
    width: 64%;
    margin-right: 5%;
  }

  #addButton{
    cursor: pointer;
    border: 1px solid #cccccc;
    border-radius: 100%;
    width: 70px;
    height: 70px;
    position: fixed;
    bottom: 10%;
    right: 5%;
  }

</style>

