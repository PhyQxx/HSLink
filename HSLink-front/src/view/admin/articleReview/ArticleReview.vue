<template>
  <div class="page">
    <div class="top">

    </div>
    <no-date v-if="noDate"></no-date>
    <div class="table" v-if="!noDate">
      <div class="table-header">
        <div class="index box">序号</div>
        <div class="article-name box">文章名称</div>
        <div class="article-type box">文章类型</div>
        <div class="article-label box">文章标签</div>
        <div class="author box">发表人</div>
        <div class="time box">发表时间</div>
        <div class="function box">操作</div>
      </div>
      <div class="table-content">
        <div class="one-data" v-for="(article, index) in articleList" :key="index">
          <div class="index box">{{index+1}}</div>
          <div class="article-name box theme-font-blue special-text" @click="getContent(article.articleId)">{{article.title}}</div>
          <div class="article-type box theme-font-blue special-text" @click="searchLabel(article.type)">{{article.type}}</div>
          <div class="article-label box theme-font-blue special-text" @click="searchLabel(article.label)">{{article.label}}</div>
          <div class="author box theme-font-blue special-text" @click="goPersonalInfo(article.releaseId)">{{article.releaseName}}</div>
          <div class="time box">{{article.releaseTime}}</div>
          <div class="function box">
            <el-button type="success" size='mini' @click="verifyArticle(article, '1')">通过</el-button>
            <el-button type="danger" size='mini' @click="verifyArticle(article, '2')">驳回</el-button>
          </div>
        </div>
        <div class="number">共{{articleList.length}}条数据</div>
      </div>
    </div>
  </div>
</template>

<script>
  import noDate from '@/components/components/NoData'
  export default {
      components: {
        noDate
      },
      data() {
        return {
          //待审核文章列表
          articleList: [],
          //无数据
          noDate: true
        }
      },
      mounted() {
        this.getVerifyList();
      },
      methods: {
        /**
         * 审核（通过/驳回）
         * @param {Object} item
         */
        verifyArticle(item, operating) {
          const OPERATING = {
            "1": "通过",
            "2": "驳回"
          };
          let _this = this;
          _this.$confirm(`确认${OPERATING[operating]}?`, '审核', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(() => {
            this.$ajax.post('/admin/verifyArticle', {
              articleId: item.articleId,
              operating: operating
            }, res => {
              console.log("审核结果", res);
              if (res > 0) {
                this.$message({
                  type: 'success',
                  message: `${OPERATING[operating]}成功`
                });
                setTimeout(() => {
                  _this.getVerifyList();
                }, 1000)
              } else {
                this.$message({
                  type: 'error',
                  message: '服务器出小差了，请稍后再试'
                });
              }
            })
          })
        },
        /**
        * 获取待审核文章列表
        */
        getVerifyList() {
          this.$ajax.post('/admin/getVerifyList', {}, res => {
            console.log('待审核文章列表', res);
            if (res.length > 0) {
              this.noDate = false;
              this.articleList = res;
            } else {
              this.noDate = true;
            }
          })
        },
        /**
         * 获取文章详情
         */
        getContent(id) {
          this.$router.push({name: 'content'});
          sessionStorage.setItem("noticeId",id);
        },
        /**
         * 按条件查询文章
         */
        searchLabel(label) {
          sessionStorage.setItem("condition",label);
          this.$router.push({name: "search"})
        },
        /**
         * 查看个人主页
        */
        goPersonalInfo(userId) {
          this.$router.push({name: 'personalinfo'});
          sessionStorage.setItem("userId",userId);
        }
      }
    }
</script>

<style scoped>
  .el-button+.el-button{
    margin-left: 0;
  }
  .table-content{
    padding-bottom: 2rem;
  }
  .number{
    font-size: 0.9rem;
    color: #999;
    float: right;
    margin: 1rem;
  }
  .special-text{
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    cursor: pointer;
  }
  .special-text:hover{
    text-decoration: underline;
  }
  .index{
    width: 3rem;
  }
  .article-name{
    width: 15rem;
  }
  .article-type{
    width: 6rem;
  }
  .article-label{
    width: 6rem;
  }
  .author{
    width: 6rem;
  }
  .time{
    width: 10rem;
    color: #999;
    text-decoration: none!important;
    cursor: default!important;
  }
  .function{
    width: 10rem;
  }
  .box{
    text-align: center;
  }
  .table-header{
    color: #999;
  }
  .one-data{
    font-size: 0.9rem;
  }
  .table-header, .one-data{
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ADDFFA;
    padding: 0.5rem 0;
  }
  .page{
    margin: 1rem;
    border-radius: 5px;
    padding: 1rem;
    background-color: #ffffff;
  }
</style>
