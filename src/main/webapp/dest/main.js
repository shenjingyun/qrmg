debugger;
require.config({
　　　　baseUrl: "js",
　　　　paths: {
　　　　　　"jquery": "jquery.min", //会自动加.js后缀。所以不要加.js后缀，否则找不到文件
　　　　　　"underscore": "lib/underscore.min",
　　　　　　"backbone": "lib/backbone.min"
　　　　}
　　});
