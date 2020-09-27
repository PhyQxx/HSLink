<template>
	<view class="page">
		<view class="cu-item height">
			<view class="action">
				<text class="text-black">文章名称：</text>
			</view>
		</view>
		<view class="cu-item title">
			<textarea placeholder="请输入文章名称"
						v-model="noticeInfo.title"
						maxlength=50
			></textarea>
		</view>
		<view class="cu-item height">
			<view class="action">
				<text class="text-black">文章标签：</text>
			</view>
		</view>
		<view class="cu-item label">
			<textarea placeholder="请输入文章标签"
						v-model="noticeInfo.label"
						maxlength=4
			></textarea>
		</view>
		<view class="cu-item">
			<view class="action">
				<text class="text-black">文章内容：</text>
			</view>
		</view>
		<view class="cu-item content ">
			<textarea placeholder="请输入文章内容"
						v-model="noticeInfo.content"
						auto-height="true"
						maxlength=2000
			></textarea>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	export default {
		components: {
		},
		data() {
			return {
				//文章信息
				noticeInfo: {
					title: '',
					label: '',
					content: ''
				},
			}
		},
		onShow() {
			this.noticeInfo = {
					title: '',
					label: '',
					content: ''
				}
		},
		mounted() {
		},
		onPullDownRefresh () {
			uni.startPullDownRefresh();
		},
		onNavigationBarButtonTap() {
			this.preservation()
		},
		methods: {
			/**
			 * 发表
			 */
			preservation() {
				const NOTICE_TYPE = {
					"学生": "学生想法",
					"家长": "家长建议",
					"教师": "校园通知"
				}
				request.post('/hs/addArticle',{
					id: this.noticeInfo.id,
					label: this.noticeInfo.label,
					title: this.noticeInfo.title,
					content: this.noticeInfo.content,
					release_id: uni.getStorageSync("userInfo").user_id,
					type: NOTICE_TYPE[uni.getStorageSync("userInfo").user_type]
				}).then(res => {
					console.log("发表文章",res);
					if (res.data === 1) {
						uni.showToast({
							icon: 'loading',
							title: '发表成功'
						});
						setTimeout(() => {
							uni.switchTab({
							    url: '/pages/tabbar/homepage/homepage'
							});
						},1000)
					}
				},err=>{
					console.log("err",err);
				})
			}
		}
	}
</script>

<style scoped>
	.page{
		background-color: #FFFFFF;
		border-radius: 10rpx;
		padding: 20rpx;
	}
	.button{
		padding: 40rpx 100rpx 80rpx;
	}
	.cu-item{
		padding: 20rpx 0;
	}
	.title{
		padding-top: 0;
	}
	.title, .label, .content{
		background-color: #F1F1F1;
		width: 100%;
		padding: 20rpx;
		border-radius: 10rpx;
	}
	textarea{
		text-indent: 32rpx;
		line-height: 1.5;
		width: 100%;
		height: 100%;
	}
	.title textarea{
		height: 128rpx;
		font-weight: bold;
	}
	.label textarea{
		height: 80rpx;
	}
	.content textarea{
		min-height: 400rpx;
		text-indent: 40rpx;
	}
	
</style>
