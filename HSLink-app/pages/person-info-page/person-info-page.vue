<template>
	<view class="page">
		<view class="top">
			<view class="left">
				<view class="header-photo">
					{{userInfo.headerPhoto}}
				</view>
			</view>
			<view class="user-info">
				<view class="info-top">
					<view class="info-left">
						<view class="name">
								姓名：{{userInfo.real_name}}
						</view>
						<view class="grade">
							等级：<text class="l">博客</text><text class="r">{{Math.floor((userInfo.integral)/1000)+1}}</text>
						</view>
						<view class="score">
							积分：{{userInfo.integral}}
						</view>
					</view>
					<view class="info-right">
						<view class="follow">
							关注：{{userInfo.followNumber}}
						</view>
						<view class="fans">
							粉丝：{{userInfo.fansNumber}}
						</view>
					</view>
				</view>
				<view class="info-bottom">
					<view class="follow-button" v-if="userInfo.isFollow === 0" @tap="addFollow(userInfo)">关注</view>
					<view class="follow-button-ed" v-if="userInfo.isFollow === 1" @tap="cancelFollow(userInfo)">已关注</view>
				</view>
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
									<view class='cu-tag radius bg-blue light margin-right-xs' v-if="item.type === '家长意见'">{{item.type}}</view>
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
	export default {
		components: {
			noData
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
				uni.setStorageSync("notice",item);
				uni.navigateTo({
					url: '/pages/tabbar/homepage/data-details'
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
				},err => {
					console.log("err",err);
				}) 
			}
		}
	}
</script>

<style scoped>
	.cu-item{
		background-color: #cfcfcf!important;
	}
	.follow-button-ed{
		width: 80%;
		background-color: #FFFFFF;
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
	.info-top{
		display: flex;
		flex-flow: nowrap;
	}
	.user-info{
		color: #FFFFFF;
		display: flex;
		flex-flow: column;
		justify-content: center;
		border: 1rpx solid #b0b0b0;
		border-radius: 10rpx;
		width: 70%;
		font-size: 32rpx;
		background-color: #ced8d8;
	}
	.header-photo{
		font-size: 80rpx;
		font-family: 'Courier New', Courier, monospace;
		color: #1296DB;
		border: 5rpx solid #1296DB;
		border-radius: 50%;
		width: 180rpx;
		height: 180rpx;
		text-align: center;
		line-height: 180rpx;
		margin: 20rpx;
	}
	.top{
		display: flex;
		justify-content: space-between;
		padding-bottom: 40rpx;
		border-bottom: 1rpx solid rgba(18,150,219,0.5);
	}
	.page{
		padding: 20rpx;
		display: flex;
		flex-flow: column;
		background-color: #FFFFFF;
	}
</style>
