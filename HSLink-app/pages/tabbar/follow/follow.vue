<template>
	<view class="content">
		页面 - 2
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				list: [{
		"_entityName": "uap_Menu",
		"id": "2eb4cb5f-4e80-019e-9d46-ac1b55d010bf",
		"orderNo": 1,
		"level": 1,
		"version": 2,
		"levelText": "1级菜单",
		"name": "预警发布",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "7b406372-7da6-c396-b944-4ea3ea93de23",
		"orderNo": 2,
		"level": 1,
		"version": 1,
		"levelText": "1级菜单",
		"name": "预警反馈",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "f71d90fe-a394-8104-88eb-4bb651491420",
		"orderNo": 3,
		"level": 1,
		"version": 3,
		"levelText": "1级菜单",
		"name": "风险预警统计",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "f7dc6070-4b7a-4501-c459-c47ee54727ac",
		"orderNo": 4,
		"level": 1,
		"version": 4,
		"levelText": "1级菜单",
		"name": "到岗到位人员关联",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "02c9ac9c-2acd-b03b-beb4-80701b2e6fd3",
		"parent": {
			"_entityName": "uap_Menu",
			"id": "7b406372-7da6-c396-b944-4ea3ea93de23",
			"orderNo": 2,
			"level": 1,
			"version": 1,
			"levelText": "1级菜单",
			"name": "预警反馈",
			"operationSystem": {
				"_entityName": "uap_OperationSystem",
				"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
				"type": "INTERNAL",
				"version": 1,
				"url": "#",
				"name": "安全风险管理",
				"createUser": "admin",
				"status": "ENABLE"
			},
			"showIcon": "FALSE",
			"status": "ENABLE"
		},
		"orderNo": 1,
		"appButtons": "/static/early_warning_feedback.png",
		"level": 2,
		"appUrl": "/pages/early-warning-feedback/early-warning-feedback-management",
		"version": 3,
		"url": "srm_WarnFeedback.browse",
		"levelText": "2级菜单",
		"name": "预警反馈管理",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "66afd592-d13b-09bb-44f9-64c853302b13",
		"parent": {
			"_entityName": "uap_Menu",
			"id": "f71d90fe-a394-8104-88eb-4bb651491420",
			"orderNo": 3,
			"level": 1,
			"version": 3,
			"levelText": "1级菜单",
			"name": "风险预警统计",
			"operationSystem": {
				"_entityName": "uap_OperationSystem",
				"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
				"type": "INTERNAL",
				"version": 1,
				"url": "#",
				"name": "安全风险管理",
				"createUser": "admin",
				"status": "ENABLE"
			},
			"showIcon": "FALSE",
			"status": "ENABLE"
		},
		"orderNo": 1,
		"level": 2,
		"version": 2,
		"url": "srm_WarnInfoReleaseStatistics.browse",
		"levelText": "2级菜单",
		"name": "安全风险预警发布统计",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "98118247-3b5b-9b29-cf9b-5ef67e60e174",
		"parent": {
			"_entityName": "uap_Menu",
			"id": "2eb4cb5f-4e80-019e-9d46-ac1b55d010bf",
			"orderNo": 1,
			"level": 1,
			"version": 2,
			"levelText": "1级菜单",
			"name": "预警发布",
			"operationSystem": {
				"_entityName": "uap_OperationSystem",
				"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
				"type": "INTERNAL",
				"version": 1,
				"url": "#",
				"name": "安全风险管理",
				"createUser": "admin",
				"status": "ENABLE"
			},
			"showIcon": "FALSE",
			"status": "ENABLE"
		},
		"orderNo": 1,
		"appButtons": "/static/early_warning_release.png",
		"level": 2,
		"appUrl": "/pages/early-warning-release/early-warning-notice",
		"version": 2,
		"url": "srm_WarnInfo.browse",
		"levelText": "2级菜单",
		"name": "预警发布管理",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "9060b850-abdb-7637-6ea3-4cc0ea80b96d",
		"parent": {
			"_entityName": "uap_Menu",
			"id": "f7dc6070-4b7a-4501-c459-c47ee54727ac",
			"orderNo": 4,
			"level": 1,
			"version": 4,
			"levelText": "1级菜单",
			"name": "到岗到位人员关联",
			"operationSystem": {
				"_entityName": "uap_OperationSystem",
				"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
				"type": "INTERNAL",
				"version": 1,
				"url": "#",
				"name": "安全风险管理",
				"createUser": "admin",
				"status": "ENABLE"
			},
			"showIcon": "FALSE",
			"status": "ENABLE"
		},
		"orderNo": 1,
		"level": 2,
		"version": 10,
		"url": "srm_PersonOnDuty.browse",
		"levelText": "2级菜单",
		"name": "到岗到位人员管理",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "0c706e19-e26f-3ded-174b-efc8d6fa5ee3",
		"parent": {
			"_entityName": "uap_Menu",
			"id": "f71d90fe-a394-8104-88eb-4bb651491420",
			"orderNo": 3,
			"level": 1,
			"version": 3,
			"levelText": "1级菜单",
			"name": "风险预警统计",
			"operationSystem": {
				"_entityName": "uap_OperationSystem",
				"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
				"type": "INTERNAL",
				"version": 1,
				"url": "#",
				"name": "安全风险管理",
				"createUser": "admin",
				"status": "ENABLE"
			},
			"showIcon": "FALSE",
			"status": "ENABLE"
		},
		"orderNo": 2,
		"level": 2,
		"version": 2,
		"url": "srm_WarnInfoCloseLoop.browse",
		"levelText": "2级菜单",
		"name": "安全风险预警闭环统计",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "46be89dc-a558-5167-f74d-e5848019dc06",
		"parent": {
			"_entityName": "uap_Menu",
			"id": "f71d90fe-a394-8104-88eb-4bb651491420",
			"orderNo": 3,
			"level": 1,
			"version": 3,
			"levelText": "1级菜单",
			"name": "风险预警统计",
			"operationSystem": {
				"_entityName": "uap_OperationSystem",
				"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
				"type": "INTERNAL",
				"version": 1,
				"url": "#",
				"name": "安全风险管理",
				"createUser": "admin",
				"status": "ENABLE"
			},
			"showIcon": "FALSE",
			"status": "ENABLE"
		},
		"orderNo": 3,
		"level": 2,
		"version": 2,
		"url": "srm_WarnInfoFutureSevenDaysPowerGridStatistics.browse",
		"levelText": "2级菜单",
		"name": "未来七日电网风险预警统计",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}, {
		"_entityName": "uap_Menu",
		"id": "4e0e395f-049f-4494-aa37-38f54f0bd8f4",
		"parent": {
			"_entityName": "uap_Menu",
			"id": "f71d90fe-a394-8104-88eb-4bb651491420",
			"orderNo": 3,
			"level": 1,
			"version": 3,
			"levelText": "1级菜单",
			"name": "风险预警统计",
			"operationSystem": {
				"_entityName": "uap_OperationSystem",
				"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
				"type": "INTERNAL",
				"version": 1,
				"url": "#",
				"name": "安全风险管理",
				"createUser": "admin",
				"status": "ENABLE"
			},
			"showIcon": "FALSE",
			"status": "ENABLE"
		},
		"orderNo": 4,
		"level": 2,
		"version": 2,
		"url": "srm_WarnInfoFutureSevenDaysTaskStatistics.browse",
		"levelText": "2级菜单",
		"name": "未来七日作业风险预警统计",
		"operationSystem": {
			"_entityName": "uap_OperationSystem",
			"id": "57b5aadb-3dac-1272-65e2-f17510151a3b",
			"type": "INTERNAL",
			"version": 1,
			"url": "#",
			"name": "安全风险管理",
			"createUser": "admin",
			"status": "ENABLE"
		},
		"showIcon": "FALSE",
		"status": "ENABLE"
	}],
			}
		},
		onLoad() {

		},
		mounted() {
			console.log("data",this.list);
			this.ergodic();
		},
		methods: {
			//遍历数组
			ergodic() {
				let menuList = [];
				this.list.forEach(item=>{
					if (item.level === 1) {
						menuList.push({
							id: item.id,
							menuName: item.name,
							subMenuList: []
						});
					}
				});
				this.list.forEach(item=>{
					if (item.level === 2) {
						menuList.forEach(i=>{
							if (i.id === item.parent.id) {
								i.subMenuList.push({
									menuName: item.name,
									url: item.appUrl
								})
							}
						})
					}
				});
				console.log("menuList",menuList);
			}
		}
	}
</script>

<style>
	.content {
		text-align: center;
		height: 400upx;
		margin-top: 200upx;
	}
</style>
