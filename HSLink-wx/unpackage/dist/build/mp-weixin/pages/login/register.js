(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/login/register"],{"21ec":function(e,t,n){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o=s(n("7cc2"));function s(e){return e&&e.__esModule?e:{default:e}}var i={data:function(){return{roleList:[],roleIndex:-1,role:"",mobile:"",username:"",password:"",againPassword:""}},onLoad:function(){this.getUserType()},methods:{register:function(){var t=this;""===this.role?e.showToast({icon:"none",title:"请选择角色"}):11!==this.mobile.length?e.showToast({icon:"none",title:"请输入正确的手机号"}):""===this.username?e.showToast({icon:"none",title:"请输入您的姓名"}):""===this.password?e.showToast({icon:"none",title:"请输入密码"}):""===this.againPassword?e.showToast({icon:"none",title:"请确认密码"}):this.againPassword!==this.password?e.showToast({icon:"none",title:"两次密码不一致"}):o.default.post("/hs/register",{type:this.role,mobile:this.mobile,realname:this.username,password:this.password}).then((function(n){n.data>0?(e.showToast({icon:"loading",title:"注册成功"}),e.setStorageSync("loginInfo",{user_type:t.role,real_name:t.username,pass_word:t.password}),setTimeout((function(){e.reLaunch({url:"/pages/login/login"})}),1e3)):e.showToast({icon:"none",title:"该手机号或姓名已被注册"}),console.log("注册",n)}),(function(e){console.log("err",e)}))},rolePickerChange:function(e){this.roleIndex=e.detail.value,-1===this.roleIndex&&(this.roleIndex=0),this.role=this.roleList[this.roleIndex]},getUserType:function(){var e=this;o.default.post("/hs/getDictionariesData",{code:"UserType"}).then((function(t){console.log("用户类型",t),e.roleList=[],t.data.forEach((function(t){"管理员"!==t.dd_detail&&e.roleList.push(t.dd_detail)}))}),(function(e){console.log("err",e)}))}}};t.default=i}).call(this,n("543d")["default"])},"23a8":function(e,t,n){"use strict";n.r(t);var o=n("c0c0"),s=n("3d72");for(var i in s)"default"!==i&&function(e){n.d(t,e,(function(){return s[e]}))}(i);n("9627");var a,r=n("f0c5"),l=Object(r["a"])(s["default"],o["b"],o["c"],!1,null,"29597c75",null,!1,o["a"],a);t["default"]=l.exports},"2ff4":function(e,t,n){},"3d72":function(e,t,n){"use strict";n.r(t);var o=n("21ec"),s=n.n(o);for(var i in o)"default"!==i&&function(e){n.d(t,e,(function(){return o[e]}))}(i);t["default"]=s.a},"941d":function(e,t,n){"use strict";(function(e){n("813a");o(n("66fd"));var t=o(n("23a8"));function o(e){return e&&e.__esModule?e:{default:e}}e(t.default)}).call(this,n("543d")["createPage"])},9627:function(e,t,n){"use strict";var o=n("2ff4"),s=n.n(o);s.a},c0c0:function(e,t,n){"use strict";var o;n.d(t,"b",(function(){return s})),n.d(t,"c",(function(){return i})),n.d(t,"a",(function(){return o}));var s=function(){var e=this,t=e.$createElement;e._self._c},i=[]}},[["941d","common/runtime","common/vendor"]]]);