-verbose

-dontwarn android.net.http.SslError

-dontwarn java.nio.file.Files
-dontwarn java.nio.file.Path
-dontwarn java.nio.file.OpenOption
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn com.google.android.gms.**

#jar warnings
-keepattributes EnclosingMethod

# OkHttp rules
-dontwarn com.squareup.okhttp**
-dontwarn okio.**

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-keepnames class com.sample.ssheleg.data.*
-keepclasseswithmembernames class * {
    native <methods>;
}

-keep class com.google.android.gms.** { *; }
-dontwarn java.lang.invoke**
-dontwarn java.lang.reflect.Method

# Gson
-keep public class com.google.gson
-keep class sun.misc.Unsafe { *; }
-dontwarn sun.misc.Unsafe
-keepclassmembers enum com.sample.ssheleg.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.sample.ssheleg.data.model.** { *; }

# For using GSON @Expose annotation
-keepattributes *Annotation*

#Rx
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Crashlytics
-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**

# Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Logs
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

# Otto bus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# Parcel library
-keep class org.parceler.Parceler$$Parcels
-keepnames class * extends org.parceler.NonParcelRepository$ConverterParcelable {
    public static final ** CREATOR;
}
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }

# Google Play Billing
-keep class com.android.vending.billing.**

#SearchView
-keep class android.support.v7.widget.SearchView { *; }

# keep drawables names
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep class **.R$*