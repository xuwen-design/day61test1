<template>
	<div>
		<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="用户名" prop="userAccount">
    <el-input v-model="ruleForm.userAccount"></el-input>
  </el-form-item>
  <el-form-item label="用户密码" prop="password">
    <el-input type="password" v-model="ruleForm.password"></el-input>
  </el-form-item>
 <!-- <el-form-item label="活动区域" prop="region">
    <el-select v-model="ruleForm.region" placeholder="请选择活动区域">
      <el-option label="区域一" value="shanghai"></el-option>
      <el-option label="区域二" value="beijing"></el-option>
    </el-select> -->
  </el-form-item>
  <el-form-item label="生日" required>
    <el-col :span="11">
      <el-form-item prop="recreateTime">
        <el-date-picker type="date" placeholder="生日" format="yyyy/MM/dd" v-model="ruleForm.recreateTime" style="width: 100%;"></el-date-picker>
      </el-form-item>
    </el-col>
   <!-- <el-col class="line" :span="2">-</el-col> -->
   <!-- <el-col :span="11">
      <el-form-item prop="date2">
        <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"></el-time-picker>
      </el-form-item>
    </el-col> -->
  </el-form-item>
  <!-- <el-form-item label="即时配送" prop="delivery">
    <el-switch v-model="ruleForm.delivery"></el-switch>
  </el-form-item>
  <el-form-item label="活动性质" prop="type">
    <el-checkbox-group v-model="ruleForm.type">
      <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
      <el-checkbox label="地推活动" name="type"></el-checkbox>
      <el-checkbox label="线下主题活动" name="type"></el-checkbox>
      <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
    </el-checkbox-group>
  </el-form-item> -->
  <el-form-item label="性别" prop="sex">
    <el-radio-group v-model="ruleForm.sex">
      <el-radio  label="男"></el-radio>
      <el-radio label="女"></el-radio>
    </el-radio-group>
  </el-form-item>
 <!-- <el-form-item label="活动形式" prop="desc">
    <el-input type="textarea" v-model="ruleForm.desc"></el-input>
  </el-form-item> -->
  <el-form-item label="手机号" prop="mobileNumber">
    <el-input v-model="ruleForm.mobileNumber"></el-input>
  </el-form-item>
   <el-form-item label="邮箱" prop="email">
    <el-input v-model="ruleForm.email"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
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
          userAccount: '',
          password: '',
          sex: '',
          recreateTime: '',
					createTime:'',
          mobileNumber: '',
					email:''
        },
        rules: {
          userAccount: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
         password: [
           { required: true, message: '请输入密码', trigger: 'blur' },
           { min: 6,  message: '长度在 >6个字符', trigger: 'blur' }
         ],
          recreateTime: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ],
		   sex: [
		    {  required: true, message: '请选择性别', trigger: 'change' }
		  ],
		  /*
          date2: [
            { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ], */
          mobileNumber: [
            { required: true, message: '请输入手机号', trigger: 'change' }
          ],
		  email:[
            { required: true, message: '请输入邮箱', trigger: 'blur' }
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
		  var vue=this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log('submit!');
			 this.ruleForm.createTime=this.$moment(this.ruleForm.recreateTime).format('YYYY/MM/DD HH:mm:ss');
			
			//发送请求 接口  新增数据
			this.axios({
				method:'POST',
				url:'http://localhost:80/ssm/useradd',
				data:Qs.stringify(this.ruleForm)
			}).then(function(res){
				if(res.data.code==200){
						vue.$router.push("/dao/home/userlist");
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
