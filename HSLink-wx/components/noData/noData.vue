<template>
	<view class="noData hideToShow" :style="{ backgroundColor: bgColor }">
		<view class="image"><image src="@/static/img/noData.png"></image></view>
		<!-- 网络连接失败默认显示内容 -->
		<view class="content" v-if="netType == 'none' && custom == false">
			<text class="title" :style="{ color: mainColor }">{{ mainText }}</text>
			<text class="desc" :style="{ color: viceColor }">{{ viceText }}</text>
			<!-- #ifdef MP -->
			<button class="btn" type="default" @tap="setting(true)">刷新试试</button>
			<!-- #endif -->
		</view>
		<!-- 自定义内容 -->
		<slot />
	</view>
</template>

<script>
import { mapGetters } from 'vuex';
import settings from '@/static/js/settings.js';
export default {
	props: {
		isShow: {
			type: Boolean,
			default: false
		},
		/* 背景颜色自定义 */
		bgColor: {
			type: String,
			default: '#ffffff'
		},
		/* 标题颜色自定义 */
		mainColor: {
			type: String,
			default: '#373a40'
		},
		/* 描述颜色自定义 */
		viceColor: {
			type: String,
			default: '#8b8b8b'
		},
		// 是否开启自定义
		custom: {
			type: false,
			default: false
		}
	},
	computed: {
		...mapGetters(['netWorkStatus'])
	},
	data() {
		return {
			type: '',
			netType: this.networkType,
			mainText: '网络居然崩溃了',
			viceText: '别紧张,去检测一下网络设置',
			netWorkImg: '/static/images/noNetWork.png',
			noDataImg: '/static/images/notFound.png'
		};
	},
	methods: {
		setting(status) {
			/* 检查到网络已打开,请点击按钮手动刷新数据 */
			if (status) {
				this.$emit('handle', status);
				/* 如果没有网络,打开系统设置检查网络连接 */
			} else {
				settings.open(settings.SETTINGS);
			}
		}
	}
};
</script>

<style lang="less" scoped>
.noData {
	padding: 100rpx;
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	.image {
		width: 187upx;
		height: 120upx;
		image {
			width: 100%;
			height: 100%;
		}
	}
	.title{
		color: #bfbfbf!important;
	}
	.content {
		color: #bfbfbf!important;
		width: 100%;
		display: flex;
		align-items: center;
		flex-direction: column;
		justify-content: center;
		letter-spacing: 1upx;
		.title {
			font-size: 36upx;
		}
		.desc {
			font-size: 28upx;
			margin-top: 6upx;
		}
		.btn {
			width: 160upx;
			height: 65upx;
			color: #868d91;
			font-size: 24upx;
			margin-top: 34upx;
			border-radius: 36upx;
			border: 1upx solid #d4d4d4;
		}
		.btn::after {
			border: none;
		}
	}
}
</style>
