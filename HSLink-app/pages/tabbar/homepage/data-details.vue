<template>
	<view class="page">
		<view class="title">
			{{noticeInfo.title}}
		</view>
		<view class="label">
			{{noticeInfo.label}}
		</view>
		<view class="author-and-time">
			<view class="author">
				{{noticeInfo.real_name}}
			</view>
			<view class="time">
				{{noticeInfo.release_time}}
			</view>
		</view>
		<view class="content">
			{{noticeInfo.content}}
		</view>
		<view class="feedback">
			<view class="reading-volume">
				阅读 245
			</view>
			<view class="fabulous">
				<image src="@/static/img/fabuloused.png" mode="" v-if="noticeInfo.fabulous === true"></image>
				<image src="@/static/img/fabulous.png" mode="" v-if="noticeInfo.fabulous === false">
				<text>点赞 123</text>
			</view>
		</view>
		<view class="no-message" v-if="noMessage === true">
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
			<view class="one-message" v-for="item in messageList">
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
				<view class="message-fabulous">
					<image src="@/static/img/fabuloused.png" mode="" v-if="noticeInfo.messageFabulous === true"></image>
					<image src="@/static/img/fabulous.png" mode="" v-if="noticeInfo.messageFabulous === false">
					<text>123</text>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	import request from '@/util/request.js';
	export default {
		data() {
			let noticeInfo = uni.getStorageSync('notice');
			noticeInfo.fabulous = true;
			noticeInfo.messageFabulous = true;
			return {
				//文章信息
				noticeInfo: noticeInfo,
				//有无留言
				noMessage: false,
				//留言列表
				messageList: []
			}
		},
		onLoad() {
			
		},
		mounted() {
			this.getMessageList()
		},
		methods: {
			//获取留言
			getMessageList() {
				request.post('/hs/getOneContent',{id: this.noticeInfo.id})
				.then(res=>{
					this.noMessage = res.data.data.message.length === 0 ? true : false;
					this.messageList = res.data.data.message;
					console.log("一条数据数据",res);
				},err=>{
					console.log("err",err);
				})
			},
			addMessage() {
				request.post('/hs/addMessage',{
					noticeId: this.noticeInfo.id
					// userId: 
					}).then(res=>{
						console.log("新增留言结果",res);
					},err=>{
						console.log("err",err);
					})
			}
			
		}
	}
</script>

<style scoped>
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
		margin-left: 20rpx;
		border-radius: 10rpx;
		width: 4rem;
		text-align: center;
		color: #ff9041;
		border: 1rpx solid #ff9041;
	}
	.content{
		padding: 20rpx!important;
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
		padding: 20rpx;
		font-size: 36rpx;
	}
	.page{
		margin: 20rpx;
		background-color: #FFFFFF;
		border-radius: 10rpx;
	}
	
</style>
