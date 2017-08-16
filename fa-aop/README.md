## AOP lib引入配置
在Project gradle 配置中添加
```groovy
dependencies {
    classpath 'com.android.tools.build:gradle:2.3.3'
    classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.10'
}
```

在module gradle 配置中添加
```
apply plugin: 'com.hujiang.android-aspectjx'
```

