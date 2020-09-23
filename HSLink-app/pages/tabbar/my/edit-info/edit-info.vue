<template>
	<view class="page">
		<form>
			<view class="cu-form-group">
				<view class="title">姓名</view>
				<input v-model="userInfo.real_name" disabled name="input"></input>
			</view>
			<view class="cu-form-group margin-top">
				<view class="title">性别</view>
				<picker @change="sexPickerChange" :value="sexIndex" :range="sexList">
					<view class="picker">
						{{sexIndex > -1 ? sexList[sexIndex] : (userInfo.sex === undefined ? "请选择性别" : userInfo.sex)}}
					</view>
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">生日</view>
				<picker mode="date" :value="userInfo.birthday" start="1900-01-01" @change="DateChange">
					<view class="picker">
						{{userInfo.birthday === undefined ? "请选择生日" : userInfo.birthday}}
					</view>
				</picker>
			</view>
			<view class="cu-form-group">
				<view class="title">地址</view>
				<input placeholder="请输入地址" v-model="userInfo.address" name="input"></input>
			</view>
			<view class="cu-form-group margin-top signature">
				<view class="cu-item">
					<view class="action">
						<text class="text-black">个性签名：</text>
					</view>
				</view>
				<view class="cu-item content margin-top">
					<textarea 	placeholder="请输入个性签名"
								v-model="userInfo.signature"
								maxlength=2000
					></textarea>
				</view>
			</view>
		</form>
	</view>
</template>

<script>
	import request from "@/util/request.js";
	import { updateUserInfo } from "@/static/js/public.js";
	export default {
		data() {
			return {
				sexIndex: -1,
				sexList: [],
				userInfo: uni.getStorageSync("userInfo")
			}
		},
		onPullDownRefresh () {
			uni.startPullDownRefresh();
		},
		onShow() {
			this.getSexList();
			console.log("用户信息",uni.getStorageSync("userInfo"));
		},
		/**
		 * 保存按钮
		 */
		onNavigationBarButtonTap() {
			console.log("用户信息",this.userInfo);
			request.post("/hs/updatePersonalInfo",{
				sex: this.userInfo.sex,
				birthday: this.userInfo.birthday,
				address: this.userInfo.address,
				signature: this.userInfo.signature,
				userId: this.userInfo.user_id,
			}).then(res => {
				console.log("保存按钮",res);
				if (res.data === 1) {
					uni.showToast({
						icon: "loading",
						title: "保存成功"
					});
					updateUserInfo();
					console.log("用户信息",this.userInfo);
				}
			},err => {
				console.log("err",err);
			})
		},
		methods: {
			/**
			 * 获取字典项数据（性别）
			 */
			getSexList() {
				request.post("/hs/getDictionariesData",{
					code: "Sex"
				}).then(res => {
					console.log("性别",res);
					this.sexList = [];
					res.data.forEach(item => {
						this.sexList.push(item.dd_detail)
					});
				},err => {
					console.log("err",err);
				})
			},
			/**
			 * 性别选择
			 * @param {Object} e
			 */
			sexPickerChange(e) {
				this.sexIndex = e.detail.value;
				this.userInfo.sex = this.sexList[this.sexIndex];
			},
			/**
			 * 生日选择
			 * @param {Object} e
			 */
			DateChange(e) {
				this.$set(this.userInfo,"birthday",e.detail.value)
			},
		}
	}
</script>

<style scoped>
	.uni-input-input, .uni-input-placeholder{
		text-align: right!important;
	}
	.content {
		background-color: #F1F1F1;
		width: 100%;
		padding: 20rpx;
		border-radius: 10rpx;
	}
	.cu-form-group uni-textarea{
		margin: 0!important;
	}
	textarea{
		width: 100%;
		height: 100rpx;
	}
	.signature{
		flex-flow: column!important;
		align-items: flex-start;
		padding: 20rpx;
		text-align: inherit!important;
	}
	.uni-input-form, .uni-input-wrapper{
		text-align: right!important;
	}
	.cu-form-group{
		border-radius: 10rpx;
		text-align: right;
	}
</style>
