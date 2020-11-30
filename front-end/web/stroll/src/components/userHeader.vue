<template>
  <nav>
    <div class="logo" @click="$router.push('/')">
      <slot name="logo-icon"><img src="../assets/logo.png"></slot>
      <slot name="logo-title">闲亭</slot>
    </div>
    <el-row class="nav-menu">
      <div class="search">
        <el-input
          placeholder="请输入内容"
          size="small"
          class="input_search">
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
      </div>
      <router-link to="/article" exact tag="li" class="header-item">文章</router-link>
      <router-link to="/drawings" tag="li" class="header-item">手绘</router-link>
      <div class="avatarBox" @click="$router.push('/mine')">
        <img :src="user.avatar"/>
      </div>
    </el-row>
    <div class="burger" @click="handleBurger()">
      <div class="top-line"></div>
      <div class="middle-line"></div>
      <div class="bottom-line"></div>
    </div>
  </nav>
</template>

<script>
  export default {
    data(){
      return{

      }
    },
    props: {
      user: {
        type: Object,
        default () {
          return {
            avatar: require('../assets/logo.png'),
            nickname:'baozilong'
          }
        }
      }
    },
    methods:{
      handleBurger(){
        const burger=document.querySelector(".burger");
        const navMenu=document.querySelector(".nav-menu");
        burger.addEventListener("click",function(){
          burger.classList.toggle("active");
          navMenu.classList.toggle("open");
        })
      }
    }
  }
</script>

<style>
  .input_search .el-input__inner{
    background:rgba(36,41,46,0.1);
  }
  .input_search .el-input__inner:focus{
    background:#FFFFFF;
  }
</style>
<style scoped>
  nav{
    position:fixed;
    top:0;
    left:0;
    right: 0;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding:16px 5vw;
    height: 62px;
    background-color: rgba(36,41,46,0.1);
  }
  .logo{
    font-size: 28px;
    font-weight: 600;
    background-image: linear-gradient(to top, #9890e3 0%, #b1f4cf 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    cursor:pointer;
  }
  .logo img{
    width:30px;
    height:30px;
    border-radius: 5px;
    flex: 1;
    vertical-align: center;
  }
  .nav-menu{
    flex:1;
    display: flex;
    justify-content: space-around;
    align-items:center;
    max-width:500px;
  }
  .burger{
    margin-left: 10px;
    display: none;
  }
  .burger div{
    width:30px;
    height:2px;
    background-color:#dadbdd;
    margin: 6px;
  }
  .header-item{
    list-style: none;
    color: #ffffff;
    font-weight: 600;
    line-height: 30px;
  }
  .header-item:hover{
    cursor: pointer;
  }
  .avatarBox{
    width:25px;
    height:25px;
    border-radius:50%;
    overflow:hidden;
  }
  .avatarBox img{
    width:25px;
    height:25px;
    border-radius:50%;
    display:block;
  }
  @media screen and (max-width: 768px){
    .nav-menu{
      position: fixed;
      top:62px;
      right:0;
      bottom:0;
      width:40vw;
      height:calc(100vh - 62px);
      background-color: rgba(65,81,101,0.7);
      flex-direction: column;
      align-items: center;
      justify-content: flex-start;
      transform: translateX(100%);
      transition:0.3s ease-in-out;
    }
    .nav-menu.open{
      transform: translateX(0);
    }
    .burger{
      display: block;
    }

    .burger.active div{
      transition: 0.3s ease-in-out 0.2s;
    }
    .burger.active .top-line{
      transform: rotate(90deg) translateX(8px) translateY(-14px);
    }
    .burger.active .bottom-line{
      transform: rotate(90deg) translateX(-8px) translateY(-6px);
    }
    .burger.active .middle-line{
      transform: rotate(90deg) translateY(2px);
    }
    @keyframes slideIn {
      from{
        transform: translateX(20px);
        opacity: 0;
      }
      to{
        transform: translateX(0);
        opacity: 1;
      }
    }
    .header-item{
      margin:3vh;
      transition: 1s slideIn ease-in;
    }
  }
</style>
