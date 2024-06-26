<template>
  <el-container>
    <el-main>
      <div class="search">
        <el-input class="searchinp" v-model="condition" placeholder="请输入内容" @keydown.enter.native="search"></el-input>
        <el-button type="primary" class="searchbtn" @click="search">搜索</el-button>
      </div>
      <div class="list">
        <div class="title">学生想法</div>
        <div class="list-box" v-loading="loading">
          <nodate v-if="noDate"/>
          <div class="one" v-for="item in thinkingList" v-if="!noDate">
            <div class="type">[{{item.label}}]</div>
            <div class="text" @click="getContent(item.id)">{{item.title}}</div>
            <div class="release" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
            <div class="release-time">{{item.release_time}}</div>
          </div>
          <p class="sum" v-if="!noDate">共{{length}}条数据</p>
        </div>
      </div>
    </el-main>
    <el-aside>
      <footers></footers>
    </el-aside>
  </el-container>

</template>

<script>
  import footers from '../components/components/Footer'
  import nodate from '../components/components/NoData'
  export default {
    name: "StudentThought",
    data() {
      return{
        //加載标志
        loading: false,
        condition: '',
        thinkingList: '',
        length: '',
        noDate: false,
      }
    },
    mounted() {
      this.loading = true;
      this.$ajax.post("/hs/getListByAttribute",{text:"",type:"学生想法"},r=>{
        this.thinkingList = r.data;
        this.length = r.data.length;
        this.loading = false
      })
    },
    methods: {
      search() {
        this.$ajax.post("/hs/getListByAttribute",{text:this.condition,type:"学生想法"},r=>{
          this.thinkingList = r.data
          this.length = r.data.length
          if (r.data.length === 0) {
            this.noDate = true
          }
        })
      },
      getContent(id) {
        this.$router.push({name: 'content'});
        sessionStorage.setItem("noticeId",id);
      },
      goPersonalInfo(userId) {
        this.$router.push({name: 'personalinfo'});
        sessionStorage.setItem("userId",userId);
      },
    },
    components: {
      footers,
      nodate
    }
  }
</script>

<style scoped>
  .el-container{
    padding: 1rem;
  }
  .el-aside{
    margin: -1rem 0 0 1rem;
    width: 14rem !important;
    overflow: hidden;
  }
  .list .title{
    font-size: 1.5rem;
    margin-bottom: 1rem;
  }
  .el-main{
    padding: 0;
    background: #EEFCFE;
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
    text-decoration: none!important;
    cursor: default!important;
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
