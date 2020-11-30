<template>
	<div>
    <div style="display: flex;">
      <p style="margin-left:50px;">文章编号:<span style="margin-left: 30px;">{{id}}</span></p>
    </div>
    <div class="row">
      <p>文章标题:<span style="margin-left: 30px;">{{data.title}}</span></p>
    </div>
    <div class="row">
      <p>文章作者:<span style="margin-left: 30px;">{{data.autor.nickname}}</span></p>
    </div>
    <div class="row">
      <p>文章分类:<span style="margin-left: 30px;">{{data.classifyName}}</span></p>
    </div>
    <div class="row">
      <p>文章标签:<span style="margin-left: 30px;">{{data.labels}}</span></p>
    </div>
		<div class="row">
			<p>发布时间:<span style="margin-left: 30px;">{{data.dateTime}}</span></p>
    </div>
    <div class="row">
      <p>文章简介:<span style="margin-left: 30px;">{{data.introduction}}</span></p>
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
        "data": {
          "autor": {
            "id": "^^lM",
            "nickname": "lqy",
            "avatar": "eMTC"
          },
          "title": "论母猪养殖",
          "introduction": "这是一个由专业饲养员的自白",
          "fileUrl": "http://bai111111.oss-cn-beijing.aliyuncs.com/test.md",
          "likeNum": 50,
          "labels": [
            "畜牧业",
            "经验"
          ],
          "type": 0,
          "dateTime": "2020/11/23",
          "status": "上架",
          "classifyName": "科普"
        },
        htmlMD:'',
			}
		},
		props: ["id"],
    components: {
      VueMarkdown,
    },
		created() {
			this.getlist()
      const url = this.data.fileUrl;
      this.$http.get(url).then((response) => {
        this.htmlMD = response.data;
      });
		},
		filters: {
			filters1: function(arg) {
				if(arg == 0) {
					return "其他"
				} else if(arg == 1) {
					return "书籍"
				}
			},
		},
		methods: {
			//订单详情
			getlist() {

			},
      //下架
      offshelf(){
			  let _this = this
        this._postData('/admin/order/detail', {
            id: this.showlist.orderId,
          shelf:'off'
          }, data => {
          this.$message({
            message: '下架成功',
            type: 'success'
          })
          _this.getlist()
        })
      },
      //上架
      onshelf(){
        let _this = this
        this._postData('/admin/order/detail', {
          id: this.showlist.orderId,
          shelf:'off'
        }, data => {
          this.$message({
            message: '上架成功',
            type: 'success'
          })
          _this.getlist()
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
