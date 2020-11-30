<template>
  <div class="oder">
    <div style="width:96%;margin: 20px auto;background-color:#FFFFFF;padding-bottom:10px;position:relative;">
      <p style="color:#707070;padding:15px 20px;margin:0 20px;border-bottom:1px solid #EFEEEE">管理员列表</p>
      <el-button size="mini" round class="addbtn" @click="dialogFormVisible = true">添加</el-button>
      <div>
        <el-table :data="tableData" style="width: 100%;margin:20px auto 10px auto;" stripe >
          <el-table-column label="管理员编号" prop="id" align="center">
          </el-table-column>
          <el-table-column label="账号" prop="account" align="center">
          </el-table-column>
          <el-table-column label="密码" prop="password" align="center">
          </el-table-column>
          <el-table-column label="类别" prop="roleName" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button @click="deleteAdmin(scope.row.id)" size="mini" round>删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <div class="block pageBox" style="text-align: center;">
          <el-pagination @current-change="handleCurrentChange" :current-page="currentPage1" :page-size="10" layout="total, prev, pager, next, jumper" :total='total'>
          </el-pagination>
        </div>
      </div>

      <el-dialog title="添加管理员" :visible.sync="dialogFormVisible">
        <el-form :model="add">
          <el-form-item label="账号" label-width="150px">
            <el-input v-model="add.account" autocomplete="off" style="width:220px;" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="150px">
            <el-input v-model="add.password" autocomplete="off" style="width:220px;" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="类别" label-width="150px">
            <el-select v-model="add.type" placeholder="请选择类别" style="width:220px;">
              <el-option v-for="item in options" :key="item.value" :label="item.name" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="handled()">确 定</el-button>
        </div>
      </el-dialog>

    </div>

  </div>
</template>

<script>
    export default {
      data() {
        return {
          options:[{
            name:"普通管理员",
            value:0
          },{
            name:"功能管理员",
            value:1
          }],
          tableData: [
            {
              "id": "123",
              "account": "1256984",
              "password": "13323332333",
              "roleName": "普通管理员"
            },
            {
              "id": "123",
              "account": "1256984",
              "password": "13323332333",
              "roleName": "用户管理员"
            }
          ],
          type:"",
          state:"",
          key:"",
          total: 0,
          page: 1,
          pageSize: 10,
          currentPage1: 1,
          dialogFormVisible: false,
          add:{
            type:'',
            account:'',
            password:''
          }
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
        deleteAdmin(a){
          // this.$http.post('/api/admin/delete', {
          //   id: a
          // }).then(response => {
          //   console.log(data)
          // })
        },
        handleoder(val, val1) {
          this.info = val1;
          console.log(this.info)
          this.dialogEadd = true
        },
        handleClose() {
          this.dialogEadd = false
        },
        handled(){
          if (this.add.account == ''||this.add.password == ''||this.add.type == ''){
            this.$message({
              message: '请填写完整',
              type: 'warning'
            });
            return;
          }
          console.log(this.add);
          let that =this;
          that._postData('/api/admin/add',{
            roleId:that.add.type,
            password:that.add.password,
            account:that.add.account
          },res=>{
            that.$message({
              message: '操作成功',
              type: 'success'
            });
            that.dialogFormVisible = false
            that.add.type = ''
            that.add.password = ''
            that.add.account = ''
            that.getlist()
          })
        }
      }
    }
</script>

<style scoped>
  .oder {
    width: 100%;
    background-color:#EFEEEE;
  }
  .btn {
    background-color: #909399 !important;
    color: #FFFFFF!important;
  }

  .addbtn{
    position:absolute;
    right:59px;
    top:12px;
  }
</style>
