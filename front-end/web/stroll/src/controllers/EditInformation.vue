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
        <img :src=msg.avatar class="myAvatar"/>
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
            // console.log(res);
            // console.log("！！： "+JSON.stringify(res));
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
      }
    },
    created() {
      let that = this;
      //得到个人全部信息
      this.$http.get('/api/person/totalInfo')
        .then(function(res){
          // console.log("！！： "+JSON.stringify(res));
          that.msg = res.data;
        }).catch(function(){
        console.log("服务器异常");
      });
    },
  }
</script>

<style scoped>
  #edit{
    height: 100%;
    width: 54%;
    margin: 0 23% 0 23%;
    background-color: #fff;
    position: absolute;
  }
  #mask{
    display: none;
    height: 100%;
    width: 100%;
    background: #58535369;
    position: fixed;
    z-index: 2;
    margin: 0;
  }
  #changeBox{
    height: 300px;
    width: 350px;
    position: absolute;
    left: 27%;
    top: 50%;
    /* margin: -175px 0 0 -150px; */
    background: #fff;
    text-align: center;
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
    padding: 20% 10%;
  }
  /*页面左半*/
  #pageLeft{
    float: left;
    text-align: center;
    position: relative;
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
    background: #f8f9fa;
    margin: 49px 0 50px 0;
  }
  /*页面右半*/
  #pageRight{
    float: right;
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
</style>
