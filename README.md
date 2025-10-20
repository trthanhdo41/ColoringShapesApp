# Coloring Shapes App

Ứng dụng Android tô màu hình học với đầy đủ chức năng CRUD và quản lý người dùng.

## 🎯 Tính năng chính

### 👤 Người dùng
- **Đăng ký/Đăng nhập/Đăng xuất** - Xác thực người dùng với email và mật khẩu
- **Quản lý hồ sơ** - Chỉnh sửa thông tin cá nhân, email, mật khẩu
- **Tô màu** - Tô màu các hình học với bảng màu đa dạng
- **Lịch sử** - Xem và quản lý lịch sử tô màu (xóa từng item hoặc tất cả)
- **Nhiệm vụ** - Xem danh sách nhiệm vụ tô màu
- **Cài đặt** - Giao diện cài đặt chuyên nghiệp với chế độ sáng/tối

### 👨‍💼 Quản trị viên
- **Quản lý người dùng** - Xem danh sách và xóa người dùng
- **Quản lý nhiệm vụ** - Thêm, sửa, xóa, bật/tắt nhiệm vụ
- **Quản lý hình học** - Thêm, sửa, xóa các hình để tô màu
- **Báo cáo** - Xem top 10 người dùng có điểm cao nhất

## 🛠️ Công nghệ sử dụng

- **Android Jetpack Compose** - UI framework hiện đại
- **Room Database (SQLite)** - Cơ sở dữ liệu local
- **MVVM Architecture** - Kiến trúc ứng dụng
- **Kotlin Coroutines** - Xử lý bất đồng bộ
- **Material Design 3** - Thiết kế giao diện

## 📱 Màn hình ứng dụng

### Màn hình chính
- **Login/Register** - Đăng nhập và đăng ký tài khoản
- **Tasks** - Danh sách nhiệm vụ tô màu
- **History** - Lịch sử tô màu với chức năng xóa
- **Settings** - Cài đặt ứng dụng và quản lý hồ sơ
- **Admin** - Panel quản trị (chỉ admin)

### Màn hình Admin
- **Users Management** - Quản lý người dùng
- **Tasks Management** - Quản lý nhiệm vụ
- **Shapes Management** - Quản lý hình học
- **Reports** - Báo cáo điểm số

## 🚀 Cài đặt và chạy

1. **Clone repository:**
   ```bash
   git clone https://github.com/trthanhdo41/ColoringShapesApp.git
   cd ColoringShapesApp
   ```

2. **Mở project trong Android Studio:**
   - Mở thư mục `android-app`
   - Đợi Gradle sync hoàn tất

3. **Chạy ứng dụng:**
   - Kết nối thiết bị Android hoặc khởi động emulator
   - Nhấn Run hoặc Shift+F10

## 👥 Tài khoản demo

- **Người dùng:** `user@demo.com` / `123456`
- **Admin:** `admin@demo.com` / `123456`

## 📋 Yêu cầu hệ thống

- Android 7.0 (API level 24) trở lên
- Android Studio Arctic Fox trở lên
- Kotlin 1.9.22
- Gradle 8.0+

## 🎨 Tính năng nổi bật

- **Giao diện đẹp** - Material Design 3 với chế độ sáng/tối
- **Thông báo** - Toast messages cho các hành động
- **Validation** - Kiểm tra dữ liệu đầu vào
- **Error handling** - Xử lý lỗi toàn diện
- **Role-based access** - Phân quyền theo vai trò
- **Database integration** - Lưu trữ dữ liệu local

## 📊 Cấu trúc dữ liệu

### Entities
- **User** - Thông tin người dùng
- **Task** - Nhiệm vụ tô màu
- **Shape** - Hình học để tô màu
- **Artwork** - Tác phẩm đã tô màu

### Database
- **Room Database** - SQLite với Room ORM
- **Foreign Keys** - Liên kết giữa các bảng
- **Auto Migration** - Tự động cập nhật schema

## 🔧 Cấu hình

### Dependencies chính
```kotlin
// Room Database
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
ksp("androidx.room:room-compiler:2.6.1")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// Compose
implementation("androidx.compose.ui:ui:1.5.4")
implementation("androidx.compose.material3:material3:1.1.2")
```

## 📝 License

Dự án này được phát triển cho mục đích học tập và demo.

## 👨‍💻 Tác giả

Phát triển bởi: [trthanhdo41](https://github.com/trthanhdo41)

---

**Lưu ý:** Đây là phiên bản demo với đầy đủ chức năng CRUD và quản lý người dùng, phù hợp cho việc học tập và phát triển ứng dụng Android.
