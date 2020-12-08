<template>
  <div class="oder">
    <div style="width:96%;height:160px;margin: 20px auto;background-color:#FFFFFF">
      <p style="color:#707070;padding:15px 40px;">查询</p>
      <div style="margin:0 20px;border-top:1px solid #EFEEEE">
        <div style="margin:15px 20px;">
          <el-input v-model="key" placeholder="标题" style="width: 200px;height: 19px;" size="mini" round></el-input>
          <el-button size="mini" round style="margin-left: 15px;" @click="search()">搜索</el-button>
        </div>
        <div style="margin:15px 20px;">

          <span style="color:#707070;font-size:14px;">文章类别：</span>
          <el-select v-model="type" placeholder="全部" @change="changetype" style="width: 120px;height: 19px;" size="mini">
            <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>

          <span style="color:#707070;font-size:14px;margin-left:10px;">文章状态：</span>
          <el-select v-model="state" placeholder="全部" @change="changestatus" style="width: 120px;height: 19px;" size="mini">
            <el-option v-for="it in states" :key="it.value" :label="it.name" :value="it.value">
            </el-option>
          </el-select>

          <el-button size="mini" round style="margin-left: 15px;" @click="clear()">恢复默认设置</el-button>
        </div>
      </div>
    </div>

    <div style="width:96%;margin: 20px auto;background-color:#FFFFFF;padding-bottom:10px;position:relative;">
      <p style="color:#707070;padding:15px 20px;margin:0 20px;border-bottom:1px solid #EFEEEE">文章列表</p>
      <div>
        <el-table :data="tableData" style="width: 100%;margin:20px auto 10px auto;" stripe >
          <el-table-column label="文章编号" prop="id" align="center">
          </el-table-column>
          <el-table-column label="标题" prop="title" align="center">
          </el-table-column>
          <el-table-column label="发布者" prop="nickname" align="center">
          </el-table-column>
          <el-table-column label="分类" prop="classifyName" align="center">
          </el-table-column>
          <el-table-column label="状态" prop="status" align="center">
            <template slot-scope="scope">
              <span>{{scope.row.status|filters}}</span>
            </template>
          </el-table-column>
          <el-table-column label="发布时间" prop="dateTime" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <!--<el-button @click="change(scope.row.id,1)" size="mini" round v-if="scope.row.status===0">下架</el-button>-->
              <!--<el-button @click="change(scope.row.id,0)" size="mini" round v-else>上架</el-button>-->
              <el-button @click="detail(scope.row.id)" size="mini" round>查看详情</el-button>
            </template>
          </el-table-column>
        </el-table>

        <div class="block pageBox" style="text-align: center;">
          <el-pagination @current-change="handleCurrentChange" :current-page="currentPage1" :page-size="10" layout="total, prev, pager, next, jumper" :total='total'>
          </el-pagination>
        </div>
      </div>

    </div>

    <el-dialog title="文章详情" :visible.sync="dialogDetail" :center="true" :before-close="handleClose" width="1000px">
      <articledetail :id="id" :data="data" @closeUsermsg1="closeUsermsg1"></articledetail>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
      </span>
    </el-dialog>

  </div>
</template>


<script>
  import articledetail from '../../components/articledetail'
  export default {
    data() {
      return {
        dialogDetail:false,
        options:[],
        states:[{
          name:"正常",
          value:0
        },{
          name:"下架",
          value:1
        }],
        tableData: [],
        type:"",
        state:"",
        key:"",
        total: 0,
        page: 1,
        pageSize: 10,
        currentPage1: 1,
        data:{},
        id:'',
      }
    },
    components: {
      articledetail,
    },
    filters: {
      filters: function(arg) {
        if(arg == 0) {
          return "正常"
        } else if(arg == 1) {
          return "下架"
        }
      },
    },
    created() {
      this.getlist()
      this.getClass()
    },
    methods: {
      search(){
        this.getlist()
      },
      getClass() {
        let _this = this
        this.$http.get(`/api/article/classify`).then(res => {
          if (res.result.code === 1){
            _this.options = res.data.classifys
          }
        }).catch(err =>{})
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
        this.$http.get(`/api/article/search`,{params: {
            page: this.page,
            key: this.key,
            classify: this.type,
            status: this.state
          }
        }).then(res => {
          if (res.result.code === 1){
            this.tableData = res.data.articles
          }
        }).catch(err =>{})
      },
      change(a,b){
        let _this = this
        this.$http.post('/api/admin/work/status',{
          id: a,
          type:0,
          status:b
        }).then(res=>{
          if (res.result.code === 1){
            _this.$message({
              type: 'success',
              message: '操作成功!'
            });
            _this.getlist()
          }
        }).catch(() => {})
      },
      closeUsermsg1() {
        this.dialogDetail = false;
        this.getlist()
      },
      detail(id) {
        this.id = id
        this.$http.get(`/api/article/detail`,{params: {
            id:id
          }
        }).then(res => {
          if (res.result.code === 1){
            this.data = res.data
            this.dialogDetail = true
          }
        }).catch(err =>{})
      },
      handleClose() {
        this.dialogDetail = false
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
