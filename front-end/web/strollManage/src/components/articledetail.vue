<template>
	<div>
    <div style="display: flex;">
      <p style="margin-left:50px;">文章编号:<span style="margin-left: 30px;">{{id}}</span></p>
    </div>
    <div class="row">
      <p>文章标题:<span style="margin-left: 30px;">{{data.title}}</span></p>
    </div>
    <div class="row" v-if="data.author">
      <p>文章作者:<span style="margin-left: 30px;">{{data.author.nickname}}</span></p>
    </div>
    <div class="row">
      <p>文章分类:<span style="margin-left: 30px;">{{data.classifyName}}</span></p>
    </div>
		<div class="row">
			<p>发布时间:<span style="margin-left: 30px;">{{data.dateTime}}</span></p>
    </div>
    <div class="row">
      <p style="float:left;">文章简介:</p>
      <span style="margin-left: 30px;display:block;float:left;width:700px;">{{data.introduction}}</span>
    </div>
    <div class="row">
      <p>点赞数量:<span style="margin-left: 30px;">{{data.likeNum}}</span></p>
    </div>
		<p style="margin-top: 20px;margin-left:50px;">文章内容:</p>
		<div style="margin-top: 10px;margin-left:80px;width:800px;">
      <VueMarkdown :source="htmlMD"></VueMarkdown>
    </div>

	</div>
</template>

<script>
  import VueMarkdown from 'vue-markdown'
	export default {
		data() {
			return {
        htmlMD:'',
			}
		},
		props: ["data","id"],
    components: {
      VueMarkdown,
    },
    mounted(){
      this.getlist()
    },
		created() {
			this.getlist()
		},
		filters: {},
		methods: {
			getlist() {
        let url = this.data.fileUrl;
        this.getArticleContent(url)
			},
      getArticleContent(url){
        let that = this;
        fetch(url,{
          method:'GET',
          mode:'cors'
        }).then(function (response) {
          let text = response.text();
          return text;
        }).then(function (data) {
          // console.log(data)
          that.htmlMD = data;
        })
      }
		}
	}
</script>

<style scoped>
  .row{
    display: flex;
    margin-top: 20px;
    margin-left:50px;
  }
</style>
