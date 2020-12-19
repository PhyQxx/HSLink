<script>
	import request from "@/util/request.js";
export default {
	data() {
		return {
			//未读消息数字
			unreadNumber: ""
		}
	},
	onLaunch: function() {
		console.log('App Launch');
		this.getTabBarNumber();
		/**
		 * 主页面tab标签红标显示
		 */
		/* setTimeout(() => {
			uni.setTabBarBadge({
				index: 1,
				text: '31'
			});
			uni.showTabBarRedDot({
				index: 3
			});
		}, 1000); */
	},
	onShow: function() {
		console.log('App Show');
	},
	onHide: function() {
		console.log('App Hide');
	},
	methods: {
		/**
		 * 获取tabBar数字
		 */
		getTabBarNumber() {
			request.post("/hs/getTabBarNumber",{
				userId: uni.getStorageSync("userInfo").user_id
			}).then(res => {
				console.log("获取tabBar数字",res);
				if (res.data.unreadNumber > 0) {
					uni.setTabBarBadge({
						index: 3,
						text: res.data.unreadNumber.toString()
					});
				} else {
						uni.removeTabBarBadge({
							index: 3
						})
					}
			},err => {
				console.log("err",err)
			})
		}
	}
};
</script>

<style>
	@import "colorui/main.css";
    @import "colorui/icon.css";
	/* 头条小程序需要把 iconfont 样式放到组件外 */


	/*每个页面公共css */
	page {
		height: 100%;
		width: 100%;
		min-height: 100%;
		/* display: flex; */
		font-size: 16px;
	}
	.margin-right-xs{
		margin-right: 2.5rem;
	}
	.over-flow span{
		overflow: hidden;
		white-space: nowrap;
		width: 8rem;
		display: inline-block;
		text-overflow: ellipsis;
	}
	.page{
		margin: 20rpx;
		border-radius: 10rpx;
		padding-bottom: 100rpx;
	}

	/* #ifdef MP-BAIDU */
	page {
/* 		width: 100%;
		height: 100%;
		display: block; */
	}

	swan-template {
		width: 100%;
		min-height: 100%;
		display: flex;
	}

	/* 原生组件模式下需要注意组件外部样式 */
	custom-component {
		width: 100%;
		min-height: 100%;
		/* display: flex; */
	}

	/* #endif */

	/* #ifdef MP-ALIPAY */
	page {
		min-height: 100vh;
	}

	/* #endif */

	/* 原生组件模式下需要注意组件外部样式 */
	m-input {
		width: 100%;
		/* min-height: 100%; */
		/* display: flex;
		flex: 1; */
	}
</style>
