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
        <div v-if="tagPath === '1-1'">
          <article-box :articleList="articleList" :page="page"/>
          <div class="pagination">
            <el-pagination layout="prev, pager, next" :total=totalLenth  @current-change="currentChange" />
          </div>
        </div>
        <div v-if="tagPath  === '1-2'" class="waterfall_box">
          <vue-waterfall-easy :imgsArr="imgsArr" :isBottom="isbottom" :page="page" @scrollReachBottom="fetchImgsData" @clickItem="gotoDetail" :img-width="193" :width="640">
            <template slot-scope="props">
              <img :src="msg.avatar" class="avatarImg"/>
              <div class="titleP">{{props.value.title}}</div>
              <div class="time">{{props.value.dateTime}}</div>
            </template>
          </vue-waterfall-easy>
        </div>

        <!--我的收藏-->
        <article-box v-if="tagPath === '2-1'" :articleList="articleList" :page="page"/>
        <div v-if="tagPath  === '2-2'" class="waterfall_box">
          <vue-waterfall-easy :imgsArr="imgsArr" :isBottom="isbottom" :page="page" @scrollReachBottom="fetchImgsData" @clickItem="gotoDetail" :img-width="193" :width="640">
          <template slot-scope="props">
            <img :src="msg.avatar" class="avatarImg"/>
            <div class="titleP">{{props.value.title}}</div>
            <div class="time">{{props.value.dateTime}}</div>
          </template>
          </vue-waterfall-easy>
        </div>

        <!--消息-->
        <message-box v-if="tag === '3'" :messageList="messageList"/>
      </div>


    </div>

    <img src="../assets/icon/addIcon.png" id="addButton" @click="$router.push('/release')"/>
  </div>
</template>

<script>
  import articleBox from "../components/articleBox";
  import messageBox from "../components/messageBox";
  import vueWaterfallEasy from '../components/vue-waterfall-easy'

  import { Loading } from 'element-ui';
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
        msg:{},
        activeIndex: '1',
        articleList:[],
        totalLenth:1,
        currentPage:1,
        tag: 1,
        tagPath: "1-1",
        messageList:[
          {
            id:0,
            imgSrc:require("../assets/3.jpg"),
            title:"Python之父，现在成为微软打工人!",
            nickname:"小明",
            avatarSrc:require("../assets/test/avatar1.jpg"),
            releaseTime:"11-13",
            comment:"这是一个好倡议",
            mode:"1-1"
          },
          {
            id:1,
            imgSrc:require("../assets/17.jpg"),
            title:"如何看待马云 4 月 11 日在内外直播中将 996 称为「修来的福报」？",
            nickname:"小红",
            avatarSrc:require("../assets/test/avatar2.jpg"),
            releaseTime:"11-10",
            comment:"大大真棒！！！",
            mode:"2-2"
          },
        ],
        imgsArr: [],         //存放所有已加载图片的数组（即当前页面会加载的所有图片）
        fetchImgsArr: [],    //存放每次滚动时下一批要加载的图片的数组
        isbottom:false,
        pageNum:1,
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        this.tag = keyPath[0] ;
        this.tagPath = keyPath[1] ;
        if(this.tagPath  === '1-2'){
          this.getlist()
        }
      },
      getlist(){
        let _this = this
        this.pageNum = 1
        this.imgsArr = []
        this.$http.get('/api/person/drawing',{
          params:{
            key:"",
            classify:"",
            page:1,
          }
        }).then(function(res){
          if (res.result.code === 1) {
            _this.imgsArr = res.data.drawing
          }
        }).catch(function(){
          console.log("服务器异常");
        });
      },
      fetchImgsData () {    //获取新的图片数据的方法，用于页面滚动满足条件时调用
        let _this = this
        this.pageNum ++
        this.$http.get('/api/person/drawing',{
          params:{
            key:"",
            classify:"",
            page:this.pageNum,
          }
        }).then(function(res){
          if (res.result.code === 1) {
            _this.fetchImgsArr = res.data.drawing
            if (_this.fetchImgsArr.length === 0) {
              _this.isbottom = false
            }
            _this.imgsArr = _this.imgsArr.concat(_this.fetchImgsArr)   //数组拼接，把下一批要加载的图片放入所有图片的数组中
          }
        }).catch(function(){
          console.log("服务器异常");
        });
      },
      gotoDetail(val){
        let drawingsId = this.imgsArr[val].id
        this.$router.push({
          path:'/drawingsDetail',
          query:{
            drawingsId:drawingsId
          }
        })
      },
      getArticleList(){
        //加个遮罩层至加载完成
        let loadingInstance = Loading.service({
          fullscreen:true,
          lock:true,
          text:"加载一下马上就好😊",
          spinner:'el-icon-loading',
          background:'rgba(0, 0, 0, 0.8)'
        });

        let that = this;
        //获取我的文章列表
        this.$http.get('/api/person/article',{
          params:{
            key:"",
            classify:"",
            page:that.currentPage,
          }
        }).then(function(res){
          if (res.result.code === 1) {
            that.articleList = res.data.articles;
            that.totalLenth = res.data.totalPage;
            that.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
              loadingInstance.close();
            });
          }
        }).catch(function(){
          console.log("服务器异常");
        });
      },
      getUserMsg(){
        let that = this;
        //得到个人信息
        this.$http.get('/api/person/info')
          .then(function(res){
            if (res.result.code === 1) {
              that.msg = res.data;
            }
          }).catch(function(){
          console.log("服务器异常");
        });
      },
      //当前页
      currentChange(val){
        this.currentPage = val;
        this.getArticleList();
      },
    },
    created() {
      let that = this;
      that.getUserMsg();
      that.getArticleList();
    },
    mounted() {
      //每次路由跳转都更新文章列表
      this.getUserMsg();
      this.getArticleList();
      this.getlist();
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
    min-height:calc(100% - 62px);
    min-width: 76%;
    margin: 62px 12% 0 12%;
    /*background-color: #fff;*/
    background-color: #ffffff87;
    position: absolute;
  }
  /*页面左半部分，个人信息*/
  #myInformation{
    float: left;
    /*height: 100%;*/
    width: 33%;
    text-align: center;
    position:relative;
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
    width: 250px;
    height: 250px;
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
    width: 250px;
    cursor: pointer;
    /*background: #f8f9fa;*/
    background: #f8f9fa91;
  }
  /*页面右半部分*/
  #myContainer{
    float: right;
    width: 67%;
    /*margin-right: 5%;*/
  }

  /deep/ .el-menu{
    background-color: rgba(255, 255, 255, 0);
  }

  #Scroll{
    height: 627px;
    /*overflow: auto;*/
    overflow-y: auto;
    overflow-x: hidden;
  }

  #addButton{
    cursor: pointer;
    border: 1px solid #cccccc96;
    border-radius: 100%;
    width: 70px;
    height: 70px;
    position: fixed;
    bottom: 5%;
    right: 15%;
    background: #ffffff8c;
  }

  #addButton:hover{
    border: 1px solid #00a0e9;
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
  .waterfall_box{
    width:98%;
  }
  .pagination{
    margin-left: 40%;
    margin-top: -10px;
  }
  /deep/ .el-pager li{
    background: rgba(255, 255, 255, 0);
  }
  /deep/ .el-pagination button:disabled{
    background: rgba(255, 255, 255, 0);
  }
  /deep/ .el-pagination .btn-next, .el-pagination .btn-prev{
    background: rgba(255, 255, 255, 0);
  }
  /deep/ .el-pagination .btn-prev{
    background: rgba(255, 255, 255, 0);
  }
  #Scroll::-webkit-scrollbar {/*滚动条整体样式*/
    width: 4px;     /*高宽分别对应横竖滚动条的尺寸*/
    height: 4px;
    scrollbar-arrow-color:red;
  }
  #Scroll::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
    border-radius: 5px;
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    background: rgba(0,0,0,0.2);
    scrollbar-arrow-color:red;
  }
  #Scroll::-webkit-scrollbar-track {/*滚动条里面轨道*/
    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
    border-radius: 0;
    background: rgba(0,0,0,0.1);
  }
</style>
