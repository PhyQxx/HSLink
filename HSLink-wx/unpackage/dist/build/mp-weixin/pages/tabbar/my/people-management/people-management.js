(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/tabbar/my/people-management/people-management"],{"054a":function(t,n,e){"use strict";e.d(n,"b",(function(){return a})),e.d(n,"c",(function(){return c})),e.d(n,"a",(function(){return o}));var o={noData:function(){return Promise.all([e.e("common/vendor"),e.e("components/noData/noData")]).then(e.bind(null,"2c8c"))}},a=function(){var t=this,n=t.$createElement;t._self._c},c=[]},"0682":function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var o={props:{size:{type:String,default:"90"},userId:{type:String,default:""},userName:{type:String,default:"H"}},computed:{photoStyle:function(){return"font-size: ".concat(this.size,"rpx; width: ").concat(2*this.size,"rpx; height: ").concat(2*this.size,"rpx; line-height: ").concat(2*this.size,"rpx;")}},data:function(){return{}},methods:{}};n.default=o},3221:function(t,n,e){},"39fc":function(t,n,e){},"5abc":function(t,n,e){"use strict";e.r(n);var o=e("852c"),a=e.n(o);for(var c in o)"default"!==c&&function(t){e.d(n,t,(function(){return o[t]}))}(c);n["default"]=a.a},"852c":function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var o=c(e("7cc2")),a=(e("ccb8"),c(e("be8a")));function c(t){return t&&t.__esModule?t:{default:t}}var r=function(){e.e("components/mehaotian-search/mehaotian-search").then(function(){return resolve(e("46ed"))}.bind(null,e)).catch(e.oe)},u=function(){Promise.all([e.e("common/vendor"),e.e("components/noData/noData")]).then(function(){return resolve(e("2c8c"))}.bind(null,e)).catch(e.oe)},i={components:{mSearch:r,noData:u,avatar:a.default},data:function(){return{noData:!1,searchStr:"",followList:[],flag:!0}},computed:{peopleListQuery:function(){var t=this;return this.followList.filter((function(n){return-1!=n.real_name.indexOf(t.searchStr)}))}},onPullDownRefresh:function(){this.getFollowPeopleList()},onShow:function(){this.getFollowPeopleList()},mounted:function(){this.getFollowPeopleList()},methods:{peopleManagement:function(n,e){var a={0:"解冻",1:"冻结"},c=this;t.showModal({title:"".concat(a[e]),content:"确认".concat(a[e],"?"),success:function(r){r.confirm&&o.default.post("/admin/peopleManagement",{userId:n.user_id,operating:e}).then((function(n){console.log("人员操作结果",n),n.data>0?(t.showToast({icon:"loading",title:"".concat(a[e],"成功")}),setTimeout((function(){c.getFollowPeopleList()}),1e3)):t.showToast({icon:"none",title:"服务器出小差了，请稍后再试"})}),(function(t){console.log("err",t)}))}})},goToUserInfo:function(n){t.navigateTo({url:"/pages/person-info-page/person-info-page?userId=".concat(n.user_id)})},getFollowPeopleList:function(){var n=this;o.default.post("/admin/getPersonnelManagement",{userId:t.getStorageSync("userInfo").user_id}).then((function(e){console.log("人员管理列表",e),t.startPullDownRefresh(),n.followList=e.data,n.noData=0===n.followList.length}),(function(t){console.log("err",t)}))}}};n.default=i}).call(this,e("543d")["default"])},aaa7:function(t,n,e){"use strict";e.r(n);var o=e("054a"),a=e("5abc");for(var c in a)"default"!==c&&function(t){e.d(n,t,(function(){return a[t]}))}(c);e("bb59");var r,u=e("f0c5"),i=Object(u["a"])(a["default"],o["b"],o["c"],!1,null,"260998cd",null,!1,o["a"],r);n["default"]=i.exports},b91b:function(t,n,e){"use strict";var o=e("3221"),a=e.n(o);a.a},bb59:function(t,n,e){"use strict";var o=e("39fc"),a=e.n(o);a.a},be8a:function(t,n,e){"use strict";e.r(n);var o=e("eea1"),a=e("f77d");for(var c in a)"default"!==c&&function(t){e.d(n,t,(function(){return a[t]}))}(c);e("b91b");var r,u=e("f0c5"),i=Object(u["a"])(a["default"],o["b"],o["c"],!1,null,"79e5d186",null,!1,o["a"],r);n["default"]=i.exports},eea1:function(t,n,e){"use strict";var o;e.d(n,"b",(function(){return a})),e.d(n,"c",(function(){return c})),e.d(n,"a",(function(){return o}));var a=function(){var t=this,n=t.$createElement,e=(t._self._c,t.userName.slice(0,1));t.$mp.data=Object.assign({},{$root:{g0:e}})},c=[]},f77d:function(t,n,e){"use strict";e.r(n);var o=e("0682"),a=e.n(o);for(var c in o)"default"!==c&&function(t){e.d(n,t,(function(){return o[t]}))}(c);n["default"]=a.a},ffbb:function(t,n,e){"use strict";(function(t){e("813a");o(e("66fd"));var n=o(e("aaa7"));function o(t){return t&&t.__esModule?t:{default:t}}t(n.default)}).call(this,e("543d")["createPage"])}},[["ffbb","common/runtime","common/vendor"]]]);