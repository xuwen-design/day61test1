<template>

<div>
	<el-container style="background-color: #42D885; border: 1px solid #eee">
		  <el-header>
<el-row :gutter="16">
  <el-col :span="12"><div class="grid-content bg-purple">欢迎您：{{sysName}}</div></el-col>
  
    <el-col :span="12">
			
	  <div class="userinfo" >
	    <img  :src="imgurl" class='avatar' alt="">
	    <div class='welcome'>
	     <!--   <p class='name comename'>欢迎</p> -->
	        <p class='name avatarname'>{{sysName}}</p>
	    </div>
	    <span class='username'>
	        <el-dropdown
	                trigger="click"
					@command="handleCommand"
	                >
	            <span class="el-dropdown-link">
	                <i class="el-icon-caret-bottom el-icon--right"></i>
	            </span>
	            <el-dropdown-menu slot="dropdown">
	                <el-dropdown-item command='info'>修改信息</el-dropdown-item>
	                <el-dropdown-item command='pass'>修改密码</el-dropdown-item>
					<el-dropdown-item command='head'>修改头像</el-dropdown-item>
									
	                <el-dropdown-item  command='logout'>退出</el-dropdown-item>
	            </el-dropdown-menu>
	        </el-dropdown>
	    </span>
	   
	</div>
			
			</el-col>
 
</el-row>
		</el-header>
		</el-container>
	<!-- <img :src="headimageurl" class="avatar"/> -->
<div class="line"></div>
<el-menu
  :default-active="activeIndex2"
  class="el-menu-demo"
  mode="horizontal"
  @select="handleSelect"
  background-color="#545c64"
  text-color="#fff"
  active-text-color="#ffd04b">
  <el-menu-item index="1">处理中心</el-menu-item>
  <el-menu-item index="2">
		 <router-link to="/dao/home">管理界面</router-link>
	</el-menu-item>
	
  <el-menu-item index="3" disabled>消息中心</el-menu-item>

</el-menu>
<div class="line"></div>
<router-view/>

</div>
</template>



<script>
  export default {
    data() {
      return {
			imgurl:'',
			userId:'',
			sysName:'VUEADMIN',
			collapsed:false,
			headimageurl:'',
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
	mounted:function(){
		var vue = this
			this.sysName=sessionStorage.getItem("userAccount");
			console.log("mounted--home-->"+sessionStorage.getItem("headimageurl")+sessionStorage.getItem("headimageurl"))
			this.userId=sessionStorage.getItem("userId");
			this.axios({
				url:'http://localhost:80/ssm/headimage/'+vue.userId,
				method:'GET'
			}).then(function(res){
				
				vue.imgurl=res.data.object;
			})
			

	},
    methods: {
			  handleSelect(key, keyPath) {
				console.log(key, keyPath);
			  },
			handleCommand(command) {
				if(command=='head'){//修改头像
					  this.$router.push('/dao/home/headimage');
					//this.$router.push('');
				}
			}
    }
  }
</script>
<style scoped  lang="less" >  //
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
	
	.userinfo-right{
	    justify-content: flex-end;
	    min-width: 330px;
	    width:330px;
	    margin-right: 20px;
	}
	.userinfo {
	    line-height: 60px;
	    text-align:right;
			
	}
	.avatar{
	    width: 40px;
	    height: 40px;
	    border-radius: 50%;
	    vertical-align: middle;
	    display: inline-block;
	}
	.welcome{
	    display: inline-block;
	    width:auto;
	    vertical-align: middle;
	    padding: 0 5px;
	    .name{
	        line-height: 20px;
	        text-align: center;
	        font-size: 14px;
	    }
	    .comename{
	        font-size: 12px;
	    }
	    .avatarname{
	        color:black;
	        font-weight:bolder;
	    }
	}
	.username {
	    cursor: pointer;
	    margin-right: 5px;
	}
	.border{
	    border:1px solid;
	}
	.notify-row{
	    line-height:60px;
	    margin-right: 20px;
	    margin-top: 5px;
	}
	ul.top-menu > li {
	    float: left;
	    margin-right: 20px;
	    position: relative;
	}
	ul.top-menu > li > a {
	    color:#3bc5ff;
	    font-size: 16px;
	    border-radius: 4px;
	    -webkit-border-radius: 4px;
	    border: 1px solid #f0f0f8 !important;
	    padding: 2px 6px 4px 6px;
	}
</style>
