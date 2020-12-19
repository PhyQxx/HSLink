<template>
	<view class="page">
		<view class="noData" v-if="noData === true">
			<noData :custom="true"><view class="title">暂无消息，打开他人主页可私信</view></noData>
		</view>
		<view class="cu-list menu-avatar" v-if="noData === false">
			<view class="cu-item" :class="modalName=='move-box-'+ index?'move-cur':''" 
						v-for="(item,index) in message" 
						:key="index"
						@touchstart="ListTouchStart" 
						@touchmove="ListTouchMove" 
						@touchend="ListTouchEnd" 
						:data-target="'move-box-' + index"
						@tap="goToMessage(item)">
				<view class="header-photo">
					<avatar :userName="item.userInfo.real_name" size="50"></avatar>
				</view>
				<view class="content">
					<view class="text-grey">{{item.userInfo.real_name}}</view>
					<view class="text-gray text-sm width">
						{{item.letterList[item.letterList.length-1].content}}
					</view>
				</view>
				<view class="action">
					<view class="text-grey text-xs">{{item.letterList[item.letterList.length-1].letter_create_time}}</view>
					<view class="cu-tag round bg-grey sm" v-if="item.unreadNumber === 0">{{item.unreadNumber}}</view>
					<view class="cu-tag round bg-red sm" v-if="item.unreadNumber > 0">{{item.unreadNumber}}</view>
				</view>
				<view class="move">
					<view class="bg-red" @tap="deleteMessage">删除</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	import avatar from "@/pages/components/avatar/avatar.vue";
	import noData from '@/components/noData/noData.vue';
	import { sortBy } from '@/static/js/public.js';
	export default {
		components: {
			avatar,
			noData
		},
		data() {
			return {
				modalName: null,
				message: [],
				//无数据
				noData: false,
			}
		},
		onShow() {
			this.getPersonalPrivateLetter();
			this.getTabBarNumber();
		},
		onPullDownRefresh () {
			this.getPersonalPrivateLetter();
			this.getTabBarNumber();
			uni.startPullDownRefresh();
		},
		methods: {
			/**
			 * 获取tabBar数字
			 */
			getTabBarNumber() {
				request.post("/hs/getTabBarNumber",{
					userId: uni.getStorageSync("userInfo").user_id
				}).then(res => {
					console.log("获取tabBar数字",res);
					if (res.data.unreadNumber > 0) {
						uni.setTabBarBadge({
							index: 3,
							text: res.data.unreadNumber.toString()
						});
					} else {
						uni.removeTabBarBadge({
							index: 3
						})
					}
				},err => {
					console.log("err",err)
				})
			},
			/**
			 * 跳转到消息页面
			 * @param {Object} item
			 */
			goToMessage(item) {
				uni.setStorageSync("otherInfo",item.userInfo);
				uni.navigateTo({
					url: "/pages/tabbar/message/chat-page"
				})
			},
			/**
			 * 删除私信
			 */
			deleteMessage() {
				uni.showToast({
					icon: 'loading',
					title: '不能删除哦'
				})
			},
			/**
			 * 获取私信
			 */
			getPersonalPrivateLetter() {
				request.post('/hs/getPersonalPrivateLetterApp',{
					userId: uni.getStorageSync("userInfo").user_id,
				}).then(res => {
					this.message = res.data;
					this.noData = res.data.length > 0 ? false : true;
					this.message.forEach((item,index) => {
						let unreadNumber = 0;
						item.letterList.forEach(i => {
							if (i.already_read === '0' && i.receive_id === uni.getStorageSync("userInfo").user_id) {
								unreadNumber++;
							}
						});
						item.unreadNumber = unreadNumber;
						item.lastTime = item.letterList.length === 0 ? '' : item.letterList[item.letterList.length-1].letter_create_time
					});
					this.message.forEach((item,index) => {
						if (item.userInfo.user_id === uni.getStorageSync("userInfo").user_id) {
							this.message.splice(index,1);
						}
					});
					this.message = this.message.sort(sortBy("lastTime",false))
					console.log("私信",res);
				},err=>{
					console.log("err",err);
				})
			},
			/**
			 * ListTouch触摸开始
			 * @param {Object} e
			 */
			ListTouchStart(e) {
				this.listTouchStart = e.touches[0].pageX
			},

			/**
			 * ListTouch计算方向
			 * @param {Object} e
			 */
			ListTouchMove(e) {
				this.listTouchDirection = e.touches[0].pageX - this.listTouchStart > 0 ? 'right' : 'left'
			},

			/**
			 * ListTouch计算滚动
			 * @param {Object} e
			 */
			ListTouchEnd(e) {
				if (this.listTouchDirection == 'left') {
					this.modalName = e.currentTarget.dataset.target
				} else {
					this.modalName = null
				}
				this.listTouchDirection = null
			}	
		}
	}
</script>

<style scoped>
	.width{
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	.cu-list.menu-avatar>.cu-item{
		margin: 20rpx 0;
		border-radius: 10rpx;
	}
	.cu-list.menu-avatar>.cu-item .action{
		width: 160rpx;
	}
	.header-photo{
		position: absolute;
		left: 0rpx;
	}
</style>
