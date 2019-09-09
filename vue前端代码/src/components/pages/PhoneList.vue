<template>
	<div>
		<!-- <div style="margin-top: 20px">
		 
		
		</div> -->
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.brandName" placeholder="查看品牌"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="success" v-on:click="getbrandsPage">查询</el-button>
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
		
		<el-table-column prop="brandId"  label="品牌ID"  width="100">
		</el-table-column>
		
		<el-table-column  prop="brandName"  label="品牌名称" width="100"  show-overflow-tooltip>
		</el-table-column>
		
		<el-table-column  prop="brandWeb"  label="官方网站"  show-overflow-tooltip>
			  </el-table-column> 
		<el-table-column  prop="brandDesc"  label="品牌标语"  show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="telephone"  label="联系电话"  show-overflow-tooltip>
		</el-table-column>
		<el-table-column  prop="email" label="用户邮箱"  show-overflow-tooltip>
		</el-table-column>  
		
		
		 <el-table-column	 prop="brandStatus" label="状态" show-overflow-tooltip :formatter="formatSex">
		 </el-table-column> 
		 
		<el-table-column label="操作" width="280">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button size="small" type="warning"  @click="seeMore(scope.$index, scope.row)">查看信息</el-button>
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
			this.getbrandsPage();
		},
    data() {
			
      return {
		filters: {
			brandName: ''
				},
		listLoading:false,
        tableData: [
			
		],
        multipleSelection: [],
		pageBean:{
			currentPage:1,//当前页
			pageSize:3,//每页数据大小
			total:22//分页的总个数
		}
      }
    },

    methods: {
			//性别显示转换  row参数表示当前行所有数据
			formatSex: function (row) {
				console.log(row.brandStatus+"状态")
				return row.brandStatus == 1 ? '启用' : '禁用';
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
					return item.brandId;
					
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
						url:'http://localhost:80/ssm/deleteBrandbacth',
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
							vue.getbrandsPage();
						}
						
					})
					
				})
				
			}
			,
			/**
			 * 查询用户数据
			 */
			// getUsers(){
			// 	vue=this;
			// 	vue.listLoading=true;
			// 	console.log("根据条件查询："+vue.filters.userAccount)
			// 	this.axios({
			// 		method:'GET',
			// 		url:'http://localhost:80/ssm/userlist',
			// 		params:{
			// 			 userAccount:vue.filters.userAccount
			// 		}
			// 	}).then(function(res){
			// 		console.log("init data-----------"+res.data)
			// 		vue.tableData=res.data;
			// 		vue.listLoading=false;
			// 		
			// 	}).catch(function(err){
			// 		vue.$message({
			// 			message:'服务器异常:'+err,
			// 			type:'error:'
			// 		})
			// 	});
			// },
			/**
			 * 编辑用户
			 */
			handleEdit(index,row){
				var brandId=row.brandId;
				this.$router.push({name:'PhoneUpdate',params:{brandId:brandId}});
			},
			/**
			 * 删除
			 * 用户
			 */
			handleDel(index,row){
				var vue=this;
				console.log(index+"删除品牌的id:====>"+row.brandId)
					this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(function(){
					console.log("then删除品牌的id:====>"+row.brandId)
					//ajax删除
					vue.listLoading = false;
					
					vue.axios({
						url:'http://localhost:80/ssm/branddelete/'+row.brandId,
						method:'GET'
					}).then(function(res){
						if(res.data.code==200){
							//删除成功
							vue.$message({
								message:'删除成功',
								type:'success'
							});
							//重新加载数据
							vue.getbrandsPage();
							
						}
						
					})
					
				
				
				}).catch(function(){
					console.log("catch删除牌子的id:====>"+row.brandId)
				})
			},
			
			//添加品牌
			handleAdd(){
				this.$router.push("/dao/home/phoneadd");
			},
			
			///seeMore
			seeMore(index,row){
				var vue=this;
				//把用户密码重置为123456为默认密码
				console.log("开始重置密码："+row.brandId);
					this.$confirm('确认重值该用户的密码吗?', '提示', {
						type: 'warning'
					}).then(function(){
						console.log("then重值密码用户的id:====>"+row.brandId)
						//ajax重值密码
						vue.listLoading = false;
						vue.axios({
							url:'http://localhost:80/ssm/seeMore/'+row.brandId,
							method:'GET'
						}).then(function(res){
							if(res.data.code==200){
								//重值成功
								vue.$message({
									message:'重值成功',
									type:'success'
								});
								//重新加载数据
								this.getbrandsPage();
							}
							
						})

					}).catch(function(){
						console.log("catch重值密码用户的id:====>"+row.brandId)
					})
	
			},
			 handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
				this.pageBean.pageSize=val;
				this.getbrandsPage();
				},
			handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
				this.pageBean.currentPage=val;
				this.getbrandsPage();
				},
				/**
				 * 分页查询用户数据
				 * 根据条件
				 */
			getbrandsPage(){
				
				var  vue=this;
				/**
				 * 发送axios请求
				 */
				this.axios({
					url:'http://localhost:80/ssm/phonepages',
					method:'GET',
					params:{
						brandName:this.filters.brandName,
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
