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
			<view class="cu-item one" style="padding:0" v-for="(item,index) in peopleListQuery" :key="index" @tap="goToUserInfo(item)">
				<view class="one-left-two">
					<avatar :userName="item.real_name" size="50"></avatar>
					<view class="one-right">
						<view class="name">
							{{item.real_name}}
						</view>
						<view class="other-info">
							<view class="other-info-left">
								关注:{{item.followNumber}}
							</view>
							<view class="other-info-middle">
								粉丝:{{item.fansNumber}}
							</view>
							<view class="other-info-right">
								积分:{{item.integral}}
							</view>
						</view>
					</view>
				</view>
				<view class="follow-flag ed" @tap.stop="peopleManagement(item, '1')" v-if="item.frozen_state === '0'">
					冻结
				</view>
				<view class="follow-flag" @tap.stop="peopleManagement(item, '0')" v-if="item.frozen_state === '1'">
					解冻
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
	import avatar from "@/pages/components/avatar/avatar.vue";
	export default {
		components: {
		    mSearch,
			noData,
			avatar
		},
		data() {
			return {
				//无数据
				noData: false,
				//搜索关键字
				searchStr: '',
				//关注列表
				followList: [],
				flag: true
			}
		},
		computed: {
		    // 模糊查询 
		    peopleListQuery(){
		        return this.followList.filter(people => {
		          return people.real_name.indexOf(this.searchStr) != -1;
		        })
		    }
			
		},
		onPullDownRefresh () {
			this.getFollowPeopleList();
		},
		onShow() {
			this.getFollowPeopleList();
		},
		mounted() {
			this.getFollowPeopleList();
		},
		methods: {
			/**
			 * 冻结/解冻
			 * @param {Object} item
			 * @param {Object} operation 冻结/解冻
			 */
			peopleManagement(item, operating) {
				const OPERATING = {
					"0": "解冻",
					"1": "冻结",
				};
				let _this = this;
				uni.showModal({
					title: `${OPERATING[operating]}`,
					content: `确认${OPERATING[operating]}?`,
					success(res) {
						if (res.confirm) {
							request.post('/admin/peopleManagement',{
								userId: item.user_id,
								operating: operating
							}).then(res=>{
								console.log("人员操作结果",res);
								if (res.data > 0) {
									uni.showToast({
										icon: "loading",
										title: `${OPERATING[operating]}成功`
									});
									setTimeout(() => {
										_this.getFollowPeopleList();
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
				uni.navigateTo({
					url: `/pages/person-info-page/person-info-page?userId=${item.user_id}`
				})
			},
			/**
			 * 获取人员管理列表
			 */
			getFollowPeopleList() {
				request.post("/admin/getPersonnelManagement",{
					userId: uni.getStorageSync("userInfo").user_id
				}).then(res => {
					console.log("人员管理列表",res);
					uni.startPullDownRefresh();
					this.followList = res.data;
						this.noData = this.followList.length === 0 ? true : false;
				},err => {
					console.log("err",err)
				})
			}
		}
	}
</script>

<style scoped>
	.other-info-middle{
		padding: 0 20rpx;
	}
	.other-info{
		display: flex;
		color: #9a9a9a;
		font-size: 30rpx;
	}
	.one-right{
		margin-left: 20rpx;
	}
	.one-left-two{
		display: flex;
		flex-flow: nowrap;
		justify-content: center;
		align-items: center;
	}
	.follow-flag{
		border-radius: 10rpx;
		background-color: #afafaf;
		color: #FFFFFF;
		font-size: 30rpx;
		padding: 6rpx 14rpx;
	}
	.ed{
		background-color: #269FDE;
		color: #FFFFFF;
		font-size: 30rpx;
	}
	.one-right .grade{
		font-size: 34rpx;
		color: #909090;
	}
	.one-right .name{
		padding-bottom: 12rpx;
		font-weight: bold;
	}
	.one-right .grade-right{
		margin-left: 16rpx;
	}
	.one-left{
		font-size: 80rpx;
		font-family: 'Courier New', Courier, monospace;
		color: #1296DB;
		border: 5rpx solid #1296DB;
		border-radius: 50%;
		width: 140rpx;
		height: 140rpx;
		text-align: center;
		line-height: 140rpx;
		margin: 20rpx;
	}
	.one{
		padding-right: 24rpx!important;
	}
	.one, .grade{
		display: flex!important;
		flex-flow: nowrap;
		align-items: center;
		justify-content: space-between;
	}
.grade .r, .grade .l{
		font-size: 14rpx;
		padding: 5rpx;
		border-radius: 5rpx;
		color: #FFFFFF;
	}
	.grade .l{
		background-color: #9DC75F;
	}
	.grade .r{
		background-color: #2D5315;
	}
</style>
