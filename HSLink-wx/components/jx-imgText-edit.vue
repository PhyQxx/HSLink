<template>
	<!-- jx-imgText-edit -->
	<view class="jx-imgText-edit">
		<view class="content">
			<block  v-for="(item,index) in editData" :key="index">
				<textarea class="textarea_com" :placeholder="editData.length == 1 ? placeText : ''" 
					:maxlength="maxlength" :auto-height="true" 
					:data-index="index"  
					v-if="item.type=='textarea'" 
					value="" v-model="item.value">
				</textarea> 
				<view class="view_com" v-else-if="item.type=='img'" > 
					<image class="img_com" :src="item.value" mode="widthFix"/>
					<view class="mask" :style="{opacity:'0.5'}"></view>
					<image class="del" src="@/static/shanchu.png" mode="widthFix" @click="delImg(index)"/>
				</view>
			</block> 
		</view>
		
	</view>
</template>

<script>
	export default {
		props: {
			maxlength: {
				type: Number,
				default: -1
			},
			
			placeText:{
				//文本框默认的文字
				type: String,
				default: '写点什么吧...'
			},
			
			editData: {
				type: Array,
				default () {
					return [];
				}
			},
		},
		data() {
			return {
				
			};
		},
		methods: {
			//提交数据
			submit () {
				return this.editData;
			},
			//删除图片
			delImg (index) {
				uni.showModal({
					title: '提示',
					content: '确定要删除该图片吗？ ',
					success: (res) => {
						if (res.confirm) {
							console.log('用户点击确定');
							this.$emit("delImg",index);
						} 
					} 
				});
			},
			
		}
	}
</script>

<style lang="less" scoped>
page{
	background: #fff;
}
.jx-imgText-edit {
	width: 100%;
	padding: 0 30rpx 0rpx 30rpx;
	box-sizing: border-box; 
	padding-top: 10rpx;
	background: #fff;
	.content {
		.textarea_com {
			// padding:20rpx 0;
			width: 100%;
			font-size: 32upx;
			background: #fff;
			line-height: 1.5;
			min-height: 64rpx!important;
		}
		.view_com {
			width: 100%;
			position: relative;
			.img_com {
				width: 100%;
			}
			.mask {
				background: rgba(0,0,0,0.5);
				position: absolute;
				left: 0;
				top: 0;
				width: 100%;
				height: 100%;
			}
			.del {
				width: 60upx;
				height: 60upx;
				position: absolute;
				bottom: 30upx;
				right: 20upx;
			}
		}
	}
}
</style>
