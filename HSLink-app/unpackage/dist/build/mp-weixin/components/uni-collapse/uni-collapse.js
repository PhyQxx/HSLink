(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["components/uni-collapse/uni-collapse"],{"1cbb":function(n,t,e){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var a={name:"UniCollapse",props:{accordion:{type:[Boolean,String],default:!1}},data:function(){return{}},provide:function(){return{collapse:this}},created:function(){this.childrens=[]},methods:{onChange:function(){var n=[];this.childrens.forEach((function(t,e){t.isOpen&&n.push(t.nameSync)})),this.$emit("change",n)}}};t.default=a},"2b7f":function(n,t,e){"use strict";e.r(t);var a=e("6726"),c=e("f909");for(var u in c)"default"!==u&&function(n){e.d(t,n,(function(){return c[n]}))}(u);e("f8ca");var r,o=e("f0c5"),i=Object(o["a"])(c["default"],a["b"],a["c"],!1,null,"238857a0",null,!1,a["a"],r);t["default"]=i.exports},6726:function(n,t,e){"use strict";var a;e.d(t,"b",(function(){return c})),e.d(t,"c",(function(){return u})),e.d(t,"a",(function(){return a}));var c=function(){var n=this,t=n.$createElement;n._self._c},u=[]},a7d0:function(n,t,e){},f8ca:function(n,t,e){"use strict";var a=e("a7d0"),c=e.n(a);c.a},f909:function(n,t,e){"use strict";e.r(t);var a=e("1cbb"),c=e.n(a);for(var u in a)"default"!==u&&function(n){e.d(t,n,(function(){return a[n]}))}(u);t["default"]=c.a}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'components/uni-collapse/uni-collapse-create-component',
    {
        'components/uni-collapse/uni-collapse-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("2b7f"))
        })
    },
    [['components/uni-collapse/uni-collapse-create-component']]
]);
