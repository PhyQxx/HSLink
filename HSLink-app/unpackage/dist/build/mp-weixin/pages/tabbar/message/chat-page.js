(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/tabbar/message/chat-page"],{"0412":function(t,e,n){"use strict";var o=n("3e47"),r=n.n(o);r.a},"0b86":function(t,e,n){"use strict";var o=n("8171"),r=n.n(o);r.a},"0c0f":function(t,e,n){"use strict";var o;n.d(e,"b",(function(){return r})),n.d(e,"c",(function(){return u})),n.d(e,"a",(function(){return o}));var r=function(){var t=this,e=t.$createElement,n=(t._self._c,t.userName.slice(0,1));t.$mp.data=Object.assign({},{$root:{g0:n}})},u=[]},"21b3":function(t,e,n){"use strict";n.r(e);var o=n("0c0f"),r=n("9d02");for(var u in r)"default"!==u&&function(t){n.d(e,t,(function(){return r[t]}))}(u);n("0412");var a,i=n("f0c5"),s=Object(i["a"])(r["default"],o["b"],o["c"],!1,null,"79e5d186",null,!1,o["a"],a);e["default"]=s.exports},"3e47":function(t,e,n){},"467d":function(t,e,n){"use strict";(function(t){n("481f");o(n("66fd"));var e=o(n("f9ea"));function o(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("543d")["createPage"])},8171:function(t,e,n){},"9d02":function(t,e,n){"use strict";n.r(e);var o=n("d9f9"),r=n.n(o);for(var u in o)"default"!==u&&function(t){n.d(e,t,(function(){return o[t]}))}(u);e["default"]=r.a},be80:function(t,e,n){"use strict";n.r(e);var o=n("dcce"),r=n.n(o);for(var u in o)"default"!==u&&function(t){n.d(e,t,(function(){return o[t]}))}(u);e["default"]=r.a},c414:function(t,e,n){"use strict";var o;n.d(e,"b",(function(){return r})),n.d(e,"c",(function(){return u})),n.d(e,"a",(function(){return o}));var r=function(){var t=this,e=t.$createElement,n=(t._self._c,t.__map(t.letterList,(function(e,n){var o=t.__get_orig(e),r=t.isMy(e,"self"),u=t.isMy(e,"bg-green");return{$orig:o,m0:r,m1:u}})));t.$mp.data=Object.assign({},{$root:{l0:n}})},u=[]},d9f9:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var o={props:{size:{type:String,default:"90"},userId:{type:String,default:""},userName:{type:String,default:"H"}},computed:{photoStyle:function(){return"font-size: ".concat(this.size,"rpx; width: ").concat(2*this.size,"rpx; height: ").concat(2*this.size,"rpx; line-height: ").concat(2*this.size,"rpx;")}},data:function(){return{}},methods:{}};e.default=o},dcce:function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var o=u(n("1da1")),r=(u(n("deef")),u(n("21b3")));function u(t){return t&&t.__esModule?t:{default:t}}var a={components:{avatar:r.default},data:function(){return{otherInfo:t.getStorageSync("otherInfo"),userInfo:t.getStorageSync("userInfo"),InputBottom:0,messageContent:"",letterList:[],interval:{}}},onShow:function(){var t=this;this.updateRead(),this.getTwoLetterApp();var e=0,n=setInterval((function(){t.getTwoLetterApp(),e+=1,5===e&&clearInterval(n)}),2e3)},onBackPress:function(){},onPullDownRefresh:function(){this.getTwoLetterApp(),t.startPullDownRefresh()},onLoad:function(){t.setNavigationBarTitle({title:this.otherInfo.real_name})},watch:{letterList:function(){this.$nextTick((function(){t.pageScrollTo({scrollTop:99999,duration:0})}))}},methods:{InputFocus:function(t){this.InputBottom=t.detail.height},InputBlur:function(t){this.InputBottom=0},goToOther:function(e){t.navigateTo({url:"/pages/person-info-page/person-info-page?userId=".concat(e.send_id)})},goToMy:function(){t.switchTab({url:"/pages/tabbar/my/my"})},getTwoLetterApp:function(){var e=this;o.default.post("/hs/getTwoLetterApp",{userId:t.getStorageSync("userInfo").user_id,otherId:this.otherInfo.user_id}).then((function(t){console.log("获取两个人的私信",t),null!==t.data&&(e.letterList=t.data)}),(function(t){console.log("err",t)}))},sendMessage:function(){var e=this;""===this.messageContent?t.showToast({icon:"none",title:"请输入消息，亲"}):o.default.post("/hs/sendLetter",{sendId:t.getStorageSync("userInfo").user_id,receiveId:this.otherInfo.user_id,content:this.messageContent}).then((function(t){e.messageContent="",console.log("发送消息",t),1===t.data&&(e.getTwoLetterApp(),e.messageContent="")}),(function(t){console.log("err",t)}))},updateRead:function(){o.default.post("/hs/updateReadApp",{userId:t.getStorageSync("userInfo").user_id,otherId:this.otherInfo.user_id}).then((function(t){console.log("改为已读",t)}),(function(t){console.log("err",t)}))},isMy:function(e,n){return e.send_id===t.getStorageSync("userInfo").user_id?n:""}}};e.default=a}).call(this,n("543d")["default"])},f9ea:function(t,e,n){"use strict";n.r(e);var o=n("c414"),r=n("be80");for(var u in r)"default"!==u&&function(t){n.d(e,t,(function(){return r[t]}))}(u);n("0b86");var a,i=n("f0c5"),s=Object(i["a"])(r["default"],o["b"],o["c"],!1,null,"54408d5d",null,!1,o["a"],a);e["default"]=s.exports}},[["467d","common/runtime","common/vendor"]]]);