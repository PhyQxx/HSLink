(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/person-info-page/person-info-page"],{"0682":function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o={props:{size:{type:String,default:"90"},userId:{type:String,default:""},userName:{type:String,default:"H"}},computed:{photoStyle:function(){return"font-size: ".concat(this.size,"rpx; width: ").concat(2*this.size,"rpx; height: ").concat(2*this.size,"rpx; line-height: ").concat(2*this.size,"rpx;")}},data:function(){return{}},methods:{}};t.default=o},"07ae":function(e,t,n){"use strict";n.d(t,"b",(function(){return a})),n.d(t,"c",(function(){return r})),n.d(t,"a",(function(){return o}));var o={noData:function(){return Promise.all([n.e("common/vendor"),n.e("components/noData/noData")]).then(n.bind(null,"2c8c"))}},a=function(){var e=this,t=e.$createElement;e._self._c},r=[]},"09c9":function(e,t,n){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=r(n("7cc2")),a=(n("ccb8"),r(n("be8a")));function r(e){return e&&e.__esModule?e:{default:e}}var u=function(){Promise.all([n.e("common/vendor"),n.e("components/noData/noData")]).then(function(){return resolve(n("2c8c"))}.bind(null,n)).catch(n.oe)},c={components:{noData:u,avatar:a.default},data:function(){return{userId:"",userInfo:{},noData:!1,noticeList:[]}},onLoad:function(e){this.userId=e.userId},mounted:function(){this.getUserInfo()},onPullDownRefresh:function(){this.getUserInfo()},onNavigationBarButtonTap:function(){this.privateLetter()},methods:{privateLetter:function(){e.setStorageSync("otherInfo",this.userInfo),e.navigateTo({url:"/pages/tabbar/message/chat-page"})},addFollow:function(t){var n=this;o.default.post("/hs/addFollow",{userId:e.getStorageSync("userInfo").user_id,followId:t.user_id}).then((function(e){n.getUserInfo(),console.log("加关注",e)}),(function(e){console.log("err",e)}))},cancelFollow:function(t){var n=this;e.showModal({title:"取消关注",content:"确认取消关注？",success:function(a){a.confirm&&o.default.post("/hs/cancelFollow",{userId:e.getStorageSync("userInfo").user_id,followId:t.user_id}).then((function(e){n.getUserInfo(),console.log("取消关注",e)}),(function(e){console.log("err",e)}))}})},goToDetails:function(t){e.navigateTo({url:"/pages/tabbar/homepage/data-details?noticeId="+t.id})},getUserInfo:function(){var t=this;o.default.post("/hs/getPersonalInfo",{userId:e.getStorageSync("userInfo").user_id,releaseId:this.userId}).then((function(n){e.startPullDownRefresh(),console.log("个人信息",n),t.userInfo=n.data.personalInfo,t.userInfo.headerPhoto=t.userInfo.real_name.slice(0,1),t.noticeList=n.data.personalList,t.noData=0===n.data.personalList.length,e.setNavigationBarTitle({title:t.userInfo.real_name})}),(function(e){console.log("err",e)}))}}};t.default=c}).call(this,n("543d")["default"])},"1edb":function(e,t,n){},"204c":function(e,t,n){"use strict";var o=n("1edb"),a=n.n(o);a.a},3221:function(e,t,n){},"60b7":function(e,t,n){"use strict";n.r(t);var o=n("07ae"),a=n("90e4");for(var r in a)"default"!==r&&function(e){n.d(t,e,(function(){return a[e]}))}(r);n("204c");var u,c=n("f0c5"),i=Object(c["a"])(a["default"],o["b"],o["c"],!1,null,"34c0f0ec",null,!1,o["a"],u);t["default"]=i.exports},8400:function(e,t,n){"use strict";(function(e){n("813a");o(n("66fd"));var t=o(n("60b7"));function o(e){return e&&e.__esModule?e:{default:e}}e(t.default)}).call(this,n("543d")["createPage"])},"90e4":function(e,t,n){"use strict";n.r(t);var o=n("09c9"),a=n.n(o);for(var r in o)"default"!==r&&function(e){n.d(t,e,(function(){return o[e]}))}(r);t["default"]=a.a},b91b:function(e,t,n){"use strict";var o=n("3221"),a=n.n(o);a.a},be8a:function(e,t,n){"use strict";n.r(t);var o=n("eea1"),a=n("f77d");for(var r in a)"default"!==r&&function(e){n.d(t,e,(function(){return a[e]}))}(r);n("b91b");var u,c=n("f0c5"),i=Object(c["a"])(a["default"],o["b"],o["c"],!1,null,"79e5d186",null,!1,o["a"],u);t["default"]=i.exports},eea1:function(e,t,n){"use strict";var o;n.d(t,"b",(function(){return a})),n.d(t,"c",(function(){return r})),n.d(t,"a",(function(){return o}));var a=function(){var e=this,t=e.$createElement,n=(e._self._c,e.userName.slice(0,1));e.$mp.data=Object.assign({},{$root:{g0:n}})},r=[]},f77d:function(e,t,n){"use strict";n.r(t);var o=n("0682"),a=n.n(o);for(var r in o)"default"!==r&&function(e){n.d(t,e,(function(){return o[e]}))}(r);t["default"]=a.a}},[["8400","common/runtime","common/vendor"]]]);