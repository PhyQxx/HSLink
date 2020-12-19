<template>
	<view class="page">
		<view class="cu-list menu sm-border">
			<view class="cu-item arrow" v-for="(item,index) in systemInfo" :key="index">
				<view class="content">
					<text class="text-grey">{{item.key}}</text>
				</view>
				<!-- <view class="action" v-if="[typeof item.value !== 'object']">
					<text class="text-grey text-sm">{{item.value}}</text>
				</view>
				<view class="action" v-if="[typeof item.value === 'object']">
					<view class="text-grey text-sm" v-for="(value,key,index) in item.value" :key="index">{{ATTRIBUTE_CODE[key]+value}}</view>
				</view> -->
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				systemInfo: [],
				//系统属性值key
				ATTRIBUTE_CODE: {
					"left": "左",
					"right": "右",
					"top": "上",
					"bottom": "下",
					"width": "宽度",
					"height": "高度",
				},
				//系统属性key
				CODE_NAME: {
					"brand": "手机品牌",
					"model": "手机型号",
					"pixelRatio": "设备像素比",
					"screenWidth": "屏幕宽度",
					"screenHeight": "屏幕高度",
					"windowWidth": "可使用窗口宽度",
					"windowHeight": "可使用窗口高度",
					"windowTop": "可使用窗口的顶部位置",
					"windowBottom": "可使用窗口的底部位置",
					"statusBarHeight": "状态栏的高度",
					"navigationBarHeight": "导航栏的高度",
					"titleBarHeight": "标题栏高度",
					"language": "应用设置的语言",
					"version": "引擎版本号",
					"titleBarHeight": "标题栏高度",
					"storage": "设备磁盘容量",
					"currentBattery": "当前电量百分比",
					"appName": "宿主APP名称",
					"AppPlatform": "App平台",
					"host": "宿主平台",
					"app": "当前运行的客户端",
					"cacheLocation": "上一次缓存的位置信息",
					"system": "操作系统版本",
					"platform": "客户端平台",
					"SDKVersion": "客户端基础库版本",
					"swanNativeVersion": "宿主平台版本号",
					"safeArea": "在竖屏正方向下的安全区域",
					"safeAreaInsets": "在竖屏正方向下的安全区域插入位置（2.5.3+）",
				}
			}
		},
		onLoad() {
			this.getSystemInfo();
		},
		onPullDownRefresh () {
			uni.startPullDownRefresh();
		},
		methods: {
			/**
			 * 获取系统信息
			 */
			getSystemInfo() {
				let _this = this;
				uni.getSystemInfo({
					success(res) {
						for(let item in res) {
							_this.systemInfo.push({
								key: _this.CODE_NAME[item],
								value: res[item]
							})
						}
						console.log("系统信息",_this.systemInfo)
					}
				})
			}
		}
	}
</script>

<style>

</style>
