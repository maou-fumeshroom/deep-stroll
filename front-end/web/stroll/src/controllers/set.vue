<template lang="pug">
.set
  .content_box
    .theme_box
      .theme_item(v-for="(theme ,index) in themes" :key="index" @click="settheme(theme.id)") {{theme.name}}
      .theme_item(@click="dialogFormVisible = true") 自定义
    .theme_box
      .theme_item(@click="changeActive('none')") 默认
      .theme_item(@click="changeActive('sun')") 阳光
      .theme_item(@click="changeActive('rain')") 雷雨
      .theme_item(@click="changeActive('snow')") 下雪

  el-dialog(title="自定义主题" :visible.sync="dialogFormVisible" width="500px")
    el-form(:model="add" v-loading="loading")
      el-form-item(label="背景图片" label-width="140px")
        div(v-if="add.bg==''")
          div.no-submit.bg-block 点击上传背景图片
            form(action="/" enctype='multipart/form-data' method="post")
              input.card-input(type="file" name="file"  placeholder="file" @change="uploadBg($event)")
        div.bg-card(v-else)
          img.bg-img(:src="add.bg")
          el-button.resubmit 重新上传
            form(action="/" enctype='multipart/form-data' method="post")
              input.card-input(type="file" name="file"  placeholder="file" @change="uploadBg($event)")
      el-form-item(label="背景音乐" label-width="140px")
        div(v-if="add.bgm" style="width:100%") {{bgmName}}
        el-button.resubmit {{add.bgm? '重新上传':'点击上传'}}
          form(action="/" enctype='multipart/form-data' method="post")
            input.card-input(type="file" name="file"  placeholder="file" @change="uploadBgm($event)")
    div(slot="footer" class="dialog-footer")
      el-button(@click="dialogFormVisible = false") 取 消
      el-button(type="primary" @click="handled()") 确 定

</template>

<script>
  export default {
    name: "set",
    data () {
      return {
        itemIndex:2,
        themes:[],
        dialogFormVisible: false,
        add:{
          bg:'',
          bgm:''
        },
        loading: false
      }
    },
    methods:{
      getTheme(){
        let _this = this
        this.$http.get('/api/config/themeList').then(function(res){
          console.log(res);
          _this.themes = res.data.themes
        }).catch(function(){
          console.log("服务器异常");
        });
      },
      changeActive(mode){
        this.$emit('changeActive',mode);
        localStorage.setItem("activeMode", mode);
      },
      settheme(id){
        let that = this
        this.$http.get('/api/config/theme',{
          params:{
            id:id
          }
        }).then(function(res){
          let theme = {
            bgm:res.data.bgmUrl,
            background:res.data.background
          }
          that.$emit('onSetTheme',theme);
        }).catch(function(){
          console.log("服务器异常");
        });
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
            _this.loading = false
          }
        }).catch(() => {})
      },
      handled(){
        let theme = {
          bgm:this.add.bgm,
          background:this.add.bg
        }
        this.$emit('onSetTheme',theme);
        this.dialogFormVisible = false
      }
    },
    created() {
      let that = this;
      this.getTheme()
    },
  }
</script>

<style scoped>
  .set{
    margin: 62px 15% 0 15%;
    height: calc(100vh - 62px);
    position:relative;
  }
  .content_box{
    max-width:800px;
    margin:auto;
    display: flex;
    align-items: center;
    justify-content: center;
    height:100%;
    flex-direction: column;
    flex: 1 1 0%;
    position:relative;
    z-index:2;
  }
  .theme_box{
    height:50px;
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    border-radius: 6px;
    box-shadow: 3px 3px 6px 3px rgba(0, 0, 0, .3);
    overflow: hidden;
    margin-bottom:40px;
  }
  .theme_item{
    cursor: pointer;
    text-decoration: none;
    padding: 0px 1.5em;
    overflow: hidden;
    transition: transform 500ms ease 0s, opacity 500ms ease 0s;
    display: flex;
    height:50px;
    line-height:50px;
    -webkit-backdrop-filter: blur(5px);
    backdrop-filter: blur(5px);
    border-right:1px solid rgba(0, 0, 0, .3);
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
</style>

