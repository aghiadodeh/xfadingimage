# Android Fading (Circle/ Custom Corner) Image

### Implementation
add `maven { url 'https://jitpack.io' }` to build.gradle in your project:
``` groovy
allprojects {
    repositories {
		...
        maven { url 'https://jitpack.io' }
    }
}
```
and in your app.gradle:
``` groovy
dependencies {
	...
	implementation 'com.github.aghiadodeh:xfadingimage:1.0.2'
}
```

in `your_activity.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    tools:context=".MainActivity">

    <com.aghiadodeh.xfadingimage.FadingImage
        android:id="@+id/fadingImage"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:layout_margin="10dp"
    />

    <com.aghiadodeh.xfadingimage.CircleImage
        android:id="@+id/circleImage"
        android:layout_width="120dp"
        android:layout_height="120dp"
        app:border="true"
        app:border_color="@android:color/black"
        app:border_width="1dp"
        android:layout_margin="10dp"/>

    <com.aghiadodeh.xfadingimage.CornerImageView
        android:id="@+id/cornerImage"
        android:layout_width="120dp"
        android:layout_height="120dp"
        app:image_corner_radius="10dp"
        android:layout_margin="10dp"/>

</LinearLayout>
```

in `YourActivity.kt`:
```kotlin
val fadingImage: FadingImage = findViewById(R.id.fadingImage)
val circleImage: CircleImage = findViewById(R.id.circleImage)
val cornerImage: CornerImageView = findViewById(R.id.cornerImage)

val imageUrl = "https://images.pexels.com/photos/5595490/pexels-photo-5595490.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
val videoUrl = "https://player.vimeo.com/external/387242416.sd.mp4?s=57e2d102f99b0c27b03d4db5fe9ca903b5646d41&profile_id=165&oauth2_token_id=57447761"
fadingImage.loadImage(url = imageUrl, duration = 500L, iFadeImage = { loaded ->
	// return boolean if image loaded successfully or not
})
/**
* load video Thumbnail
*/
// fadingImage.loadVideoThumbnail(url = videoUrl, duration = 1800L)

circleImage.loadImage(url = imageUrl, duration = 500L, iFadeImage = { loaded ->
	// return boolean if image loaded successfully or not
})

cornerImage.loadImage(url = imageUrl, duration = 500L, iFadeImage = { loaded ->
	// return boolean if image loaded successfully or not
})
```

# set dark filter to image:
```kitlon
circleImage.darkenImage()
```

# clear dark filter to image:
```kitlon
circleImage.defaultColorFilter()
```

### Overview
![](https://i.imgur.com/agBGwtE.png)
![](https://i.imgur.com/eM0DugD.png)
