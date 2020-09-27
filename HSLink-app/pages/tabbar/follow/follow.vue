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
			<view class="cu-item" style="padding:0" v-for="(item,index) in noticeListQuery" :key="index">
				<view class="cu-list menu solid-bottom" @click="goToDetails(item)">
					<view class="cu-item arrow" style="min-height: 90rpx;padding-top: 10rpx;">
						<view class="action">
							<view class="action">
								<view class='cu-tag radius bg-orange light margin-right-xs' v-if="item.type === '校园通知'">{{item.type}}</view>
								<view class='cu-tag radius bg-blue light margin-right-xs' v-if="item.type === '家长建议'">{{item.type}}</view>
								<view class='cu-tag radius bg-green light margin-right-xs' v-if="item.type === '学生想法'">{{item.type}}</view>
								<text class="text-black text-lg">{{item.title}}</text>
							</view>
						</view>
					</view>
				</view>
				<view class="text-content" style="margin:10rpx 0 0 0;">
					<text class="text-gray">文章标签：</text>
					<text class="">{{item.label}}</text>
				</view>
				<view class="text-content" style="margin:10rpx 0 0 0;">
					<text class="text-gray">发表人：</text>
					<text class=""  @tap="goToUserInfo(item)">{{item.real_name}}</text>
					<text class="cancel" @tap="cancelFollow(item)">取消关注</text>
				</view>
				<view class="text-content" style="margin:10rpx 0 0 0;">
					<text class="text-gray">发表时间：</text>
					<text class="">{{item.release_time}}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
    import mSearch from '@/components/mehaotian-search/mehaotian-search.vue';
	import noData from '@/components/noData/noData.vue';
	import { sortBy } from '@/static/js/public.js';
export default {
	components: {
	    mSearch,
		noData
	},
	data() {
		return {
			//无数据
			noData: false,
			//搜索关键字
			searchStr: '',
			//首页数据
			noticeList: [],
		};
	},
	computed: {
	    // 模糊查询 
	    noticeListQuery(){
	        return this.noticeList.filter(notice => {
	          return notice.title.indexOf(this.searchStr) != -1 || notice.type.indexOf(this.searchStr) != -1
			  || notice.real_name.indexOf(this.searchStr) != -1
	        })
	    }
		
	},
	onLoad() {
		
	},
	onShow() {
		this.getAllData();
	},
	onPullDownRefresh () {
		this.getAllData();
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
		 * 更新方法
		 */
		update() {
			this.getAllData();
		},
		/**
		 * 取消关注
		 * @param {Object} item
		 */
		cancelFollow(item) {
			let _this = this;
			uni.showModal({
				title: '取消关注',
				content: '确认取消关注？',
				success(res) {
					if (res.confirm) {
						request.post("/hs/cancelFollow",{
							userId: uni.getStorageSync("userInfo").user_id,
							followId: item.user_id
						}).then(res => {
							console.log("取消关注",res);
							if (res.data === 1) {
								_this.getAllData();
							}
						},err => {
							console.log("err",err);
						})
					} else if (res.cancel) {
						console.log("取消");
					}
 				}
			})
		},
		/**
		 * 跳转详情页面
		 * @param {Object} item
		 */
		goToDetails(item) {
			uni.navigateTo({
				url: '/pages/tabbar/homepage/data-details?noticeId='+item.id
			})
		},
		/**
		 * 获取关注人员文章数据
		 */
		getAllData() {
			request.post('/hs/getFollowList',{
				userId: uni.getStorageSync("userInfo").user_id
			})
			.then(res=>{
				this.noticeList = res.data;
				uni.startPullDownRefresh();
				this.noData = this.noticeList.length === 0 ? true : false;
 				console.log("关注页面数据",res.data);
			},err=>{
				console.log("err",err);
			})
		}
	}
};
</script>

<style scoped>
	.cancel{
		float: right;
		margin-right: 32rpx;
		padding: 0 5rpx;
		color: red;
		border: 1rpx solid red;
		border-radius: 10rpx;
	}
.content {
	min-height: 85vh;
	padding-bottom: 100rpx;
}
.text-gray{
	display: inline-block;
	width: 4rem;
}
.cu-card>.cu-item{
	margin: 20rpx!important;
}
</style>
