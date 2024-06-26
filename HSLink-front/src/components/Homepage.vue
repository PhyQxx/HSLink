<template>
  <el-container>
    <div class="search">
      <el-input class="searchinp" v-model="condition" placeholder="请输入内容" @keydown.enter.native="search"></el-input>
      <el-button type="primary" class="searchbtn" @click="search">搜索</el-button>
    </div>
    <el-main>
      <div id="top">
        <div class="school-dynamic">
          <div id="title">
            校<br>园<br>动<br>态
          </div>
          <el-carousel indicator-position="outside">
            <el-carousel-item v-for="(item,index) in rotationPhotoList" :key="index">
              <img class="rotation-photo" :src='imgPath(item)'/>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
      <div id="bottom">
        <div class="left">
          <el-tabs v-model="activeName" v-loading="tabLoading">
            <el-tab-pane label="校园通知" name="first">
              <div class="newest-notice module">
                <div class="title">
                  <i class="el-icon-chat-dot-square"></i> 最 新 通 知
                </div>
                <div class="content">
                  <div class="one theme-font-blue" v-for="(item, index) in newestNotice" :data-id="item.id" :key='index'>
                    <div class="type" @click="searchLabel(item.label)">
                      [{{item.label}}]
                    </div>
                    <div class="text" @click="getContent(item.id)">{{item.title}}</div>
                    <div class="release" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
                    <div class="release-time">{{item.release_time}}</div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="家长意见" name="second">
              <div class="good-advice module">
                <div class="title">
                  <i class="el-icon-chat-line-square"></i> 优 秀 建 议
                </div>
                <div class="content">
                  <div class="one theme-font-blue" v-for="(item, index) in goodAdvice" :data-id="item.id" :key='index'>
                    <div class="type" @click="searchLabel(item.label)">[{{item.label}}]</div>
                    <div class="text" @click="getContent(item.id)">{{item.title}}</div>
                    <div class="release" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
                    <div class="release-time">{{item.release_time}}</div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="学生想法" name="third">
              <div class="magical-thinking module">
                <div class="title">
                  <i class="el-icon-chat-square"></i> 神 奇 想 法
                </div>
                <div class="content">
                  <div class="one theme-font-blue" v-for="(item, index) in magicalThinking" :data-id="item.id"  :key='index'>
                    <div class="type" @click="searchLabel(item.label)">[{{item.label}}]</div>
                    <div class="text" @click="getContent(item.id)">{{item.title}}</div>
                    <div class="release" @click="goPersonalInfo(item.user_id)">{{item.real_name}}</div>
                    <div class="release-time">{{item.release_time}}</div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
        <div class="right">
          <div class="prominent-teacher">
            <div class="header">
              <i class="el-icon-trophy"></i>
              优 秀 教 师
            </div>
            <div class="one" v-for="(item, index) in prominentTeacher"  :key='index'>
              <el-tooltip class="item" effect="light" :content="item.userName" placement="top">
                <div class="name theme-font-blue">{{item.userName}}老师</div>
              </el-tooltip>
              <el-tooltip class="item" effect="light" :content="item.post" placement="top">
                <div class="class theme-font-blue">{{item.post}}</div>
              </el-tooltip>
            </div>
          </div>
          <div class="merit-student">
            <div class="header">
              <i class="el-icon-trophy-1"></i>
              三 好 学 生
            </div>
            <div class="one" v-for="(item, index) in meritStudent" :key='index'>
              <el-tooltip class="item" effect="light" :content="item.userName" placement="top">
                <div class="name theme-font-blue">{{item.userName}}同学</div>
              </el-tooltip>
              <el-tooltip class="item" effect="light" :content="item.post" placement="top">
                <div class="class theme-font-blue">{{item.post}}</div>
              </el-tooltip>
            </div>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
  import { listWinners } from "@/api/admin/winners";

  export default {
        name: "Hompage",
      data() {
        return{
          //轮播图片
          rotationPhotoList: [],
          tabLoading: false,
          condition: '',
          newestNotice: '',
          goodAdvice: '',
          magicalThinking: '',
          activeName: 'first',
          prominentTeacher: [],
          meritStudent: [],
        }
      },
      mounted() {
          this.getRotationPhotoList();
          this.getNoticeList();
          this.getWinnerList()
      },
      methods: {
          /**
           * 获取获奖人员
           */
          getWinnerList() {
            listWinners().then(res => {
              if (res.data.data.length > 0) {
                res.data.data.forEach(item => {
                  if (item.type === '优秀教师') {
                    this.prominentTeacher.push(item)
                  } else if (item.type === '三好学生') {
                    this.meritStudent.push(item)
                  }
                })
              }
            })
          },
        /**
         * 图片地址
         */
        imgPath(item) {
          return `${sessionStorage.getItem('url')}/hs/showImg/${item.fileEncryption}`
        },
          //获取通知列表
        getNoticeList() {
          this.tabLoading = true;
          this.$ajax.post("/hs/getAllContent",{},r=>{
            this.tabLoading = false;
            this.newestNotice = r.data.schoolNoticeList.slice(0,12);
            this.goodAdvice = r.data.parentAdvice.slice(0,12);
            this.magicalThinking = r.data.studentThinking.slice(0,12);
          })
        },
          //获取轮播图片
        getRotationPhotoList() {
          this.$ajax.post('/hs/getRotationPhotoList',{},res=>{
            this.rotationPhotoList = res.data.rotationPhotoList;
          })
        },
        searchLabel(label) {
          sessionStorage.setItem("condition",label);
          this.$router.push({name: "search"})
        },
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
        }
      }
    }
</script>

<style scoped>
  .rotation-photo{
    object-fit: contain;
    width: 100%;
    height: 100%;
  }
  .el-main{
    padding: 0.5rem 1rem;
  }
  .search{
    padding: 1rem 1rem 0;
  }
  .searchinp{
    float: left;
    width: calc(100% - 6rem);

  }
  .searchbtn{
    float: left;
    width: 6rem;
  }
  .release-time{
    color: #999;
    text-decoration: none!important;
    cursor: default!important;
  }
  #top{
    background: #fff;
    border-radius: 5px;
    padding-bottom: 4rem;
  }
  #bottom{
    display: flex;
    background: #fff;
    margin-top: 1rem;
    border-radius: 5px;
    padding: 0 0 0 1rem;
  }
  #title{
    border-left: 1px solid #419EFF;
    cursor: default;
  }
  .merit-student, .prominent-teacher{
    font-size: 0.9rem;
  }
  .merit-student{
    border: 1px solid #419EFF;
    border-radius: 5px;
    background: #fff;
    margin-top: 1rem;
    width: 100%;
    height: 9.5rem;
  }
  .merit-student .header{
    cursor: default;
    height: 2rem;
    line-height: 2rem;
    padding: 0 1rem;
    font-weight: bold;
    color: #1B94D4;
    background: url("../assets/img/title-background.jpg");

  }
  .merit-student .one{
    display: flex;
    height: 1.5rem;
    line-height: 1.5rem;
    padding: 0 1rem;
  }
  .merit-student .one div:nth-child(1){
    flex: 3;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: pointer;
  }
  .merit-student .one div:nth-child(2){
    flex: 3;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: pointer;
  }
  .prominent-teacher .one{
    display: flex;
    height: 1.5rem;
    line-height: 1.5rem;
    padding: 0 1rem;
  }
  .prominent-teacher .one .subject{
    flex: 1!important;
  }
  .prominent-teacher .one div:nth-child(1){
    flex: 3;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: pointer;
  }
  .prominent-teacher .one div:nth-child(2){
    flex: 3;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: pointer;
  }
  .prominent-teacher{
    margin-top: 2.4rem;
    border: 1px solid #419EFF;
    border-radius: 5px;
    background: #fff;
    width: 100%;
    height: 9.5rem;
  }
  .prominent-teacher .header{
    cursor: default;
    height: 2rem;
    line-height: 2rem;
    padding: 0 1rem;
    font-weight: bold;
    color: #1B94D4;
    background: url("../assets/img/title-background.jpg");
  }
  .left{
    width: 70%;
  }
  .right{
    width: calc(30% - 2rem);
    padding: 1rem;
  }
  .one div{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: pointer;
  }
  .one div:hover{
    text-decoration: underline;
  }
  .content .one{
    display: flex;
    padding: 0 1rem;
    font-size: 0.9rem;
    height: 1.5rem;
    line-height: 1.5rem;
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
    flex: 1.6;
  }
  .module{
    border: 1px solid #419EFF;
    border-radius: 5px;
    background: #fff;
    width: calc(100% - 1rem);
    height: 20.2rem;
  }
  .module .title{
    cursor: default;
    height: 2rem;
    line-height: 2rem;
    padding: 0 1rem;
    font-weight: bold;
    color: #1B94D4;
    background: url("../assets/img/title-background.jpg");
  }
  .school-dynamic{
    display: flex;
    /*width: calc(100% - 2rem);*/
    padding: 1rem;
    height: 24rem;
    width: 80%;
    margin: 0 auto;
  }
  #title{
    color: #419EFF;
    padding: 1rem;
    font-size: 1.5rem;
    font-family: 楷体;
  }
  .el-carousel >>> .el-carousel__container{
    height: 90% !important;
  }
  .el-carousel >>> .el-carousel__indicators{
    height: 10%!important;
  }
  .el-carousel{
    margin-top: 1rem;
    padding: 1rem;
    width: calc(100% - 3.5rem);
    height: 100%;
    border: 1px solid #419EFF;
    border-radius: 5px;
    background: #fff;
  }
  .el-container{
    display: flex;
    flex-flow: column;
    /*padding: 0;*/
    background: #EEFCFE;
  }
</style>
