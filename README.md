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
