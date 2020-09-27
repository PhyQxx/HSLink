<template>
	<view class="page">
		<view class="top">
			<view class="title">
				等待帮助
			</view>
			<view class="no-data" v-if="notHelpNoData">
				暂无等待帮助
			</view>
			<view class="help-list" v-if="!notHelpNoData">
				<view class="one-help" v-for="(item,index) in notHelpList" :key="index">
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
						<textarea v-model="item.answer"
									auto-height="true"
									maxlength=2000
									placeholder="请输入解决方案"
						></textarea>
					</view>
					<view class="button">
						<button type="default" @tap="answerHelp(item)">提交</button>
					</view>
				</view>
			</view>
		</view>
		<view class="bottom">
			<view class="title">
				历史帮助
			</view>
			<view class="no-data" v-if="historyHelpNoData">
				暂无历史帮助
			</view>
			<view class="help-list" v-if="!historyHelpNoData">
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
				//未帮助列表
				notHelpList: [],
				//已帮助列表
				historyHelpList: [],
				//未帮助列表标志
				notHelpNoData: false,
				//已帮助列表标志
				historyHelpNoData: false
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
			answerHelp(item) {
				if (this.answer === "") {
					uni.showToast({
						icon: "none",
						title: "请输入解决方案"
					})
				} else {
					request.post("/admin/answerHelp",{
						answerId: uni.getStorageSync("userInfo").user_id,
						answer: item.answer,
						id: item.id
					}).then(res => {
						console.log("提交解决方案",res);
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
			 * 获取帮助答复列表
			 */
			getHistoryHelp() {
				request.post("/admin/getHelpAnswerList",{
					userId: uni.getStorageSync("userInfo").user_id
				}).then(res => {
					this.notHelpList = [];
					this.historyHelpList = [];
					res.data.forEach(item => {
						if (item.answer_id === undefined) {
							this.notHelpList.push(item);
						} else {
							this.historyHelpList.push(item);
						}
					})
					this.notHelpNoData = res.data.length === 0 ? true : false;
					this.historyHelpNoData = res.data.length === 0 ? true : false;
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
