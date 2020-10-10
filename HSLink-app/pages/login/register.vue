<template>
	<view class="page">
		<view class="content">
			<view class="role one">
				<view class="left">
					角色：
				</view>
				<view class="right">
					<view class="login-list flex border-all">
						<view class="iconfont icon-shoujihao flex"></view>
					    <picker @change="rolePickerChange" :value="roleIndex" :range="roleList">
					        <view class="picker">
					            {{roleIndex > -1 ? roleList[roleIndex] : ((this.role === '' || this.role === undefined) ? '请选择角色' : this.role)}}
					        </view>
					    </picker>
					</view>
				</view>
			</view>
			<view class="mobile one">
				<view class="left">
					手机号：
				</view>
				<view class="right">
					<input type="text" v-model="mobile" placeholder="请输入手机号"/>
				</view>
			</view>
			<view class="username one">
				<view class="left">
					姓名：
				</view>
				<view class="right">
					<input type="text" v-model="username" placeholder="请输入姓名"/>
				</view>
			</view>
			<view class="password one">
				<view class="left">
					密码：
				</view>
				<view class="right">
					<input type="password" v-model="password" placeholder="请输入密码"/>
				</view>
			</view>
			<view class="again-password one">
				<view class="left">
					确认密码：
				</view>
				<view class="right">
					<input type="password" v-model="againPassword" placeholder="请确认密码"/>
				</view>
			</view>
			<view class="button">
				<button class="login-btn" @tap="register">注 册</button>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	export default {
		data() {
			return {
				//角色列表
				roleList: [],
				//角色序号
				roleIndex: -1,
				//角色
				role: '',
				//手机号
				mobile: '',
				//账号
				username: '',
				//密码
				password: '',
				//确认密码
				againPassword: ''
			}
		},
		onLoad() {
			this.getUserType();
		},
		methods: {
			/**
			 * 注册
			 */
			register() {
				if (this.role === '') {
					uni.showToast({
						icon: 'none',
						title: '请选择角色'
					})
				} else if (this.mobile.length !== 11) {
					uni.showToast({
						icon: 'none',
						title: '请输入正确的手机号'
					})
				} else if (this.username === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入您的姓名'
					})
				} else if (this.password === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入密码'
					})
				} else if (this.againPassword === '') {
					uni.showToast({
						icon: 'none',
						title: '请确认密码'
					})
				} else if (this.againPassword !== this.password) {
					uni.showToast({
						icon: 'none',
						title: '两次密码不一致'
					})
				} else {
					request.post('/hs/register',{
						type: this.role,
						mobile: this.mobile,
						realname: this.username,
						password: this.password
					}).then(res => {
						if (res.data > 0) {
							uni.showToast({
								icon: 'loading',
								title: '注册成功'
							});
							uni.setStorageSync('loginInfo',{
								user_type: this.role,
								real_name: this.username,
								pass_word: this.password
							})
							setTimeout(() => {
								uni.reLaunch({
								    url: '/pages/login/login'
								});
							},1000)
						} else {
							uni.showToast({
								icon: 'none',
								title: '该手机号或姓名已被注册'
							});
						}
						console.log('注册',res);
					},err => {
						console.log('err',err)
					})
				}
			},
			/**
			 * 选择角色
			 * @param {Object} e
			 */
			rolePickerChange(e) {
			    this.roleIndex = e.detail.value;
				if (this.roleIndex === -1) {
					this.roleIndex = 0
				}
				this.role = this.roleList[this.roleIndex];
			},
			/**
			 * 获取字典项数据（用户类型）
			 */
			getUserType() {
				request.post("/hs/getDictionariesData",{
					code: "UserType"
				}).then(res => {
					console.log("用户类型",res);
					this.roleList = [];
					res.data.forEach(item => {
						if (item.dd_detail !== "管理员") {
							this.roleList.push(item.dd_detail)
						}
					});
				},err => {
					console.log("err",err);
				})
			},
		}
	}
</script>

<style scoped>
	.button{
		padding: 0 20rpx;
	}
	.login-btn {
		background: #797979;
		color: #FFFFFF;
	}
	.one .left{
		width: 200rpx;
		color: #8f8f8f;
	}
	.one .right{
		color: #a7a7a7;
	}
	.one{
		display: flex;
		padding: 20rpx;
		background-color: #FFFFFF;
		margin: 20rpx;
		border-radius: 10rpx;
	}	
	.page{
	}
</style>
