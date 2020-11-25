<template>
  <div id = "release">

<!--    导航栏-->
    <div id="nav">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1">文章</el-menu-item>
        <el-menu-item index="2">手绘</el-menu-item>
      </el-menu>
    </div>

    <div id="releaseBox">
<!--      文章发布-->
      <div id="articleRel" v-if="tag === '1'">
        <div class="pageLeft">
          <h4 >选择封面</h4>
          <img :src=msg.coverSrc class="cover"/>
          <br/>
          <el-button class="uploadButton" @click="upload">上传封面</el-button>
        </div>

        <div class="pageRight">
          <span class="title">文章题目：</span>
          <el-input class="inputBox" placeholder="请输入内容" v-model="msg.title" clearable/>
          <br/>
          <span>文章介绍：</span>
<!--          <el-input class="inputBox" placeholder="请输入内容" v-model="msg.introduce" clearable/>-->
          <el-input type="textarea" placeholder="请输入介绍" v-model="msg.introduce" maxlength="150" show-word-limit/>
          <el-select class="select" v-model="sort" filterable placeholder="请选择分类" clearable>
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>

          <el-select v-model="label" filterable placeholder="请选择标签" clearable>
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>

          <el-button class="uploadButton" @click="upload">上传文档</el-button>
          <p>您成功上传文件：路径</p>
        </div>

      </div>

<!--      手绘发布-->
      <div id="drawingRel" v-if="tag === '2'">
        <div class="pageLeft">
          <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple>
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
          <ul id="nineBox">
            <li class="itemBox" @click="focus(item)" v-for="(item,index) in drawings.slice(0,len)" :key="index"/>
          </ul>
        </div>


        <div class="drawRight">
          <span class="title">手绘题目：</span>
          <el-input class="inputBox" placeholder="请输入内容" v-model="msg.title" clearable/>
          <br/>
          <span>手绘介绍：</span>
          <el-input type="textarea" placeholder="请输入介绍" v-model="msg.introduce" maxlength="150" show-word-limit/>
          <el-select class="select" v-model="sort" filterable placeholder="请选择分类" clearable>
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
          <br/>
          <el-select class="select" v-model="label" filterable placeholder="请选择标签" clearable>
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </div>
      </div>
    </div>

    <div id="Button">
      <el-button class="relButton" type="primary" @click="release">发布</el-button>
      <el-button class="canButton" @click="cancel">取消</el-button>
    </div>

  </div>
</template>

<script>
    export default {
      name: "Release",
      data(){
        return{
          activeIndex: '1',
          tag:'1',
          msg:{
            coverSrc:require("../assets/logo.png"),
            title:"",
            introduce:"",
          },
          options: [{
            value: '选项1',
            label: '电影'
          }, {
            value: '选项2',
            label: '新闻'
          }, {
            value: '选项3',
            label: '财经'
          }, {
            value: '选项4',
            label: '动漫'
          }, {
            value: '选项5',
            label: '风景'
          }],
          sort:"",
          label:"",
          drawings:[
            {id:'1', src:""},
            {id:'2', src:""},
            {id:'3', src:""},
            {id:'4', src:""},
            {id:'5', src:""},
            {id:'6', src:""},
            {id:'7', src:""},
            {id:'8', src:""},
            {id:'9', src:""},
          ],
          len:9,
        }
      },
      methods: {
        handleSelect(key, keyPath) {
          this.tag = keyPath[0] ;
        },
        upload(){

        },
        release(){

        },
        cancel(){

        },
        focus:function (item) {
          this.len += 1;
        },

      }
    }
</script>

<style scoped>
  #release{
    margin: 1% 10%;
  }

  #releaseBox{
    margin: 2% 15%;
  }

  /*页面左半*/
  .pageLeft{
    float: left;
    text-align: center;
  }

  /*文章页面左半*/
  .cover{
    border: 1px solid #000;
    width: 280px;
    height: 280px;
    margin-top: 10px;
  }

  .uploadButton{
    display: inline-block;
    width: 150px;
    background: #f8f9fa;
    margin-top: 50px;
    margin-bottom: 9px;
  }

  /*页面右半*/
  .pageRight{
    float: right;
    width: 55%;
  }

  .inputBox{
    display: inline-block;
    width: auto;
  }

  .title{
    margin-bottom: 30px;
    display: inline-block;
  }

  .select{
    margin: 30px 50px 0 0;
  }

  /deep/ .el-textarea__inner{
    height: 130px;
  }

  #Button{
    position: absolute;
    bottom: 10%;
    width: 550px;
    left: 50%;
    transform: translateX(-50%);
  }

  .relButton{
    width: 100px;
    float: left;
  }

  .canButton{
    float: right;
    width: 100px;
  }

  #nineBox{
    list-style-type: none;
    width: 464px;
    height: 464px;
    padding: 0;
    position: absolute;
    top: 21%;
    border: 1px dashed #d9d9d9;
}

  .itemBox{
    width: 150px;
    height: 150px;
    float: left;
    margin: 2px;
    border: 2px solid #dee2e6;
    border-radius: 5px;
    cursor: pointer;
  }

  .drawRight{
    float: right;
    width: 43%;
  }

  .upload-demo{
    position: absolute;
    z-index: 1;
  }

  /deep/ .el-upload-dragger {
     background-color: rgba(204, 204, 204, 0);
     border: 0;
    width: 450px;
    height: 450px;
  }

  /deep/.el-icon-upload {
    position: absolute;
    left: 52%;
    top: 38%;
    transform: translate(-50%, -50%);
  }

  /deep/.el-upload__text {
    position: absolute;
    left: 52%;
    top: 57%;
    transform: translate(-50%, -50%);
  }

</style>
