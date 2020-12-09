<template>
  <div id="drawingsDetail" v-if="loadingOK">

    <div id="message">
      <i class="el-icon-arrow-left" style="margin:10px 0 0 10px;" @click="back"/>
      <i v-if="userId == drawingsMsg.author.id" class="el-icon-delete delButton" @click="deleteDraw"/>
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
  import {client} from "../utils/alioss";
  import { Loading } from 'element-ui';

  export default {
    data() {
      return{
        drawingsId: this.$route.query.drawingsId,
        drawingsMsg:{},
        loadingOK:false,
        userId:0,
      }
    },
    components: {
      drawingArray
    },
    methods:{
      back(){
        this.$router.go(-1)
      },
      deleteDraw(){
        let that = this;
        //删除手绘
        console.log("idididi: "+ this.drawingsId)
        this.$http.post('/api/person/works/delete',{
          id:that.drawingsId,
          type:1,
        },{emulateJSON: true})
          .then(function(res){
            if(res.result.code === 1){
              that.$notify({
                title: '成功',
                message: '删除手绘成功！',
                type: 'success',
                duration:1000
              });
            }
          }).catch(err =>{});
        //删除所有手绘链接
        let len = this.drawingsMsg.images.length;
        for(let i=0;i<len;i++){
          let temp = this.drawingsMsg.images[i].split("/");
          console.log(temp[3])
          let urlName = temp[3];
          client().delete(urlName).then(
            result=>{
              console.log("2"+result)
            }
          )
        }
        this.$router.go(-1);
      },
    },
    created() {
      //加个遮罩层至加载完成
      let loadingInstance = Loading.service({
        fullscreen:true,
        lock:true,
        text:"加载一下马上就好😊",
        spinner:'el-icon-loading',
        background:'rgba(0, 0, 0, 0.8)'
      });

      let that = this;
      if (localStorage.getItem("userId")){
        this.userId = localStorage.getItem("userId")
      }
      //获取手绘详情
      this.$http.get('/api/drawing/detail',{
        params:{
          id:this.drawingsId,
        }
      }).then(function(res){
        that.drawingsMsg = res.data;
        console.log(res.data)
        that.loadingOK = true;
        that.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
          loadingInstance.close();
        });
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
    padding:10px 0;
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
  .delButton{
    float: right;
    margin: 10px 15px 0;
    cursor: pointer;
  }
</style>
