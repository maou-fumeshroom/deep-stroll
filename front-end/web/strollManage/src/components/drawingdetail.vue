<template>
  <div>
    <div style="display: flex;">
      <p style="margin-left:50px;">手绘编号:<span style="margin-left: 30px;">{{id}}</span></p>
    </div>
    <div class="row">
      <p>手绘标题:<span style="margin-left: 30px;">{{data.title}}</span></p>
    </div>
    <div class="row" v-if="data.author">
      <p>手绘作者:<span style="margin-left: 30px;">{{data.author.nickname}}</span></p>
    </div>
    <div class="row">
      <p>手绘分类:<span style="margin-left: 30px;">{{data.classifyName}}</span></p>
    </div>
    <div class="row">
      <p>发布时间:<span style="margin-left: 30px;">{{data.dateTime}}</span></p>
    </div>
    <div class="row">
      <p style="float:left;">手绘简介:</p>
      <span style="margin-left: 30px;display:block;float:left;width:700px;">{{data.introduction}}</span>
    </div>
    <div class="row">
      <p>点赞数量:<span style="margin-left: 30px;">{{data.likeNum}}</span></p>
    </div>
    <p style="margin-top: 20px;margin-left:50px;">手绘内容:</p>
    <div style="margin-top: 10px;margin-left:80px;width:800px;">
      <el-carousel type="card" height="200px">
        <el-carousel-item v-for="(img,index) in data.images" :key="index" :interval="10000">
          <img :src="img" class="carouselImg"/>
        </el-carousel-item>
      </el-carousel>
    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {}
    },
    props: ["id","data"],
    created() {},
    filters: {},
    methods: {}
  }
</script>

<style>
  .el-carousel__item--card{
    display:flex;
    align-items: center;
    justify-content: center;
  }
</style>

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
