<template>
	<view class="content">
		<m-search
				:show="false"
				placeholder="搜索"
				button="none"
				backgroundColor="#efecec"
				v-model="searchStr"
		></m-search>
		<view class="all-select" @tap="allSelect(isAllSelect)">
			{{isAllSelect ? '全选' : '不选'}}
		</view>
		<view class="noData" v-if="noData === true">
			<noData :custom="true"><view class="title">暂无数据,点击重新加载</view></noData>
		</view>
		<view class="list cu-card article dynamic" v-else-if="noData === false">
			<view class="cu-item one" style="padding:0" v-for="(item,index) in peopleListQuery" :key="index" @tap="checkboxChange(item)">
				<view class="one-left-two">
					<view class="checkbox">
						<icon type="success" size="26" v-if="item.checked"/>
						<view class="not-selected" v-if="!item.checked"></view>
					</view>
					<avatar :userName="item.real_name" size="40"></avatar>
					<view class="one-right">
						<view class="name">
							{{item.real_name}}
							<text class="name-right">
								（{{item.user_type}})
							</text>
						</view>
					</view>
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
				flag: true,
			}
		},
		computed: {
		    // 模糊查询 
		    peopleListQuery(){
		        return this.followList.filter(people => {
		          return people.real_name.indexOf(this.searchStr) != -1;
		        })
		    },
			//是否全选
			isAllSelect() {
				let result = false;
				this.followList.forEach(item => {
					if (item.checked === false) {
						result = true;
					}
				})
				return result;
			}
			
		},
		onNavigationBarButtonTap() {
			let peopleList = [];
			this.followList.forEach(item => {
				if (item.checked === true) {
					peopleList.push(item.user_id)
				}
			})
			console.log(peopleList)
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
			 * 全选
			 */
			allSelect(isAllSelect) {
				if (isAllSelect) {
					this.followList.forEach(item => {
						item.checked = true;
					})
				} else {
					this.followList.forEach(item => {
						item.checked = false;
					})
				}
			},
			/**
			 * 多选框选择
			 * @param {Object} e
			 */
			checkboxChange(item) {
				if (item.checked === true) {
					this.$set(item,'checked',false);
				} else {
					this.$set(item,'checked',true);
				}
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
					if (this.followList.length > 0) {
						this.followList.forEach(item => {
							this.$set(item,"checked",false);
						})
					}
				},err => {
					console.log("err",err)
				})
			}
		}
	}
</script>

<style scoped>
	.all-select{
		position: absolute;
		top: 10px;
		right: 20px;
		color: #0081FF;
	}
	.not-selected{
		width: 52rpx;
		height: 52rpx;
		border: 1rpx solid #0081FF;
		border-radius: 50%;
	}
	.checkbox{
		padding: 20rpx 20rpx 20rpx 40rpx;
	}
	.name-right{
		font-weight: normal;
		font-size: 30rpx;
		color: #999;
	}
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
