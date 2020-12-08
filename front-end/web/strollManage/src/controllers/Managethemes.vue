<template>
  <div class="oder">
    <div style="width:96%;margin: 20px auto;background-color:#FFFFFF;padding-bottom:10px;position:relative;">
      <p style="color:#707070;padding:15px 20px;margin:0 20px;border-bottom:1px solid #EFEEEE">管理员列表</p>
      <el-button size="mini" round class="addbtn" @click="dialogFormVisible = true">添加</el-button>
      <div>
        <el-table :data="tableData" style="width: 100%;margin:20px auto 10px auto;" stripe >
          <el-table-column label="主题编号" prop="id" align="center">
          </el-table-column>
          <el-table-column label="主题名称" prop="name" align="center">
          </el-table-column>
          <el-table-column label="背景图片" prop="bg" align="center">
            <template slot-scope="scope">
              <img class="table-img" :src="scope.row.bg"/>
            </template>
          </el-table-column>
          <el-table-column label="背景音乐" prop="bgm" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center" width="172">
            <template slot-scope="scope">
              <el-button v-if="scope.row.isdefault === 0" @click="setDefault(scope.row.id)" size="mini" round>设为默认</el-button>
              <el-button @click="deleteAdmin(scope.row.id)" size="mini" round>删除</el-button>
            </template>
          </el-table-column>
        </el-table>

      </div>

      <el-dialog title="添加主题" :visible.sync="dialogFormVisible" width="500px">
        <el-form :model="add" v-loading="loading">
          <el-form-item label="主题名称" label-width="140px">
            <el-input v-model="add.name" autocomplete="off" style="width:220px;" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item label="背景图片" label-width="140px">
            <div v-if="add.bg==''">
              <div class="no-submit bg-block">点击上传背景图片
                <form action="/" enctype='multipart/form-data' method="post">
                  <input class="card-input" type="file" name="file"  placeholder="file" @change="uploadBg($event)"></input>
                </form>
              </div>
            </div>
            <div v-else class="bg-card">
              <img class="bg-img" :src="add.bg"/>
              <el-button class="resubmit"> 重新上传
                <form action="/" enctype='multipart/form-data' method="post">
                  <input class="card-input" type="file" name="file"  placeholder="file" @change="uploadBg($event)"></input>
                </form>
              </el-button>
            </div>
          </el-form-item>
          <el-form-item label="背景音乐" label-width="140px">
            <div v-if="add.bgm" style="width:100%">{{bgmName}}</div>
            <el-button class="resubmit" v-if="add.bgm" @click="audioPlay()"> {{bgmRunning? '暂停':'播放'}}</el-button>
            <el-button class="resubmit"> {{add.bgm? '重新上传':'点击上传'}}
              <form action="/" enctype='multipart/form-data' method="post">
                <input class="card-input" type="file" name="file"  placeholder="file" @change="uploadBgm($event)"></input>
              </form>
            </el-button>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="handled()">确 定</el-button>
        </div>
      </el-dialog>

    </div>

    <audio ref="audio" :src="add.bgm"></audio>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        dialogFormVisible: false,
        add:{
          name:'',
          bg:'',
          bgm:''
        },
        bgmName:'',
        bgmRunning:false,
        loading:false,
      }
    },
    created() {
      this.getlist()
    },
    methods: {
      getlist() {
        this.$http.get(`/api/admin/theme`).then(res => {
          if (res.result.code === 1){
            this.tableData = res.data.themes
          }
        }).catch(err =>{})
      },
      deleteAdmin(a){
        let _this = this
        this.$confirm('是否确认删除该主题?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post('/api/admin/theme/delete',{
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
      setDefault(a){
        let _this = this
        this.$http.post('/api/admin/theme/default',{
          id: a
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
      uploadBg(event){
        let file = event.target.files[0]
        let fileType = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase();
        if (fileType!=="jpg" && fileType!=="png"){
          this.$message({
            type: 'warning',
            message: '图片格式不符合规定'
          });
          return
        }
        if(file.size >  1048576){
          this.$message({
            type: 'warning',
            message: '上传图片过大'
          });
          return
        }
        let param = new FormData()
        param.append('file', file)
        let config = {
          headers: {'Content-Type': 'multipart/form-data'}
        }
        let _this = this
        _this.loading = true
        this.$http.post('/api/file',param, config).then(res=>{
          if (res.result.code === 1){
            _this.$message({
              type: 'success',
              message: '上传成功!'
            });
            console.log(res)
            _this.add.bg = res.data.url
            _this.loading = false
          }
        }).catch(() => {})
      },
      audioPlay(){
        if (this.bgmRunning){
          this.$refs.audio.pause()
          this.bgmRunning = false
        } else {
          this.$refs.audio.play()
          this.bgmRunning = true
        }
      },
      uploadBgm(event){
        let file = event.target.files[0]
        let fileType = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase();
        if (fileType!=="mp3" && fileType!=="mkv" && fileType!=="wma"){
          this.$message({
            type: 'warning',
            message: '音频格式不符合规定'
          });
          return
        }
        if(file.size >  10485760){
          this.$message({
            type: 'warning',
            message: '上传音频过大'
          });
          return
        }
        let param = new FormData()
        param.append('file', file)
        let config = {
          headers: {'Content-Type': 'multipart/form-data'}
        }
        let _this = this
        _this.loading = true
        this.$http.post('/api/file',param, config).then(res=>{
          if (res.result.code === 1){
            _this.$message({
              type: 'success',
              message: '上传成功!'
            });
            console.log(res)
            _this.add.bgm = res.data.url
            _this.bgmName = file.name
            _this.loading = false
          }
        }).catch(() => {})
      },
      handled(){
        if (this.add.name === ''||this.add.bgm === ''||this.add.bg === ''){
          this.$message({
            message: '请填写完整',
            type: 'warning'
          });
          return;
        }
        console.log(this.add);
        let that =this;
        this.$http.post('/api/admin/theme/add',{
          name:that.add.name,
          bg:that.add.bg,
          bgm:that.add.bgm
        }).then(res=>{
          if (res.result.code === 1){
            that.$message({
              message: '操作成功',
              type: 'success'
            });
            that.dialogFormVisible = false
            that.add.name = ''
            that.add.bg = ''
            that.add.bgm = ''
            that.bgmRunning = false
            that.$refs.audio.pause()
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

  .no-submit{
    position: relative;
    width: 220px;
    text-align:center;
  }
  .bg-card {
    width: 220px;
    display: block;
  }
  .bg-block {
    border-radius: 4px;
    border: 1.5px dashed #00a0e9;
    padding:30px 0;
    font-size:13px;
    color: #00a0e9;
  }
  .card-input {
    cursor: pointer;
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    outline: none;
    background-color: transparent;
    opacity: 0;
  }
  .resubmit{
    position:relative;
  }
  .bg-img{
    width:220px;
    height:auto;
    border-radius: 4px;
  }
  .table-img{
    width:200px;
    height:auto;
    border-radius: 4px;
    vertical-align:middle;
  }
</style>

