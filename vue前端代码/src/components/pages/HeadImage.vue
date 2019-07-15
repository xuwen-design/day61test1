<template>
	<div>
		   <form  method="post" action="http://localhost:80/ssm/headimage" enctype="multipart/form-data">
				 <input type="hidden" v-model="userId" name="userId" />
				 <input type="file" name="file" />
				 <input type="submit" value="上传"  />
			 </form>
	</div>
</template>



<script>
  export default {
    data() {
      return {
        userId: '',
      };
    },
		mounted(){
			this.userId=sessionStorage.getItem("userId")
		}
  }
</script>

<style>
	
	</style>
	
</style>