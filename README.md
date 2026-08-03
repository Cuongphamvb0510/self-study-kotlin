# Android XML — Các thành phần cơ bản

Trong Android XML, "thẻ" thường được gọi là **View** hoặc **ViewGroup**.

---

## 1. View cơ bản

Các View dùng để hiển thị nội dung hoặc tương tác với người dùng.

| Thành phần     | Công dụng                     |
|----------------|-------------------------------|
| `TextView`     | Hiển thị văn bản              |
| `Button`       | Nút bấm                       |
| `ImageView`    | Hiển thị hình ảnh             |
| `EditText`     | Nhập văn bản                  |
| `ImageButton`  | Nút bấm bằng hình ảnh         |
| `CheckBox`     | Ô chọn nhiều lựa chọn         |
| `RadioButton`  | Chọn một trong nhiều lựa chọn |
| `Switch`       | Bật / tắt                     |
| `ProgressBar`  | Hiển thị tiến trình           |
| `SeekBar`      | Thanh kéo                     |
| `Spinner`      | Danh sách lựa chọn            |
| `RecyclerView` | Hiển thị danh sách            |

### Ví dụ

```xml

<TextView />

<Button />

<ImageView />

<EditText />

<CheckBox />

<RadioButton />

<Switch />
```

---

## ViewGroup

`ViewGroup` là các thành phần dùng để **chứa và sắp xếp các View khác**.

Ví dụ:

* `TextView`
* `Button`
* `ImageView`

có thể được đặt bên trong một `ViewGroup`.

---

## 2.1 LinearLayout

`LinearLayout` sắp xếp các View theo một chiều:

* `vertical`: Theo chiều dọc
* `horizontal`: Theo chiều ngang

### Vertical

```xml

<LinearLayout android:layout_width="match_parent" android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="@string/hello" />

    <Button android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="@string/click_me" />

</LinearLayout>
```

Kết quả:

```text
TextView
   ↓
Button
```

### Horizontal

```xml

<LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
    android:orientation="horizontal">

    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="@string/hello" />

    <Button android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="@string/click_me" />

</LinearLayout>
```

Kết quả:

```text
TextView    Button
```

---

## 2.2 ConstraintLayout

`ConstraintLayout` dùng để bố trí các View dựa trên các **Constraint (ràng buộc)**.

Ví dụ:

```xml

<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView android:id="@+id/textView" android:layout_width="wrap_content"
        android:layout_height="wrap_content" android:text="@string/hello"
        app:layout_constraintTop_toTopOf="parent" app:layout_constraintStart_toStartOf="parent" />

    <Button android:id="@+id/button" android:layout_width="wrap_content"
        android:layout_height="wrap_content" android:text="@string/click_me"
        app:layout_constraintTop_toBottomOf="@id/textView"
        app:layout_constraintStart_toStartOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

Một số Constraint thường gặp:

```text
Top
Bottom
Start
End
```

Ví dụ:

```text
TextView
    │
    │ Top_toBottomOf
    ▼
Button
```

`ConstraintLayout` phù hợp với những giao diện có bố cục phức tạp.

---

## 2.3 FrameLayout

`FrameLayout` cho phép các View **chồng lên nhau**.

Ví dụ:

```xml

<FrameLayout android:layout_width="match_parent" android:layout_height="200dp">

    <ImageView android:layout_width="match_parent" android:layout_height="match_parent"
        android:src="@drawable/avatar" />

    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:text="@string/hello" />

</FrameLayout>
```

Có thể hình dung:

```text
┌─────────────────────┐
│                     │
│      ImageView      │
│                     │
│        Hello        │
│                     │
└─────────────────────┘
```

`TextView` nằm trên `ImageView`.

Một ứng dụng phổ biến của `FrameLayout` là làm **container để hiển thị Fragment**.

---

## 2.4 ScrollView

`ScrollView` cho phép người dùng **cuộn nội dung theo chiều dọc**.

Ví dụ:

```xml

<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent">

    <LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView android:layout_width="match_parent" android:layout_height="wrap_content"
            android:text="@string/content_1" />

        <TextView android:layout_width="match_parent" android:layout_height="wrap_content"
            android:text="@string/content_2" />

        <TextView android:layout_width="match_parent" android:layout_height="wrap_content"
            android:text="@string/content_3" />

    </LinearLayout>

</ScrollView>
```

Ví dụ:

```text
┌──────────────────┐
│ Nội dung 1       │
│ Nội dung 2       │
│ Nội dung 3       │
│ Nội dung 4       │
│ Nội dung 5       │
│        ↓         │
│    Cuộn xuống    │
└──────────────────┘
```

> `ScrollView` chỉ nên có **một View con trực tiếp**. Nếu cần chứa nhiều View, thường sử dụng
`LinearLayout` bên trong.

Cấu trúc:

```text
ScrollView
    │
    ▼
LinearLayout
    │
    ├── TextView
    ├── TextView
    ├── Button
    └── ImageView
```

---

## 2.5 HorizontalScrollView

`HorizontalScrollView` tương tự `ScrollView`, nhưng cho phép cuộn theo **chiều ngang**.

Ví dụ:

```xml

<HorizontalScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="wrap_content">

    <LinearLayout android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:orientation="horizontal">

        <ImageView android:layout_width="200dp" android:layout_height="150dp" />

        <ImageView android:layout_width="200dp" android:layout_height="150dp" />

        <ImageView android:layout_width="200dp" android:layout_height="150dp" />

    </LinearLayout>

</HorizontalScrollView>
```

Có thể hình dung:

```text
┌────────┐ ┌────────┐ ┌────────┐
│ Image 1│ │ Image 2│ │ Image 3│
└────────┘ └────────┘ └────────┘
       ←────── Cuộn ngang ──────→
```

---

## 2.6 So sánh nhanh

| ViewGroup               | Công dụng chính                 |
|-------------------------|---------------------------------|
| `LinearLayout`          | Sắp xếp View theo hàng hoặc cột |
| `ConstraintLayout`      | Bố trí View bằng Constraint     |
| `FrameLayout`           | Chồng các View lên nhau         |
| `ScrollView`            | Cuộn nội dung theo chiều dọc    |
| `HorizontalScrollView`  | Cuộn nội dung theo chiều ngang  |
| `FragmentContainerView` | Container để hiển thị Fragment  |

---

## 2.7 Cách chọn ViewGroup

Có thể hiểu đơn giản:

```text
Muốn xếp View theo hàng/cột
        ↓
LinearLayout

Muốn bố cục phức tạp, nhiều ràng buộc
        ↓
ConstraintLayout

Muốn các View chồng lên nhau
        ↓
FrameLayout

Nội dung dài cần cuộn dọc
        ↓
ScrollView

Nội dung dài cần cuộn ngang
        ↓
HorizontalScrollView

Muốn hiển thị Fragment
        ↓
FragmentContainerView
```

> **Lưu ý:** `FragmentContainerView` không phải là ViewGroup thông thường để bạn đặt trực tiếp
`TextView`, `Button`, `ImageView` vào bên trong. Nó được thiết kế chuyên biệt cho việc chứa và quản
> lý `Fragment`.

---

# AndroidManifest.xml là gì?

`AndroidManifest.xml` là file **khai báo cấu hình chính** của app Android.

Có thể hiểu đơn giản:

```text
Layout XML  → màn hình trông như thế nào
Kotlin code → app xử lý logic thế nào
Manifest    → hệ thống Android biết app của bạn gồm những gì
```

File nằm ở:

```text
app/src/main/AndroidManifest.xml
```

Android dùng file này để biết:

* App tên gì, icon gì, theme gì
* Có những Activity / Service / BroadcastReceiver nào
* Activity nào là màn hình mở đầu (launcher)
* App cần xin quyền gì (`INTERNET`, `CAMERA`, ...)
* App có cho app khác mở không (`exported`)

Nếu **không khai báo trong Manifest**, Android sẽ không nhận component đó. Ví dụ: quên khai báo `WeatherActivity` thì `startActivity(...)` có thể bị lỗi khi chạy.

---

## 1. Cấu trúc tổng quát

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <!-- Xin quyền -->
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.StudyAndroid">

        <!-- Các Activity / Service / Receiver nằm trong đây -->
        <activity android:name=".activity.LoginActivity" />

    </application>

</manifest>
```

Cấu trúc:

```text
manifest
 ├── uses-permission
 └── application
      ├── activity
      ├── service
      ├── receiver
      └── provider
```

Trong dự án hiện tại chủ yếu dùng:

* `uses-permission`
* `application`
* `activity`
* `intent-filter`

---

## 2. Thẻ `manifest`

Đây là thẻ gốc. Mọi thứ khác nằm bên trong nó.

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    ...
</manifest>
```

`xmlns:android=...` dùng để Android hiểu các thuộc tính dạng `android:...`.

---

## 3. Thẻ `uses-permission`

Dùng để **xin quyền** từ hệ thống.

Ví dụ trong project:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

Vì `WeatherActivity` gọi API OpenWeather qua mạng, app cần quyền `INTERNET`.

Một số quyền thường gặp:

| Permission | Khi nào cần |
|------------|-------------|
| `INTERNET` | Gọi API, tải ảnh từ mạng |
| `ACCESS_NETWORK_STATE` | Kiểm tra có mạng không |
| `CAMERA` | Chụp ảnh / mở camera |
| `READ_MEDIA_IMAGES` | Đọc ảnh trên máy (Android mới) |
| `POST_NOTIFICATIONS` | Hiện notification (Android 13+) |

> Có quyền "thông thường" (như `INTERNET`) được cấp khi cài app.  
> Có quyền "nguy hiểm" (như `CAMERA`) thì phải xin thêm lúc runtime trong code.

---

## 4. Thẻ `application`

Đây là khối cấu hình chung của cả app.

Ví dụ trong project:

```xml
<application
    android:allowBackup="true"
    android:dataExtractionRules="@xml/data_extraction_rules"
    android:fullBackupContent="@xml/backup_rules"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@style/Theme.StudyAndroid">
```

Ý nghĩa các thuộc tính hay dùng:

| Thuộc tính | Ý nghĩa |
|------------|---------|
| `android:icon` | Icon app trên launcher |
| `android:roundIcon` | Icon bo tròn (một số máy dùng) |
| `android:label` | Tên app hiển thị |
| `android:theme` | Theme mặc định cho toàn app |
| `android:allowBackup` | Có cho backup dữ liệu app không |
| `android:supportsRtl` | Hỗ trợ giao diện đọc phải → trái |

Có thể hình dung:

```text
application
 ├── cấu hình chung: icon, tên, theme
 └── chứa danh sách các màn hình (activity)
```

---

## 5. Thẻ `activity`

Mỗi màn hình thường tương ứng một `Activity`, và **phải khai báo** trong Manifest.

Ví dụ:

```xml
<activity
    android:name=".activity.WeatherActivity"
    android:exported="false"
    android:windowSoftInputMode="adjustResize" />
```

### Các thuộc tính quan trọng

#### `android:name`

Tên class Activity.

```text
.activity.WeatherActivity
```

Dấu `.` ở đầu nghĩa là tính từ `namespace/package` của app.  
Ví dụ package là `com.example.studyandroid` thì tương đương:

```text
com.example.studyandroid.activity.WeatherActivity
```

#### `android:exported`

Cho biết app khác / hệ thống có được phép mở Activity này từ bên ngoài không.

| Giá trị | Ý nghĩa |
|---------|---------|
| `true` | App khác / hệ thống có thể mở |
| `false` | Chỉ app của bạn tự mở nội bộ |

Trong project:

* `LoginActivity` → `exported="true"` vì đây là màn hình mở app
* Các màn còn lại (`MainActivity`, `WeatherActivity`, ...) → `exported="false"`

#### `android:windowSoftInputMode`

Chỉnh hành vi khi bàn phím hiện lên.

```xml
android:windowSoftInputMode="adjustResize"
```

`adjustResize` nghĩa là thu nhỏ layout khi bàn phím bật, giúp form nhập liệu không bị che.

---

## 6. Thẻ `intent-filter`

`intent-filter` nói với Android: **Activity này biết xử lý loại Intent nào**.

Quan trọng nhất: khai báo **màn hình launcher** (màn mở đầu khi bấm icon app).

Trong project hiện tại:

```xml
<activity
    android:name=".activity.LoginActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

Hai dòng này nghĩa là:

| Dòng | Ý nghĩa |
|------|---------|
| `MAIN` | Đây là điểm vào chính của app |
| `LAUNCHER` | Hiện icon app trên màn hình máy, bấm vào sẽ mở Activity này |

Vì vậy khi mở app, Android sẽ vào `LoginActivity`, không phải `MainActivity`.

Có thể hình dung:

```text
User bấm icon app
        ↓
Android đọc Manifest
        ↓
Tìm activity có MAIN + LAUNCHER
        ↓
Mở LoginActivity
```

---

## 7. Đọc Manifest của project này

```xml
<manifest ...>
    <uses-permission android:name="android.permission.INTERNET" />

    <application ...>
        <activity android:name=".activity.MainActivity" android:exported="false" ... />
        <activity android:name=".activity.LoginActivity" android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".activity.LessonDetailActivity" ... />
        <activity android:name=".activity.BaseActivity" ... />
        <activity android:name=".activity.CourseDetailActivity" ... />
        <activity android:name=".activity.AddCourseActivity" ... />
        <activity android:name=".activity.WeatherActivity" ... />
    </application>
</manifest>
```

Tóm tắt theo project:

| Thành phần | Ý nghĩa trong app |
|------------|-------------------|
| `INTERNET` | Cho phép call API thời tiết |
| `LoginActivity` + `MAIN/LAUNCHER` | Màn hình mở đầu |
| Các `activity` khác | Các màn hình nội bộ của app |
| `exported="false"` | Không cho app ngoài mở trực tiếp |

---

## 8. Các thẻ khác (tổng quan nhanh)

Chưa dùng nhiều trong project này, nhưng hay gặp khi học tiếp:

| Thẻ | Dùng để |
|-----|---------|
| `service` | Chạy tác vụ nền (download, music, sync...) |
| `receiver` | Lắng nghe sự kiện hệ thống/broadcast |
| `provider` | Chia sẻ dữ liệu với app khác |
| `uses-feature` | Khai báo phần cứng cần có (camera, gps...) |
| `meta-data` | Đính kèm dữ liệu cấu hình thêm |

---

## 9. Lỗi thường gặp khi học Manifest

1. **Quên khai báo Activity**  
   Viết class rồi `startActivity(...)` nhưng chưa thêm vào Manifest → app crash.

2. **Sai `exported`**  
   Activity có `intent-filter` thường cần `android:exported="true"`.

3. **Nhầm màn launcher**  
   Gắn `MAIN + LAUNCHER` vào Activity sai → mở app vào màn không đúng.

4. **Call API nhưng quên `INTERNET`**  
   Có thể fail khi request mạng.

5. **Sai `android:name`**  
   Gõ sai package/class → Manifest không map đúng class Kotlin.

---

## 10. Checklist nhanh

Khi tạo màn hình mới, nhớ hỏi:

```text
1. Đã tạo Activity Kotlin chưa?
2. Đã thêm <activity> vào AndroidManifest chưa?
3. Có phải màn mở đầu không?
   - Có  → thêm MAIN + LAUNCHER, exported=true
   - Không → thường exported=false
4. Màn này cần quyền gì không? (INTERNET, CAMERA, ...)
5. Có cần chỉnh bàn phím không? (windowSoftInputMode)
```

> **Nhớ:** Manifest không viết logic nghiệp vụ. Nó chỉ **đăng ký và cấu hình** để hệ thống Android biết cách chạy app của bạn.
