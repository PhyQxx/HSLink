<template>
	<view class="page">
		<view class="cu-list menu sm-border">
			<view class="cu-item arrow" @tap="updatePhone">
				<view class="content">
					<text class="cuIcon-phone text-grey"></text>
					<text class="text-grey">手机号：{{phone}}</text>
				</view>
				<view class="action">
					<text class="text-grey text-sm">修改</text>
				</view>
			</view>
			<view class="cu-item arrow" @tap="cancellation">
				<view class="content">
					<text class="cuIcon-info text-grey"></text>
					<text class="text-grey">注销账号</text>
				</view>
			</view>
			<view class="cu-item arrow" @tap="updatePassword">
				<view class="content">
					<text class="cuIcon-edit text-grey"></text>
					<text class="text-grey">修改密码</text>
				</view>
			</view>
		</view>
		<view class="cu-modal" :class="phoneDialog ? 'show' : ''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">修改手机号</view>
					<view class="action" @tap="hideModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					<input type="number" v-model="newPhone" placeholder="请输入手机号"/>
				</view>
				<view class="cu-bar bg-white justify-end">
					<view class="action">
						<button class="cu-btn line-blue text-blue" @tap="hideModal">取消</button>
						<button class="cu-btn bg-blue margin-left" @tap="clickUpdatePhoneConfirm">确定</button>
					</view>
				</view>
			</view>
		</view>
		<view class="cu-modal" :class="passwordDialog ? 'show' : ''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">修改密码</view>
					<view class="action" @tap="hideModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					<input type="password" v-model="oldPassword" placeholder="请输入原始密码"/>
					<input type="password" v-model="newPassword" placeholder="请输入新密码"/>
					<input type="password" v-model="againPassword" placeholder="请确认密码"/>
				</view>
				<view class="cu-bar bg-white justify-end">
					<view class="action">
						<button class="cu-btn line-blue text-blue" @tap="hideModal">取消</button>
						<button class="cu-btn bg-blue margin-left" @tap="clickUpdatePasswordConfirm">确定</button>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/util/request.js';
	export default {
		data() {
			return {
				//原手机号
				phone: uni.getStorageSync("userInfo").mobile,
				//新手机号
				newPhone: '',
				//修改手机号弹窗标志
				phoneDialog: false,
				//修改密码弹窗标志
				passwordDialog: false,
				//旧密码
				oldPassword: '',
				//新密码
				newPassword: '',
				//确认密码
				againPassword: ''
				
			}
		},
		onPullDownRefresh () {
			uni.startPullDownRefresh();
		},
		methods: {
			/**
			 * 注销
			 */
			cancellation() {
				uni.showToast({
					icon: 'none',
					title: '请联系管理员注销账号'
				})
			},
			/**
			 * 修改密码接口
			 */
			clickUpdatePasswordConfirm() {
				if (this.oldPassword === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入原始密码',
					})
				} else if (this.oldPassword !== uni.getStorageSync("userInfo").pass_word) {
					uni.showToast({
						icon: 'none',
						title: '原始密码错误',
					})
				} else if (this.newPassword === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入新密码',
					})
				} else if (this.newPassword === this.oldPassword) {
					uni.showToast({
						icon: 'none',
						title: '密码未改变',
					})
				}else if (this.againPassword === '') {
					uni.showToast({
						icon: 'none',
						title: '请确认密码',
					})
				} else {
					request.post("/hs/updatePassword",{
						password: this.newPassword,
						id: uni.getStorageSync("userInfo").user_id
					}).then(res => {
						console.log("修改密码",res);
						if (res.data === 1) {
							uni.showToast({
								icon: 'loading',
								title: `修改成功\r\n请重新登录`
							});
							setTimeout(() => {
								uni.reLaunch({
								    url: '/pages/login/login'
								});
							},1000)
						}
					},err => {
						console.log("err",err)
					})
				}
			},
			/**
			 * 修改手机号接口
			 */
			clickUpdatePhoneConfirm() {
				if (this.newPhone === '') {
					uni.showToast({
						icon: 'none',
						title: '请输入手机号',
					})
				} else if (this.newPhone === uni.getStorageSync("userInfo").mobile) {
					uni.showToast({
						icon: 'none',
						title: '手机号未改变',
					})
				} else {
					request.post("/hs/updatePhone",{
						mobile: this.newPhone,
						id: uni.getStorageSync("userInfo").user_id
					}).then(res => {
						console.log("修改手机号",res.data)
						if (res.data === 1) {
							uni.showToast({
								icon: 'loading',
								title: `修改成功\r\n请重新登录`
							});
							setTimeout(() => {
								uni.reLaunch({
								    url: '/pages/login/login'
								});
							},1000)
						}
					},err => {
						console.log("err",err)
					})
				}
			},
			/**
			 * 打开修改密码弹窗
			 */
			updatePassword() {
				this.passwordDialog = true;
			},
			/**
			 * 打开修改手机号弹窗
			 */
			updatePhone() {
				this.phoneDialog = true;
			},
			/**
			 * 关闭弹窗
			 */
			hideModal() {
				this.phoneDialog = false;
				this.passwordDialog = false;
			}
		}
	}
</script>

<style scoped>
.padding-xl input{
	background-color: #FFFFFF;
	text-align: left;
	margin: 20rpx 0;
	border-radius: 10rpx;
	font-size: 36rpx;
}
.cu-modal{
	z-index: 998!important;
}
</style>
