## 基本
基于kotlin的mvp写的一款生活常用app。封装了很多常用的功能，以及开发中比较常用框架，对开发中对问题进行记录和深入对分析。

## 特性
- 一键注入依赖。
- 事件分发
- 引用的本地存储方式
- 模仿android的Handler机制实现易用队列Queue，内部使用HandlerThread
- RxBus,Rxjava2方式
- Aop编程支持(@Async @Cacheable @DebugTrace @HookMethod @LogMethod @Prefs @Safe)。Aop方式测试XML存储和对象序列化存储性能上的差异

## AOP 支持
在Project gradle 配置中添加
```groovy
dependencies {
    classpath 'com.android.tools.build:gradle:2.3.3'
    classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.10'
}
```

在module gradle 配置中添加
```gradle
apply plugin: 'com.hujiang.android-aspectjx'
```


## 第三方库
**1. FloatingView**

使用了开源的 FloatingView 库，基于Facebook的POP动画库
需要注意的是，`compile 'com.ufreedom.uikit:FloatingViewLib:1.0.2'`这个版本中的没有对结束后的动画View做回收处理，但是由于是接口形式依赖，我们可以自己去实现，再动画结束后调用`yumFloating.clear()`

**2. [换肤框架-Android-skin-support](https://github.com/ximsfei/Android-skin-support#%E5%BA%94%E7%94%A8%E5%86%85%E6%8D%A2%E8%82%A4)**

基于AppCompatActivity的偷换控件原理实现了无缝换肤功能。参照源码实现在Activity onCreate中为LayoutInflater setFactory, 将View的创建过程交给自定义的SkinCompatViewInflater类来实现
原博客地址为： [Android-skin-support 一款用心去做的Android 换肤框架](http://blog.csdn.net/ximsfei/article/details/54586827)

**3. [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)**

a .`BaseQuickAdapter`的`setEmptyView`在下面情形下有crash的bug问题：当Adapter加入了HeaderView，然后调用`setEmptyView`，就会crash。具体处理参考:`UpcomingFragment`(一个即将来袭电影的Fragment)
b .`BaseQuickAdapter`设置了 `setOnItemLongClickListener`后不能设置OnItemLongClickListener为null，这样会出现空指针异常，这与库中实现原理相关，具体参考源码。
c .多布局类型时，自定义类型的int值不能与 `BaseQuickAdapter`中 `HEADER_VIEW,LOADING_VIEW,FOOTER_VIEW,EMPTY_VIEW` 值有雷同，否则就会crash。

**4. [SAF-Kotlin-log](https://github.com/fengzhizi715/SAF-Kotlin-log)**

一个很方便基于Kotlin写的日志打印库,作者是 Tony Shen(现魔窗移动端负责)。并且基于Kotlin的特性扩写了很多功能，详细请移步：**LExt.kt**

## 遗留问题
1. 加载图片
glide 第一次加载图片有些显示不全，滑动到最低下再反过来就能看到完成的图片，但是picasso加载不会有这个问题，为什么呢？
2. Kotlin assert 问题

### proguard-rules
-keep class com.jackyang.android.support.injection.**{*;}