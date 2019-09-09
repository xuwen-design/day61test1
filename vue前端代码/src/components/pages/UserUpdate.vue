<template>
	
	<div>
		<div>
				<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
		  <el-form-item label="用户名" prop="userAccount">
		    <el-input v-model="ruleForm.userAccount"></el-input>
		  </el-form-item>
		 
		  </el-form-item>
		
		  <el-form-item label="性别" prop="sex">
		    <el-radio-group v-model="ruleForm.sex">
		      <el-radio  label="男"></el-radio>
		      <el-radio label="女"></el-radio>
		    </el-radio-group>
		  </el-form-item>
	
		  <el-form-item label="手机号" prop="mobileNumber">
		    <el-input v-model="ruleForm.mobileNumber"></el-input>
		  </el-form-item>
		   <el-form-item label="邮箱" prop="email">
		    <el-input v-model="ruleForm.email"></el-input>
		  </el-form-item>
		  <el-form-item>
		    <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
		    <el-button @click="resetForm('ruleForm')">重置</el-button>
		  </el-form-item>
		</el-form>
		
			</div>

	</div>
</template>

<script>
import Qs from 'Qs';
	export default{
		data() {
		  return {
			  userId:'',
		    ruleForm: {
				userId:'',
				userAccount:'',
				sex:'',
				mobileNumber:'',
				email:''
		    },
		    rules: {
		      userAccount: [
		        { required: true, message: '请输入用户名', trigger: 'blur' }
		      ],
		      recreateTime: [
		        { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
		      ],
		   sex: [
		    {  required: true, message: '请选择性别', trigger: 'change' }
		  ],
		      mobileNumber: [
		        { required: true, message: '请输入手机号', trigger: 'change' }
		      ],
		  email:[
		        { required: true, message: '请输入邮箱', trigger: 'blur' }
		      ],
		    }
		  };
		},
		methods:{
			submitForm(formName) {
			var vue=this
			  this.$refs[formName].validate((valid) => {
			    if (valid) {
			      console.log('submit!');			
			//发送请求 接口  新增数据
			this.axios({
	
				method:'POST',
				url:'http://localhost:80/ssm/userupdate',
				data:Qs.stringify(vue.ruleForm)
			}).then(function(res){
				if(res.data.code==200){
						vue.$router.push("/dao/home/userlist");
				}else{
					vue.$message('修改失败');
				}
				
				
			}).catch(function(){
				vue.$message({
					'message':'服务器端异常,修改失败',
					type:'error',
					showClose: true
				})
			})
			
			    } else {
			      console.log('error submit!!');
			      return false;
			    }
			  });
			},
			resetForm(formName) {
			  this.$refs[formName].resetFields();
			}
		},
		
		/**
		 * 路由钩子函数  在进入在组件之前会被执行
		 * 在执行beforRouteEnter时候，组件还没有被创建出来；先执行beforRouteEnter，再执行beforeCreate
		 * @param {Object} to   要跳转到的组件
		 * @param {Object} from 上一个组件
		 * @param {Object} next 函数 回调函数中vm表示组件实例 不能使用this
		 */
		 beforeRouteEnter(to,from,next){
		    console.log('beforRouteEnter')
		    console.log('--------to:'+to.params.userId+"--------->") // 
		    console.log();
			next((vm)=>{ //参数vm就是当前组件的实例。
			console.log("---------------->"+vm)
		    })
		},
		beforeRouteUpdate(to,from,next){
		    console.log('beforeRouteUpdate')
		    next()
		},
		beforeRouteLeave(to,from,next){//离开组件的时候触发
		  
		    next()
		},
		mounted:function () {
			var vue = this
			//获取路由参数
			console.log("monted:-------->"+this.$route.params.userId)
			//修改数据
			this.userId=this.$route.params.userId;
			//根据用户id查询用户信息
			this.axios({
				//根据用户id查询用户信息
				url:'http://localhost:80/ssm/selectById/'+vue.userId,
				method:'GET'
			}).then(function(res){
				vue.ruleForm.userAccount=res.data.object.userAccount;
				vue.ruleForm.userId=res.data.object.userId;
				vue.ruleForm.sex=res.data.object.sex;
				vue.ruleForm.mobileNumber=res.data.object.mobileNumber;
				vue.ruleForm.email=res.data.object.email;
			})
			
			
		}
	}
</script>

<style>
</style>
