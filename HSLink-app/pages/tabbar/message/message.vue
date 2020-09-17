<template>
	<view class="page">
		<view class="cu-list menu-avatar">
			<view class="cu-item" :class="modalName=='move-box-'+ index?'move-cur':''" 
						v-for="(item,index) in message" 
						:key="index"
						@touchstart="ListTouchStart" 
						@touchmove="ListTouchMove" 
						@touchend="ListTouchEnd" 
						:data-target="'move-box-' + index"
						@tap="goToMessage(item)">
				<view class="header-photo">
					{{item.userInfo.real_name.slice(0,1)}}
				</view>
				<view class="content">
					<view class="text-grey">{{item.userInfo.real_name}}</view>
					<view class="text-gray text-sm width">
						{{item.letterList[item.letterList.length-1].content}}
					</view>
				</view>
				<view class="action">
					<view class="text-grey text-xs">{{item.letterList[item.letterList.length-1].letter_create_time}}</view>
					<view class="cu-tag round bg-grey sm">{{item.unreadNumber}}</view>
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
	export default {
		data() {
			return {
				modalName: null,
				message: [],
			}
		},
		onShow() {
			this.getPersonalPrivateLetter();
		},
		methods: {
			/**
			 * 跳转到消息页面
			 * @param {Object} item
			 */
			goToMessage(item) {
				uni.setStorageSync("messageInfo",item);
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
					this.message.forEach((item,index) => {
						let unreadNumber = 0;
						item.letterList.forEach(i => {
							if (i.already_read === '0' && i.receive_id === uni.getStorageSync("userInfo").user_id) {
								unreadNumber++;
							}
						});
						item.unreadNumber = unreadNumber;
						
					});
					this.message.forEach((item,index) => {
						if (item.userInfo.user_id === uni.getStorageSync("userInfo").user_id) {
							this.message.splice(index,1);
						}
					});
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
		left: 20rpx;
		width: 100rpx;
		height: 100rpx;
		line-height: 100rpx;
		text-align: center;
		border-radius: 50%;
		background-color: #1296DB;
		color: #FFFFFF;
		font-size: 40rpx;
		font-weight: bold;
	}
</style>
