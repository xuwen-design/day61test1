<template>
	<div>
		<!-- <div style="margin-top: 20px">
		 
		
		</div> -->
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.userAccount" placeholder="姓名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="success" v-on:click="getusersPage">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
				
					<el-form-item>
					 <el-button @click="handleDeleteBatch()" type="danger">批量删除</el-button>
				</el-form-item>
					<el-form-item>
					  <el-button @click="toggleSelection()" type="info">取消选择</el-button>
				</el-form-item>
			</el-form>
		</el-col>
  <el-table ref="multipleTable"  :data="tableData" tooltip-effect="dark" style="width: 100%" highlight-current-row v-loading="listLoading" @selection-change="handleSelectionChange">
		<el-table-column type="selection" width="85">
		</el-table-column>
		
		<el-table-column prop="userId"  label="用户ID"  width="50">
		</el-table-column>
		
		<el-table-column  prop="userAccount"  label="账号" width="100"  show-overflow-tooltip>
		</el-table-column>
		
		<el-table-column  prop="sex"  label="性别"  show-overflow-tooltip>
			  </el-table-column> 
		<el-table-column  prop="userName"  label="用户真实姓名"  show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="mobileNumber"  label="手机号"  show-overflow-tooltip>
		</el-table-column>
		<el-table-column  prop="email" label="用户邮箱"  show-overflow-tooltip>
		</el-table-column>  
		
		
		 <el-table-column
				 prop="status"
				 label="状态"
				 show-overflow-tooltip :formatter="formatSex">
			   </el-table-column> 
			<el-table-column label="操作" width="250">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button size="small" type="warning"  @click="resetPwd(scope.$index, scope.row)">重置密码</el-button>	
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
		</el-table-column>
	</el-table>
	
	<div class="block">
   <!-- <span class="demonstration">完整功能</span> -->
    <el-pagination
	 background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageBean.currentPage"
      :page-sizes="[3, 10, 20, 100]"
      :page-size="pageBean.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pageBean.total">
    </el-pagination>
  </div>
	
	
  </div>
</template>

<script>
import Qs from 'Qs';
	var vue;
  export default {
		//初始化数据  vue的生命周期函数
		mounted:function () {
			this.getusersPage();
		},
    data() {
			
      return {
				filters: {
					userAccount: ''
				},
				listLoading:false,
        tableData: [],
        multipleSelection: [],
		pageBean:{
			currentPage:1,//当前页
			pageSize:3,//每页数据大小
			total:100//分页的总个数
		}
      }
    },

    methods: {
			//性别显示转换  row参数表示当前行所有数据
			formatSex: function (row) {
				console.log(row.status+"状态")
				return row.status == 1 ? '已激活' :  '禁用';
			},
			//取消选中的行
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
			//点击复选框触发
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
	  
			//批量删除
	handleDeleteBatch(){
			var vue=this;
			//map() 方法返回一个由原数组中的每个元素调用一个指定方法后的返回值组成的新数组。
				var ids = this.multipleSelection.map(function(item){
					return item.userId;
					
				}).toString();
				console.log("批量删除："+ids)
				//提示
				this.$confirm('确认删除吗?', '提示', {
					type: 'warning'
				}).then(function(){
					
					/**
					 * axios批量删除所有用户信息
					 */
					vue.axios({
						url:'http://localhost:80/ssm/deletebacth',
						method:'POST',
						data:Qs.stringify({'ids':ids})
					}).then(function(res){
						if(res.data.code==200){
							vue.$message({
								type:'success',
								message:'删除成功',
								showClose:true
							});
							//重新加载数据
							vue.getUsers();
						}
						
					})
					
				})
				
			}
			,
			/**
			 * 查询用户数据
			 */
			getUsers(){
				vue=this;
				vue.listLoading=true;
				console.log("根据条件查询："+vue.filters.userAccount)
				this.axios({
					method:'GET',
					url:'http://localhost:80/ssm/userlist',
					params:{
						 userAccount:vue.filters.userAccount
					}
				}).then(function(res){
					console.log("init data-----------"+res.data)
					vue.tableData=res.data;
					vue.listLoading=false;
					
				}).catch(function(err){
					vue.$message({
						message:'服务器异常:'+err,
						type:'error:'
					})
				});
			},
			/**
			 * 编辑用户
			 */
			handleEdit(index,row){
				var userId=row.userId;
				this.$router.push({name:'UserUpdate',params:{userId:userId}});
			},
			/**
			 * 删除
			 * 用户
			 */
			handleDel(index,row){
				var vue=this;
				console.log(index+"删除用户的id:====>"+row.userId)
					this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(function(){
					console.log("then删除用户的id:====>"+row.userId+"-->"+row.userAccount)
					//ajax删除
					vue.listLoading = false;
					
					vue.axios({
						url:'http://localhost:80/ssm/userdelete/'+row.userId,
						method:'GET'
					}).then(function(res){
						if(res.data.code==200){
							//删除成功
							vue.$message({
								message:'删除成功',
								type:'success'
							});
							//重新加载数据
							vue.getUsers();
							
						}
						
					})
					
				
				
				}).catch(function(){
					console.log("catch删除用户的id:====>"+row.userId)
				})
			},
			
			//添加用户
			handleAdd(){
				this.$router.push("/dao/home/useradd");
			},
			
			///重值密码
			resetPwd(index,row){
				var vue=this;
				//把用户密码重置为123456为默认密码
				console.log("开始重置密码："+row.userId);
					this.$confirm('确认重值该用户的密码吗?', '提示', {
						type: 'warning'
					}).then(function(){
						console.log("then重值密码用户的id:====>"+row.userId+"-->"+row.userAccount)
						//ajax重值密码
						vue.listLoading = false;
						vue.axios({
							url:'http://localhost:80/ssm/resetPwd/'+row.userId,
							method:'GET'
						}).then(function(res){
							if(res.data.code==200){
								//重值成功
								vue.$message({
									message:'重值成功',
									type:'success'
								});
								//重新加载数据
								vue.getUsers();
							}
							
						})

					}).catch(function(){
						console.log("catch重值密码用户的id:====>"+row.userId)
					})
	
			},
			 handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
				this.pageBean.pageSize=val;
				this.getusersPage();
				},
			handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
				this.pageBean.currentPage=val;
				this.getusersPage();
				},
				/**
				 * 分页查询用户数据
				 * 根据条件
				 */
			getusersPage(){
				
				var  vue=this;
				/**
				 * 发送axios请求
				 */
				this.axios({
					url:'http://localhost:80/ssm/userpages',
					method:'GET',
					params:{
						userAccount:this.filters.userAccount,
						currentPage:this.pageBean.currentPage,
						pageSize:this.pageBean.pageSize
					}
					
				}).then(function(res){
					
					vue.tableData=res.data.data;
					vue.pageBean.total=res.data.total;
					vue.listLoading=false;
					
				})
				
			}
			
			
    }
  }
</script>
