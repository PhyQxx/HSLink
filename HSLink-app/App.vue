<script>
export default {
	onLaunch: function() {
		console.log('App Launch');

		setTimeout(() => {
			uni.setTabBarBadge({
				index: 1,
				text: '31'
			});
			uni.showTabBarRedDot({
				index: 3
			});
		}, 1000);
	},
	onShow: function() {
		console.log('App Show');
	},
	onHide: function() {
		console.log('App Hide');
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
