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
    export default {
      data () {
        return {
          imgsArr: [],         //存放所有已加载图片的数组（即当前页面会加载的所有图片）
          fetchImgsArr: [],    //存放每次滚动时下一批要加载的图片的数组
          isbottom:false,
        }
      },
      components: {
        vueWaterfallEasy
      },
      created () {
        this.imgsArr = this.initImgsArr(0, 10)       //初始化第一次（即页面加载完毕时）要加载的图片数据
        this.fetchImgsArr = this.initImgsArr(10, 18) // 模拟每次请求的下一批新的图片的数据数据
      },
      methods: {
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
            this.isbottom=false
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
    }
</script>

<style scoped>
  .waterfall_box{
    max-width:800px;
    height:calc(100vh - 62px);
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
    color: #bfbfbf;
  }
</style>
