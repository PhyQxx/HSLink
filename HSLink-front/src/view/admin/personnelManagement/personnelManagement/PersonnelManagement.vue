<template>
  <div class="page">
    <div class="top">

    </div>
    <no-date v-if="noDate"></no-date>
    <div class="table" v-if="!noDate">
      <div class="table-header">
        <div class="index box">序号</div>
        <div class="article-name box">人员名称</div>
        <div class="article-type box">人员类型</div>
        <div class="time box">注册时间</div>
        <div class="article-type box">是否冻结</div>
        <div class="function box">操作</div>
      </div>
      <div class="table-content">
        <div class="one-data" v-for="(person, index) in personList" :key="index">
          <div class="index box">{{index+1}}</div>
          <div class="article-name box theme-font-blue special-text" @click="goPersonalInfo(person.user_id)">{{person.real_name}}</div>
          <div class="article-type box">{{person.user_type}}</div>
          <div class="time box theme-font-blue special-text">{{person.create_time}}</div>
          <div class="article-type box" :class="person.frozen_state === '0' ? 'theme-font-green' : 'theme-font-red'">{{person.frozen_state === '0' ? '未冻结' : '已冻结'}}</div>
          <div class="function box">
            <el-button type="danger" size='mini' @click="peopleManagement(person, '1')" v-if="person.frozen_state === '0'">冻结</el-button>
            <el-button type="success" size='mini' @click="peopleManagement(person, '0')" v-if="person.frozen_state === '1'">解冻</el-button>
          </div>
        </div>
        <div class="number">共{{personList.length}}条数据</div>
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
    name: "PersonnelManagement",
    data() {
        return{
          //无数据
          noDate: true,
          //人员列表
          personList: []
        }
    },
    mounted() {
      this.getFollowPeopleList()
    },
    methods: {
      /**
			 * 冻结/解冻
			 * @param {Object} item
			 * @param {Object} operation 冻结/解冻
			 */
			peopleManagement(item, operating) {
				const OPERATING = {
					"0": "解冻",
					"1": "冻结",
				};
        let _this = this;
        _this.$confirm(`确认${OPERATING[operating]+item.real_name}?`, `${OPERATING[operating]}`, {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(() => {
          this.$ajax.post('/admin/peopleManagement', {
            userId: item.user_id,
            operating: operating
          }, res => {
            console.log("人员操作结果", res);
            if (res > 0) {
              this.$message({
                type: 'success',
                message: `${OPERATING[operating]+item.real_name}成功`
              });
              setTimeout(() => {
                _this.getFollowPeopleList();
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
      * 查看个人主页
      */
      goPersonalInfo(userId) {
        this.$router.push({name: 'personalinfo'});
        sessionStorage.setItem("userId",userId);
      },
      /**
       * 获取人员列表
       */
      getFollowPeopleList() {
        this.$ajax.post('/admin/getPersonnelManagement', {}, res => {
          console.log('人员列表', res);
          if (res.length > 0) {
            this.personList = res;
            this.noDate = false;
          } else {
            this.noDate = true
          }
        })
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
    text-align: center;
  }
  .article-name{
    width: 6rem;
    text-align: center;
  }
  .article-type{
    width: 6rem;
    color: #999999;
    text-align: center;
  }
  .time{
    width: 14rem;
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
</style>
