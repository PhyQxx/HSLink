(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["components/uni-collapse/uni-collapse"],{"0bc2":function(n,t,e){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var c={name:"UniCollapse",props:{accordion:{type:[Boolean,String],default:!1}},data:function(){return{}},provide:function(){return{collapse:this}},created:function(){this.childrens=[]},methods:{onChange:function(){var n=[];this.childrens.forEach((function(t,e){t.isOpen&&n.push(t.nameSync)})),this.$emit("change",n)}}};t.default=c},"85d1":function(n,t,e){},"8d73":function(n,t,e){"use strict";e.r(t);var c=e("e9c5"),u=e("95f8");for(var r in u)"default"!==r&&function(n){e.d(t,n,(function(){return u[n]}))}(r);e("c905");var a,o=e("f0c5"),i=Object(o["a"])(u["default"],c["b"],c["c"],!1,null,"238857a0",null,!1,c["a"],a);t["default"]=i.exports},"95f8":function(n,t,e){"use strict";e.r(t);var c=e("0bc2"),u=e.n(c);for(var r in c)"default"!==r&&function(n){e.d(t,n,(function(){return c[n]}))}(r);t["default"]=u.a},c905:function(n,t,e){"use strict";var c=e("85d1"),u=e.n(c);u.a},e9c5:function(n,t,e){"use strict";var c;e.d(t,"b",(function(){return u})),e.d(t,"c",(function(){return r})),e.d(t,"a",(function(){return c}));var u=function(){var n=this,t=n.$createElement;n._self._c},r=[]}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'components/uni-collapse/uni-collapse-create-component',
    {
        'components/uni-collapse/uni-collapse-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("8d73"))
        })
    },
    [['components/uni-collapse/uni-collapse-create-component']]
]);
