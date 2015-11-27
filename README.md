#### 配置运行使用本地 file protocol

__ 主要目的 __

用于解决，外部网站无法访问本地资源

例如：file:///android_asset/www/ 替换成 localfile://进行文件加载

````
/**
 * 加载本地js
 */
 <script type="text/javascript" src="localfile://js/index.js"></script>
 /**
  * 加载本地css
  */
 <link rel="stylesheet" rev="stylesheet" type="text/css" href="localfile://css/index.css">
 /**
  * 加载本地图片
  */
 <img src="localfile://img/icon.png">
````
