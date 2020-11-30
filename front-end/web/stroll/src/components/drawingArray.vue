<template lang="pug">
    .drawings_box
      .is_show

        img.icon.disabled(v-if="showingIndex===0" :src="leftIcon_dis")
        img.icon(v-else :src="leftIcon" @click="showingIndex -= 1")

        img.img_showing(:src="showingImg")

        img.icon(v-if="showingIndex<drawingsSrc.length-1" :src="rightIcon" @click="showingIndex += 1")
        img.icon.disabled(v-else :src="rightIcon_dis")

      .drawings_list
        div(v-for="(img,index) in drawingsSrc" :key="index")
          img.list_item(:class="showingIndex===index?'is_choose':''" :src="img" @click="showingIndex = index")

</template>

<script>
    export default {
        name: "drawingArray",
      data() {
        return {
          showingImg:"",
          showingIndex:0,
          leftIcon:require('../../static/leftIcon.png'),
          rightIcon:require('../../static/rightIcon.png'),
          leftIcon_dis:require('../../static/leftIcon_dis.png'),
          rightIcon_dis:require('../../static/rightIcon_dis.png')
        }
      },
      props: {
        drawingsSrc: {             //
          type: Array,
          required: true,
        },
      },
      watch: {
        showingIndex(newV, oldV) {
          this.showingImg = this.drawingsSrc[newV]
        }
      },
      created() {
        this.showingIndex = 0,
        this.showingImg = this.drawingsSrc[0]
      },
    }
</script>

<style scoped>
  .drawings_box{
    width:100%;
    margin:50px 0 20px 0;
  }
  .is_show{
    width:100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    height:300px;
    overflow: hidden;
    padding:0 40px;
  }
  .img_showing{
    height:300px;
    width:auto;
  }
  .icon{
    width:40px;
    cursor: pointer;
  }
  .disabled{
    cursor: not-allowed;
  }
  .drawings_list{
    width:100%;
    display: flex;
    align-items: center;
    justify-content: center;
    height:70px;
    overflow: hidden;
    margin-top:50px;
  }
  .list_item{
    width:50px;
    height:50px;
    margin:0 10px;
    vertical-align:middle;
    object-fit: cover;
    overflow: hidden;
    border:2px solid #ffffff;
  }
  .is_choose{
    border:2px solid #000000;
  }
</style>
