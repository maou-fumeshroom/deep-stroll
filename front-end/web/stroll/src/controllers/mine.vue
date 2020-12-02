<template>
  <div id = "myPage">
    <!--    页面左半部分，个人信息-->
    <div id="myInformation">
      <div id="inforBox">
        <img :src=msg.avatar class="myAvatar"/>
        <p class="myName">{{msg.nickname}}</p>
        <div class="allNum">
          <span>文章数</span>
          <span class="vertical">{{msg.article}}</span>
          <span>手绘数</span>
          <span class="vertical">{{msg.drawing}}</span>
          <span>点赞数</span>
          <span>{{msg.likeNum}}</span>
        </div>
        <p class="myIntroduce">{{msg.sign}}</p>
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
        </el-submenu>
        <el-menu-item index="3">消息</el-menu-item>
      </el-menu>

      <!--子组件，显示我的作品-文章-->
      <!--我的作品-->
      <div id="Scroll" >
        <article-box v-if="tagPath === '1-1'" :articleList="articleList" :page="page"/>
        <vue-waterfall-easy v-if="tagPath  === '1-2'" style="width: 98%;" :imgsArr="imgsArr" :isBottom="isbottom" :page="page" @scrollReachBottom="fetchImgsData" @clickItem="gotoDetail">
          <template slot-scope="props">
            <img :src="props.value.avatar" class="avatarImg"/>
            <div class="titleP">{{props.value.title}}</div>
            <div class="time">{{props.value.dateTime}}</div>
          </template>
        </vue-waterfall-easy>

        <!--我的收藏-->
        <article-box v-if="tagPath === '2-1'" :articleList="articleList" :page="page"/>
        <vue-waterfall-easy v-if="tagPath  === '2-2'" style="width: 98%;" :imgsArr="imgsArr" :isBottom="isbottom" :page="page" @scrollReachBottom="fetchImgsData" @clickItem="gotoDetail">
          <template slot-scope="props">
            <img :src="props.value.avatar" class="avatarImg"/>
            <div class="titleP">{{props.value.title}}</div>
            <div class="time">{{props.value.dateTime}}</div>
          </template>
        </vue-waterfall-easy>

        <!--消息-->
        <message-box v-if="tag === '3'" :messageList="messageList"/>
      </div>


    </div>

    <img src="../assets/logo.png" id="addButton" @click="$router.push('/release')"/>
  </div>
</template>

<script>
  import articleBox from "../components/articleBox";
  import messageBox from "../components/messageBox";
  import vueWaterfallEasy from '../components/vue-waterfall-easy'
  export default {
    name: 'mine',
    components:{
      articleBox,
      messageBox,
      vueWaterfallEasy
    },
    data () {
      return {
        page:"mine",
        msg:{
          // id:"0",
          nickname:"王明明",
          avatar:require("../assets/logo.png"),
          article:26,
          drawing:5,
          likeNum:31,
          sign:"好好学习，天天向上"
        },
        activeIndex: '1',
        articleList:[
          {
            id:1,
            cover:require("../assets/logo.png"),
            title:"Python之父，现在成为微软打工人",
            nickname:"小明",
            avatar:require("../assets/logo.png"),
            dateTime:"2020-11-13",
            likeNum:232,
            comment:56,
            introduction:"当程序员开始诉控996加班时，中国多款浏览器竟然匪夷所思地禁止访问",
            status:0,
            classifyName:"互联网",
            // mdSrc:"http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481592000.md"
          },
          {
            id:2,
            cover:require("../assets/logo.png"),
            title:"如何看待马云 4 月 11 日在内外直播中将 996 称为「修来的福报」？",
            nickname:"小红",
            avatar:require("../assets/logo.png"),
            dateTime:"2020-11-10",
            likeNum:60,
            comment:12,
            introduction:"4月11日内外直播中，在被问及如何看待996.icu事件时，马云主要提出了一下三个观点： 马云谈996 1. 能996是你们的幸福 ",
            status:0,
            classifyName:"互联网",
            // mdSrc:"http://bai111111.oss-cn-beijing.aliyuncs.com/article1606481596000.md"
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
        imgsArr: [],         //存放所有已加载图片的数组（即当前页面会加载的所有图片）
        fetchImgsArr: [],    //存放每次滚动时下一批要加载的图片的数组
        isbottom:false,

      }
    },
    methods: {
      handleSelect(key, keyPath) {
        this.tag = keyPath[0] ;
        this.tagPath = keyPath[1] ;
        if(this.tagPath  === '1-2'){
          this.imgsArr = this.initImgsArr(0, 10)       //初始化第一次（即页面加载完毕时）要加载的图片数据
          this.fetchImgsArr = this.initImgsArr(10, 18) // 模拟每次请求的下一批新的图片的数据数据
        }
      },
      initImgsArr (n, m) {   //初始化图片数组的方法，把要加载的图片装入
        var arr = []
        for (var i = n; i < m; i++) {
          arr.push({
            cover: require(`../assets/${i + 1}.jpg`),
            link: '',
            info: '一些图片描述文字',
            id: i+'0'+i,
            title: "一些图片描述文字一些图片描述文字一些图片描述文字",
            avatar: require(`../assets/logo.png`),
            dateTime: "2000/08/20 10:10:10",
            likeNum: 819358248421784,
            comment: -6653595630801168,
          })
        }
        return arr
      },
      fetchImgsData () {    //获取新的图片数据的方法，用于页面滚动满足条件时调用
        if (this.fetchImgsArr.length === 0){
          this.isbottom=false;
        }
        this.imgsArr = this.imgsArr.concat(this.fetchImgsArr)   //数组拼接，把下一批要加载的图片放入所有图片的数组中
      },
      gotoDetail(val){
        let drawingsId = this.imgsArr[val].id
        this.$router.push({
          path:'/drawingsDetail',
          query:{
            drawingsId:drawingsId
          }
        })
      }
    },
    mounted() {
      $("#Scroll").scroll(function(event){
        console.log("!!!!!!Scroll")
        var scrollTop = document.getElementById("Scroll").scrollTop;
        //可视区高度
        var divHeight = document.getElementById("Scroll").clientHeight;
        //滚动条总高度
        var scrollHeight = document.getElementById("Scroll").scrollHeight;
        // console.log("scrollTop: "+scrollTop+" divHeight: "+divHeight+" scrollHeight: "+scrollHeight)
        if(scrollTop+divHeight >= scrollHeight && scrollTop+divHeight <= scrollHeight){
          //加载新图片，并加锁等待图片加载完成
          console.log('到底啦！！！')
        }
      });
    },
  }
</script>

<style scoped>
  #myPage{
    height: 100%;
    width: 76%;
    margin: 0 12% 0 12%;
    background-color: #fff;
    position: absolute;
  }
  /*页面左半部分，个人信息*/
  #myInformation{
    float: left;
    height: 100%;
    width: 30%;
    text-align: center;
    position:relative;
    padding-top: 8%;
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
    width: 67%;
    /*margin-right: 5%;*/
    padding-top: 5%;
  }

  #Scroll{
    height: 635px;
    overflow: auto;
  }

  #addButton{
    cursor: pointer;
    border: 1px solid #cccccc;
    border-radius: 100%;
    width: 70px;
    height: 70px;
    position: fixed;
    bottom: 5%;
    right: 15%;
  }

  /deep/ #articleUl{
    padding-left: 5px;
  }

  /*手绘部分*/
  .avatarImg{
    margin:5px 5px 5px 15px;
    width:30px;
    height:30px;
    border-radius: 50%;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
    float:left;
  }
  .titleP{
    font-size:0.8em;
    line-height:30px;
    margin:5px 0 5px 5px;
    text-overflow:ellipsis;
    white-space:nowrap;
    overflow:hidden;
    width:calc(100% - 65px);
  }
  .time{
    float:right;
    margin:0 15px 5px 0;
    font-size:0.5em;
    color: #bfbfbf;
  }

</style>
