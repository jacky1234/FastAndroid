-ignorewarnings                     # 忽略警告，避免打包时某些警告出现
-dontwarn android.support.v4.**     #缺省proguard 会检查每一个引用是否正确，但是第三方库里面往往有些不会用到的类，没有正确引用。如果不配置的话，系统就会报错。
-dontwarn android.os.**
-dontskipnonpubliclibraryclasses    # 是否混淆第三方jar
-dontskipnonpubliclibraryclassmembers

-keep class com.jackyang.android.support.injection.**{*;}

#保持 native 的方法不去混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
#保留继承自Activity、Application这些类的子类，因为这些子类都有可能被外部调用
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
#保留在Activity中的方法参数是view的方法，从而我们在layout里面编写onclick的就不会被影响
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
#保留自定义控件指定规则的方法不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    *** get*();
}
#枚举类不能被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#保留Parcelable不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
#需要序列化和反序列化的类不能被混淆（注：Java反射用到的类也不能被混淆）
-keepnames class * implements java.io.Serializable
#保护实现接口Serializable的类中，指定规则的类成员不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#保持R文件不被混淆，否则，你的反射是获取不到资源id的
-keep class **.R$* { *; }

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#fastjson 可以混淆也可以不混淆
-keep class javax.ws.rs.** {*;}
-dontwarn com.alibaba.fastjson.**

#For design support library
-keep class android.support.design.widget.** {*;}
-keep interface android.support.design.widget.** {*;}

-dontwarn org.joda.convert.**

#Aop
-keep class com.jackyang.android.aop.** {*;}
#keep annotation on methods
-keepattributes *Annotation*
-keep class com.jack.ioultimateencrypt.sample.aop.AopShow
-keepclassmembernames class **{
    @com.jackyang.android.aop.annotation.Async  *;
    @com.jackyang.android.aop.annotation.Cacheable  *;
    @com.jackyang.android.aop.annotation.DebugTrace  *;
    @com.jackyang.android.aop.annotation.HookMethod  *;
    @com.jackyang.android.aop.annotation.LogMethod  *;
    @com.jackyang.android.aop.annotation.Prefs  *;
    @com.jackyang.android.aop.annotation.Safe  *;
}

# ImmersionBar
-keep class com.gyf.barlibrary.* {*;}

#假设调用不产生任何影响，在proguard代码优化时会将该调用remove掉。如system.out.println和Log.v等等
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

# AndroidUtilCode
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**

#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt