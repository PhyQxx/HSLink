<template>
	<view class="page">
		<view class="top">
			<view class="cu-item maigin-bottom">
				<view class="action">
					<text class="text-black">提问内容：</text>
				</view>
			</view>
			<view class="cu-item content">
				<textarea placeholder="请描述一下遇到的问题···"
							v-model="content"
							auto-height="true"
							maxlength=2000
				></textarea>
			</view>
			<view class="button">
				<button type="default" @tap="submitHelp">提交</button>
			</view>
		</view>
		<view class="bottom">
			<view class="title">
				历史帮助
			</view>
			<view class="no-data" v-if="noData">
				暂无历史帮助
			</view>
			<view class="help-list" v-if="!noData">
				<view class="one-help" v-for="(item,index) in historyHelpList" :key="index">
					<view class="cu-item maigin-bottom">
						<view class="action">
							<text class="text-black">提问内容：</text>
						</view>
					</view>
					<view class="cu-item content maigin-bottom">
						<textarea v-model="item.question"
									auto-height="true"
									maxlength=2000
									disabled="true"
						></textarea>
					</view>
					<view class="cu-item time maigin-bottom">
						提问时间：{{item.create_time}}
					</view>
					
					<view class="cu-item maigin-bottom">
						<view class="action">
							<text class="text-black">解决方案：</text>
						</view>
					</view>
					<view class="cu-item content maigin-bottom">
						<view class="no-answer" v-if="item.answer === ''">
							暂无解决方案
						</view>
						<textarea v-model="item.answer"
									auto-height="true"
									maxlength=2000
									disabled="true"
									v-if="item.answer !== ''"
						></textarea>
					</view>
					<view class="cu-item time maigin-bottom" v-if="item.answer_time !== ''">
						回答时间：{{item.answer_time}}
					</view>
					<view class="cu-item time maigin-bottom" @tap="goToUserInfo(item)" v-if="item.real_name !== ''">
						回答人：{{item.real_name}}
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	export default {
		data() {
			return {
				content: "",
				historyHelpList: [],
				noData: false
			}
		},
		onLoad() {
			this.getHistoryHelp();
		},
		onPullDownRefresh () {
			uni.startPullDownRefresh();
		},
		methods: {
			/**
			 * 跳转个人主页页面
			 * @param {Object} item
			 */
			goToUserInfo(item) {
				uni.navigateTo({
					url: `/pages/person-info-page/person-info-page?userId=${item.answer_id}`
				})
			},
			/**
			 * 提交帮助
			 */
			submitHelp() {
				if (this.content === "") {
					uni.showToast({
						icon: "none",
						title: "请描述一下遇到的问题···"
					})
				} else {
					request.post("/hs/addHelpQuestion",{
						userId: uni.getStorageSync("userInfo").user_id,
						question: this.content
					}).then(res => {
						console.log("提交帮助",res);
						if (res.data === 1) {
							uni.showToast({
								icon: "loading",
								title: "提交成功"
							});
							this.getHistoryHelp();
						}
					},err => {
						console.log("err",err);
					})
				}
			},
			/**
			 * 获取历史帮助
			 */
			getHistoryHelp() {
				request.post("/hs/getHistoryHelpList",{
					userId: uni.getStorageSync("userInfo").user_id
				}).then(res => {
					this.historyHelpList = res.data;
					this.historyHelpList.forEach(item => {
						if (item.answer_id === undefined) {
							item.answer_id = '';
							item.answer = '';
							item.answer_time = '';
							item.real_name = '';
						}
					})
					this.noData = res.data.length === 0 ? true : false;
					console.log("历史帮助列表",res);
				},err => {
					console.log("err",err);
				})
			}
		}
	}
</script>

<style scoped>
	.no-data{
		text-align: center;
		padding: 40rpx 0;
	}
	.one-help{
		margin-bottom: 20rpx;
		border-bottom: 5rpx dashed #969696;
	}
	.time{
		font-size: 30rpx;
		color: #b0b0b0;
	}
	.title{
		text-align: center;
		font-family: "楷体";
		font-size: 48rpx;
		font-weight: bold;
		color: #969696;
		padding-top: 20rpx;
	}
	.button{
		padding: 40rpx 200rpx;
	}
	.maigin-bottom{
		margin-bottom: 20rpx;
	}
	.content {
		background-color: #F1F1F1;
		width: 100%;
		padding: 20rpx;
		border-radius: 10rpx;
	}
	textarea{
		line-height: 1.5;
		width: 100%;
		height: 100%;
	}
	.top, .bottom{
		padding: 20rpx;
		background-color: #FFFFFF;
		margin-bottom: 20rpx;
	}
</style>
