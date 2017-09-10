## 基本
基于kotlin的mvp写的一款生活常用app。封装了很多常用的功能，以及常用框架：
Rxjava2，Retrofit，以及BaseRecyclerViewAdapterHelper等框架。

## 特性
- 一键注入依赖。
- 事件分发
- 引用的本地存储方式
- 队列,基于HandlerThread
- Rxbus,Rxjava2方式
- Aop编程支持(@Async @Cacheable @DebugTrace @HookMethod @LogMethod @Prefs @Safe)

## 第三方库
1. 使用了开源的FloatView库，基于Facebook的POP动画库
需要注意的是，`compile 'com.ufreedom.uikit:FloatingViewLib:1.0.2'`这个版本中的没有对结束后的动画View做回收处理，但是由于是接口形式依赖，我们可以自己去实现，再动画结束后调用`yumFloating.clear()`

## 遗留问题
1. 加载图片
glide 第一次加载图片有些显示不全，滑动到最低下再反过来就能看到完成的图片，但是picasso加载不会有这个问题，为什么呢？


### proguard-rules
-keep class com.jackyang.android.support.injection.**{*;}