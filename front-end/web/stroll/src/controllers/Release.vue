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

          <el-upload
            class="uploadCover" drag
            action=""
            :show-file-list="false"
            :http-request="UploadCover"
            :before-upload="beforeAvatarUpload">
            <img v-if="msg.coverSrc" :src=msg.coverSrc class="cover"/>
            <div v-else class="uploadLogo">
              <i  class="el-icon-upload msgCoverI"/>
              <div class="el-upload__text msgCoverText">将文件拖到此处，或<em>点击上传</em></div>
            </div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png/jpeg文件，且不超过500kb</div>
          </el-upload>
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

          <!--          <el-button class="uploadButton" @click="uploadMd">上传文档</el-button>-->
          <el-upload
            action=""
            :show-file-list="false"
            :http-request="uploadMd">
            <el-button class="uploadButton">上传文档</el-button>
          </el-upload>
          <p class="uploadOK">您已成功上传文件！</p>
        </div>

      </div>

      <!--      手绘发布-->
      <div id="drawingRel" v-if="tag === '2'">
        <div class="pageLeft">
          <!--          <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple>-->
          <!--            <i class="el-icon-upload"/>-->
          <!--            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>-->
          <!--            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>-->
          <!--          </el-upload>-->
          <ul id="nineBox">
            <li class="itemBox" v-for="(item,index) in drawings.slice(0,len)" :key="index">
              <img v-if="item.src" :src="item.src" class="itemImg"/>
            </li>
          </ul>
          <el-upload
            class="drawUpload"
            action=""
            :show-file-list="false"
            :http-request="UploadImg"
            :before-upload="beforeAvatarUpload"
            multiple>
            <el-button>上传</el-button>
            <!--            <img v-if="msg.coverSrc" :src=msg.coverSrc class="cover"/>-->
            <!--            <i v-else class="el-icon-plus avatar-uploader-icon"/>-->
          </el-upload>
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

          <p>只能上传jpg/png/jpeg文件，且不超过500kb，最多上传9张</p>
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
  import {client} from "../utils/alioss"
  export default {
    name: "Release",
    data(){
      return{
        activeIndex: '1',
        tag:'1',
        msg:{
          coverSrc:"",
          title:"",
          introduce:"",
          mdSrc:""
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
          // {id:'1', src:"../../static/images/logo.png"},
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
        len:0,
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        this.tag = keyPath[0] ;
      },
      release(){
      },
      cancel(){
      },
      uploadMd(file){
        var fileName = 'article' + `${Date.parse(new Date())}`+'.md';  //定义唯一的文件名
        client().multipartUpload(fileName, file.file).then(
          result => {
            this.msg.mdSrc = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
            $('.uploadOK').css('display', 'block');
            // var temp = document.getElementsByClassName("uploadOK");
            // temp.style = "display:block";
            // temp.style.display = block;
          })
      },
      UploadCover(file) {
        var fileName = 'cover' + `${Date.parse(new Date())}`;  //定义唯一的文件名
        //定义唯一的文件名，打印出来的uid其实就是时间戳
        client().multipartUpload(fileName, file.file).then(
          result => {
            this.msg.coverSrc = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
          })
      },
      UploadImg(file) {
        var fileName = 'image' + `${Date.parse(new Date())}`;  //定义唯一的文件名
        if(this.len < 9){
          //定义唯一的文件名，打印出来的uid其实就是时间戳
          client().multipartUpload(fileName, file.file).then(
            result => {
              this.drawings[this.len].src = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
              this.len += 1;
              // uploadBannerPic(this.fileList).then(res => {
              //   console.log("lalala")
              //   //此处为给自己属性进行赋值，http后面的代码很有可能会和我的不一样，一切与自己阿里云上的数据为准
              //   //根据需要可能项目还需对自己的数据库进行保存
              //   // this.contacts.conImg='http://bai111111.oss-cn-beijing.aliyuncs.com/'+this.fileList[0].result.name;
              // })
            })
        }else{
          console.log("最多上传9张")
        }
      },
      /**
       * 图片限制
       * 图片限制在理论上来说可以不用写，如果想要简单，可以不写
       * 上传图片切记不要过大，可能会导致上传失败
       */
      beforeAvatarUpload (file) {
        const isJPEG = file.name.split('.')[1] === 'jpeg';
        const isJPG = file.name.split('.')[1] === 'jpg';
        const isPNG = file.name.split('.')[1] === 'png';
        const isLt500K = file.size / 1024 / 500 < 2;
        if (!isJPG && !isJPEG && !isPNG) {
          this.$message.error('上传图片只能是 JPEG/JPG/PNG 格式!');
        }
        if (!isLt500K) {
          this.$message.error('单张图片大小不能超过 500KB!');
        }
        return (isJPEG || isJPG || isPNG) && isLt500K;
      },
    }
  }
</script>

<style scoped>
  #release{
    height: 100%;
    width: 76%;
    margin: 0 12% 0 12%;
    background-color: #fff;
    position: absolute;
  }
  #nav{
    padding: 6% 5% 0 5%;
  }
  #releaseBox{
    margin: 3% 12%;
    position: relative;
  }
  /*页面左半*/
  .pageLeft{
    float: left;
    text-align: center;
  }
  /*文章页面左半*/
  .cover{
    width: 311px;
    height: 358px;
  }
  .uploadButton{
    display: inline-block;
    width: 150px;
    background: #f8f9fa;
    margin-top: 50px;
    margin-bottom: 9px;
  }
  .uploadCover{
    border: 1px dashed #d9d9d9;
    width: 315px;
    height: 360px;
    margin-top: 10px;
    text-align: center;
  }
  .uploadCover /deep/ .el-upload-dragger{
    width: 315px;
    height: 360px;
  }
  .uploadLogo .el-icon-upload{
    position: absolute;
    left: 52%;
    top: 26%;
    transform: translate(-50%, -50%);
  }
  /*手绘页面左半*/
  /deep/ .el-upload-dragger {
    background-color: rgba(204, 204, 204, 0);
    border: 0;
    width: 450px;
    height: 450px;
  }
  .el-icon-upload {
    position: absolute;
    left: 52%;
    top: 38%;
    transform: translate(-50%, -50%);
  }
  .el-upload__text {
    position: absolute;
    left: 52%;
    top: 57%;
    transform: translate(-50%, -50%);
  }
  .drawUpload .el-button{
    width: 130px;
    background: #f8f9fa;
  }
  #nineBox{
    list-style-type: none;
    width: 404px;
    height: 404px;
    padding: 0;
    border: 1px solid #d9d9d9;
    border-radius: 5px;
  }
  .itemBox{
    width: 130px;
    height: 130px;
    float: left;
    margin: 2px;
    border: 2px solid #dee2e6;
    border-radius: 5px;
    cursor: pointer;
  }
  .itemImg{
    width: 100%;
    height: 100%;
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
    margin: 30px 50px 10px 0;
  }
  .uploadOK{
    display: none;
    /*display: block;*/
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
  .drawRight{
    float: right;
    width: 43%;
  }
  .drawRight p{
    font-size: 10px;
    color: #c0c4cc;
  }
</style>
