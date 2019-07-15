import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from  '@/components/pages/login'
import Home from '@/components/pages/Home'
import UserList from '@/components/pages/UserList'
import User from '@/components/pages/User'
import dao from '@/components/pages/dao'
import UserAdd from '@/components/pages/UserAdd'
import UserUpdate from '@/components/pages/UserUpdate'
import HeadImage from '@/components/pages/HeadImage'
// import load from '@/components/pages/load'
import PhoneList from '@/components/pages/PhoneList'
import PhoneUpdate from '@/components/pages/PhoneUpdate'
import PhoneAdd from '@/components/pages/PhoneAdd'

Vue.use(Router)

export default new Router({
  routes: [
	{ 
	path: '/',
      name: 'login',
      component: login
		  
	  },
		{

			path: 'userupdate/:userId',
			name: 'UserUpdate',
			component: UserUpdate
		},
		
		{
			path:'/dao',
			name:'dao',
			component:dao,
			children:[
				{ 
					path: 'home',
					name: 'Home',
					component: Home,
					children:[
						{
							path: 'userlist',
							name: 'Userlist',
							component: UserList
						},
						{
							path: 'useradd',
							name: 'UserAdd,',
							component: UserAdd
							
						},
						{
							path: 'headimage',
							name: 'HeadImage',
							component: HeadImage,
							// path: 'load',
							// name: 'load',
							// component: load,
						},
						{
							path:'phonelist',
							name:'Phonelist',
							component:PhoneList
						},
						{
							path:'phoneadd',
							name:'PhoneAdd',
							component:PhoneAdd
						},
						{
						
							path: 'phoneupdate/:brandId',
							name: 'PhoneUpdate',
							component: PhoneUpdate
						}
							]
					}
				
			]
		}
		

  ]
})
