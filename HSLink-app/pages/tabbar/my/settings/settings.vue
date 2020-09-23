<template>
	<view class="page">
		<view class="cu-list menu sm-border">
			<view class="cu-item arrow" @tap="goToPage('account')">
				<view class="content">
					<text class="cuIcon-profile text-grey"></text>
					<text class="text-grey">账户与安全</text>
				</view>
			</view>
			<view class="cu-item arrow" @tap="goToPage('systemInformation')">
				<view class="content">
					<text class="cuIcon-question text-grey"></text>
					<text class="text-grey">系统信息</text>
				</view>
			</view>
			<view class="cu-item arrow" @tap="goToPage('userAgreement')">
				<view class="content">
					<text class="cuIcon-form text-grey"></text>
					<text class="text-grey">用户协议</text>
				</view>
			</view>
			<view class="cu-item arrow" @tap="goToPage('privacyAgreement')">
				<view class="content">
					<text class="cuIcon-form text-grey"></text>
					<text class="text-grey">隐私协议</text>
				</view>
			</view>
		</view>
		<view class="button" @tap="signOut">
			<button type="default">退出登录</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		onPullDownRefresh () {
			uni.startPullDownRefresh();
		},
		methods: {
			/**
			 * 退出
			 */
			signOut() {
				uni.showModal({
					title: "退出登录",
					content: "确认退出登录？",
					success(res) {
						if (res.confirm) {
							uni.setStorageSync("userInfo",{});
							uni.showToast({
								icon: 'loading',
								title: `退出成功`
							});
							setTimeout(() => {
								uni.reLaunch({
								    url: '/pages/login/login'
								});
							},1000)
						}
					}
				})
			},
			/**
			 * 跳转设置详情页面
			 * @param {Object} pageName
			 */
			goToPage(pageName) {
				const FUNCTION_CODE = {
					"account": "/pages/tabbar/my/settings/account-security/account-security",
					"systemInformation": "/pages/tabbar/my/settings/system-information/system-information",
					"userAgreement": "/pages/tabbar/my/settings/agreement/agreement?title=UserAgreement",
					"privacyAgreement": "/pages/tabbar/my/settings/agreement/agreement?title=PrivacyAgreement",
				};
				uni.navigateTo({
					url: `${FUNCTION_CODE[pageName]}`
				})
			}
		}
	}
</script>

<style scoped>
	.button{
		position: absolute;
		width: 80%;
		bottom: 80rpx;
		left: 10%;
	}
	.cu-list{
		border-radius: 10rpx;
	}
</style>
