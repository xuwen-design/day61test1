<template>
	<div>
		<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
				<el-form-item label="品牌名" prop="brandName">
					<el-input v-model="ruleForm.brandName"></el-input>
				</el-form-item>
				
				<el-form-item label="品牌描述" prop="brandDesc">
					<el-input  v-model="ruleForm.brandDesc"></el-input>
				</el-form-item>
				
				<el-form-item label="品牌官网" prop="brandWeb">
					<el-input  v-model="ruleForm.brandWeb"></el-input>
				</el-form-item>

				<el-form-item label="联系方式" prop="telephone">
					<el-input v-model="ruleForm.telephone"></el-input>
				</el-form-item>
				 <el-form-item label="邮箱" prop="email">
					<el-input v-model="ruleForm.email"></el-input>
				</el-form-item>
				
<!-- 				<el-form-item label="邮箱" prop="email">
					<el-input v-model="ruleForm.email"></el-input>
				</el-form-item> -->
				
				<el-form-item>
					<el-button type="primary" @click="submitForm('ruleForm')">立即添加</el-button>
					<el-button @click="resetForm('ruleForm')">重置</el-button>
				</el-form-item>
</el-form>

	</div>
</template>

<script>
	import Qs from 'Qs';
  export default {
    data() {
      return {
        ruleForm: {
          brandName:'',
          brandDesc:'',
          brandWeb:'',
          telephone:'',
		email:''
        },
        rules: {
          brandName: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
         brandDesc: [
           { required: true, message: '描述', trigger: 'blur' },
           { min: 6,  message: '长度在 >6个字符', trigger: 'blur' }
         ],
          brandWeb: [
             { required: true, message: '请输入网址', trigger: 'blur' },
          ],
		telephone: [
		    {  required: true, message: '联系方式', trigger: 'change' }
		  ],
		 email: [
			   { required: true, message: '请输入email', trigger: 'blur' },
			]
			
			
			
		  /*
          date2: [
            { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ], */
       
        }
      };
    },
    methods: {
      submitForm(formName) {
		  var vue=this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log('submit!');
			 // this.ruleForm.createTime=this.$moment(this.ruleForm.recreateTime).format('YYYY/MM/DD HH:mm:ss');
			
			//发送请求 接口  新增数据
			this.axios({
				method:'POST',
				url:'http://localhost:80/ssm/brandadd',
				data:Qs.stringify(this.ruleForm)
			}).then(function(res){
				if(res.data.code==200){
						vue.$router.push("/dao/home/phonelist");
				}else{
					vue.$message('添加失败');
				}
				
				
			}).catch(function(){
				vue.$message('服务器端异常')
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
    }
  }
</script>

<style>
</style>
