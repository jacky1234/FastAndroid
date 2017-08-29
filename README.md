## 基本
基于kotlin的mvp写的一款生活常用app。封装了很多常用的功能，以及常用框架：
Rxjava2，Retrofit，以及BaseRecyclerViewAdapterHelper等框架。

## 特性
- 一键注入依赖。
- 事件分发
- 引用的本地存储方式
- 队列,基于HandlerThread
- Rxbus,Rxjava2方式



### proguard-rules
-keep class com.jackyang.android.support.injection.**{*;}