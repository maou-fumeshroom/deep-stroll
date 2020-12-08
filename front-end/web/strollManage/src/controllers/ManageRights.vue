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
              <el-button v-if="scope.row.id>1" @click="deleteAdmin(scope.row.id)" size="mini" round>删除</el-button>
              <el-button v-else @click="deleteAdmin(scope.row.id)" size="mini" round disabled>删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <div class="block pageBox" style="text-align: center;">
          <el-pagination @current-change="handleCurrentChange" :current-page="currentPage1" :page-size="10" layout="total,prev, pager, next, jumper" :total='total'>
          </el-pagination>
        </div>
      </div>

      <el-dialog title="添加管理员" :visible.sync="dialogFormVisible" width="500px">
        <el-form :model="add">
          <el-form-item label="账号" label-width="140px">
            <el-input v-model="add.account" autocomplete="off" style="width:220px;" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="140px">
            <el-input v-model="add.password" autocomplete="off" style="width:220px;" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="类别" label-width="140px">
            <el-select v-model="add.type" placeholder="请选择类别" style="width:220px;">
              <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id">
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
            name:'',
            id:''
          }],
          tableData: [],
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
        this.getrole()
      },
      methods: {
        handleCurrentChange(val) {
          //分页
          this.page = val;
          this.getlist();
        },
        getrole(){
          this.$http.get(`/api/admin/role`).then(res => {
            if (res.result.code === 1){
              this.options = res.data.roles
            }
          }).catch(err =>{})
        },
        getlist() {
          this.$http.get(`/api/admin/list`,{params:{
              page:this.page
            }
          }).then(res => {
            if (res.result.code === 1){
              this.tableData = res.data.admins
              this.total = res.data.totalPage
            }
          }).catch(err =>{})
        },
        deleteAdmin(a){
          let _this = this
          this.$confirm('是否确认删除该管理员?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post('/api/admin/delete',{
              id: a
            }).then(res=>{
              if (res.result.code === 1){
                _this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                _this.getlist()
              }
            }).catch(() => {})
          })
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
          this.$http.post('/api/admin/add',{
            roleId:that.add.type,
            password:that.add.password,
            account:that.add.account
          }).then(res=>{
            if (res.result.code === 1){
              that.$message({
                message: '操作成功',
                type: 'success'
              });
              that.dialogFormVisible = false
              that.add.type = ''
              that.add.password = ''
              that.add.account = ''
              that.getlist()
            }
          }).catch(err =>{})
        }
      }
    }
</script>

<style scoped>
  .oder {
    width: 100%;
    background-color:#EFEEEE;
  }

  .addbtn{
    position:absolute;
    right:59px;
    top:12px;
  }
</style>
