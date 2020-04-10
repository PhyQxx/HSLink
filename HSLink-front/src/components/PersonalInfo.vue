<template>
  <el-container>
    <el-main>
      <el-page-header @back="goBack" :content="pageTitle"></el-page-header>
      <div class="" v-if="editor">
        <div class="search">
          <el-input class="searchinp" v-model="condition" placeholder="请输入内容" @keydown.enter.native="search"></el-input>
          <el-button type="primary" class="searchbtn" @click="search">搜索</el-button>
        </div>
        <div class="list">
          <div class="one" v-for="item in list">
            <div class="type" @click="searchLabel(item.label)">
              [{{item.label}}]
            </div>
            <div class="text" @click="getContent(item.id)">{{item.title}}</div>
<!--            <div class="release">{{item.real_name}}</div>-->
            <div class="release-time">{{item.release_time}}</div>
          </div>
          <p class="sum">共{{length}}条数据</p>
        </div>
      </div>
      <addArticle
        v-if="!editor"
        @editorTitle="editorTitle"
        @goInfo="goInfo"
        @refresh="refresh"
      ></addArticle>
    </el-main>
    <el-aside>
      <div class="person-info">
        <div class="info">
          <div class="name">
            <div class="header-photo">{{all.header_photo}}</div>
            <div class="username">{{all.real_name}}</div>
            <div class="go-homepage pointer">TA的个人主页 ></div>
          </div>
          <div class="grades">
            <div class="grade">
              <span>等级:</span>
              <div class="number">
                <span>博客</span>
                <span>2</span>
              </div>
            </div>
            <div class="integral">
              <span>积分: </span>
              <span>{{all.integral}}</span>
            </div>
          </div>
        </div>
        <hr>
        <div class="operation" v-if="!isOwn">
          <el-button type="primary" plain>关 注</el-button>
          <el-button type="primary" plain>私 信</el-button>
        </div>
        <div class="operation" v-if="isOwn">
          <el-button type="primary" plain @click="goEditor">新增文章</el-button>
          <el-button type="primary" plain>修改密码</el-button>
        </div>
      </div>
      <footers></footers>
    </el-aside>
  </el-container>
</template>

<script>
  import addArticle from './AddArticle'
    export default {
        name: "PersonalInfo",
      data() {
          return{
            pageTitle: '个人中心',
            editor: true,
            condition: '',
            list: '',
            all: '',
            isOwn: true,
          }
      },
      mounted() {
        let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
        if (userInfo.user_id == sessionStorage.getItem("userId")) {
          this.isOwn = true
        } else {
          this.isOwn = false
        }
        this.$ajax.post("/hs/getListByAttribute",{text:"",releaseId: sessionStorage.getItem("userId")},r=>{
          this.list = r;
          this.length = r.length;
          this.all = {
            real_name: r[0].real_name,
            header_photo: r[0].real_name.substring(0, 1),
            integral: r[0].integral
          }
        })

      },
      methods: {
        refresh() {
          this.$ajax.post("/hs/getListByAttribute",{text:"",releaseId: sessionStorage.getItem("userId")},r=>{
            this.list = r;
            this.length = r.length;
            this.all = {
              real_name: r[0].real_name,
              header_photo: r[0].real_name.substring(0, 1),
              integral: r[0].integral
            }
          })
        },
        editorTitle(pageTitle) {
          this.pageTitle = pageTitle
        },
        goBack() {
          this.$router.back(-1)
        },
        goEditor() {
          this.editor = false
        },
        goInfo() {
          this.editor = true
        },
        searchLabel(label) {
          sessionStorage.setItem("condition",label)
          this.$router.push({name: "search"})
        },
        search() {
          sessionStorage.setItem("condition",this.condition)
          this.$ajax.post("/hs/getListByAttribute",{text:this.condition,releaseId: sessionStorage.getItem("userId")},r=>{
            this.list = r
            this.length = r.length
          })
        },
        getContent(id) {
          this.$router.push({name: 'content'});
          sessionStorage.setItem("noticeId",id);
        }
      },
      components: {
        addArticle
      }
    }
</script>

<style scoped>
  .el-page-header{
    padding: 1rem;
  }
  .search{
    padding: 1rem;
    margin-bottom: 2rem;
  }
  .searchinp{
    float: left;
    width: calc(100% - 6rem);

  }
  .searchbtn{
    float: left;
    width: 6rem;
  }
  .el-aside{
    margin-left: 1rem;
  }
  .el-main{
    padding: 0;
  }
  .sum{
    color: #999;
    font-size: 0.9rem;
    float: right;
    margin-right: 1rem;
  }
  .el-main{
    background: #fff;
  }
  .list{
    background: #fff;
    border-radius: 5px;
    padding: 1rem 1rem 3rem 1rem;
  }
  .list .one{
    display: flex;
    height: 1.5rem;
    line-height: 1.5rem;
    margin-bottom: 0.5rem;
    font-size: 0.9rem;
  }
  .one div{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: pointer;
    color: #1B94D4;
  }
  .one div:hover{
    text-decoration: underline;
  }
  .release-time{
    color: #999!important;
    text-decoration: none!important;
    cursor: default!important;
  }
  .one .type{
    flex: 1;
  }
  .one .text{
    flex: 4;
  }
  .one .release{
    flex: 1;
  }
  .one .release-time{
    flex: 2;
  }
  .operation .el-button:nth-child(1){
    margin-left: 1.5rem;
  }
  .operation .el-button:nth-child(2){
    margin-right: 1.5rem;
    float: right;
  }
  .integral span:nth-child(2)，.ranking span:nth-child(2){
    font-weight: bold;
  }
  .number{
    font-size: 0.8rem;
    color: #fff;
    border-radius: 5px;
    position: absolute;
    margin-left: 2.6rem;
    margin-top: -1.1rem!important;
  }
  .number span:nth-child(1){
    background: #9DC75F;
    float: left;
  }
  .number span:nth-child(2){
    background: #2D5315;
    float: left;
    min-width: 1rem;
    text-align: center;

  }
  .grades{
    margin-top: 1rem;
  }
  .grades div{
    margin-top: 0.5rem;
  }
  .person-info{
    background: #fff;
    padding: 1rem;
  }
  .username{
    margin-left: 3.5rem;
    font-size: 0.9rem;
  }
  .go-homepage{
    font-size: 0.9rem;
    color: #3399ea;
    margin-left: 3.5rem;
    margin-top: 0.5rem;
  }
  .header-photo{
    height: 3rem;
    width: 3rem;
    line-height: 3rem;
    font-weight: bold;
    border-radius: 50%;
    background: #3399ea;
    color: #fff;
    text-align: center;
    float: left;
    font-size: 1.5rem;
    margin-right: 2rem;
  }
</style>
