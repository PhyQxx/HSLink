<template>
  <el-main>
    <div class="search">
      <el-input class="searchinp" v-model="condition" placeholder="请输入内容"></el-input>
      <el-button type="primary" class="searchbtn">搜索</el-button>
    </div>
    <el-calendar>
      <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
      <template
        slot="dateCell"
        slot-scope="{date, data}">
        <p :class="data.isSelected ? 'is-selected' : ''">
          {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}
        </p>
      </template>
    </el-calendar>
  </el-main>
</template>

<script>
    export default {
        name: "Home",
      data() {
          return{
            condition: '',
          }
      },

    }
</script>

<style scoped>
  .search{
    margin: 0 0 3rem 0;
  }
  .el-main{
    padding: 0;
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
