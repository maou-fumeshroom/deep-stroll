<template lang="pug">
  .background
    .content_box
      .main_content
        .main_item(:class="islogin===1?'active':''")
          .choose_box
            .choose_item(@click="toLogin()") 登录
            .choose_item.register_choose(@click="toRegister()") 注册
          .container
            el-form.form_box(ref="loginForm" :model="loginForm" :rules="rules" label-width="60px")
              el-form-item(label="账号")
                el-input(v-model="loginForm.account" placeholder="请输入手机号")
              el-form-item(label="密码")
                el-input(type="password" v-model="loginForm.password" placeholder="请输入密码")
              el-form-item(label="手机号" v-if="islogin!==1")
                el-input(v-model="loginForm.phone" placeholder="请输入手机号")
              el-form-item(label="验证码" v-if="islogin!==1")
                el-input(v-model="loginForm.sms" placeholder="请输入验证码")
                button.codebtn(type="button" @click="handlegetcode()" :disabled="CodeStatus") {{ CodeBtn }}
              button.submitbtn(type="button" @click="submitLogin()" v-if="islogin===1") 登录
              button.submitbtn(type="button" @click="submitRegister()" v-else) 注册
</template>

<script>
    export default {
        name: "login",
      data () {
        return {
          loginForm:{
            account:'',
            password:'',
            phone:'',
            sms:''
          },
          rules:{},
          islogin:1,
          CodeStatus: false,
          CodeBtn: '获取验证码',
          a:null,
          b:'1',
        }
      },
      methods:{
        toLogin(){
          this.islogin = 1
        },
        toRegister(){
          this.islogin = 2
        },
        handlegetcode() {
          let _this =this
          const mobile = /^[1][3,4,5,7,8,6][0-9]{9}$/;
          if (/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.loginForm.phone)) {
            this.timeOut();
            // this.$http
            //   .post('', {
            //     number: this.phone
            //   })
            //   .then(res => {
            //     this.timeOut();
            //     _this.$message({
            //       title: '获取验证码成功',
            //       icon: 'none'
            //     });
            //   })
            //   .catch(err => {
            //     console.log(err);
            //   });
          } else {
            _this.$message.warning('手机号格式错误');
          }
        },
        timeOut() {
          // 获取验证码按钮倒计时
          let t = 60000;
          this.CodeStatus = true;
          let _this = this;
          let timeOut = setInterval(function() {
            t -= 1000;
            _this.CodeBtn = t / 1000;
            if (t <= 0) {
              clearTimeout(timeOut);
              _this.CodeBtn = '获取验证码';
              _this.CodeStatus = false;
            }
          }, 1000);
        },
        submitLogin(){
          let that = this;
          //登录接口
          this.$http.post('/api/login',{
            telephone:this.loginForm.account,
            pwd:this.loginForm.password,
          },{emulateJSON: true})
            .then(function(res){
              // console.log(res)
              const token = res.data.token;
              // console.log(token)
              // localStorage.setItem("token", "Smith");
              localStorage.setItem("token",token)
              // console.log("????: "+localStorage.getItem('token'))
              that.$router.push({
                path:'/article'
              })
            })

        },
        submitRegister(){
          this.$http.post('1111',{
            a:this.a,
            b:this.b
          })


        }
      }
    }
</script>

<style lang="scss" scoped>
  .background{
    height:100vh;
    width: 100%;
    display: flex;
    flex-direction: column;
    flex: 1 1 0%;
    background: url("../../static/firstbg.png") center center / auto 100% rgb(245, 245, 245);
    background-position: center center;
  }
  .content_box{
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    flex: 1 1 0%;
  }
  .main_content{
    height:300px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: -40px;
  }
  .main_item{
    text-decoration: none;
    margin: 0px 1em;
    overflow: hidden;
    border-radius: 6px;
    transition: transform 500ms ease 0s, opacity 500ms ease 0s;
    display: flex;
    flex-direction:column;
    align-items: center;
    justify-content: space-around;
    width:500px;
    height:400px;
    box-shadow: 3px 3px 6px 3px rgba(0, 0, 0, .3);
  }
  .main_item.active{
    height:300px;
    .container{
      backdrop-filter: blur(5px);
      -webkit-backdrop-filter: blur(5px);
      opacity: 1;
      height:230px;
    }
  }
  .container{
    text-decoration: none;
    margin: 0px 1em;
    overflow: hidden;
    display: flex;
    flex-direction:column;
    align-items: center;
    justify-content: space-around;
    opacity: 0.8;
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
    background: hsla(0, 0%, 100%, .75);
    width:500px;
    height:330px;
  }
  .form_box{
    margin:auto;
    width:300px;
  }
  .choose_box{
    display:flex;
    justify-content:center;
    align-items: center;
    height:70px;
    width:100%;
  }
  .choose_item{
    width:50%;
    height:100%;
    font-size: 20px;
    line-height:70px;
    text-align: center;
    cursor:pointer;
    backdrop-filter: blur(5px);
    -webkit-backdrop-filter: blur(5px);
    background: hsla(0, 0%, 100%, .75);
  }
  .register_choose{
    opacity: 0.7;
  }
  .codebtn {
    position:absolute;
    right:1.5px;
    top:2.5px;
    width: 94px;
    height: 28px;
    line-height: 28px;
    background: #45a4d6;
    border: none;
    border-radius: 4px;
    color: rgba(255, 255, 255, 1);
    font-size: 6px;
    font-weight: bold;
    padding:0;
  }
  button{
    outline:none;
  }
  .submitbtn{
    width: 94px;
    height: 28px;
    line-height: 28px;
    background: #45a4d6;
    border: none;
    border-radius: 4px;
    color: rgba(255, 255, 255, 1);
    font-size: 6px;
    font-weight: bold;
    padding:0;
    margin-left:calc(50% - 47px);
  }
</style>

