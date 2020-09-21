<template>
	<view class="page">
		<view class="top">
			<view class="header-photo">
				{{userInfo.real_name.slice(0,1)}}
			</view>
			<view class="user-info">
				<view class="info-left">
					<view class="name">
						姓名：{{userInfo.real_name}}
					</view>
					<view class="grade">
						等级：<text class="l">天才</text><text class="r">{{Math.floor((userInfo.integral)/1000)+1}}</text>
					</view>
					<view class="score">
						积分：{{userInfo.integral}}
					</view>
				</view>
				<view class="info-right">
					<view class="score" @tap="goPage('followList')">
						关注：{{userOtherInfo.followNumber}}
					</view>
					<view class="score" @tap="goPage('fansList')">
						粉丝：{{userOtherInfo.fansNumber}}
					</view>
				</view>
			</view>
		</view>
		<view class="bottom">
			<view class="cu-list grid" :class="['col-' + gridCol,gridBorder?'':'no-border']">
				<view class="cu-item" 
						v-for="(item,index) in cuIconList" 
						:key="index" 
						v-if="index<gridCol*2"
						@tap="goToPage(item)">
					<view :class="['cuIcon-' + item.cuIcon,'text-' + item.color]">
					</view>
					<text>{{item.name}}</text>
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
				gridCol: 3,
				gridBorder: false,
				cuIconList: [
					{
						cuIcon: 'favor',
						color: 'orange',
						name: '收藏',
						code: 'collection'
					}, 
					{
						cuIcon: 'list',
						color: 'yellow',
						name: '我的文章',
						code: 'myArticle'
					}, 
					{
						cuIcon: 'edit',
						color: 'red',
						name: '编辑信息',
						code: 'personInfo'
					}, 
					{
						cuIcon: 'settings',
						color: 'olive',
						name: '设置',
						code: 'settings'
					}, 
					{
						cuIcon: 'questionfill',
						color: 'mauve',
						badge: 0,
						name: '帮助',
						code: 'help'
					},
				],
				userInfo: uni.getStorageSync("userInfo"),
				userOtherInfo: {}
			}
		},
		onLoad() {
		},
		onPullDownRefresh () {
			this.getUserInfo();
		},
		mounted() {
			this.getUserInfo();
		},
		methods: {
			/**
			 * 跳转页面
			 * @param {Object} pageName 页面名称
			 */
			goPage(pageName) {
				const FUNCTION_CODE = {
					'followList': '/pages/tabbar/follow/follow-list',
					'fansList': '/pages/tabbar/follow/fans-list',
				}
				uni.navigateTo({
					url: `${FUNCTION_CODE[pageName]}`
				})
			},
			/**
			 * 获取个人信息
			 */
			getUserInfo() {
				request.post("/hs/getPersonalInfo",{
					userId: uni.getStorageSync("userInfo").user_id,
					releaseId: uni.getStorageSync("userInfo").user_id
				}).then(res => {
					uni.startPullDownRefresh();
					console.log("个人信息",res);
					this.userOtherInfo = res.data.personalInfo;
				},err => {
					console.log("err",err);
				}) 
			},
			/**
			 * 跳转页面
			 * @param {Object} item
			 */
			goToPage(item) {
				const FUNCTION_CODE = {
					'collection': '',
					'myArticle': '/pages/tabbar/my/my-article/my-article',
					'personInfo': '',
					'settings': '',
					'help': ''
				};
				uni.navigateTo({
					url: `${FUNCTION_CODE[item.code]}`
				})
			}
		}
	}
</script>

<style scoped>
	.bottom{
		display: flex;
		flex-flow: column;
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
	.user-info{
		color: #FFFFFF;
		display: flex;
		flex-flow: nowrap;
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
