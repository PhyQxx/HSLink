(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/tabbar/my/notice/notice"],{"0ea6":function(e,t,n){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=i(n("1da1"));function i(e){return e&&e.__esModule?e:{default:e}}var a=function(){n.e("components/uni-collapse/uni-collapse").then(function(){return resolve(n("2b7f"))}.bind(null,n)).catch(n.oe)},c=function(){n.e("components/uni-collapse-item/uni-collapse-item").then(function(){return resolve(n("fc2c"))}.bind(null,n)).catch(n.oe)},u={components:{uniCollapse:a,uniCollapseItem:c},data:function(){return{messageNoticeList:[],verifyNoticeList:[],messageNoticeNumber:"",verifyNoticeNumber:""}},onShow:function(){this.getNoticeData()},methods:{clickNumber:function(){console.log(1111)},goToDetails:function(t){o.default.post("/hs/updateNoticeRead",{type:"0",id:t.id}).then((function(e){console.log("消息通知已读",e)}),(function(e){console.log("err",e)})),e.navigateTo({url:"/pages/tabbar/homepage/data-details?noticeId="+t.article_id})},goToMyDetails:function(t){o.default.post("/hs/updateNoticeRead",{type:"1",id:t.id}).then((function(e){console.log("消息通知已读",e)}),(function(e){console.log("err",e)})),"1"===t.result?e.navigateTo({url:"/pages/tabbar/homepage/data-details?noticeId="+t.article_id}):(e.setStorageSync("notice",t),e.navigateTo({url:"/pages/notice-edit/notice-edit"}))},getNoticeData:function(){var t=this;o.default.post("/hs/getNoticeData",{authorId:e.getStorageSync("userInfo").user_id}).then((function(e){t.messageNoticeList=e.data.messageNoticeList,t.messageNoticeNumber=e.data.messageNoticeList.length,t.verifyNoticeList=e.data.verifyNoticeList,t.verifyNoticeNumber=e.data.verifyNoticeList.length,console.log("通知",e)}),(function(e){console.log("err",e)}))}}};t.default=u}).call(this,n("543d")["default"])},"228f":function(e,t,n){"use strict";n.r(t);var o=n("ba69"),i=n("2d07");for(var a in i)"default"!==a&&function(e){n.d(t,e,(function(){return i[e]}))}(a);n("f744");var c,u=n("f0c5"),l=Object(u["a"])(i["default"],o["b"],o["c"],!1,null,"559333c6",null,!1,o["a"],c);t["default"]=l.exports},"2d07":function(e,t,n){"use strict";n.r(t);var o=n("0ea6"),i=n.n(o);for(var a in o)"default"!==a&&function(e){n.d(t,e,(function(){return o[e]}))}(a);t["default"]=i.a},"7d80":function(e,t,n){},aea4:function(e,t,n){"use strict";(function(e){n("481f");o(n("66fd"));var t=o(n("228f"));function o(e){return e&&e.__esModule?e:{default:e}}e(t.default)}).call(this,n("543d")["createPage"])},ba69:function(e,t,n){"use strict";n.d(t,"b",(function(){return i})),n.d(t,"c",(function(){return a})),n.d(t,"a",(function(){return o}));var o={uniCollapse:function(){return n.e("components/uni-collapse/uni-collapse").then(n.bind(null,"2b7f"))},uniCollapseItem:function(){return n.e("components/uni-collapse-item/uni-collapse-item").then(n.bind(null,"fc2c"))}},i=function(){var e=this,t=e.$createElement;e._self._c},a=[]},f744:function(e,t,n){"use strict";var o=n("7d80"),i=n.n(o);i.a}},[["aea4","common/runtime","common/vendor"]]]);