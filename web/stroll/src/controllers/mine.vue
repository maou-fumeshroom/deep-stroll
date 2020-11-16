<template>
  <div id = "myPage">

<!--    左侧分类选择菜单-->
   <div id="articleTags">
     <el-col :span="12">
       <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
         <el-submenu index="1">
           <template slot="title">
             <span>导航一</span>
           </template>
           <el-menu-item-group>
             <template slot="title">分组1</template>
             <el-menu-item index="1-1">选项1</el-menu-item>
             <el-menu-item index="1-2">选项2</el-menu-item>
           </el-menu-item-group>
           <el-menu-item-group title="分组2">
             <el-menu-item index="1-3">选项3</el-menu-item>
           </el-menu-item-group>
           <el-submenu index="1-4">
             <template slot="title">选项4</template>
             <el-menu-item index="1-4-1">选项1</el-menu-item>
           </el-submenu>
         </el-submenu>
         <el-menu-item index="2">
           <span slot="title">导航二</span>
         </el-menu-item>
         <el-menu-item index="3">
           <span slot="title">导航三</span>
         </el-menu-item>
         <el-menu-item index="4">
           <span slot="title">导航四</span>
         </el-menu-item>
       </el-menu>
     </el-col>
   </div>

<!--    右侧文章列表-->
    <ul id="article">
      <li class="articleBox" @click="focus(item)" v-for="(item,index) in articleList.slice(0,len)" :key="index">
        <img :src = "item.imgSrc" class = "articleCover"/>
        <h3 class="articleTitle">{{item.title}}</h3>
        <div class="articleMsg">

        </div>
      </li>
    </ul>
  </div>
</template>

<script>
    export default {
      name: 'mine',
      data () {
        return {
          articleList:[
            {
              id:1,
              imgSrc:require("../assets/logo.png"),
              title:"Python之父，现在成为微软打工人",
              authorNickname:"小明",
              authorAvatar:require("../assets/logo.png"),
              releaseTime:"11-13",
              likes:"232",
              commentsNum:"56"
            },
            {
              id:2,
              imgSrc:require("../assets/logo.png"),
              title:"如何看待马云 4 月 11 日在内外直播中将 996 称为「修来的福报」？",
              authorNickname:"小红",
              authorAvatar:require("../assets/logo.png"),
              releaseTime:"11-10",
              likes:"60",
              commentsNum:"12"
            },
            {
              id:3,
              imgSrc:require("../assets/logo.png"),
              title:"如何看待 Python 之父 Guido 加盟微软？",
              authorNickname:"李华",
              authorAvatar:require("../assets/logo.png"),
              releaseTime:"11-9",
              likes:"3",
              commentsNum:"1"
            },
          ],
          len: 8,
        }
      },
      methods: {
        //展开指定的分类
        handleOpen(key, keyPath) {
          console.log(key, keyPath);
        },
        //收起指定的分类
        handleClose(key, keyPath) {
          console.log(key, keyPath);
        }
      }
    }
</script>

<style scoped>
  /*左侧分类选择菜单*/
  #articleTags{
    width: 30%;
  }

  /*右侧文章列表*/
  #article{
    background: rgba(199, 201, 211, 0.29);
    width: 85%;
    float: right;
    list-style-type: none;
    margin: auto;
  }

  .articleBox{
    margin: 45px;
    height: 100px;
    position: relative;
    background: #fff;
    box-shadow: 5px 5px 10px #656262;
    cursor: pointer;
  }

  .articleCover{
    height: 100%;
    margin-left: 5px;
  }

  .articleTitle{
    display: inline-block;
    position: absolute;
    top: 10%;
    margin-left: 10px;
  }
</style>
