<template>
  <div class="oder">
    <div v-for="(item,index) in menu" :key="index">
      <div style="width:96%;margin: 20px auto;background-color:#FFFFFF;position:relative">
        <p style="color:#707070;padding:15px 40px">{{item.name}}</p>
        <el-checkbox v-model="item.state===0" class="check" @change="setState(item.id,item.state)">用户是否可见</el-checkbox>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        data(){
          return{
            "menu": []
          }
        },
      created() {
        this.getlist()
      },
      methods: {
        getlist() {
          this.$http.get(`/api/admin/menu`).then(res => {
            if (res.result.code === 1){
              this.menu = res.data.menu
            }
          }).catch(err =>{})
        },
        setState(id , state){
          let _this = this
          this.$http.post('/api/admin/menu/status',{
            id: id,
            status: 1-state
          }).then(res=>{
            if (res.result.code === 1){
              _this.$message({
                type: 'success',
                message: '操作成功!'
              });
              _this.getlist()
            }
          }).catch(() => {})
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
