<template>
  <div id = "edit">

    <div id="mask">
      <div id="changeBox">
        <h4>修改密码</h4>
        <el-input class="inputPass" placeholder="请输入旧密码" v-model="oldPassword" show-password/>
        <el-input class="inputPass" placeholder="请输入新密码" v-model="newPassword1" show-password/>
        <el-input class="inputPass" placeholder="确认新密码" v-model="newPassword2" show-password/>
        <el-button type="primary" @click="savePwd">保存</el-button>
        <el-button @click="cancelPwd">取消</el-button>
      </div>
    </div>

    <div id="editBox">
      <div id="pageLeft">


        <el-upload
          class="uploadAvatar"
          action=""
          :show-file-list="false"
          :http-request="UploadAvatar"
          :before-upload="beforeAvatarUpload">
          <img v-if="msg.avatar" :src="msg.avatar" class="myAvatar"/>
          <img v-else src="../assets/icon/uploadIcon.png" class="uploadIcon"/>
          <p class="upTag">只能上传jpg/png/jpeg文件，且不超过500kb</p>
        </el-upload>

<!--        <img :src=msg.avatar class="myAvatar"/>-->



        <el-button class="changePass" @click="changePassword">修改密码</el-button>
        <el-button class="saveButton" type="primary" @click="save">保存</el-button>
      </div>

      <div id="pageRight">
        <el-input class="inputBox" placeholder="请输入昵称" v-model="msg.nickname" clearable/>
        <el-radio class="radio" v-model="msg.sex" :label = '0'>女</el-radio>
        <el-radio v-model="msg.sex" :label = '1'>男</el-radio>
        <br/>
        <el-input class="inputBox" placeholder="请输入电话号码" v-model="msg.telephone" clearable/>
        <el-input class="inputBox" placeholder="请输入邮箱" v-model="msg.email" clearable/>
        <el-input class="inputBox" placeholder="请输入个性签名" v-model="msg.sign" clearable/>
        <el-button class="cancelButton" @click="cancel">取消</el-button>
      </div>
    </div>

  </div>
</template>

<script>
  import {client} from "../utils/alioss";

  export default {
    name: "EditInformation",
    data () {
      return {
        msg:{},
        oldPassword:"",
        newPassword1:"",
        newPassword2:""
      }
    },
    methods: {
      changePassword(){
        var mask = document.getElementById("mask");
        mask.style.display = "block";
      },
      save(){
        let that = this;
        //修改个人信息接口
        this.$http.post('/api/person/updateInfo',{
          sex:this.msg.sex,
          nickname:this.msg.nickname,
          sign: this.msg.sign,
          telephone: this.msg.telephone,
          avatar:this.msg.avatar,
          email: this.msg.email
        },{emulateJSON: true})
          .then(function(res){
            if (res.result.code === 1) {
              that.$notify({
                title: '成功',
                message: '个人信息修改成功！',
                type: 'success',
                duration: 1500
              });
            }
          }).catch(function(){
          console.log("服务器异常");
        });
        this.$router.push({
          path:'/mine',
        })
      },
      cancel(){
        this.$router.push({
          path:'/mine',
        })
      },
      savePwd(){
        var mask = document.getElementById("mask");
        mask.style.display = "none";
      },
      cancelPwd(){
        var mask = document.getElementById("mask");
        mask.style.display = "none";
      },
      UploadAvatar(file) {
        //先删除旧头像
        let temp = this.msg.avatar.split("/");
        console.log(temp[3]);
        let urlName = temp[3];
        client().delete(urlName).then(
          result=>{
            console.log("2"+result)
          }
        );
        //再上传新头像
        var fileName = 'avatar' + `${Date.parse(new Date())}`;  //定义唯一的文件名
        //定义唯一的文件名，打印出来的uid其实就是时间戳
        client().multipartUpload(fileName, file.file).then(
          result => {
            this.msg.avatar = 'http://bai111111.oss-cn-beijing.aliyuncs.com/'+fileName;
          })
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
        // return (isJPEG || isJPG || isPNG);
      },
    },
    created() {
      let that = this;
      //得到个人全部信息
      this.$http.get('/api/person/totalInfo')
        .then(function(res){
          if (res.result.code === 1) {
            that.msg = res.data;
          }
        }).catch(function(){});
    },
  }
</script>

<style scoped>
  #edit{
    height:calc(100% - 62px);
    width: 76%;
    margin: 62px 12% 0 12%;
    /*background-color: #fff;*/
    background-color: #ffffffa8;
    position: absolute;
  }
  #mask{
    display: none;
    height: 100%;
    width: 100%;
    margin: 0;
    margin-left: -12%;
    background: #58535369;
    position: fixed;
    z-index: 2;
  }
  #changeBox{
    height: 300px;
    width: 350px;
    position: absolute;
    left: 50%;
    top: 50%;
    /* margin: -175px 0 0 -150px; */
    background: #ffffffeb;
    text-align: center;
    margin-top:-50px;
    -webkit-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
  }
  h4{
    margin: 10px 0 20px 0;
    font-family: cursive;
  }
  .inputPass{
    display: inline-block;
    width: 300px;
    margin-bottom:35px;
  }
  #editBox{
    /*padding: 5% 25%;*/
    padding-top:100px;
    display:flex;
    justify-content: center;
  }
  /*页面左半*/
  #pageLeft{
    margin:0 40px;
    text-align: center;
    position: relative;
  }
  .uploadAvatar{
    border-radius: 100%;
    border: 1px solid #000;
    width: 270px;
    height: 270px;
  }
  .upTag{
    bottom: 29%;
    position: absolute;
    width: 270px;
    font-size: 0.5em;
    color: #909399;
    left: 0;
    cursor: auto;
  }
  .uploadIcon{
    margin-top: 66px;
  }
.myAvatar{
  border-radius: 100%;
  border: 1px solid #000;
  width: 270px;
  height: 270px;
}
.changePass{
  display: block;
  width: 270px;
  cursor: pointer;
  /*background: #f8f9fa;*/
  background: #f8f9fa91;
  margin: 48px 0 47px 0;
}
/*页面右半*/
  #pageRight{
    margin:0 40px;
    text-align: center;
    position: relative;
  }
  .inputBox{
    width: 300px;
    display: block;
    margin-bottom:50px;
  }
  .radio{
    margin-bottom:50px ;
  }
  .saveButton{
    width: 200px;
    cursor: pointer;
  }
  .cancelButton{
    width: 200px;
    cursor: pointer;
    background: #f8f9fa;
  }

  /deep/ .el-input__inner{
    background-color: #ffffffb3;
  }
</style>
