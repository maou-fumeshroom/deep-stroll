<template>
  <div id = "edit">

    <div id="mask">
      <div id="changeBox">
        <h4>修改密码</h4>
        <el-input class="inputPass" placeholder="请输入旧密码" v-model="oldPassword" show-password/>
        <el-input class="inputPass" placeholder="请输入新密码" v-model="newPassword1" show-password/>
        <el-input class="inputPass" placeholder="确认新密码" v-model="newPassword2" show-password/>
        <el-button type="primary" @click="savePass">保存</el-button>
        <el-button @click="cancelPass">取消</el-button>
      </div>
    </div>

    <div id="editBox">
      <div id="pageLeft">
        <img :src=msg.avatarSrc class="myAvatar"/>
        <el-button class="changePass" @click="changePassword">修改密码</el-button>
        <el-button class="saveButton" type="primary" @click="save">保存</el-button>
      </div>

      <div id="pageRight">
        <el-input class="inputBox" placeholder="请输入内容" v-model="msg.nickname" clearable/>
        <el-radio class="radio" v-model="msg.gender" label="女">女</el-radio>
        <el-radio v-model="msg.gender" label="男">男</el-radio>
        <br/>
        <el-input class="inputBox" placeholder="请输入内容" v-model="msg.phoneNumber" clearable/>
        <el-input class="inputBox" placeholder="请输入内容" v-model="msg.Email" clearable/>
        <el-input class="inputBox" placeholder="请输入内容" v-model="msg.introduce" clearable/>
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
          msg:{
            id:"0",
            nickname:"王明明",
            avatarSrc:require("../assets/logo.png"),
            gender:"男",
            phoneNumber:"13313131133",
            Email:"14259763@qq.com",
            introduce:"好好学习，天天向上",
          },
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
          console.log(this.msg);
          this.$router.push({
            path:'/mine',
          })
        },
        cancel(){
          this.$router.push({
            path:'/mine',
          })
        },
        savePass(){
          var mask = document.getElementById("mask");
          mask.style.display = "none";
        },
        cancelPass(){
          var mask = document.getElementById("mask");
          mask.style.display = "none";
        }
      }
    }
</script>

<style scoped>
  #edit{
    height: 100%;
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
    left: 50%;
    top: 50%;
    margin: -175px 0 0 -150px;
    background: #fff;
    text-align: center;
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
    padding: 5% 25%;
  }


  /*页面左半*/
  #pageLeft{
    float: left;
    text-align: center;
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
