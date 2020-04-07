<template>
  <el-container>
    <div id="top">
      <div class="school-dynamic">
        <div id="title">
          校<br>园<br>动<br>态
        </div>
        <el-carousel indicator-position="outside">
          <el-carousel-item>
            <img src="../assets/img/carousel/carousel1.jpg" height="100%" width="100%"/>
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/img/carousel/carousel2.jpg" height="100%" width="100%"/>
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/img/carousel/carousel3.jpg" height="100%" width="100%"/>
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/img/carousel/carousel4.jpg" height="100%" width="100%"/>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <div id="bottom">
      <div class="left">
        <el-tabs v-model="activeName" @tab-click="">
          <el-tab-pane label="校园通知" name="first">
            <div class="newest-notice module">
              <div class="title">
                <i class="el-icon-chat-dot-square"></i> 最 新 通 知
              </div>
              <div class="content">
                <div class="one theme-font-blue" v-for="item in newestNotice" :data-id="item.id" >
                  <div class="type">
                    [{{item.label}}]
                  </div>
                  <div class="text" @click="getContent(item.id)">{{item.title}}</div>
                  <div class="release">{{item.real_name}}</div>
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
                <div class="one theme-font-blue" v-for="item in goodAdvice" :data-id="item.id">
                  <div class="type">
                    [{{item.label}}]
                  </div>
                  <div class="text">{{item.title}}</div>
                  <div class="release">{{item.real_name}}</div>
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
                <div class="one theme-font-blue" v-for="item in magicalThinking" :data-id="item.id">
                  <div class="type">
                    [{{item.label}}]
                  </div>
                  <div class="text">{{item.title}}</div>
                  <div class="release">{{item.real_name}}</div>
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
          <div class="one" v-for="item in prominentTeacher">
            <div class="name theme-font-blue">{{item.name}}老师</div>
            <!--          <div class="subject theme-font-blue">数学</div>-->
<!--            <div class="grade theme-font-blue">2016届</div>-->
            <div class="class theme-font-blue">{{item.class}}</div>
          </div>
        </div>
        <div class="merit-student">
          <div class="header">
            <i class="el-icon-trophy-1"></i>
            三 好 学 生
          </div>
          <div class="one" v-for="item in meritStudent">
            <div class="name theme-font-blue">{{item.name}}同学</div>
<!--            <div class="grade theme-font-blue">2016届</div>-->
            <div class="subject theme-font-blue">{{item.class}}</div>
          </div>
        </div>
      </div>
    </div>

  </el-container>
</template>

<script>
    export default {
        name: "Hompage",
      data() {
        return{
          newestNotice: '',
          goodAdvice: '',
          magicalThinking: '',
          activeName: 'first',
          prominentTeacher: [
            {
              id: '',
              name: '张伟',
              class: '2016届物联网一班',
            },
            {
              id: '',
              name: '吕小布',
              class: '2016届物联网二班',
            },
            {
              id: '',
              name: '李星',
              class: '大数据一班',
            },
            {
              id: '',
              name: '吴达',
              class: '软件外包一班',
            },
            {
              id: '',
              name: '田七',
              class: '机算计科学与技术一班',
            },
          ],
          meritStudent: [
            {
              id: '',
              name: '王阔',
              class: '2016届物联网一班',
            },
            {
              id: '',
              name: '于洋',
              class: '2016届生物医学1班',
            },
            {
              id: '',
              name: '冯宇',
              class: '2016届大数据一班',
            },
            {
              id: '',
              name: '刘强',
              class: '2016届软件外包一班',
            },
            {
              id: '',
              name: '张智超',
              class: '2016届护理一班',
            },
          ],
        }
      },
      mounted() {
          this.$ajax.post("/hs/getAllContent",{},r=>{
            this.newestNotice = r.schoolNoticeList.slice(0,12);
            this.goodAdvice = r.parentAdvice.slice(0,12);
            this.magicalThinking = r.studentThinking.slice(0,12);
        })
      },
      methods: {
        getContent(id) {
          this.$router.push({name: 'content'});
          sessionStorage.setItem("noticeId",id);
        }
      }
    }
</script>

<style scoped>
  .release-time{
    color: #999;
    text-decoration: none;
    cursor: default;
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
    padding: 0 2rem;
  }
  .merit-student .one div:nth-child(1){
    flex: 2;
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
    padding: 0 2rem;
  }
  .prominent-teacher .one .subject{
    flex: 1!important;
  }
  .prominent-teacher .one div:nth-child(1){
    flex: 2;
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
    width: 30%;
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
    flex: 1;
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
    width: 80%;
    padding: 0 10%;
    height: 24rem;
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
    /*padding: 0 20%;*/
    background: #EEFCFE;
    display: block;
  }
</style>
