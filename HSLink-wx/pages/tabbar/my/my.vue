<template>
	<view class="page">
		<view class="top">
			<view class="header-photo">
				<avatar :userName="userInfo.real_name" size="90"></avatar>
			</view>
			<view class="user-name">
				{{userInfo.real_name}}
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
			<view class="other-info">
				<view class="score" @tap="goPage('followList')">
					关注：{{userOtherInfo.followNumber}}
				</view>
				<view class="score" @tap="goPage('fansList')">
					粉丝：{{userOtherInfo.fansNumber}}
				</view>
				<view class="score">
					积分：{{userInfo.integral}}
				</view>
			</view>
		</view>
		<view class="bottom">
			<view class="cu-list grid" :class="['col-' + gridCol,gridBorder?'':'no-border']">
				<view class="cu-item" 
						v-for="(item,index) in cuIconList" 
						:key="index" 
						v-if="item.admin === 0"
						@tap="goToPage(item)">
					<view :class="['cuIcon-' + item.cuIcon,'text-' + item.color]">
						<view class="cu-tag badge" v-if="item.badge > 0">
							<block v-if="item.badge > 0">{{item.badge>99?'99+':item.badge}}</block>
						</view>
					</view>
					<text>{{item.name}}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	import { updateUserInfo } from "@/static/js/public.js";
	import avatar from "@/pages/components/avatar/avatar.vue";
	export default {
		components: {
			avatar
		},
		data() {
			return {
				gridCol: 3,
				gridBorder: false,
				cuIconList: [
					{
						cuIcon: 'favor',
						color: 'orange',
						name: '收藏',
						admin: 0,
						badge: 0,
						code: 'collection'
					}, 
					{
						cuIcon: 'list',
						color: 'yellow',
						name: '我的文章',
						admin: 0,
						badge: 0,
						code: 'myArticle'
					}, 
					{
						cuIcon: 'notification',
						color: 'green',
						name: '通知',
						admin: 0,
						badge: 0,
						code: 'notice'
					}, 
					{
						cuIcon: 'edit',
						color: 'red',
						name: '编辑信息',
						admin: 0,
						badge: 0,
						code: 'personInfo'
					}, 
					{
						cuIcon: 'newshot',
						color: 'olive',
						name: '文章管理',
						admin: uni.getStorageSync("userInfo").user_type === "管理员" ? 0 : 1,
						badge: 0,
						code: 'articleManagement'
					},
					{
						cuIcon: 'peoplelist',
						color: 'cyan',
						name: '人员管理',
						admin: uni.getStorageSync("userInfo").user_type === "管理员" ? 0 : 1,
						badge: 0,
						code: 'peopleManagement'
					},
					{
						cuIcon: 'friendadd',
						color: 'mauve',
						name: '注册管理',
						admin: uni.getStorageSync("userInfo").user_type === "管理员" ? 0 : 1,
						badge: 0,
						code: 'registerManagement'
					},
					{
						cuIcon: 'comment',
						color: 'brown',
						name: '帮助答复',
						admin: uni.getStorageSync("userInfo").user_type === "管理员" ? 0 : 1,
						badge: 0,
						code: 'helpAnswer'
					},
					{
						cuIcon: 'settings',
						color: 'purple',
						name: '设置',
						admin: 0,
						badge: 0,
						code: 'settings'
					}, 
					{
						cuIcon: 'questionfill',
						color: 'pink',
						name: '帮助',
						admin: uni.getStorageSync("userInfo").user_type === "管理员" ? 1 : 0,
						badge: 0,
						code: 'help'
					},
					
				],
				userInfo: {},
				userOtherInfo: {},
				//通知数量
				noticeNumber: 0
			}
		},
		/**
		 * 跳转通知页面
		 * @param {Object} e
		 */
		onNavigationBarButtonTap(e) {
			uni.navigateTo({
				url: '/pages/tabbar/my/notice/notice'
			})
		},
		onShow() {
			this.getUserInfo();
			this.getMyPageNumber();
			this.getNoticeData();
			this.userInfo = uni.getStorageSync("userInfo");
			let timesRun = 0;
			let interval = setInterval(() => {
				updateUserInfo();
			timesRun += 1;
			if(timesRun === 5){
			clearInterval(interval);
			}
			}, 10000);
		},
		onLoad() {
			
		},
		onPullDownRefresh () {
			this.getMyPageNumber();
			this.getNoticeData();
			this.getUserInfo();
		},
		mounted() {
		},
		methods: {
			/**
			 * 获取通知
			 */
			getNoticeData() {
				let _this = this;
				request.post('/hs/getNoticeData',{
					authorId: uni.getStorageSync("userInfo").user_id
				}).then(res => {
					this.noticeNumber = res.data.messageNoticeList.length + res.data.verifyNoticeList.length;
					_this.cuIconList[2].badge = this.noticeNumber;
					if(this.noticeNumber == 0) {
						//隐藏
						// #ifdef APP-PLUS
						const pages = getCurrentPages();
						const page = pages[pages.length - 1];
						const currentWebview = page.$getAppWebview();
						currentWebview.hideTitleNViewButtonRedDot({
							index:0
						});
						// #endif
					}else{
						//显示
						// #ifdef APP-PLUS
						const pages = getCurrentPages();
						const page = pages[pages.length - 1];
						const currentWebview = page.$getAppWebview();
						currentWebview.showTitleNViewButtonRedDot({
							index:0
						});
						// #endif
					}
					console.log("通知",res);
				},err => {
					console.log("err",err);
				})
			},
			/**
			 * 获取文章管理/帮助答复的未操作数字
			 */
			getMyPageNumber() {
				let _this = this;
				request.post("/admin/getMyPageNumber",{
				}).then(res => {
					console.log("获取文章管理/帮助答复的未操作数字",res);
					_this.cuIconList[4].badge = res.data.articleManagementNumber;
					_this.cuIconList[6].badge = res.data.registerManagementNumner;
					_this.cuIconList[7].badge = res.data.helpAnswerNumber;
				},err => {
					console.log("err",err)
				})
			},
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
					"collection": "/pages/tabbar/my/collection/collection",
					"myArticle": "/pages/tabbar/my/my-article/my-article",
					"personInfo": "/pages/tabbar/my/edit-info/edit-info",
					"settings": "/pages/tabbar/my/settings/settings",
					"help": "/pages/tabbar/my/help/help",
					"articleManagement": "/pages/tabbar/my/article-management/article-management",
					"peopleManagement": "/pages/tabbar/my/people-management/people-management",
					"helpAnswer": "/pages/tabbar/my/help-answer/help-answer",
					"registerManagement": "/pages/tabbar/my/register-management/register-management",
					"notice": "/pages/tabbar/my/notice/notice"
				};
				uni.navigateTo({
					url: `${FUNCTION_CODE[item.code]}`
				})
			}
		}
	}
</script>

<style scoped>
	.max{
		width: 180rpx;
		height: 180rpx;
	}
	.signature{
		font-size: 30rpx;
		font-weight: bold;
		padding-bottom: 10rpx;
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
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.other-info{
		display: flex;
		justify-content: space-between;
		padding: 20rpx 40rpx;
		color: #9a9a9a;
		font-size: 30rpx;
	}
	.middle{
		padding: 40rpx 20rpx 0;
	}
	.user-name{
		padding-top: 20rpx;
		text-align: center;
	}
	.top{
		display: flex;
		flex-flow: column;
		justify-content: center;
	}
	.page{
		padding: 20rpx;
		display: flex;
		flex-flow: column;
		background-color: #FFFFFF;
	}
</style>
