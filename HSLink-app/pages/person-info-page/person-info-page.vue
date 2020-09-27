<template>
	<view class="page">
		<view class="top">
			<view class="left">
				<avatar :userName="userInfo.real_name" size="90"></avatar>
			</view>
			<view class="user-info">
				<view class="other-info">
					<view class="follow">
						<view class="one-top">{{userInfo.followNumber}}</view>
						<view class="one-bottom">关注</view>
					</view>
					<view class="fans">
						<view class="one-top">{{userInfo.fansNumber}}</view>
						<view class="one-bottom">粉丝</view>
					</view>
					<view class="score">
						<view class="one-top">{{userInfo.integral}}</view>
						<view class="one-bottom">积分</view>
					</view>
				</view>
				<view class="info-bottom">
					<view class="follow-button" v-if="userInfo.isFollow === 0" @tap="addFollow(userInfo)">关注</view>
					<view class="follow-button-ed" v-if="userInfo.isFollow === 1" @tap="cancelFollow(userInfo)">已关注</view>
				</view>
			</view>
		</view>
		<view class="middle">
			<view class="cu-item content">
				<textarea v-model="userInfo.signature"
							auto-height="true"
							maxlength=2000
							disabled="false"
				></textarea>
			</view>
		</view>
		<view class="bottom">
			<view class="noData" v-if="noData === true">
				<noData :custom="true"><view class="title" @tap="update()">暂无数据,点击重新加载</view></noData>
			</view>
			<view class="list cu-card article dynamic" v-else-if="noData === false">
				<view class="cu-item" style="padding:0" v-for="(item,index) in noticeList" :key="index">
					<view class="cu-list menu solid-bottom" @click="goToDetails(item)">
						<view class="cu-item arrow" style="min-height: 90rpx;padding-top: 10rpx;">
							<view class="action">
								<view class="action">
									<view class='cu-tag radius bg-orange light margin-right-xs' v-if="item.type === '校园通知'">{{item.type}}</view>
									<view class='cu-tag radius bg-blue light margin-right-xs' v-if="item.type === '家长建议'">{{item.type}}</view>
									<view class='cu-tag radius bg-green light margin-right-xs' v-if="item.type === '学生想法'">{{item.type}}</view>
									<text class="text-black text-lg">{{item.title}}</text>
								</view>
							</view>
						</view>
					</view>
					<view class="text-content" style="margin:10rpx 0 0 0;">
						<text class="text-gray">文章标签：</text>
						<text class="">{{item.label}}</text>
					</view>
					<view class="text-content" style="margin:10rpx 0 0 0;">
						<text class="text-gray">发表时间：</text>
						<text class="">{{item.release_time}}</text>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	import noData from '@/components/noData/noData.vue';
	import { sortBy } from '@/static/js/public.js';	
	import avatar from "@/pages/components/avatar/avatar.vue";
	export default {
		components: {
			noData,
			avatar
		},
		data() {
			return {
				//用户ID
				userId: '',
				//用户信息
				userInfo: {},
				//无数据
				noData: false,
				//个人文章列表
				noticeList: [],
			}
		},
		onLoad(option) {
			this.userId = option.userId;
		},
		mounted() {
			this.getUserInfo();
		},
		onPullDownRefresh () {
			this.getUserInfo();
		},
		onNavigationBarButtonTap() {
			uni.setStorageSync("otherInfo",this.userInfo);
			uni.navigateTo({
				url: "/pages/tabbar/message/chat-page"
			})
		},
		methods: {
			/**
			 * 加关注
			 * @param {Object} userInfo
			 */
			addFollow(userInfo) {
				request.post("/hs/addFollow",{
					userId: uni.getStorageSync("userInfo").user_id,
					followId: userInfo.user_id
				}).then(res => {
					this.getUserInfo();
					console.log("加关注",res)
				},err => {
					console.log("err",err)
				})
			},
			/**
			 * 取消关注
			 * @param {Object} userInfo
			 */
			cancelFollow(userInfo) {
				let _this = this;
				uni.showModal({
					title: "取消关注",
					content: "确认取消关注？",
					success(res) {
						if (res.confirm) {
							request.post("/hs/cancelFollow",{
								userId: uni.getStorageSync("userInfo").user_id,
								followId: userInfo.user_id
							}).then(res => {
								_this.getUserInfo();
								console.log("取消关注",res)
							},err => {
								console.log("err",err)
							})
						}
					}
				})
			},
			/**
			 * 跳转详情页面
			 * @param {Object} item
			 */
			goToDetails(item) {
				uni.navigateTo({
					url: '/pages/tabbar/homepage/data-details?noticeId='+item.id
				})
			},
			/**
			 * 获取个人信息
			 */
			getUserInfo() {
				request.post("/hs/getPersonalInfo",{
					userId: uni.getStorageSync("userInfo").user_id,
					releaseId: this.userId
				}).then(res => {
					uni.startPullDownRefresh();
					console.log("个人信息",res);
					this.userInfo = res.data.personalInfo;
					this.userInfo.headerPhoto = this.userInfo.real_name.slice(0,1);
					this.noticeList = res.data.personalList;
					this.noData = res.data.personalList.length === 0 ? true : false;
					uni.setNavigationBarTitle({
						title: this.userInfo.real_name
					})
				},err => {
					console.log("err",err);
				}) 
			}
		}
	}
</script>

<style scoped>
	.middle{
		padding-bottom: 20rpx;
		border-bottom: 1rpx solid rgba(18,150,219,0.5);
	}
	.cu-item{
		background-color: #cfcfcf!important;
		margin: 16rpx 0!important;
	}
	.follow-button-ed{
		width: 80%;
		border: 1rpx solid #969696;
		color: #8d8d8d;
		text-align: center;
		border-radius: 10rpx;
	}
	.top .left{
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.info-bottom{
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 10rpx;
	}
	.follow-button{
		color: #FFFFFF;
		width: 80%;
		background-color: #269FDE;
		text-align: center;
		border-radius: 10rpx;
	}
	.grade .r, .grade .l{
		font-size: 14rpx;
		padding: 5rpx;
		border-radius: 5rpx;
	}
	.grade .l{
		background-color: #9DC75F;
	}
	.grade .r{
		background-color: #2D5315;
	}
	.user-info view{
		padding: 10rpx 10rpx;
	}
	.other-info .one-top, .other-info .one-bottom{
		text-align: center;
	}
	.other-info{
		display: flex;
		justify-content: space-between;
		padding: 20rpx 40rpx;
		color: #9a9a9a;
		font-size: 30rpx;
	}
	.info-top{
		display: flex;
		flex-flow: nowrap;
		color: #7f7f7f;
	}
	.user-info{
		display: flex;
		flex-flow: column;
		justify-content: center;
		border-radius: 10rpx;
		width: 70%;
		font-size: 32rpx;
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
	.top{
		display: flex;
		justify-content: space-between;
	}
	.page{
		padding: 20rpx;
		display: flex;
		flex-flow: column;
		background-color: #FFFFFF;
	}
</style>
