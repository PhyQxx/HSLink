<template>
	<view>
		<!-- <view class="cu-avatar round" :style="size" style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg)"></view> -->
		<view class="header-photo" :style="photoStyle">
			{{userName.slice(0,1)}}
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			size: {
				type: String,
				default: "90"
			},
			userId: {
				type: String,
				default: ""
			},
			userName: {
				type: String,
				default: "H"
			}
		},
		computed: {
			photoStyle() {
				return `font-size: ${this.size}rpx; width: ${2*this.size}rpx; height: ${2*this.size}rpx; line-height: ${2*this.size}rpx;`
			}
		},
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style scoped>
	.header-photo{
		background-image: url("@/static/img/photo-bc.jpg");
		background-size: 100% 100%;
		font-family: "宋体";
		color: #FFFFFF;
		border-radius: 50%;
		text-align: center;
		margin: 20rpx;
	}
</style>
