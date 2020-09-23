<template>
	<view class="page">
		<view class="content">
			<textarea v-model="content"
						auto-height="true"
						disabled="true"
			></textarea>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	export default {
		data() {
			return {
				title: "",
				content: ""
			}
		},
		onLoad(option) {
			const TITLE = {
				"UserAgreement": "用户协议",
				"PrivacyAgreement": "隐私协议"
			};
			uni.setNavigationBarTitle({
				title: TITLE[option.title]
			});
			this.getDictionariesData(option.title)
		},
		onPullDownRefresh () {
			uni.startPullDownRefresh();
		},
		methods: {
			/**
			 * 获取协议内容
			 */
			getDictionariesData(code) {
				request.post("/hs/getDictionariesData",{
					code: code
				}).then(res => {
					console.log("协议内容",res);
					this.content = res.data[0].dd_detail;
				},err => {
					console.log("err",err);
				})
			}
		}
	}
</script>

<style scoped>
	.content{
		padding: 20rpx!important;
		background-color: #FFFFFF;
		border-radius: 10rpx;
	}
	uni-textarea{
		width: 100%!important;
	}
</style>
