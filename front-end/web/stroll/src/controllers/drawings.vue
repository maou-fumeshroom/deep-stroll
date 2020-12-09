<template>
  <div class="waterfall_box">
    <vue-waterfall-easy :imgsArr="imgsArr" :isBottom="isbottom" @scrollReachBottom="fetchImgsData" @clickItem="gotoDetail">
      <template slot-scope="props">
        <img :src="props.value.avatar" class="avatarImg"/>
        <div class="titleP">{{props.value.title}}</div>
        <div class="time">{{props.value.dateTime}}</div>
      </template>
    </vue-waterfall-easy>
  </div>
</template>

<script>
  import vueWaterfallEasy from '../components/vue-waterfall-easy'
  import bus from '../utils/bus'
  export default {
    data () {
      return {
        imgsArr: [],         //存放所有已加载图片的数组（即当前页面会加载的所有图片）
        fetchImgsArr: [],    //存放每次滚动时下一批要加载的图片的数组
        isbottom:false,
        page:1,
        key:''
      }
    },
    components: {
      vueWaterfallEasy
    },
    created () {
      let that = this;
      //获取手绘分类
      this.$http.get('/api/drawing/classify')
        .then(function(res){
          if (res.result.code === 1) {
            that.msg = res.data;
          }
        }).catch(function(){
        console.log("服务器异常");
      });
      this.getlist()
      bus.$on('search', res=>{
        this.key = res
        that.getlist()
      })
    },
    mounted() {
      this.getlist();
    },
    methods: {
      getlist(){
        let _this = this
        this.page = 1
        this.imgsArr = []
        this.$http.get('/api/drawing/search',{
          params:{
            key:_this.key,
            classify:"",
            page:1,
            status:0,
          }
        }).then(function(res){
          if (res.result.code === 1) {
            _this.imgsArr = res.data.drawing
            if (res.data.drawing.length === 0) {
              _this.isbottom = false
            }
          }
        }).catch(function(){
          console.log("服务器异常");
        });
      },
      fetchImgsData () {    //获取新的图片数据的方法，用于页面滚动满足条件时调用
        let _this = this
        this.page ++
        this.$http.get('/api/drawing/search',{
          params:{
            key:_this.key,
            classify:"",
            page:this.page,
            status:0,
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
      }
    },
  }
</script>

<style scoped>
  .waterfall_box{
    max-width:800px;
    min-height:calc(100vh - 62px);
    margin-top:62px;
    margin-right:auto;
    margin-left:auto;
  }
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
    color: #515151;
  }
</style>
