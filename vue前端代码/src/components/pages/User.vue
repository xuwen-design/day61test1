<template>
	<div>
		<el-row :gutter="20">
  <el-col :span="4"><div class="grid-content bg-purple"><router-link to="/home/user/userall">全部</router-link></div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple"><router-link to="/home/user/usersh">待审核</router-link></div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple">带接待</div></el-col>
  <el-col :span="4"><div class="grid-content bg-purple">已接单</div></el-col>
    <el-col :span="4"><div class="grid-content bg-purple">未接单</div></el-col>
</el-row>

<div>
<router-view></router-view>
	
</div>
</div>
</template>

<script>
	export default{
		data() {
			return {
			
			}
		},
	}
</script>

<style scoped>
	.el-row {
    margin-bottom: 20px;
	text-align: center;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
