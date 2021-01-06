<template>
  <div class="page">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="等待帮助" name="1">
        <notHelp></notHelp>
      </el-tab-pane>
      <el-tab-pane label="历史帮助" name="2">
        <historyHelp></historyHelp>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import notHelp from '@/view/admin/helpReply/notHelp/notHelp';
  import historyHelp from '@/view/admin/helpReply/historyHelp/historyHelp';
    export default {
      components: {
        notHelp,
        historyHelp
      },
      name: "",
      data() {
        return{
          activeName: '1'
        }
      },
      mounted() {
      },
      methods: {
        /**
         * 切换tab
         */
        handleClick(tab, event) {
          console.log(tab, event);
        },
        
      }
    }
</script>

<style scoped>

</style>
