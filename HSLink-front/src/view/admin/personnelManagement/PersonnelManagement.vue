<template>
  <div id="demo"></div>
</template>

<script>
  import layui from 'layui';
  layui.use('table', function(){
    var table = layui.table;

    //第一个实例
    table.render({
      elem: '#demo'
      ,height: 312
      ,url: '192.168.10.29:8048/hs/test' //数据接口
      ,page: true //开启分页
      ,cols: [[ //表头
        {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
        ,{field: 'username', title: '用户名', width:80}
        ,{field: 'sex', title: '性别', width:80, sort: true}
        ,{field: 'city', title: '城市', width:80}
        ,{field: 'sign', title: '签名', width: 177}
        ,{field: 'experience', title: '积分', width: 80, sort: true}
        ,{field: 'score', title: '评分', width: 80, sort: true}
        ,{field: 'classify', title: '职业', width: 80}
        ,{field: 'wealth', title: '财富', width: 135, sort: true}
      ]]
    });

  });
    export default {
        name: "PersonnelManagement"
    }
</script>

<style scoped>

</style>
