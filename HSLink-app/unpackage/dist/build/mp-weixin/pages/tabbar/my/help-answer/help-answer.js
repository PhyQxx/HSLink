(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/tabbar/my/help-answer/help-answer"],{"08f1":function(t,n,e){"use strict";var o=e("9f7d"),a=e.n(o);a.a},"0af8":function(t,n,e){"use strict";e.r(n);var o=e("5949"),a=e.n(o);for(var r in o)"default"!==r&&function(t){e.d(n,t,(function(){return o[t]}))}(r);n["default"]=a.a},5949:function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var o=a(e("1da1"));function a(t){return t&&t.__esModule?t:{default:t}}var r={data:function(){return{notHelpList:[],historyHelpList:[],notHelpNoData:!1,historyHelpNoData:!1}},onLoad:function(){this.getHistoryHelp()},onPullDownRefresh:function(){t.startPullDownRefresh()},methods:{goToUserInfo:function(n){t.navigateTo({url:"/pages/person-info-page/person-info-page?userId=".concat(n.answer_id)})},answerHelp:function(n){var e=this;""===n.answer?t.showToast({icon:"none",title:"请输入解决方案"}):o.default.post("/admin/answerHelp",{answerId:t.getStorageSync("userInfo").user_id,answer:n.answer,id:n.id}).then((function(n){console.log("提交解决方案",n),1===n.data&&(t.showToast({icon:"loading",title:"提交成功"}),e.getHistoryHelp())}),(function(t){console.log("err",t)}))},getHistoryHelp:function(){var n=this;o.default.post("/admin/getHelpAnswerList",{userId:t.getStorageSync("userInfo").user_id}).then((function(t){n.notHelpList=[],n.historyHelpList=[],t.data.forEach((function(t){void 0===t.answer_id?n.notHelpList.push(t):n.historyHelpList.push(t)})),n.notHelpNoData=0===t.data.length,n.historyHelpNoData=0===t.data.length,console.log("历史帮助列表",t)}),(function(t){console.log("err",t)}))}}};n.default=r}).call(this,e("543d")["default"])},"75be":function(t,n,e){"use strict";(function(t){e("481f");o(e("66fd"));var n=o(e("8e3c"));function o(t){return t&&t.__esModule?t:{default:t}}t(n.default)}).call(this,e("543d")["createPage"])},"8e3c":function(t,n,e){"use strict";e.r(n);var o=e("92e1"),a=e("0af8");for(var r in a)"default"!==r&&function(t){e.d(n,t,(function(){return a[t]}))}(r);e("08f1");var i,s=e("f0c5"),u=Object(s["a"])(a["default"],o["b"],o["c"],!1,null,"36583ab0",null,!1,o["a"],i);n["default"]=u.exports},"92e1":function(t,n,e){"use strict";var o;e.d(n,"b",(function(){return a})),e.d(n,"c",(function(){return r})),e.d(n,"a",(function(){return o}));var a=function(){var t=this,n=t.$createElement;t._self._c},r=[]},"9f7d":function(t,n,e){}},[["75be","common/runtime","common/vendor"]]]);