<template>
	<view class="content">
		<m-search
				:show="false"
				placeholder="搜索"
				button="none"
				backgroundColor="#efecec"
				v-model="searchStr"
		></m-search>
		<view class="noData" v-if="noData === true">
			<noData :custom="true"><view class="title" @tap="update()">暂无数据,点击重新加载</view></noData>
		</view>
		<view class="list cu-card article dynamic" v-else-if="noData === false">
			<view class="cu-item one" style="padding:0" v-for="(item,index) in followList" :key="index" @tap="goToUserInfo(item)">
				<view class="one-left-two">
					<avatar :userName="item.real_name" size="50"></avatar>
					<view class="one-right">
						<view class="name">
							{{item.real_name}}
						</view>
						<view class="other-info">
							<view class="other-info-left">
								关注:{{item.followNumber}}
							</view>
							<view class="other-info-middle">
								粉丝:{{item.fansNumber}}
							</view>
							<view class="other-info-right">
								积分:{{item.integral}}
							</view>
						</view>
					</view>
				</view>
				<!-- <view class="follow-flag ed" @tap="cancelFollow(item)">
					已关注
				</view> -->
			</view>	
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	import mSearch from '@/components/mehaotian-search/mehaotian-search.vue';
	import noData from '@/components/noData/noData.vue';
	import { sortBy } from '@/static/js/public.js';
	import avatar from "@/pages/components/avatar/avatar.vue";
	export default {
		components: {
		    mSearch,
			noData,
			avatar
		},
		data() {
			return {
				//无数据
				noData: false,
				//搜索关键字
				searchStr: '',
				//关注列表
				followList: [],
				flag: true
			}
		},
		onPullDownRefresh () {
			this.getFollowPeopleList();
		},
		onShow() {
			this.getAllData();
		},
		mounted() {
			this.getFollowPeopleList();
		},
		methods: {
			/**
			 * 跳转个人主页页面
			 * @param {Object} item
			 */
			goToUserInfo(item) {
				uni.navigateTo({
					url: `/pages/person-info-page/person-info-page?userId=${item.user_id}`
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
								_this.getFollowPeopleList();
								console.log("取消关注",res)
							},err => {
								console.log("err",err)
							})
						}
					}
				})
			},
			/**
			 * 获取粉丝列表
			 */
			getFollowPeopleList() {
				request.post("/hs/getFansPeopleList",{
					userId: uni.getStorageSync("userInfo").user_id
				}).then(res => {
					console.log("粉丝列表",res);
					uni.startPullDownRefresh();
					this.followList = res.data;
					if (this.followList.length === 0) {
						this.noData = true;
					} else {
						this.noData = false;
						this.followList.forEach(item => {
							item.headerPhoto = item.real_name.slice(0,1);
						});
					}
				},err => {
					console.log("err",err)
				})
			}
		}
	}
</script>

<style scoped>
	.other-info-middle{
		padding: 0 20rpx;
	}
	.other-info{
		display: flex;
		color: #9a9a9a;
		font-size: 30rpx;
	}
	.one-right{
		margin-left: 20rpx;
	}
	.one-left-two{
		display: flex;
		flex-flow: nowrap;
		justify-content: center;
		align-items: center;
	}
	.follow-flag{
		border-radius: 10rpx;
		background-color: #269FDE;
		color: #FFFFFF;
		font-size: 32rpx;
		padding: 4rpx 10rpx;
	}
	.ed{
		background-color: #FFFFFF;
		border: 1rpx solid #b3b3b3;
		color: #b3b3b3;
	}
	.one-right .grade{
		font-size: 34rpx;
		color: #909090;
	}
	.one-right .name{
		padding-bottom: 12rpx;
		font-weight: bold;
	}
	.one-right .grade-right{
		margin-left: 16rpx;
	}
	.one-left{
		font-size: 80rpx;
		font-family: 'Courier New', Courier, monospace;
		color: #1296DB;
		border: 5rpx solid #1296DB;
		border-radius: 50%;
		width: 140rpx;
		height: 140rpx;
		text-align: center;
		line-height: 140rpx;
		margin: 20rpx;
	}
	.one{
		padding-right: 24rpx!important;
	}
	.one, .grade{
		display: flex!important;
		flex-flow: nowrap;
		align-items: center;
		justify-content: space-between;
	}
.grade .r, .grade .l{
		font-size: 14rpx;
		padding: 5rpx;
		border-radius: 5rpx;
		color: #FFFFFF;
	}
	.grade .l{
		background-color: #9DC75F;
	}
	.grade .r{
		background-color: #2D5315;
	}
</style>
