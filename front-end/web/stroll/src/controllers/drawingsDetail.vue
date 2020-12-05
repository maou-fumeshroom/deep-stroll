<template>
  <div id="drawingsDetail" v-if="loadingOK">

    <div id="message">
      <i class="el-icon-arrow-left" style="margin:10px 0 0 10px;" @click="back"/>
      <h2 class="title">{{drawingsMsg.title}}</h2>
      <p class="introduction">{{drawingsMsg.introduction}}</p>
      <img :src = "drawingsMsg.author.avatar" class="avatar"/>
      <span class="nickname">{{drawingsMsg.author.nickname}}</span>
      <span class="time">{{drawingsMsg.dateTime}}</span>
      <hr/>

      <drawing-array :drawings-src="drawingsMsg.images"></drawing-array>

    </div>

  </div>
</template>

<script>
  import drawingArray from '../components/drawingArray'
  export default {
    data() {
      return{
        drawingsId: this.$route.query.drawingsId,
        drawingsMsg:{},
        loadingOK:false
      }
    },
    components: {
      drawingArray
    },
    methods:{
      back(){
        this.$router.go(-1)
      }
    },
    created() {
      let that = this;
      //获取手绘详情
      this.$http.get('/api/drawing/detail',{
        params:{
          id:this.drawingsId,
        }
      }).then(function(res){
        that.drawingsMsg = res.data;
        console.log(res.data)
        that.loadingOK = true;
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
    padding-top:10px;
    height: 100%;
    position:relative;
  }
  .el-icon-arrow-left{
    cursor: pointer;
  }
  /*文章详情页面上半部分，文章的信息*/
  #message{
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
    border-radius: 4px;
    overflow: hidden;
    /*background: #ffffff;*/
    background-color: #ffffffa8;
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
