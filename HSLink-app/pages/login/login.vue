<template>
	<view class="login">
		<view class="login-main">
			<view class="login-logo">
			<image src="@/static/img/logo.png" mode=""></image>
			</view>

			<view class="login-list flex border-all">
				<view class="iconfont icon-shoujihao flex"></view>
			    <picker @change="rolePickerChange" :value="roleIndex" :range="roleList">
			        <view class="picker">
			            {{roleIndex > -1 ? roleList[roleIndex] : ((this.role === '' || this.role === undefined) ? '请选择角色' : this.role)}}
			        </view>
			    </picker>
			</view>
			<view class="login-list flex border-all">
				<view class="iconfont icon-shoujihao flex"></view>
				
				<view class="login-input">
					<input placeholder="请输入账号" class="is-input1 " v-model="username" />
				</view>
			</view>
			<view class="login-list flex border-all">
				<view class="iconfont icon-yanzhengma flex"></view>
				<view class="login-input">
					<input type="password" placeholder="请输入密码" class="is-input1 " v-model="password" />
				</view>
			</view>
			<button class="cu-btn login-btn" @tap="doLogin">登 录</button>
		</view>
	</view>
</template>


<script>
	import request from '@/util/request.js';
	export default {
		data() {
			return {
				//角色列表
				roleList: ["教师","家长","学生"],
				//角色序号
				roleIndex: -1,
				//角色
				role: '',
				//账号
				username: '',
				//密码
				password: '',
				
			}
		},
		onLoad() {
			this.role = uni.getStorageSync("userInfo").user_type;
			this.username = uni.getStorageSync("userInfo").real_name;
			this.password = uni.getStorageSync("userInfo").pass_word;
		},
		methods: {
			/**
			 * 登录
			 */
			doLogin() {
				if (this.role === '' || this.role === undefined) {
					uni.showToast({
						icon: 'none',
						title: '请选择角色'
					})
				} else if(this.username === '' || this.username === undefined) {
					uni.showToast({
						icon: 'none',
						title: '请输入账号'
					})
				} else if(this.password === '' || this.password === undefined) {
					uni.showToast({
						icon: 'none',
						title: '请输入密码'
					})
				} else {
					request.post('/hs/login',{
						role:this.role,
						username: this.username,
						password:this.password
						}).then(res => {
							console.log("登录信息",res)
							if (res.data === null) {
								uni.showToast({
									icon: 'none',
									title: '账号或密码错误'
								})
							} else {
								uni.setStorageSync("userInfo", res.data);
								uni.switchTab({
								    url: '/pages/tabbar/homepage/homepage'
								});
							}
						},err => {
							
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
		}
	}
</script>



<style lang="scss">
	page {
		background-color: #FFFFFF;

	}
	.picker{
		font-size: 29rpx;
		color: #6d6d6d;
		padding-left: 20rpx;
	}
	.flex{
		display: flex;
	}
	.login {
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
	}

	.login-main {
		flex: 1;
		padding: 0 70upx;

		.login-logo {
			width: 400upx;
			height: 200upx;
			padding-bottom: 50upx;
			margin: 200upx auto 0 auto;

			image {
				width: 100%;
				height: 100%;
			}
		}

		.logo-tip {
			text-align: center;
			padding-top: 25upx;
			font-size: 32upx;
			font-weight: 200;
			color: rgba(51, 51, 51, 1);
			padding-bottom: 50upx;
		}

		.login-list {
			margin-top: 35upx;
			height: 100upx;
			align-items: center;
			padding: 0 30upx;

			&.border-all {
				&:after {
					border: 1px solid #D0D0D0;
					border-radius: 100upx;
				}
			}

			.iconfont {
				width: 65upx;
				font-size: 44upx;
				align-items: center;

				&:after {
					margin-left: 20upx;
					content: '';
					width: 2upx;
					height: 35upx;
					background: #D0D0D0;
					display: block;
				}
			}

			.login-input {
				flex: 1;

				input {
					font-size: 28upx;
					color: #333333;
					padding-left: 20upx;
				}
			}

			.code-sx {
				content: '';
				width: 2upx;
				height: 25upx;
				background: #D0D0D0;
				margin-right: 25upx;
			}

			.codeimg {
				font-size: 24upx;
				color: #999999;
			}
		}

		.login-btn {
			margin-top: 70upx;
			height: 96upx;
			width: 100%;
			background: #797979;
			border-radius: 47upx;
			font-size: 34upx;
			color: #ffffff;
		}

		.login-tip {
			padding-top: 26upx;
			font-size: 22upx;
			color: #666666;
			text-align: center;

			navigator {
				margin-left: 10upx;
				display: inline-block;
				color: #5055A8;
			}
		}
	}

	.login-footer {
		padding: 0 70upx;

		.footer-tip {
			align-items: center;
			font-size: 24upx;
			color: #999999;
			text-align: center;

			&:before {
				flex: 1;
				content: '';
				height: 2upx;
				background: #D0D0D0;
				margin-right: 30upx;
			}

			&:after {
				margin-left: 30upx;
				flex: 1;
				content: '';
				height: 2upx;
				background: #D0D0D0;
			}

		}

		.footer-other {
			padding: 40upx 0 100upx 0;
			justify-content: center;

			.other-list {
				width: 80upx;
				height: 80upx;
				margin: 0 75upx;

				image {
					width: 100%;
					height: 100%;
				}
			}
		}
	}
</style>
