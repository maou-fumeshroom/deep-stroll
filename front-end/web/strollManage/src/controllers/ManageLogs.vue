<template>
  <div class="oder">
    <div style="width:96%;margin: 20px auto;background-color:#FFFFFF;padding-bottom:10px;position:relative;">
      <p style="color:#707070;padding:15px 20px;margin:0 20px;border-bottom:1px solid #EFEEEE">日志列表</p>
      <div>
        <el-table :data="tableData" style="width: 100%;margin:20px auto 10px auto;" stripe >
          <el-table-column label="时间" prop="dataTime" align="center">
          </el-table-column>
          <el-table-column label="管理员" prop="adminId" align="center">
          </el-table-column>
          <el-table-column label="操作" prop="content" align="center">
          </el-table-column>
        </el-table>

        <div class="block pageBox" style="text-align: center;">
          <el-pagination @current-change="handleCurrentChange" :current-page="currentPage1" :page-size="10" layout="total, prev, pager, next, jumper" :total='total'>
          </el-pagination>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [
          {
            "content": "删除了一个用户",
            "adminId": "111",
            "dataTime": "2020/11/09 05:00"
          },
          {
            "content": "下架了一个文章",
            "adminId": "222",
            "dataTime": "2020/11/07 12:00"
          }
        ],
        total: 0,
        page: 1,
        pageSize: 10,
        currentPage1: 1,
      }
    },
    created() {
      this.getlist()
    },
    methods: {
      handleCurrentChange(val) {
        //分页
        this.page = val;
        this.getlist();
      },
      getlist() {
        // this.$http.get(`/api/admin/list`,{
        //   page: this.page
        // }).then(response => {
        //   this.tableData = data.users
        //   this.total = data.totalPage
        // })
      },
    }
  }
</script>

<style scoped>
  .oder {
    width: 100%;
    background-color:#EFEEEE;
  }
</style>
