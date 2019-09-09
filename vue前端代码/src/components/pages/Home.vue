<template>
	<div>
		    <!-- <img src="./assets/logo1.jpg"> -->
<el-container style="height: 500px; border: 1px solid #eee">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu :default-openeds="['1', '3']">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>用户</template>
        <el-menu-item-group>
         <!-- <template slot="title">分组一</template> -->
          <el-menu-item index="1-1"><router-link to="/dao/home/userlist">用户列表</router-link></el-menu-item>
          <el-menu-item index="1-2"><router-link to="/dao/home/order">订单</router-link></el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>商品</template>
        <el-menu-item-group>
          <el-menu-item index="2-1">选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-setting"></i>品牌</template>
        <el-menu-item-group>
         
          <el-menu-item index="3-1"><router-link to="/dao/home/phonelist">手机</router-link></el-menu-item>
          <el-menu-item index="3-2">电脑</el-menu-item>
					<el-menu-item index="3-3">服装</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-aside>
  
  <el-container>
    
    <el-main>
		   <router-view/>
    </el-main>
  </el-container>
</el-container>
	</div>
	
</template>

<script>
	
	export default {
  
  };
</script>

<style scoped>
	.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
	 a{
		text-decoration: none;
	}
</style>
