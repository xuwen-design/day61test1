<template>
	
	<div>
		<div>
		<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
		  <el-form-item label="品牌" prop="brandName">
		    <el-input v-model="ruleForm.brandName"></el-input>
		  </el-form-item>
		 
		  </el-form-item>
		
		  <el-form-item label="官网" prop="brandWeb">
		   <el-input v-model="ruleForm.brandWeb"></el-input>
		  </el-form-item>
		  
		  <el-form-item label="描述" prop="brandDesc">
		    <el-input v-model="ruleForm.brandDesc"></el-input>
		  </el-form-item>

		  <el-form-item label="联系方式" prop="telephone">
		    <el-input v-model="ruleForm.telephone"></el-input>
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
	export default{
		data() {
		  return {
			 brandId:'',
		    ruleForm: {
							brandId:'',
							brandName:'',
							brandWeb: '',
							brandDesc: '',
							telephone: '',
							email:''
		    },
		    rules: {
		      brandName: [
		        { required: true, message: '请输入品牌名', trigger: 'blur' }
		      ],
		      brandWeb: [
		        { required: true, message: '请输入新网址', trigger: 'blur' }
		      ],
		   brandDesc: [
		    {  required: true, message: '请新标语', trigger: 'blur' }
		  ],
		      telephone: [
		        { required: true, message: '请输入新联系方式', trigger: 'blur' }
		      ],
		  email:[
		        { required: true, message: '请输入邮箱', trigger: 'blur' }
		      ],
		    }
		  };
		},
		methods:{
			submitForm(formName) {
			var vue=this;
			  this.$refs[formName].validate((valid) => {
			    if (valid) {
			      console.log('submit!');			
			//发送请求 接口  新增数据
			this.axios({
				method:'POST',
				url:'http://localhost:80/ssm/phoneupdate',
				data:Qs.stringify(this.ruleForm)
			}).then(function(res){
				if(res.data.code==200){
						vue.$router.push("/dao/home/phonelist");
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
		    console.log('--------to:'+to.params.brandId+"--------->") // 
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
			var vue=this;
			//获取路由参数
			console.log("monted:-------->"+this.$route.params.brandId)
			//修改数据
			this.brandId=this.$route.params.brandId;
			//根据id查询信息
			this.axios({
				//根据id查询信息
				url:'http://localhost:80/ssm/selectBrandById/'+vue.brandId,
				method:'GET'
			}).then(res=>{
			//	vue.ruleForm=res.data.obj;
			//需要哪些赋值哪些
			/**
			 * 		userId:'',
							userAccount: '',
							sex: '',
							mobileNumber: '',
							email:''
			 */
			vue.ruleForm.brandName=res.data.object.brandName;
			vue.ruleForm.brandWeb=res.data.object.brandWeb;
			vue.ruleForm.brandDesc=res.data.object.brandDesc;
			vue.ruleForm.telephone=res.data.object.telephone;
			vue.ruleForm.email=res.data.object.email;
			})
		}
	}
</script>

<style>
</style>
