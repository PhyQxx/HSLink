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
          <div class="article-name box">人员姓名</div>
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
              <el-button type="primary" size='mini' @click="openEdit(person)">编辑</el-button>
            </div>
          </div>
          <div class="number">共{{personList.length}}条数据</div>
        </div>
      </div>
    </div>
    <el-dialog
      title="个人信息"
    :visible.sync="dialogVisible"
    width="fit-content">
      <div class="person-info">
        <div class="one">
          <div class="label">姓名：</div>
          <div class="content">{{personInfo.real_name}}</div>
        </div>
        <div class="one">
          <div class="label">性别：</div>
          <div class="content">{{personInfo.sex}}</div>
        </div>
        <div class="one">
          <div class="label">出生日期：</div>
          <div class="content">{{personInfo.birthday}}</div>
        </div>
        <div class="one">
          <div class="label">手机号：</div>
          <div class="content">{{personInfo.mobile}}</div>
        </div>
        <div class="one">
          <div class="label">身份：</div>
          <div class="content">{{personInfo.user_type}}</div>
        </div>
        <div class="one">
          <div class="label">个性签名：</div>
          <div class="content">{{personInfo.signature}}</div>
        </div>
        <div class="one">
          <div class="label">职务：</div>
          <div class="content">
            <el-select size="mini"
                       v-model="personInfo.post"
                       filterable
                       allow-create
                       default-first-option
                       placeholder="请输入职务">
              <el-option v-for="item in postList"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value"/>
            </el-select>
          </div>
        </div>
        <div class="one">
          <div class="label">班级：</div>
          <div class="content">
            <el-select size="mini"
                       v-model="personInfo.class_name"
                       filterable
                       allow-create
                       default-first-option
                       placeholder="请输入班级">
              <el-option v-for="item in classList"
                         :key="item.class_name"
                         :label="item.class_name"
                         :value="item.class_name"/>
            </el-select>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updatePersonalInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import noDate from '@/components/components/NoData'
  import { getClassList } from '@/api/index/user'

  export default {
    components: {
      noDate
    },
    name: "PersonnelManagement",
    data() {
        return{
          //加载标志
          loading: false,
          //班级列表
          classList: [],
          //参数
          queryParams: {
            name: '',
            userType: ''
          },
          //角色列表
          userTypeList: [],
          //职务列表
          postList: [
            {label: '无', value: '无'},
            {label: '班主任', value: '班主任'},
          ],
          //编辑信息弹框标志
          dialogVisible: false,
          //无数据
          noDate: true,
          //人员列表
          personList: [],
          //个人信息
          personInfo: {}
        }
    },
    mounted() {
      this.getUserType();
      this.getClassList();
      this.getFollowPeopleList()
    },
    methods: {
      /**
       * 获取班级列表
       */
      getClassList() {
        getClassList({}).then(res => {
          if (res.data.data.length > 0) {
            res.data.data.forEach(item => {
              if (item && item.class_name !== '') {
                this.classList.push(item)
              }
            });
          }
        })
      },
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
       * 编辑个人信息
       */
      updatePersonalInfo() {
        if (this.personInfo.post === '班主任' && this.personInfo.user_type === '学生') {
          this.$notify.error('学生不可赋予班主任职务')
        } else {
          this.$ajax.post('/hs/updatePersonalInfo',
            {className: this.personInfo.class_name, post: this.personInfo.post, userId: this.personInfo.user_id}, res => {
              if (res.data > 0) {
                this.dialogVisible = false;
                this.$notify.success('修改成功')
              }
            })
        }
      },
      /**
       * 打开个人信息
       */
      openEdit(person) {
        this.dialogVisible = true;
        this.$ajax.post('/admin/getPersonInfo', {userId: person.user_id}, res => {
          this.personInfo = res.data
        })
      },
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
            if (res.data > 0) {
              this.$notify({
                type: 'success',
                message: `${OPERATING[operating]+item.real_name}成功`
              });
              setTimeout(() => {
                _this.getFollowPeopleList();
              }, 1000)
            } else {
              this.$notify.error('服务器出小差了，请稍后再试');
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
  .page {
    padding: 0;
    .table {
      padding: 0 1rem;
    }
  }
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
