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
            <img v-if="articleMsg.cover" :src="articleMsg.cover" class="cover"/>
            <div v-else class="uploadLogo">
              <i  class="el-icon-upload msgCoverI"/>
              <div class="el-upload__text msgCoverText">将文件拖到此处，或<em>点击上传</em></div>
            </div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png/jpeg文件，且不超过500kb</div>
          </el-upload>
        </div>

        <div class="pageRight">
          <span class="title">文章题目：</span>
          <el-input class="inputBox" placeholder="请输入内容" v-model="articleMsg.title" clearable/>
          <br/>
          <span>文章介绍：</span>
          <el-input style="margin-top:10px;" type="textarea" placeholder="请输入介绍" v-model="articleMsg.introduction" maxlength="150" show-word-limit/>
          <el-select class="select" v-model="articleMsg.classify" filterable placeholder="请选择分类" clearable>
            <el-option v-for="item in articleOptions" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>



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
          <ul id="nineBox">
            <li class="itemBox" v-for="(item,index) in drawingMsg.image.slice(0,len)" :key="index">
              <img v-if="item" :src="item" class="itemImg"/>
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
          </el-upload>
        </div>


        <div class="drawRight">
          <span class="title">手绘题目：</span>
          <el-input class="inputBox" placeholder="请输入内容" v-model="drawingMsg.title" clearable/>
          <br/>
          <span>手绘介绍：</span>
          <el-input style="margin-top:10px;"  type="textarea" placeholder="请输入介绍" v-model="drawingMsg.introduction" maxlength="150" show-word-limit/>
          <el-select class="select" v-model="drawingMsg.classify" filterable placeholder="请选择分类" clearable>
            <el-option v-for="item in drawingOptions" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
          <br/>


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
        articleMsg:{
            cover:"",
            title:"",
            introduction:"",
            fileUrl:"",
            classify:null,
            labels:[],
        },
        drawingOptions: [],
        articleOptions:[],
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
        drawingMsg:{
            title:"",
            classify:null,
            labels:[],
            introduction:"",
            image:[]
        },
        len:0,
        isComplete:false,
      }
    },
    created(){
      this.getClassify()
    },
    methods: {
      handleSelect(key, keyPath) {
        this.tag = keyPath[0] ;
      },
      getClassify(){
        this.$http.get(`/api/article/classify`).then(res => {
            if (res.result.code === 1){
              this.articleOptions = res.data.classifys
            }
        }).catch(err =>{})
        this.$http.get(`/api/drawing/classify`).then(res => {
          if (res.result.code === 1){
            this.drawingOptions = res.data.classifys
          }
        }).catch(err =>{})
      },
      release(){
        let _this = this

        //文章上传
        if (this.tag === '1'){
          //先判断有无未填信息
          if(this.articleMsg.cover !== "" &&
            this.articleMsg.title !== "" &&
            this.articleMsg.classify !== null &&
            this.articleMsg.introduction !== "" &&
            this.articleMsg.fileUrl !== ""){
            this.isComplete = true;
          }

          //如果全部填写，才能发布成功
          if(this.isComplete){
            // console.log("文章： "+ JSON.stringify(this.articleMsg));
            //发布文章
            this.$http.post('/api/person/article/add',{
              cover:this.articleMsg.cover,
              title:this.articleMsg.title,
              classify:this.articleMsg.classify,
              labels:this.articleMsg.labels,
              introduction:this.articleMsg.introduction,
              fileUrl:this.articleMsg.fileUrl
            },{emulateJSON: true})
              .then(function(res){
                if (res.result.code === 1){
                  _this.$message.success("发布成功")
                  _this.$router.push({
                    path:'/mine',
                  })
                }
              }).catch(err =>{});
          }else{
            this.$notify({
              title: '警告',
              message: '请完整填写文章信息后再上传',
              type: 'warning',
              duration:1000
            });
          }

        }else{
          //手绘上传

          //先判断有无未填信息
          if(this.drawingMsg.title !== "" &&
            this.drawingMsg.classify !== null &&
            this.drawingMsg.introduction !== "" &&
            this.drawingMsg.image.length !== 0){
            this.isComplete = true;
          }

          //如果全部填写，才能发布成功
          if(this.isComplete){
            console.log("手绘： "+JSON.stringify(this.drawingMsg));
            this.$http.post('/api/person/drawing/add',{
              title:this.drawingMsg.title,
              classify:this.drawingMsg.classify,
              labels:this.drawingMsg.labels,
              introduction:this.drawingMsg.introduction,
              image:this.drawingMsg.image
            },{emulateJSON: true})
              .then(function(res){
                if (res.result.code === 1){
                  _this.$message.success("发布成功")
                  _this.$router.push({
                    path:'/mine',
                  })
                }
              }).catch(err =>{});
          }else{
            this.$notify({
              title: '警告',
              message: '请完整填写手绘信息后再上传',
              type: 'warning',
              duration:1000
            });
          }

        }
      },
      cancel(){
        this.$router.push({
          path:'/mine',
        })
      },
      uploadMd(file){
        var fileName = 'article' + `${Date.parse(new Date())}`+'.md';  //定义唯一的文件名
        client().multipartUpload(fileName, file.file).then(
          result => {
            this.articleMsg.fileUrl = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
            $('.uploadOK').css('display', 'block');
            this.$notify({
              title: '成功',
              message: '您已成功上传文件😊！',
              type: 'success',
              duration: 1000
            });
          })
      },
      UploadCover(file) {
        var fileName = 'cover' + `${Date.parse(new Date())}`;  //定义唯一的文件名
        //定义唯一的文件名，打印出来的uid其实就是时间戳
        client().multipartUpload(fileName, file.file).then(
          result => {
            this.articleMsg.cover = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
          })
      },
      UploadImg(file) {
        var fileName = 'image' + `${Date.parse(new Date())}`;  //定义唯一的文件名
        if(this.len < 9){
          //定义唯一的文件名，打印出来的uid其实就是时间戳
          client().multipartUpload(fileName, file.file).then(
            result => {
              // this.drawings[this.len].src = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
              // this.drawingMsg.image[this.len].src = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
              this.drawingMsg.image[this.len] = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
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
        const isJPEG = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase() === 'jpeg';
        const isJPG = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase() === 'jpg';
        const isPNG = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase() === 'png';
        const isLt500K = file.size / 1024 / 500 < 2;
        if (!isJPG && !isJPEG && !isPNG) {
          this.$message.error('上传图片只能是 JPEG/JPG/PNG 格式!');
        }
        if (!isLt500K) {
          this.$message.error('单张图片大小不能超过 500KB!');
        }
        return (isJPEG || isJPG || isPNG) && isLt500K;
        // return (isJPEG || isJPG || isPNG);
      },
    }
  }
</script>

<style scoped>
  #release{
    height:calc(100% - 62px);
    width: 76%;
    margin: 62px 12% 0 12%;
    /*background-color: #fff;*/
    background-color: #ffffffa8;
    position: absolute;
  }
  #nav{
    padding: 0 5%;
  }
  #releaseBox{
    margin: 3% auto;
    position: relative;
  }
  #articleRel{
    width:100%;
    display:flex;
    justify-content: center;
  }
  #drawingRel{
    width:100%;
    display:flex;
    justify-content: center;
  }
  /*页面左半*/
  .pageLeft{
    margin:0 20px;
    text-align: center;
    margin-top: -1%;
  }
  /*文章页面左半*/
  .cover{
    width: 311px;
    height: 358px;
  }
  .uploadButton{
    display: inline-block;
    width: 150px;
    background: #f8f9fa
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
    background-color: #ffffffbd;
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
    width: 415px;
    height: 415px;
    padding: 0;
    border: 1px solid #d9d9d9;
    border-radius: 5px;
    background-color: #ffffffbd;
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
    max-width: 400px;
    margin:0 20px;
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
    margin: 30px 50px 90px 0;
  }
  .uploadOK{
    display: none;
    font-size: 12px;
    color: #606266;
    margin-top: 9px;
    /*display: block;*/
  }
  /deep/ .el-textarea__inner{
    height: 130px;
    background-color: #ffffffbd;
  }

  /deep/ .el-input__inner{
    background-color: #ffffffbd;
  }

  /deep/ .el-menu{
    background-color: rgba(255, 255, 255, 0);
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
    max-width:400px;
    margin:0 20px
  }
  .drawRight p{
    font-size: 10px;
    /*color: #c0c4cc;*/
    color: #84878b;
  }
</style>
