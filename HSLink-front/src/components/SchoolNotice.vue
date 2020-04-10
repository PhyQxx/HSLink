<template>
  <el-main>
    <div class="search">
      <el-input class="searchinp" v-model="condition" placeholder="请输入内容" @keydown.enter.native="search"></el-input>
      <el-button type="primary" class="searchbtn" @click="search">搜索</el-button>
    </div>
    <el-calendar>
      <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
      <template
        slot="dateCell"
        slot-scope="{date, data}">
        <div >{{ data.day.split('-').slice(1).join('-') }}</div>
        <br>
        <div class="things" v-html="dealMyDate(data.day)" @click="getDay(data)">
        </div>
      </template>
    </el-calendar>
    <el-dialog
      title="校园通知"
      :visible.sync="dialogVisible"
      width="40%">
      <div class="list">
        <div class="one" v-for="item in list" >
          <div class="type">[{{item.label}}]</div>
          <div class="text" @click="getContent(item.id)">{{item.title}}</div>
          <div class="release" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
          <div class="release-time">{{item.release_time}}</div>
        </div>
        <p class="sum">共{{length}}条数据</p>
      </div>
      <span slot="footer" class="dialog-footer">
<!--    <el-button @click="dialogVisible = false">取 消</el-button>-->
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </el-main>
</template>

<script>
  import $ from 'jquery'
    export default {
        name: "Home",
      data() {
          return{
            dialogVisible: false,
            condition: '',
            resDate: '',
            list: '',
            length: '',
          }
      },
      mounted() {
          this.$ajax.post("/hs/getListByAttribute",{type:"校园通知",text:''},r=>{
            let list = [];
            for (let i = 0; i < r.length; i++) {
              let one = {};
              one.date = r[i].release_time.substring(0,10);
              one.content = r[i].label;
              list.push(one)
            }
            this.resDate = list
          })
      },
      methods: {
        search() {
          sessionStorage.setItem("condition",this.condition)
          this.$router.push({name: "search"})
        },
        getContent(id) {
          this.$router.push({name: 'content'});
          sessionStorage.setItem("noticeId",id);
        },
        goPersonalInfo(userId) {
          this.$router.push({name: 'personalinfo'});
          sessionStorage.setItem("userId",userId);
        },
        getDay(date) {
          this.dialogVisible = true
          console.log(date)
          this.$ajax.post("/hs/getListByAttribute",{release_time: date.day,text: ''},
            r=> {
              this.list  = r;
              this.length  = r.length;
              })
        },
        dealMyDate(v) {
          let len = this.resDate.length
          let res = ""
          for(let i=0; i<len; i++){
            if(this.resDate[i].date == v) {
              res += "<div>"+this.resDate[i].content+"</div>"
              // break
            }
          }
          return res
        }
      }

    }
</script>
<style scoped>
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
    color: #999!important;
    text-decoration: none!important;
    cursor: default!important;
  }
  .sum{
    color: #999;
    font-size: 0.9rem;
    float: right;
    margin-right: 1rem;
  }
  .things{
    margin-top: -1rem;
    font-size: 0.5rem;
    color: #1989FA;
    max-height: 3rem;
    overflow: auto;
  }
  .search{
    margin: 0 0 3rem 0;
  }
  .el-main{
    padding: 1rem;
    /*background: #fff;*/
  }
  .searchinp{
    float: left;
    width: calc(100% - 6rem);

  }
  .searchbtn{
    float: left;
    width: 6rem;
  }
  .el-calendar{
    margin: 0;
  }
  .el-calendar__body{
    background: #fff;
  }
  .el-container{
    background: #EEFCFE;
    height: 45rem;
  }
  .is-selected {
    color: #1989FA;
  }
</style>
