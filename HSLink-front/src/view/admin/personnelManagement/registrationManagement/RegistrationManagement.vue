<template>
  <div class="page">
    <div class="top">
      <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="6rem">
          <el-form-item label="人员姓名">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入人员姓名"
              clearable
              size="small"
              @keyup.enter.native="getFollowPeopleList"
            />
          </el-form-item>
          <el-form-item label="人员类型">
            <el-select v-model="queryParams.userType" placeholder="请选择人员类型" clearable size="small">
              <el-option
                v-for="dict in userTypeList"
                :key="dict.id"
                :label="dict.dd_detail"
                :value="dict.dd_detail"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="getFollowPeopleList">搜索</el-button>
          </el-form-item>
        </el-form>
    </div>
    <div class="list-box" v-loading="loading">
      <no-date v-if="noDate"/>
      <div class="table" v-if="!noDate">
        <div class="table-header">
          <div class="index box">序号</div>
          <div class="article-name box">人员名称</div>
          <div class="article-type box">人员类型</div>
          <div class="time box">注册时间</div>
          <div class="function box">操作</div>
        </div>
        <div class="table-content">
          <div class="one-data" v-for="(person, index) in personList" :key="index">
            <div class="index box">{{index+1}}</div>
            <div class="article-name box theme-font-blue special-text" @click="goPersonalInfo(person.user_id)">{{person.real_name}}</div>
            <div class="article-type box">{{person.user_type}}</div>
            <div class="time box theme-font-blue special-text">{{person.create_time}}</div>
            <div class="function box">
              <el-button type="success" size='mini' @click="passRegistration(person)">通过</el-button>
              <el-button type="danger" size='mini' @click="deleteRegistration(person)">删除</el-button>
            </div>
          </div>
          <div class="number">共{{personList.length}}条数据</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import noDate from '@/components/components/NoData'
  import { delPerson, editPerson } from '@/api/index/user'

  export default {
    components: {
      noDate
    },
    name: "PersonnelManagement",
    data() {
      return{
        //加载标志
        loading: false,
        //参数
        queryParams: {
          name: '',
          userType: '',
          takeEffect: '0'
        },
        //角色列表
        userTypeList: [],
        //无数据
        noDate: true,
        //人员列表
        personList: [],
      }
    },
    mounted() {
      this.getUserType();
      this.getFollowPeopleList()
    },
    methods: {
      /**
       * 获取人员类型
       */
      getUserType() {
        this.getDict('UserType').then(res => {
          this.userTypeList = res.data.data.filter(item => {
            return item.dd_detail !== '管理员'
          })
        })
      },
      /**
       * 删除人员
       */
      deleteRegistration(person) {
        this.$confirm(`是否确认删除${person.real_name}的注册信息`, "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPerson(person.user_id);
        }).then(() => {
          this.getFollowPeopleList();
          this.$notify.success("删除成功");
        })
      },
      /**
       * 通过审核
       */
      passRegistration(person) {
        this.$confirm(`是否确认通过${person.real_name}的注册信息`, "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return editPerson({userId: person.user_id, takeEffect: '1'});
        }).then(() => {
          this.getFollowPeopleList();
          this.$notify.success("通过成功");
        })
      },
      /**
       * 获取人员列表
       */
      getFollowPeopleList() {
        this.loading = true;
        this.$ajax.post('/admin/getPersonnelManagement', this.queryParams, res => {
          if (res.data.length > 0) {
            this.personList = res.data;
            this.noDate = false;
          } else {
            this.noDate = true
          }
          this.loading = false
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .person-info {
    width: 40vw;
    display: flex;
    flex-flow: column;
    .one {
      display: flex;
      margin-bottom: 1rem;
      align-items: center;
      .label {
        width: 6em;
        text-align-last: justify;
      }
      .content {
        flex: 1;
        color: #00afff;
      }
    }
  }
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
    display: flex;
    .el-button {
      margin-right: 0.5rem;
    }
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
