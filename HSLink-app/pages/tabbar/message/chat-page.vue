<template>
	<view class="pages">
		<view class="cu-chat" id="chart-page">
			<view class="cu-item" :class="isMy(item,'self')" v-for="(item,index) in letterList" :key="index">
				<view class="header-photo cu-avatar radius" v-if="item.send_id !== userInfo.user_id">
					{{messageInfo.userInfo.real_name.slice(0,1)}}
				</view>
				<view class="main">
					<view class="content shadow" :class="isMy(item,'bg-green')">
						<text>{{item.content}}</text>
					</view>
				</view>
				<view class="header-photo cu-avatar radius" v-if="item.send_id === userInfo.user_id">
					{{userInfo.real_name.slice(0,1)}}
				</view>
				<view class="date">{{item.letter_create_time}}</view>
			</view>
		</view>
	
		<view class="cu-bar foot input" :style="[{bottom:InputBottom+'px'}]">
			<view class="action">
				<text class="cuIcon-sound text-grey"></text>
			</view>
			<input class="solid-bottom" :adjust-position="false" :focus="false" maxlength="300" cursor-spacing="10"
			 @focus="InputFocus" @blur="InputBlur" v-model="messageContent"></input>
			<view class="action">
				<text class="cuIcon-emojifill text-grey"></text>
			</view>
			<button class="cu-btn bg-green shadow" @tap="sendMessage">发送</button>
		</view>
	
	</view>
</template>

<script>
	import request from '@/util/request.js';
	import $ from '@/static/js/jquery-1.12.2.js';
	export default {
		
		data() {
			return {
				messageInfo: uni.getStorageSync("messageInfo"),
				userInfo: uni.getStorageSync('userInfo'),
				InputBottom: 0,
				//消息内容
				messageContent: '',
				//私信列表
				letterList: [],
				//定时任务
				interval: {}
			}
		},
		onShow() {
			this.updateRead();
			this.getTwoLetterApp();
			this.interval = setInterval(() => {
				this.getTwoLetterApp();
			} ,5000);
		},
		onBackPress() {
			clearInterval(this.interval);
		},
		onLoad() {
			uni.setNavigationBarTitle({
				title: this.messageInfo.userInfo.real_name
			});
		},
		watch: {
			letterList() {
				console.log("messageList change");
				this.$nextTick(() => {
					uni.pageScrollTo({scrollTop: 99999, duration: 0});
				})
			}
		},
		methods: {
			/**
			 * 获取两个人的私信
			 */
			getTwoLetterApp() {
				request.post('/hs/getTwoLetterApp',{
					userId: uni.getStorageSync("userInfo").user_id,
					otherId: this.messageInfo.userInfo.user_id,
				}).then(res => {
					console.log("获取两个人的私信",res);
					if (res.data !== null) {
						this.letterList = res.data;
					} else {
						
					}
				},err=>{
					console.log("err",err);
				})
			},
			/**
			 * 发送消息
			 */
			sendMessage() {
				if (this.messageContent === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入消息，亲'
					})
				} else {
					request.post('/hs/sendLetter',{
						sendId: uni.getStorageSync("userInfo").user_id,
						receiveId: this.messageInfo.userInfo.user_id,
						content: this.messageContent
					}).then(res => {
						this.messageContent = '';
						console.log("发送消息",res);
						if (res.data === 1) {
							this.getTwoLetterApp();
							this.messageContent = '';
						}
					},err=>{
						console.log("err",err);
					})
				}
			},
			/**
			 * 改为已读
			 */
			updateRead() {
				request.post('/hs/updateReadApp',{
					userId: uni.getStorageSync("userInfo").user_id,
					otherId: this.messageInfo.userInfo.user_id
				}).then(res => {
					console.log("改为已读",res);
				},err=>{
					console.log("err",err);
				})
			},
			/**
			 * 判断是否是发送人
			 * @param {Object} item
			 */
			isMy(item,classText) {
				return item.send_id === uni.getStorageSync("userInfo").user_id ? classText : ''
			},
			InputFocus(e) {
				this.InputBottom = e.detail.height
			},
			InputBlur(e) {
				this.InputBottom = 0
			}
		}
	}
</script>

<style scoped>
	.header-photo{
		text-align: center;
		background-color: #1296DB;
		color: #FFFFFF;
		font-size: 40rpx;
		font-weight: bold;
	}
.cu-chat{
	margin-bottom: 100rpx;
}
</style>
