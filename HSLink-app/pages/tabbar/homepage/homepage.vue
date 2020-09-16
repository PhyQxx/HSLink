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
								<view class='cu-tag radius bg-orange light margin-right-xs'>{{item.type}}</view>
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
					<text class="">{{item.real_name}}</text>
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
	methods: {
		//跳转详情页面
		goToDetails(item) {
			uni.setStorageSync("notice",item);
			uni.navigateTo({
				url: '/pages/tabbar/homepage/data-details'
			})
		},
		//获取首页数据
		getAllData() {
			request.post('/hs/getAllContent',{})
			.then(res=>{
				res.data.data.parentAdvice.forEach(item=>{
					this.$set(item,'type','家长意见');
				});
				res.data.data.schoolNoticeList.forEach(item=>{
					this.$set(item,'type','校园通知');
				});
				res.data.data.studentThinking.forEach(item=>{
					this.$set(item,'type','学生想法');
				})
				this.noticeList = res.data.data.parentAdvice.concat(res.data.data.schoolNoticeList, res.data.data.studentThinking);
				this.noData = this.noticeList.length === 0 ? true : false;
 				console.log("首页数据",this.noticeList);
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
}
.text-gray{
	display: inline-block;
	width: 4rem;
}
.cu-card>.cu-item{
	margin: 20rpx!important;
}
</style>
