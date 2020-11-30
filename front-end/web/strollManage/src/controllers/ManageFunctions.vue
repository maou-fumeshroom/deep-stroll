<template>
  <div class="oder">
    <div v-for="(item,index) in menu" :key="index">
      <div style="width:96%;margin: 20px auto;background-color:#FFFFFF;position:relative">
        <p style="color:#707070;padding:15px 40px">{{item.name}}</p>
        <el-checkbox v-model="item.status===0" class="check">用户是否可见</el-checkbox>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        data(){
          return{
            "menu": [
              {
                "path": "5w^W",
                "status": 0,
                "name": "文章",
                "id": -3023255962316768
              },
              {
                "path": "5w^W",
                "status": 1,
                "name": "手绘",
                "id": -3023255962316768
              },
            ]
          }
        }
    }
</script>

<style scoped>
  .oder {
    width: 100%;
    background-color:#EFEEEE;
  }
  .check{
    position:absolute;
    right:40px;
    top:15px;
  }
</style>
