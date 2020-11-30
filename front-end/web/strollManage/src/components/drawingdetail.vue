<template>
  <div>
    <div style="display: flex;">
      <p style="margin-left:50px;">手绘编号:<span style="margin-left: 30px;">{{id}}</span></p>
    </div>
    <div class="row">
      <p>手绘标题:<span style="margin-left: 30px;">{{data.title}}</span></p>
    </div>
    <div class="row">
      <p>手绘作者:<span style="margin-left: 30px;">{{data.autor.nickname}}</span></p>
    </div>
    <div class="row">
      <p>手绘分类:<span style="margin-left: 30px;">{{data.classifyName}}</span></p>
    </div>
    <div class="row">
      <p>手绘标签:<span style="margin-left: 30px;">{{data.labels}}</span></p>
    </div>
    <div class="row">
      <p>发布时间:<span style="margin-left: 30px;">{{data.dateTime}}</span></p>
    </div>
    <div class="row">
      <p>手绘简介:<span style="margin-left: 30px;">{{data.introduction}}</span></p>
    </div>
    <div class="row">
      <p>点赞数量:<span style="margin-left: 30px;">{{data.likeNum}}</span></p>
    </div>
    <p style="margin-top: 20px;margin-left:50px;">手绘内容:</p>
    <div style="margin-top: 10px;margin-left:80px;width:800px;">
      <el-carousel type="card" height="200px">
        <el-carousel-item v-for="(img,index) in data.images" :key="index">
          <image :src="img" class="carouselImg"/>
        </el-carousel-item>
      </el-carousel>
    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        "data": {
          "autor": {
            "id": "^^lM",
            "nickname": "lqy",
            "avatar": "eMTC"
          },
          "title": "论母猪养殖",
          "introduction": "这是一个由专业饲养员的自白",
          "images": ["https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2387044829,3631423133&fm=26&gp=0.jpg",
          "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2211283380,2655893385&fm=26&gp=0.jpg",
          "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3816070274,3523402595&fm=26&gp=0.jpg"],
          "likeNum": 50,
          "labels": [
            "畜牧业",
            "经验"
          ],
          "type": 0,
          "dateTime": "2020/11/23",
          "status": "上架",
          "classifyName": "科普"
        },
        htmlMD:'',
      }
    },
    props: ["id"],
    components: {

    },
    created() {
      this.getlist()
      console.log(this.data.images)
    },
    filters: {
      filters1: function(arg) {
        if(arg == 0) {
          return "其他"
        } else if(arg == 1) {
          return "书籍"
        }
      },
    },
    methods: {
      //订单详情
      getlist() {

      },
      //下架
      offshelf(){
        let _this = this
        this._postData('/admin/order/detail', {
          id: this.showlist.orderId,
          shelf:'off'
        }, data => {
          this.$message({
            message: '下架成功',
            type: 'success'
          })
          _this.getlist()
        })
      },
      //上架
      onshelf(){
        let _this = this
        this._postData('/admin/order/detail', {
          id: this.showlist.orderId,
          shelf:'off'
        }, data => {
          this.$message({
            message: '上架成功',
            type: 'success'
          })
          _this.getlist()
        })
      }
    }
  }
</script>

<style scoped>
  .row{
    display: flex;
    margin-top: 20px;
    margin-left:50px;
  }
  .carouselImg{
    height:200px;
  }
</style>
