<template>
  <el-container>
    <el-main>
      <el-page-header @back="goBack" content="搜索"></el-page-header>
      <div class="search">
        <el-input class="searchinp" v-model="condition" placeholder="请输入内容" @keydown.enter.native="search"></el-input>
        <el-button type="primary" class="searchbtn" @click="search">搜索</el-button>
      </div>
      <nodate v-if="noDate"></nodate>
      <div class="list" v-if="!noDate">
        <div class="one" v-for="item in list" >
            <div class="type">[{{item.label}}]</div>
            <div class="text" @click="getContent(item.id)">{{item.title}}</div>
            <div class="release" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
            <div class="release-time">{{item.release_time}}</div>
        </div>
        <p class="sum">共{{length}}条数据</p>
      </div>
    </el-main>
    <el-aside>
      <footers></footers>
    </el-aside>
  </el-container>

</template>

<script>
  import footers from './components/Footer'
  import nodate from './components/NoData'
    export default {
        name: "Search",
      data() {
          return{
            condition: '',
            list: '',
            length: '',
            noDate: false,
          }
      },
      mounted() {
          this.condition = sessionStorage.getItem("condition")
          this.$ajax.post("/hs/getListByAttribute",{text:this.condition},r=>{
            this.list = r
            this.length = r.length
            if (r.length === 0) {
              this.noDate = true
            }
          })
      },
      methods: {
        goBack() {
          this.$router.back(-1)
        },
        search() {
          sessionStorage.setItem("condition",this.condition)
          this.$ajax.post("/hs/getListByAttribute",{text:this.condition},r=>{
            this.list = r
            this.length = r.length
          })
        },
        getContent(id) {
          this.$router.push({name: 'content'});
          sessionStorage.setItem("noticeId",id);
        },
        goPersonalInfo(userId) {
          this.$router.push({name: 'personalinfo'});
          sessionStorage.setItem("userId",userId);
        }
      },
      components: {
          footers,
          nodate
      }
    }
</script>

<style scoped>
  .el-page-header{
    padding: 1rem;
  }
  .el-container{
    margin: 1rem;
  }
  .el-main{
    background: #fff;
  }
  .sum{
    color: #999;
    font-size: 0.9rem;
    float: right;
    margin-right: 1rem;
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
    cursor: default;
    text-decoration: none!important;
  }
  .one .text{
    flex: 4;
  }
  .one .release{
    flex: 1;
  }
  .one .release-time{
    flex: 1.5;
  }
  .el-aside{
    margin: -1rem 0 0 1rem;
    width: 14rem!important;
    overflow: hidden;
  }
  .el-main{
    padding: 0;
  }
  .search{
    margin: 0 0 3rem 0;
  }
  .searchinp{
    float: left;
    width: calc(100% - 6rem);

  }
  .searchbtn{
    float: left;
    width: 6rem;
  }
</style>
