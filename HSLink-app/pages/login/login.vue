<template>
	<view class="login">
		<view class="login-main">
			<view class="login-logo">
			
			</view>

		
			<view class="login-list flex border-all">
				<view class="iconfont icon-shoujihao flex"></view>
				<view class="login-input">
					<input type="number" maxlength="11" placeholder="请输入手机号" class="is-input1 " v-model="phone" />
				</view>
			</view>
			<view class="login-list flex border-all">
				<view class="iconfont icon-yanzhengma flex"></view>
				<view class="login-input">
					<input type="number" maxlength="6" placeholder="请输入验证码" class="is-input1 " v-model="code" />
				</view>
				<view class="code-sx"></view>
				<view class="codeimg" @click.stop="getCode()">{{getCodeText}}</view>
			</view>
			<button class="cu-btn login-btn" @tap="doLogin">登 录/注 册</button>
			<view class="login-tip">点击登录表示同意
				<navigator>软件许可及服务协议</navigator>
			</view>
		</view>
		<view class="login-footer">
			<view class="footer-tip flex">其他登录方式</view>
			<view class="footer-other flex">
				<view class="other-list">
					<image src="../../static/ic-QQ@2x.png" mode="aspectFill" @tap="login_qq()"></image>
				</view>
				<view class="other-list">
					<image src="../../static/ic-weixin@2x.png" mode="aspectFill" @tap="login_weixin()"></image>
				</view>
			</view>
		</view>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				phone: "",
				code: '',
				key: '',
				getCodeText: '获取验证码',
				getCodeBtnColor: "#ffffff",
				getCodeisWaiting: false,
			}
		},
		onLoad() {
			this.checkGuide();
		},
		methods: {
			checkGuide() {
				// 思路： 检测是否有启动缓存，如果没有，就是第一次启动，第一次启动就去 启动介绍页面
				const launchFlag = uni.getStorageSync('launchFlag');
				if (launchFlag) {
					this.isLogin();
				} else {
					uni.redirectTo({
						url: '/pages/guide/list'
					});
				}
			},

			isLogin() {
				// 判断缓存中是否登录过，直接登录
				try {
					const value = uni.getStorageSync('access_token');
					if (value) {
						//有登录信息
						console.log("已登录用户：", value);
						uni.switchTab({
							url: '/pages/index/index'
						});
					}
				} catch (e) {

				}
			},


			Timer() {},
			getCode() {
				let _this = this;
				uni.hideKeyboard()
				if (_this.getCodeisWaiting) {
					return;
				}
				if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(_this.phone))) {
					uni.showToast({
						title: '请填写正确手机号码',
						icon: "none"
					});
					return false;
				}
				_this.getCodeText = "发送中..."
				_this.getCodeisWaiting = true;
				_this.getCodeBtnColor = "rgba(255,255,255,0.5)"

				uni.request({
					url: _this.websiteUrl + '/sms/notification-sms/codes',
					data: {
						'phone': _this.phone
					},
					method: 'POST',
					header: {
						'Content-Type': 'application/x-www-form-urlencoded',
						//自定义请求头信息
					},
					success: (res) => {
						_this.key = res.data.data.key;
						//TODO 开发模式
						_this.code = res.data.data.code;
					}
				});
				//示例用定时器模拟请求效果
				setTimeout(() => {
					//uni.showToast({title: '验证码已发送',icon:"none"});
					_this.setTimer();
				}, 1000)
			},
			setTimer() {
				let holdTime = 59,
					_this = this;
				_this.getCodeText = "重新获取(60)"
				_this.Timer = setInterval(() => {
					if (holdTime <= 0) {
						_this.getCodeisWaiting = false;
						_this.getCodeBtnColor = "#ffffff";
						_this.getCodeText = "获取验证码"
						clearInterval(_this.Timer);
						return;
					}
					_this.getCodeText = "重新获取(" + holdTime + ")"
					holdTime--;
				}, 1000)
			},
			doLogin() {
				let _this = this;
				uni.hideKeyboard()
				//模板示例部分验证规则
				// if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.phone))){ 
				// 	uni.showToast({title: '请填写正确手机号码',icon:"none"});
				// 	return false; 
				// } 

				uni.request({
					url: _this.websiteUrl + '/token/sys/login-sms',
					data: {
						'key': _this.key,
						'code': _this.code,
						'phone': _this.phone
					},
					method: 'POST',
					header: {
						'Content-Type': 'application/x-www-form-urlencoded',
					},
					success: (res) => {
						if (res.data.code == 200) {
							_this.login(true, res.data.data, function() {
								_this.getRongyToken();
							});
						} else {
							uni.showToast({
								title: '验证码不正确',
								icon: "none"
							});
							return false;
						}
					}
				});
			},
			//QQ登录
			login_qq() {
				let _this = this;
				uni.login({
					provider: 'qq',
					success: function(loginRes) {
						// 获取用户信息
						uni.getUserInfo({
							provider: 'qq',
							success: function(infoRes) {
								_this.other_login(loginRes, infoRes, 'qq')
							}
						});
					}
				});
			},
			//微信登录
			login_weixin() {
				let _this = this;
				uni.login({
					provider: 'weixin',
					success: function(loginRes) {
						// 获取用户信息
						uni.getUserInfo({
							provider: 'weixin',
							success: function(infoRes) {
								_this.other_login(loginRes, infoRes, 'wx');
							}
						});
					}
				});
			},
			//授权登录
			other_login(loginRes, infoRes, type) {
				let _this = this;
				let url;
				let pram = {};
				// _this.loginRes=JSON.stringify(loginRes).toString();
				// _this.infoRes=JSON.stringify(infoRes).toString();
				switch (type) {
					case 'qq':
						url = '/token/sys/login-qq';
						pram = {
							'openid': loginRes.authResult.openid,
							'nickname': infoRes.userInfo.nickname,
							'gender': infoRes.userInfo.gender,
							'province': infoRes.userInfo.province,
							'city': infoRes.userInfo.city,
							'figureurl': infoRes.userInfo.figureurl_qq
						}
						break;
					case 'wx':
						url = '/token/sys/login-wechat';
						pram = {
							'openid': loginRes.authResult.openid,
							'nickname': infoRes.userInfo.nickName,
							'sex': infoRes.userInfo.gender,
							'province': infoRes.userInfo.province,
							'city': infoRes.userInfo.city,
							'country': infoRes.userInfo.country,
							'headimgurl': infoRes.userInfo.avatarUrl,
							'unionid': loginRes.authResult.unionid
						}
						break;
					default:
				}
				uni.request({
					url: _this.websiteUrl + url,
					data: pram,
					method: 'POST',
					header: {
						'Content-Type': 'application/x-www-form-urlencoded',
					},
					success: (res) => {
						if (res.data.code == 200) {
							//_this.testData=JSON.stringify(res.data.data).toString();;
							_this.login(true, res.data.data, function() {
								_this.getRongyToken();
							});

						}
					}
				});

			},
			async getRongyToken() { 
				let _this = this,
					url = '/app/api/v1.0/rongy/getRongyToken',
					param = {},
					method = 'GET';
				_this.sendRequest(url, param, method, (res) => {
					let data = {
						'appKey': 'xxxxxxx',
						'token': res.token,
						'targetIds': res.rongyUserId,
						'targetName': res.userName,
						'targetAvatar': res.portrait
					}
					uni.setStorage({
						key: 'ryData',
						data: data
					});
				});
			},
		}
	}
</script>



<style lang="scss">
	page {
		background-color: #FFFFFF;

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
			width: 248upx;
			height: 242upx;
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
			background: linear-gradient(-90deg, rgba(80, 85, 168, 1), rgba(104, 110, 210, 1));
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
