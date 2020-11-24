<template>
  <ul>
    <li class="messageBox" v-for="(item,index) in messageList.slice(0,len)" :key="index">
      <img :src = "item.imgSrc" class = "messageCover"/>
      <div class="personMsg">
        <img :src = "item.avatarSrc" class="perAvatar"/>
        <span class="perName">{{item.nickname}}</span>
        <span class="messageTime">{{item.releaseTime}}</span>
        <br/>
        <span class="msgMode" v-if="item.mode === '1-1'">赞了这篇文章👍</span>
        <span class="msgMode" v-if="item.mode === '1-2'">评论了这篇文章：</span>
        <span class="msgMode" v-if="item.mode === '2-1'">赞了这个手绘👍</span>
        <span class="msgMode" v-if="item.mode === '2-2'">评论了这个手绘：</span>
        <span v-if="item.mode === '1-2'">{{item.comment}}</span>
        <span v-if="item.mode === '2-2'">{{item.comment}}</span>
        <p class="messageTitle">{{item.title}}</p>
      </div>
    </li>
  </ul>
</template>

<script>
    export default {
      name: "messageBox",
      data () {
        return {
          len: 8,
        }
      },
      // 拿到从父组件传来的值，动态更新子组件的信息，重复利用
      props:[
        'messageList',
      ],
    }
</script>

<style scoped>
  .messageBox{
    margin: 45px;
    height: 100px;
    position: relative;
    background: #fff;
    box-shadow: 1px 3px 10px #65626285;
    list-style-type: none;
  }

  .messageCover{
    height: 100%;
    margin-left: 5px;
  }

  .personMsg{
    display: inline-block;
    position: absolute;
    top: 10%;
    margin-left: 10px;
    width: 85%;
    height: 35%;
  }

  .perAvatar{
    width: 25px;
    height: 25px;
    border-radius: 100%;
  }

  .messageTime{
    margin-right: 3%;
    /*float: right;*/
  }

  .msgMode{
    margin: 5px;
  }

  .messageTitle{
    margin-left: 5px;
    width: 90%;
    height: 65%;
    overflow: hidden;
  }

</style>
