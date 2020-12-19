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
								<text class="text-black text-lg  over-flow">{{item.title}}</text>
							</view>
						</view>
					</view>
				</view>
				<view class="text-content" style="margin:10rpx 0 0 0;">
					<text class="text-gray">文章标签：</text>
					<text class="">{{item.label}}</text>
				</view>
				<view class="text-content" style="margin:10rpx 0 0 0;" @tap="goToUserInfo(item)">
					<text class="text-gray">发表人：</text>
					<text class="">{{item.releaseName}}</text>
				</view>
				<view class="text-content" style="margin:10rpx 0 0 0;">
					<text class="text-gray">发表时间：</text>
					<text class="">{{item.releaseTime}}</text>
				</view>
				<view class="cu-item flex flex-direction bg-white solid-top">
					<button class="cu-btn bg-white text-orange lg" style="color: #1296DB;font-weight: 400;" 
					@tap.stop="verifyArticle(item,'1')">
						通过
					</button>
					<button class="cu-btn bg-white text-orange lg" style="color: red;font-weight: 400;"
					@tap.stop="verifyArticle(item,'2')">
						驳回
					</button>
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
			//文章管理数据
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
		 * 审核（通过/驳回）
		 * @param {Object} item
		 */
		verifyArticle(item, operating) {
			const OPERATING = {
				"1": "通过",
				"2": "驳回"
			};
			let _this = this;
			uni.showModal({
				title: "审核",
				content: `确认${OPERATING[operating]}?`,
				success(res) {
					if (res.confirm) {
						request.post('/admin/verifyArticle',{
							articleId: item.articleId,
							operating: operating
						}).then(res=>{
							console.log("审核结果",res);
							if (res.data > 0) {
								uni.showToast({
									icon: "loading",
									title: `${OPERATING[operating]}成功`
								});
								setTimeout(() => {
									_this.getAllData();
								},1000)
							} else {
								uni.showToast({
									icon: "none",
									title: "服务器出小差了，请稍后再试"
								})
							}
						},err=>{
							console.log("err",err);
						})
					}
				}
			})
		},
		/**
		 * 跳转个人主页页面
		 * @param {Object} item
		 */
		goToUserInfo(item) {
			if (item.user_id === uni.getStorageSync("userInfo").user_id) {
				uni.switchTab({
				    url: '/pages/tabbar/my/my'
				});
			} else {
				uni.navigateTo({
					url: `/pages/person-info-page/person-info-page?userId=${item.releaseId}`
				})
			}
		},
		/**
		 * 跳转详情页面
		 * @param {Object} item
		 */
		goToDetails(item) {
			uni.navigateTo({
				url: `/pages/tabbar/homepage/data-details?noticeId=${item.articleId}&isMessage=false`
			})
		},
		/**
		 * 获取文章管理数据
		 */
		getAllData() {
			request.post('/admin/getVerifyList',{})
			.then(res=>{
				this.noticeList = res.data.sort(sortBy("release_time",false));
				uni.startPullDownRefresh();
				this.noData = this.noticeList.length === 0 ? true : false;
 				console.log("文章管理数据",this.noticeList);
			},err=>{
				console.log("err",err);
			})
		}
	}
};
</script>

<style scoped>
.content {
	min-height: 85vh;
	padding-bottom: 100rpx;
}
.text-gray{
	display: inline-block;
	width: 6rem;
}
.cu-card>.cu-item{
	margin: 20rpx!important;
}
</style>
