<template>
  <div class="oder">
    <div style="width:96%;height:120px;margin: 20px auto;background-color:#FFFFFF">
      <p style="color:#707070;padding:15px 40px;">筛选</p>
      <div style="margin:0 20px;border-top:1px solid #EFEEEE">

        <div style="margin:15px 20px;">

          <span style="color:#707070;font-size:14px;">分类使用状态：</span>
          <el-select v-model="state" placeholder="全部" @change="changestatus" style="width: 120px;height: 19px;" size="mini">
            <el-option v-for="it in states" :key="it.value" :label="it.name" :value="it.value">
            </el-option>
          </el-select>

          <span style="color:#707070;font-size:14px;margin-left:10px;">分类类别：</span>
          <el-select v-model="type" placeholder="全部" @change="changetype" style="width: 120px;height: 19px;" size="mini">
            <el-option v-for="item in options" :key="item.value" :label="item.name" :value="item.value">
            </el-option>
          </el-select>

          <el-button size="mini" round style="margin-left: 15px;" @click="clear()">恢复默认设置</el-button>
        </div>
      </div>
    </div>

    <div style="width:96%;margin: 20px auto;background-color:#FFFFFF;padding-bottom:10px;position:relative;">
      <p style="color:#707070;padding:15px 20px;margin:0 20px;border-bottom:1px solid #EFEEEE">分类列表</p>
      <el-button size="mini" round class="addbtn" @click="dialogFormVisible = true">添加</el-button>
      <div>
        <el-table :data="tableData" style="width: 100%;margin:20px auto 10px auto;" stripe >
          <el-table-column label="分类编号" prop="id" align="center">
          </el-table-column>
          <el-table-column label="分类名称" prop="name" align="center">
          </el-table-column>
          <el-table-column label="分类状态" prop="status" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.status|filters}}</span>
            </template>
          </el-table-column>
          <!--<el-table-column label="操作" align="center">-->
            <!--<template slot-scope="scope">-->
              <!--<el-button @click="deleteAdmin(scope.row.id)" size="mini" round v-if="scope.row.status===0">弃用</el-button>-->
              <!--<el-button @click="change(scope.row.id)" size="mini" round v-else>恢复</el-button>-->
            <!--</template>-->
          <!--</el-table-column>-->
        </el-table>

        <div class="block pageBox" style="text-align: center;">
          <el-pagination @current-change="handleCurrentChange" :current-page="currentPage1" :page-size="10" layout="total, prev, pager, next, jumper" :total='total'>
          </el-pagination>
        </div>
      </div>

      <el-dialog title="添加分类" :visible.sync="dialogFormVisible" width="500px">
        <el-form :model="add">
          <el-form-item label="分类名称" label-width="150px">
            <el-input v-model="add.account" autocomplete="off" style="width:220px;" placeholder="请输入名称"></el-input>
          </el-form-item>
          <el-form-item label="分类类别" label-width="150px">
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
          name:"文章",
          value:0
        },{
          name:"手绘",
          value:1
        }],
        states:[{
          name:"正常",
          value:0
        },{
          name:"弃用",
          value:1
        }],
        tableData: [
          {
            "id": "123",
            "name": "散文",
            "status": 0,
          },
          {
            "id": "123",
            "name": "手绘",
            "status": 1,
          }
        ],
        type:0,
        state:"",
        total: 0,
        page: 1,
        pageSize: 10,
        currentPage1: 1,
        dialogFormVisible: false,
        add:{
          type:'',
          account:''
        }
      }
    },
    filters: {
      filters: function(arg) {
        if(arg == 0) {
          return "正常"
        } else if(arg == 1) {
          return "弃用"
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
      changetype(val){
        this.type=val;
        this.getlist()
      },
      changestatus(val){
        this.state=val
        this.getlist()
      },
      clear(){
        this.type=0
        this.state =""
        this.getlist()
      },
      handleCurrentChange(val) {
        //分页
        this.page = val;
        this.getlist();
      },
      getlist() {
        this.$http.get(`/api/admin/classify`,{
          params:{
            page: this.page,
            type: this.type,
            status: this.state
          }
        }).then(res => {
          if (res.result.code === 1){
            this.tableData = res.data.classifys
            this.total = res.data.totalPage
          }
        }).catch(err =>{})
      },
      change(a,b,c){
        // this.$http.post('/api/admin/user/status', {
        //   id: a,
        //   vip: b,
        //   status: c
        // }).then(response => {
        //   console.log(data)
        // })
      },
      handled(){
        if (this.add.account === ''|| this.add.type === ''){
          this.$message({
            message: '请填写完整',
            type: 'warning'
          });
          return;
        }
        console.log(this.add);
        let that =this;
        this.$http.post('/api/admin/classify/add',{
          name:that.add.account,
          type:that.add.type
        }).then(res=>{
          if (res.result.code === 1){
            that.$message({
              message: '操作成功',
              type: 'success'
            });
            that.dialogFormVisible = false
            that.add.account = ''
            that.add.bg = ''
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
    right:85px;
    top:12px;
  }
</style>
