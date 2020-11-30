<template>
  <div class="oder">
    <div style="width:96%;height:160px;margin: 20px auto;background-color:#FFFFFF">
      <p style="color:#707070;padding:15px 40px;">查询</p>
      <div style="margin:0 20px;border-top:1px solid #EFEEEE">
        <div style="margin:15px 20px;">
          <el-input v-model="key" placeholder="昵称/手机号" style="width: 200px;height: 19px;" size="mini" round></el-input>
          <el-button size="mini" round style="margin-left: 15px;" @click="search()">搜索</el-button>
        </div>
        <div style="margin:15px 20px;">

          <span style="color:#707070;font-size:14px;">用户状态：</span>
          <el-select v-model="state" placeholder="全部" @change="changestatus" style="width: 120px;height: 19px;" size="mini">
            <el-option v-for="it in states" :key="it.value" :label="it.name" :value="it.value">
            </el-option>
          </el-select>

          <span style="color:#707070;font-size:14px;margin-left:10px;">用户类别：</span>
          <el-select v-model="type" placeholder="全部" @change="changetype" style="width: 120px;height: 19px;" size="mini">
            <el-option v-for="item in options" :key="item.value" :label="item.name" :value="item.value">
            </el-option>
          </el-select>

          <el-button size="mini" round style="margin-left: 15px;" @click="clear()">恢复默认设置</el-button>
        </div>
      </div>
    </div>

    <div style="width:96%;margin: 20px auto;background-color:#FFFFFF;padding-bottom:10px;position:relative;">
      <p style="color:#707070;padding:15px 20px;margin:0 20px;border-bottom:1px solid #EFEEEE">用户列表</p>
      <div>
        <el-table :data="tableData" style="width: 100%;margin:20px auto 10px auto;" stripe >
          <el-table-column label="用户编号" prop="id" align="center">
          </el-table-column>
          <el-table-column label="昵称" prop="nickname" align="center">
          </el-table-column>
          <el-table-column label="性别" prop="sex" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.sex|filters}}</span>
            </template>
          </el-table-column>
          <el-table-column label="手机号" prop="telephone" align="center">
          </el-table-column>
          <el-table-column label="邮箱" prop="email" align="center">
          </el-table-column>
          <el-table-column label="用户类别" prop="vip" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.vip|filters1}}</span>
            </template>
          </el-table-column>
          <el-table-column label="用户状态" prop="status" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.status|filters2}}</span>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="createTime" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center" width="172">
            <template slot-scope="scope">
              <el-button @click="change(scope.row.id,1,scope.row.status)" size="mini" round v-if="scope.row.vip===0">设为VIP</el-button>
              <el-button @click="change(scope.row.id,0,scope.row.status)" size="mini" round v-else>取消VIP</el-button>
              <el-button @click="change(scope.row.id,scope.row.vip,0)" size="mini" round v-if="scope.row.status===0">冻结</el-button>
              <el-button @click="change(scope.row.id,scope.row.vip,1)" size="mini" round v-else>解冻</el-button>
            </template>
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
        options:[{
          name:"普通用户",
          value:0
        },{
          name:"VIP用户",
          value:1
        }],
        states:[{
          name:"正常",
          value:0
        },{
          name:"冻结",
          value:1
        }],
        tableData: [
          {
            "id": "123",
            "nickname": "李青颖",
            "telephone": "13323332333",
            "sex": 1,
            "email": "b.trbpwrpqi@qq.com",
            "status": 0,
            "vip": 1,
            "createTime": "2020/11/7 01:00"
          },
          {
            "id": "123",
            "nickname": "白嘉欣",
            "telephone": "13323332333",
            "sex": 0,
            "email": "b.trbpwrpqi@qq.com",
            "status": 1,
            "vip": 0,
            "createTime": "2020/11/7 01:00"
          }
        ],
        type:"",
        state:"",
        key:"",
        total: 0,
        page: 1,
        pageSize: 10,
        currentPage1: 1,
      }
    },
    filters: {
      filters: function(arg) {
        if(arg == 0) {
          return "男"
        } else if(arg == 1) {
          return "女"
        }
      },
      filters1: function(arg) {
        if(arg == 0) {
          return "普通用户"
        } else if(arg == 1) {
          return "VIP用户"
        }
      },
      filters2: function(arg) {
        if(arg == 0) {
          return "正常"
        } else if(arg == 1) {
          return "冻结"
        }
      },
    },
    created() {
      this.getlist()
    },
    methods: {
      search(){
        this.getlist()
      },
      //已完成订单
      changetype(val){
        console.log(val)
        this.type=val;
        this.getlist()

      },
      changestatus(val){
        console.log(val)
        this.state=val
        this.getlist()
      },
      clear(){
        this.type=""
        this.key=""
        this.state =""
        this.getlist()
      },
      handleCurrentChange(val) {
        //分页
        this.page = val;
        this.getlist();
      },
      getlist() {
        // this.$http.get(`/api/admin/user`,{
        //   page: this.page,
        //   key: this.key,
        //   vip:this.type,
        //   status:this.status
        // }).then(response => {
        //   this.tableData = data.users
        //   this.total = data.totalPage
        // })
      },
      change(a,b,c){
        // this.$http.post('/api/admin/user/status', {
        //   id: a,
        //   vip: b,
        //   status: c
        // }).then(response => {
        //   console.log(data)
        // })
      }
    }
  }
</script>

<style scoped>
  .oder {
    width: 100%;
    background-color:#EFEEEE;
  }

  .active1 {
    background-color: #909399 !important;
    color: #FFFFFF!important;
  }

  .act {
    background-color: #909399 !important;
    color: #FFFFFF!important;
  }

  .btn {
    background-color: #909399 !important;
    color: #FFFFFF!important;
  }

  .addbtn{
    position:absolute;
    right:45px;
    top:12px;
  }
</style>

