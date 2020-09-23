<template>
	<view class="page">
		<view class="top">
			<view class="title">
				{{noticeInfo.title}}
			</view>
			<view class="label">
				{{noticeInfo.label}}
			</view>
			<view class="author-and-time">
				<view class="author" @tap="goToUserInfo(noticeInfo)" style="padding: 0;">
					{{noticeInfo.real_name}}
				</view>
				<view class="time">
					{{noticeInfo.release_time}}
				</view>
			</view>
			<view class="content">
				<textarea placeholder="请输入文章内容"
							v-model="noticeInfo.content"
							auto-height="true"
							disabled="true"
				></textarea>
			</view>
		</view>
		<view class="feedback">
			<view class="reading-volume">
				阅读 245
			</view>
			<view class="fabulous">
				<uni-fav 	:checked="checked" 
							class="favBtn" 
							circle="true" 
							bg-color="#FFFFFF"
							bg-color-checked="#1296DB" 
							@click="onClick"></uni-fav>
			</view>
		</view>
		<view class="no-message" v-if="noMessage === true"  @tap="addMessage">
			写留言
		</view>
		<view class="leave-message" v-if="noMessage === false">
			<view class="message-title">
				<view class="left">
					精选留言
				</view>
				<view class="right" @tap="addMessage">
					写留言
				</view>
			</view>
			<view class="one-message" v-for="(item,index) in messageList" :key="index">
				<view class="left">
					<view class="header-photo">
						{{item.real_name.slice(0,1)}}
					</view>
					<view class="message">
						<view class="name">
							{{item.real_name}}
						</view>
						<view class="message-content">
							{{item.content}}
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="cu-modal" :class="messageDialog ? 'show' : ''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">留言</view>
					<view class="action" @tap="hideModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					<input type="text" v-model="messageContent" placeholder="请输入留言"/>
				</view>
				<view class="cu-bar bg-white justify-end">
					<view class="action">
						<button class="cu-btn line-blue text-blue" @tap="hideModal">取消</button>
						<button class="cu-btn bg-blue margin-left" @tap="clickPromptConfirm">确定</button>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	import uniFav from '@/components/uni-fav/uni-fav.vue';
	export default {
		components: {
			uniFav
		},
		data() {
			let noticeInfo = uni.getStorageSync('notice');
			noticeInfo.fabulous = true;
			noticeInfo.messageFabulous = true;
			return {
				//是否已收藏
				checked: true,
				//文章信息
				noticeInfo: noticeInfo,
				//有无留言
				noMessage: false,
				//留言列表
				messageList: [],
				//留言窗口
				messageDialog: false,
				//留言内容
				messageContent: '',
			}
		},
		onLoad() {
			
		},
		mounted() {
			this.getMessageList()
		},
		onPullDownRefresh () {
			uni.startPullDownRefresh();
		},
		methods: {
			/**
			 * 关闭留言窗口
			 */
			hideModal() {
				this.messageDialog = false;
			},
			/**
			 * @param {Object} item
			 */
			goToUserInfo(item) {
				if (item.user_id === uni.getStorageSync("userInfo").user_id) {
					uni.switchTab({
					    url: '/pages/tabbar/my/my'
					});
				} else {
					uni.navigateTo({
						url: `/pages/person-info-page/person-info-page?userId=${item.user_id}`
					})
				}
			},
			/**
			 * 点击留言弹出输入框确定
			 */
			clickPromptConfirm() {
				if (this.messageContent === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入留言，亲'
					})
				} else {
					request.post('/hs/addMessage',{
						noticeId: this.noticeInfo.id,
						userId: uni.getStorageSync("userInfo").user_id,
						content: this.messageContent
						}).then(res=>{
							console.log("新增留言结果",res);
							if (res.data === 1) {
								this.messageDialog = false;
								uni.showToast({
									icon: 'loading',
									title: '留言成功',
								});
								setTimeout(()=>{
									this.getMessageList();
								},1000)
							}
						},err=>{
							console.log("err",err);
					})
				}
			},
			/** 
			 * 获取留言 
			 */
			getMessageList() {
				request.post('/hs/getOneContent',{id: this.noticeInfo.id})
				.then(res=>{
					this.noMessage = res.data.message.length === 0 ? true : false;
					this.messageList = res.data.message;
					console.log("一条数据数据",res);
				},err=>{
					console.log("err",err);
				})
			},
			/**
			 * 新增留言（打开弹框）
			 */
			addMessage() {
				this.messageDialog = true;
			}
			
		}
	}
</script>

<style scoped>
	.padding-xl input{
		background-color: #FFFFFF;
		text-align: left;
	}
	.one-message{
		margin-bottom: 20rpx;
	}
	.page .top, .one-message{
		background-color: #FFFFFF;
		border-radius: 10rpx;
		padding: 20rpx;
	}
	.content {
		background-color: #F1F1F1;
		width: 100%;
		padding: 20rpx;
		border-radius: 10rpx;
	}
	textarea{
		text-indent: 32rpx;
		line-height: 1.5;
		width: 100%;
		height: 100%;
	}
	.message-title .left{
		color: #a7a7a7;
	}
	.message-title .right{
		color: #1296DB;
	}
	.message-title{
		padding: 20rpx;
		display: flex;
		flex-flow: wrap;
		justify-content: space-between;
	}
	.message .name{
		color: #a7a7a7;
		margin-bottom: 10rpx;
	}
	.left .message{
		margin-left: 32rpx;
	}
	.one-message .left{
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.one-message .header-photo{
		color: #1296DB;
		border: 1rpx solid #1296DB;
		border-radius: 50%;
		width: 76rpx;
		height: 76rpx;
		text-align: center;
		line-height: 76rpx;
	}
	.one-message{
		display: flex;
		padding: 20rpx;
		justify-content: space-between;
		align-items: center;
	}
	.no-message{
		text-align: center;
		padding: 20rpx 0;
	}
	.reading-volume, .fabulous, .no-message, .message-fabulous{
		color: #1296DB;
	}
	.fabulous image, .message-fabulous image{
		width: 36rpx;
		height: 36rpx;
		display: inline-block;
	}
	.label{
		margin: 20rpx 0;
		border-radius: 10rpx;
		width: 4rem;
		font-size: 30rpx;
		text-align: center;
		color: #ff9041;
		border: 1rpx solid #ff9041;
	}
	.content{
		padding: 20rpx!important;
	}
	.feedback{
		padding: 20rpx;
		background-color: #FFFFFF;
		margin-top: 20rpx;
	}
	.author-and-time, .feedback{
		display: flex;
	}
	.author{
		color: #08ACEE;
	}
	.time{
		color: #a5a5a5;
	}
	.author, .time, .reading-volume, .fabulous{
		display: flex;
		justify-content: center;
		align-items: center;
		padding-left: 20rpx;
		height: 60rpx;
		line-height: 60rpx;
	}
	.title{
		padding: 20rpx 0;
		font-size: 36rpx;
	}
	
</style>
