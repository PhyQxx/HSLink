<template>
  <div class="page">
    <div class="no-help">
      <div class="help" v-for="(help, index) in historyHelpList" :key='index'>
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
        <div class="answer-content">
          <div class="label">解决方案：</div>
          <div class="content">{{help.answer}}</div>
        </div>
        <div class="answer-time">
          <div class="label">回答时间：</div>
          <div class="content grey">{{help.answer_time}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        data() {
            return{
              //历史帮助
              historyHelpList: [],
              //已帮助列表标志
              historyHelpNoData: false
            }
        },
        mounted() {
          this.getHelpAnswerList()
        },
        methods: {
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
                res.data.forEach(item => {
                  if (item.answer_id !== undefined) {
                    this.historyHelpList.push(item);
                  }
                  this.historyHelpNoData = this.historyHelpList.length === 0 ? true : false;
                });
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
  .question, .question-person, .question-time, .answer-content, .answer-personal, .answer-time{
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
