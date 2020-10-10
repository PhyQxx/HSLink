<template>
	<view class="page">
		<uni-collapse>
			<view class="first-number">
				{{messageNoticeNumber}}
			</view>
		    <uni-collapse-item title="留言通知" thumb="../../../../static/img/message.png">
				<view class="one no" v-if="messageNoticeNumber === 0">
					暂无消息通知
				</view>
		        <view class="one" v-for="(item,index) in messageNoticeList" 
									:key="index"
									@tap="goToDetails(item)">
		            <view class="top">
		            	<text class="top-left">
		            		{{item.real_name}}
		            	</text>
						<text class="top-right">
							{{item.create_time}}
						</text>
		            </view>
					<view class="bottom">
						{{item.content}}
					</view>
		        </view>
		    </uni-collapse-item>
			<view class="second-number">
				{{verifyNoticeNumber}}
			</view>
		    <uni-collapse-item title="文章审核" thumb="../../../../static/img/examine.png" style="margin-top: -22px;">
				<view class="one no"  v-if="verifyNoticeNumber === 0">
					暂无审核通知
				</view>
		        <view  v-for="(item,index) in verifyNoticeList" 
				:key="index"
				@tap="goToMyDetails(item)">
		            <view class="a">
		            	<view class="left">
		            		<view class="pass" v-if="item.result === '1'">
		            			通过
		            		</view>
							<view class="reject" v-if="item.result === '2'">
								驳回
							</view>
		            	</view>
						<view class="right">
							<view class="right-top">
								{{item.title}}
							</view>
							<view class="right-bottom">
								{{item.release_time}}
							</view>
						</view>
		            </view>
		        </view>
		    </uni-collapse-item>
		</uni-collapse>
	</view>
</template>

<script>
	import uniCollapse from '@/components/uni-collapse/uni-collapse.vue'
	import uniCollapseItem from '@/components/uni-collapse-item/uni-collapse-item.vue'
	import request from '@/util/request.js'
	export default {
		components: {
			uniCollapse,
			uniCollapseItem
		},
		data() {
			return {
				//消息通知列表
				messageNoticeList: [],
				//审核通知列表
				verifyNoticeList: [],
				//消息通知数量
				messageNoticeNumber: '',
				//审核通知数量
				verifyNoticeNumber: '',
			}
		},
		onShow() {
			this.getNoticeData();
		},
		methods: {
			/**
			 * 判断数量是否为0
			 */
			clickNumber() {
				console.log(1111)
			},
			/**
			 * 跳转详情页面
			 * @param {Object} item
			 */
			goToDetails(item) {
				request.post('/hs/updateNoticeRead',{
					type: '0',
					id: item.id
				}).then(res => {
					console.log("消息通知已读",res)
				},err => {
					console.log("err",err)
				})
				uni.navigateTo({
					url: '/pages/tabbar/homepage/data-details?noticeId='+item.article_id
				})
			},
			/**
			 * 跳转我的文章详情页面
			 * @param {Object} item
			 */
			goToMyDetails(item) {
				request.post('/hs/updateNoticeRead',{
					type: '1',
					id: item.id
				}).then(res => {
					console.log("消息通知已读",res)
				},err => {
					console.log("err",err)
				})
				if (item.result === '1') {
					uni.navigateTo({
						url: '/pages/tabbar/homepage/data-details?noticeId='+item.article_id
					})
				} else {
					uni.setStorageSync('notice',item);
					uni.navigateTo({
						url: '/pages/notice-edit/notice-edit'
					})
				}
			},
			/**
			 * 获取通知
			 */
			getNoticeData() {
				request.post('/hs/getNoticeData',{
					authorId: uni.getStorageSync("userInfo").user_id
				}).then(res => {
					this.messageNoticeList = res.data.messageNoticeList;
					this.messageNoticeNumber = res.data.messageNoticeList.length;
					this.verifyNoticeList = res.data.verifyNoticeList;
					this.verifyNoticeNumber = res.data.verifyNoticeList.length;
					console.log("通知",res);
				},err => {
					console.log("err",err);
				})
			}
		}
	}
</script>

<style scoped>
	.no {
		padding: 20rpx;
	}
	.first-number{
		position: absolute;
		right: 96rpx;
		margin-top: 28rpx;
		border-radius: 50%;
		background: red;
		color: #FFFFFF;
		padding: 6rpx;
		font-size: 28rpx;
		width: 44rpx;
		text-align: center;
	}
	.second-number{
		position: relative;
		left: 592rpx;
		top: 26rpx;
		height: 44rpx;
		background: red;
		border-radius: 50%;
		width: 44rpx;
		color: #FFFFFF;
		font-size: 28rpx;
		line-height: 44rpx;
		text-align: center;
	}
	.a .right-top{
		width: 250px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	.a .right-bottom{
		font-size: 30rpx;
		color: #999999;
		margin-top: 10rpx;
	}
	.pass{
		font-size: 30rpx;
		color: #39B54A;
		border: 1rpx solid #39B54A;
		border-radius: 10rpx;
		background-color: #FFFFFF;
	}
	.reject{
		font-size: 30rpx;
		color: #f10000;
		border: 1rpx solid #f10000;
		border-radius: 10rpx;
		background-color: #FFFFFF;
	}
	.a .left{
		padding: 20rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-right: 20rpx;
	}
	.a{
		display: flex;
		padding-top: 20rpx;
	}
	.one, .a{
		background-color: #e6e6e6;
		margin: 20rpx;
		border-radius: 10rpx;
		padding-bottom: 20rpx;
	}
	.one .bottom{
		padding: 0 20rpx;
		font-size: 30rpx;
		color: #a3a3a3;
	}
	.top .top-right{
		color: #A5A5A5;
	}
	.one .top{
		display: flex;
		justify-content: space-between;
		padding: 20rpx;
	}
	.uni-collapse, .uni-collapse-cell--open{
		background-color: rgba(0,0,0,0);
	}
	.page{
		background-color: #FFFFFF;
		padding-bottom: 0;
	}
</style>
