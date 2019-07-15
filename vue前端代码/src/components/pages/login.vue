<template>
	<div>
		<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-position="left" label-width="0px" class="demo-ruleForm login-container">
			  <h3 class="title">后台管理系统登录</h3>
			  <el-form-item prop="userAccount">
				<el-input type="text" v-model="ruleForm.userAccount" auto-complete="off" placeholder="账号">
				</el-input>
			  </el-form-item>
			  
			  <el-form-item prop="password">
				<el-input type="password" v-model="ruleForm.password" auto-complete="off" placeholder="密码"></el-input>
			  </el-form-item>
			  
			  <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
			  
			  <el-form-item style="width:100%;">
				<el-button type="primary" style="width:45%;" @click="handleSubmit('ruleForm')" :loading="logining">登录</el-button>
				<el-button style="width:45%;" @click.native.prevent="handleReset('ruleForm')">重置</el-button>
				<el-button type="danger" style="width:60%;" @click.native.prevent="exit()" plain>退出</el-button>
			  </el-form-item>
			  
		</el-form>
	</div>
</template>

<script>
	import Qs from 'Qs';
	var vue;
	 export default {
		 /**
		  * 请参考：https://element.eleme.cn/#/zh-CN/component/form
		  */
	        data: function(){
	            return {
					logining:false,
					checked:true,
	                ruleForm: {
	                    userAccount: 'jack',
	                    password: '123456'
	                },
	                rules: {
	                    userAccount: [
	                        { required: true, message: '用户名不能为空', trigger: 'blur' }
	                    ],
	                    password: [
	                        { required: true, message: '密码不能为空', trigger: 'blur' }
	                    ]
	                }
	            }
	        },
			methods: {
					handleSubmit(ruleForm) {
						vue=this;
							
							//校验表单 validate()方法就是校验表单的方法，  valid校验的结果
					this.$refs[ruleForm].validate((valid) => {
					  if (valid) {//校验通过
						console.log('submit!');
						//发送axios来查询后台
						this.axios({
							method:'POST',
							data:Qs.stringify({
								userAccount:this.ruleForm.userAccount,
								password:this.ruleForm.password
							}),
							url:'http://localhost:80/ssm/login'
						}).then(function(res){
							console.log(res.data.code)
							if(res.data.code==200){
								
								sessionStorage.setItem("userAccount",res.data.object.userAccount);
								sessionStorage.setItem("userId",res.data.object.userId);
								// sessionStorage.setItem("headimageurl",res.date.object.headimageurl);
								console.log(res.data.object.headimageurl);
								
								 // vue.logining=true;
								vue.$router.push('/dao');
								
							}else{
								//elementui的提示框
								 vue.$message({
								  message: '用户名或者密码错误',
								  type: 'error',
								   showClose: true
								});
								//路由跳转
								  vue.$router.push('/');
							}
							
						}).catch(function(error){
						
						 vue.$message({
						  message: '服务器异常',
						  type: 'error',
						   showClose: false
						});
						})
						
					  }else {
						 vue.$message({
						   message: '服务器异常',
						   type: 'error',
						    showClose: true
						 });
						return false;
					  }
					});
			},
			handleReset(ruleForm) {
				this.$refs[ruleForm].resetFields();
			},
			exit() {
				//
			}
			
				
			}
    }
</script>

<style>
		  .login-container {
	  /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
	  -webkit-border-radius: 5px;
	  border-radius: 5px;
	  -moz-border-radius: 5px;
	  background-clip: padding-box;
	  margin: 180px auto;
	  width: 350px;
	  padding: 35px 35px 15px 35px;
	  background: #fff;
	  border: 1px solid #eaeaea;
	  box-shadow: 0 0 25px #cac6c6;
	  .title {
	    margin: 0px auto 40px auto;
	    text-align: center;
	    color: #505458;
	  }
	  .remember {
	    margin: 0px 0px 35px 0px;
	  }
	}
</style>
