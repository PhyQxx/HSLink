<template>
  <div class="page">
    <div class="top">
      <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="6rem">
        <el-form-item label="文章标题">
          <el-input
            v-model="queryParams.content"
            placeholder="请输入文章标题"
            clearable
            size="small"
            @keyup.enter.native="getVerifyList"
          />
        </el-form-item>
        <el-form-item label="作者姓名">
          <el-input
            v-model="queryParams.author"
            placeholder="请输入作者姓名"
            clearable
            size="small"
            @keyup.enter.native="getVerifyList"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="getVerifyList">搜索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="list-box" v-loading="loading">
      <no-date v-if="noDate"/>
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
          //参数
          queryParams: {
            content: '',
            author: ''
          },
          //加载标志
          loading: false,
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
              if (res.data > 0) {
                this.$notify({
                  type: 'success',
                  message: `${OPERATING[operating]}成功`
                });
                setTimeout(() => {
                  _this.getVerifyList();
                }, 1000)
              } else {
                this.$notify.error('服务器出小差了，请稍后再试');
              }
            })
          })
        },
        /**
        * 获取待审核文章列表
        */
        getVerifyList() {
          this.loading = true;
          this.$ajax.post('/admin/getVerifyList', this.queryParams, res => {
            if (res.data.length > 0) {
              this.noDate = false;
              this.articleList = res.data;
            } else {
              this.noDate = true;
            }
            this.loading = false
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
