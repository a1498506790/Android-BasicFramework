# Android-BasicFramework
一个自用的 Android 基础框架库，目前正在不断完善中。

## 整体框架

- 网络请求

  	[retrofit2](https://github.com/square/retrofit) + [okhttp](https://github.com/square/okhttp) + [gson](https://github.com/google/gson)，并提供了对应的封装类：HttpClient、MyCallback。


- 图片加载

  	[glide](https://github.com/bumptech/glide)，并提供了对应的封装类：ImageLoad。


- 依赖注入

  	[butterknife](https://github.com/JakeWharton/butterknife)


- 事件传递

  	[eventbus3](https://github.com/greenrobot/EventBus)


- Adapter

	[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

## 已有功能

- 封装了基础类，BaseActivity、BaseFragment。
- 对 Toolbar 进行了封装，一行代码实现标题栏。
- 引入腾讯 X5 内核，并提供了 ProgressWebView，提供更好的浏览体验。
- 右滑关闭当前 Activity。
- 多状态布局。
- 常用工具类。
- 常用自定义控件。

## 一些细节

- 解决闪屏页白屏问题。
- 指定了 Activty 进入、退出动画。

## 联系我

- **QQ 群：** 5707887
- **Blog：**[http://blog.csdn.net/airsaid](http://blog.csdn.net/airsaid)
- **Email：** airsaid1024@gmail.com
