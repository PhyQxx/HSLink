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
		<view class="cu-item content">
			<textarea placeholder="请输入文章内容"
						v-model="noticeInfo.content"
						auto-height="true"
						maxlength=2000
			></textarea>
		</view>
		<view class="button">
			<button type="warn"  @tap="deleteArticle">删除</button>
			<button type="default" @tap="preservation">保存</button>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	export default {
		components: {
		},
		data() {
			let noticeInfo = uni.getStorageSync('notice');
			noticeInfo.fabulous = true;
			noticeInfo.messageFabulous = true;
			return {
				//文章信息
				noticeInfo: noticeInfo,
			}
		},
		onLoad() {
			
		},
		onNavigationBarButtonTap(e) {
			this.deleteArticle()
		},
		mounted() {
		},
		methods: {
			/**
			 * 删除
			 * @param {Object} e
			 */
			deleteArticle() {
				let _this = this;
				uni.showModal({
				    title: '删除',
				    content: '确认删除这篇文章？',
				    success(res) {
				        if (res.confirm) {
				            request.post('/hs/deleteOne',{
				            	id: _this.noticeInfo.id
				            }).then(res => {
				            	console.log("删除这篇文章",res);
								if (res.data === 1) {
									uni.showToast({
										icon: 'loading',
										title: '删除成功'
									});
									setTimeout(() => {
										uni.navigateBack({
											delta: 1
										})
									},1000)
								}
				            },err=>{
				            	console.log("err",err);
				            })
				        } else if (res.cancel) {
				            console.log('用户点击取消');
				        }
				    }
				});
			},
			/**
			 * 保存
			 */
			preservation() {
				if (this.noticeInfo.title === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入文章标题'
					})
				} else if (this.noticeInfo.label.length !== 4) {
					uni.showToast({
						icon: 'none',
						title: '文章标签只能4个字符哦'
					})
				} else if (this.noticeInfo.content === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入文章内容'
					})
				} else {
					request.post('/hs/updateOneContent',{
						id: this.noticeInfo.id,
						label: this.noticeInfo.label,
						title: this.noticeInfo.title,
						content: this.noticeInfo.content,
					}).then(res => {
						console.log("保存文章",res);
						if (res.data === 1) {
							uni.showToast({
								icon: 'loading',
								title: '保存成功'
							});
							setTimeout(() => {
								uni.navigateBack({
									delta: 1
								})
							},1000)
						}
					},err=>{
						console.log("err",err);
					})
				}
			}
		}
	}
</script>

<style scoped>
	.button button:nth-child(2){
		margin-left: 80rpx;
	}
	.button button{
		width: 200rpx;
	}
	.button{
		display: flex;
		margin: 40rpx;
		justify-content: center;
		align-items: center;
	}
	.cu-item{
		padding: 20rpx;
	}
	.title{
		padding-top: 0;
	}
	.title, .label, .content{
		background-color: #F1F1F1;
		border-radius: 10rpx;
		padding: 20rpx;
		margin: 20rpx;
	}
	.title textarea, .label textarea, .content textarea{
		width: 100%;
	}
	.title textarea{
		height: 128rpx;
		font-weight: bold;
	}
	.label textarea{
		height: 80rpx;
	}
	.page{
		background-color: #FFFFFF;
	}
</style>
