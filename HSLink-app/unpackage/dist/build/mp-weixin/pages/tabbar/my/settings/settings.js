(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/tabbar/my/settings/settings"],{"38a2":function(t,e,n){"use strict";n.r(e);var a=n("dead"),r=n.n(a);for(var o in a)"default"!==o&&function(t){n.d(e,t,(function(){return a[t]}))}(o);e["default"]=r.a},4169:function(t,e,n){},"80ef":function(t,e,n){"use strict";(function(t){n("481f");a(n("66fd"));var e=a(n("97cd"));function a(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("543d")["createPage"])},"81c5":function(t,e,n){"use strict";var a=n("4169"),r=n.n(a);r.a},"97cd":function(t,e,n){"use strict";n.r(e);var a=n("af02"),r=n("38a2");for(var o in r)"default"!==o&&function(t){n.d(e,t,(function(){return r[t]}))}(o);n("81c5");var u,c=n("f0c5"),i=Object(c["a"])(r["default"],a["b"],a["c"],!1,null,"3b1102ea",null,!1,a["a"],u);e["default"]=i.exports},af02:function(t,e,n){"use strict";var a;n.d(e,"b",(function(){return r})),n.d(e,"c",(function(){return o})),n.d(e,"a",(function(){return a}));var r=function(){var t=this,e=t.$createElement;t._self._c},o=[]},dead:function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var n={data:function(){return{}},onPullDownRefresh:function(){t.startPullDownRefresh()},methods:{signOut:function(){t.showModal({title:"退出登录",content:"确认退出登录？",success:function(e){e.confirm&&(t.setStorageSync("userInfo",""),t.showToast({icon:"loading",title:"退出成功"}),setTimeout((function(){t.reLaunch({url:"/pages/login/login"})}),1e3))}})},goToPage:function(e){var n={account:"/pages/tabbar/my/settings/account-security/account-security",systemInformation:"/pages/tabbar/my/settings/system-information/system-information",userAgreement:"/pages/tabbar/my/settings/agreement/agreement?title=UserAgreement",privacyAgreement:"/pages/tabbar/my/settings/agreement/agreement?title=PrivacyAgreement"};t.navigateTo({url:"".concat(n[e])})}}};e.default=n}).call(this,n("543d")["default"])}},[["80ef","common/runtime","common/vendor"]]]);