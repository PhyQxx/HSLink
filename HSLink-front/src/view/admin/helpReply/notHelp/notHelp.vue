<template>
  <div class="page">
    <div class="no-help">
      <div class="help" v-for="(help, index) in notHelpList" :key='index'>
        <div class="question">
          <div class="label">提问内容：</div>
          <div class="content">{{help.question}}</div>
        </div>
        <div class="question-person">
          <div class="label">提问人：</div>
          <div class="content pointer theme-font-blue" @click="goToPersonal(help)">{{help.questionName}}</div>
        </div>
        <div class="question-time">
          <div class="label">提问时间：</div>
          <div class="content grey">{{help.create_time}}</div>
        </div>
        <el-button class="answer" type="success" size="small" @click="answer(help)">帮助解决</el-button>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        data() {
            return{
              //未回答帮助
              notHelpList: [],
              //未帮助列表标志
              notHelpNoData: false,
            }
        },
        mounted() {
          this.getHelpAnswerList()
        },
        methods: {
          /**
           * 帮助解决
           */
          answer(help) {
            let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
            this.$prompt(`${help.question}`, '请输入解决方案', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              inputPattern: /\S/,
              inputErrorMessage: '内容不能为空'
            }).then(({ value }) => {
              this.$ajax.post("/admin/answerHelp",{
                answerId: userInfo.user_id,
                answer: value,
                id: help.id}, res => {
                console.log('帮助解决', res);
                if (res === 1) {
                  this.$message({
                    type: 'success',
                    message: '提交成功'
                  });
                } else {
                  this.$message.error("提交失败")
                }
              })
            })
          },
          /**
           * 跳转个人首页
           */
          goToPersonal(help) {
            this.$router.push({name: 'personalinfo'});
            sessionStorage.setItem("userId",help.create_id);
          },
            /**
             * 获取帮助答复列表
             */
            getHelpAnswerList() {
              this.$ajax.post('/admin/getHelpAnswerList', {}, res => {
                res.forEach(item => {
                  if (item.answer_id === undefined) {
                    this.notHelpList.push(item);
                  }
                  this.notHelpNoData = this.notHelpList.length === 0 ? true : false;
                });
                console.log('未帮助答复列表', this.notHelpList);
              })
            }
        }
    }
</script>

<style scoped>
  .help{
    border: 1px solid rgb(221, 221, 221);
    margin-bottom: 1rem;
    border-radius: 5px;
  }
  .question, .question-person, .question-time{
    padding: 0.5rem;
    display: flex;
  }
  .answer{
    margin: 0.5rem;
  }
  .label{
    width: 6rem;
    text-align-last: justify;
    margin-right: 1rem;
  }
  .page{
    margin: 0;
    padding: 0;
  }
</style>
