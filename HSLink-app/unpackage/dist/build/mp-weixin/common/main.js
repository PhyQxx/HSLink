(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["common/main"],{"151f":function(e,t,n){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=o(n("1da1"));function o(e){return e&&e.__esModule?e:{default:e}}var u={data:function(){return{unreadNumber:""}},onLaunch:function(){console.log("App Launch"),this.getTabBarNumber()},onShow:function(){console.log("App Show")},onHide:function(){console.log("App Hide")},methods:{getTabBarNumber:function(){r.default.post("/hs/getTabBarNumber",{userId:e.getStorageSync("userInfo").user_id}).then((function(t){console.log("获取tabBar数字",t),t.data.unreadNumber>0?e.setTabBarBadge({index:3,text:t.data.unreadNumber.toString()}):e.removeTabBarBadge({index:3})}),(function(e){console.log("err",e)}))}}};t.default=u}).call(this,n("543d")["default"])},"1f3d":function(e,t,n){"use strict";n.r(t);var r=n("83bc");for(var o in r)"default"!==o&&function(e){n.d(t,e,(function(){return r[e]}))}(o);n("2a5d");var u,a,c,f,i=n("f0c5"),l=Object(i["a"])(r["default"],u,a,!1,null,null,null,!1,c,f);t["default"]=l.exports},"2a5d":function(e,t,n){"use strict";var r=n("3f5f"),o=n.n(r);o.a},"3f5f":function(e,t,n){},"6b70":function(e,t,n){"use strict";(function(e){n("481f");var t=u(n("66fd")),r=u(n("1f3d"));n("592c"),n("7960"),n("deef");var o=u(n("5333"));function u(e){return e&&e.__esModule?e:{default:e}}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function c(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){f(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function f(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}t.default.config.productionTip=!1,t.default.prototype.$store=o.default,r.default.mpType="app";var i=new t.default(c({store:o.default},r.default));e(i).$mount()}).call(this,n("543d")["createApp"])},"83bc":function(e,t,n){"use strict";n.r(t);var r=n("151f"),o=n.n(r);for(var u in r)"default"!==u&&function(e){n.d(t,e,(function(){return r[e]}))}(u);t["default"]=o.a}},[["6b70","common/runtime","common/vendor"]]]);