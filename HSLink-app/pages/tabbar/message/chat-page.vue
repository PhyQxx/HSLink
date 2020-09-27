<template>
	<view class="pages">
		<view class="cu-chat" id="chart-page" :style="[{'margin-bottom': 50+InputBottom+'px'}]">
			<view class="cu-item" :class="isMy(item,'self')" v-for="(item,index) in letterList" :key="index">
				<view class="cu-avatar radius" v-if="item.send_id !== userInfo.user_id" @tap="goToOther(item)">
					<avatar :userName="otherInfo.real_name" size="45"></avatar>
				</view>
				<view class="main">
					<view class="content shadow" :class="isMy(item,'bg-green')">
						<text>{{item.content}}</text>
					</view>
				</view>
				<view class="cu-avatar radius" v-if="item.send_id === userInfo.user_id" @tap="goToMy">
					<avatar :userName="userInfo.real_name" size="45"></avatar>
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
	import avatar from "@/pages/components/avatar/avatar.vue";
	export default {
		components: {
			avatar
		},
		data() {
			return {
				otherInfo: uni.getStorageSync("otherInfo"),
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
			let timesRun = 0;
			let interval = setInterval(() => {
				this.getTwoLetterApp();
			timesRun += 1;
			if(timesRun === 5){
			clearInterval(interval);
			}
			}, 2000);
		},
		onBackPress() {
		},
		onLoad() {
			uni.setNavigationBarTitle({
				title: this.otherInfo.real_name
			});
		},
		watch: {
			letterList() {
				this.$nextTick(() => {
					uni.pageScrollTo({scrollTop: 99999, duration: 0});
				})
			}
		},
		methods: {
			/**
			 * 调整弹框高度
			 */
			InputFocus(e) {
				setTimeout(() => {
				this.InputBottom = e.detail.height;
				console.log(this.InputBottom)
				},500)
			},
			InputBlur(e) {
				setTimeout(() => {
					this.InputBottom = 0
				},500)
			},
			/**
			 * 跳转到其他人的主页
			 * @param {Object} item
			 */
			goToOther(item) {
				uni.navigateTo({
					url: `/pages/person-info-page/person-info-page?userId=${item.send_id}`
				})
			},
			/**
			 * 跳转到我的主页
			 * @param {Object} item
			 */
			goToMy() {
				uni.switchTab({
				    url: '/pages/tabbar/my/my'
				});
			},
			
			/**
			 * 获取两个人的私信
			 */
			getTwoLetterApp() {
				request.post('/hs/getTwoLetterApp',{
					userId: uni.getStorageSync("userInfo").user_id,
					otherId: this.otherInfo.user_id,
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
						receiveId: this.otherInfo.user_id,
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
					otherId: this.otherInfo.user_id
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
		}
	}
</script>

<style scoped>
	/* .cu-item .date{
		color: #FFFFFF;
	}
	.pages{
		background-image: url("@/static/img/chat-bg.jpg");
		background-size: 100% 100%;
		height: calc(100vh - 100rpx);
		overflow: scroll;
	} */
	.cu-avatar{
		background-color: rgba(0,0,0,0);
	}
.cu-chat{
	margin-bottom: 100rpx;
}
</style>
