Đề tài: Ứng dụng tô màu hình khối

Mô tả bài toán:
Phát triển ứng dụng Android để tô màu các hình. Thông tin hình, màu sắc và thời gian tô được lưu trong cơ sở dữ liệu.
Ứng dụng hỗ trợ hai loại người dùng:

Người chơi (thực hiện nhiệm vụ tô màu)

Quản trị viên (quản lý cài đặt)

Chức năng chính: đăng nhập, thực hiện nhiệm vụ, quản lý thông tin người dùng, xem lịch sử và chấm điểm.

Ứng dụng phải có giao diện thân thiện, dễ sử dụng và bảo mật cơ bản.

Mục tiêu

Học thuật: củng cố kiến thức lập trình Android (MVVM/MVP, Services, SQLite, bảo mật cơ bản).
Thực hành: thiết kế UI/UX, kiến trúc ứng dụng, lập trình di động, tích hợp database, làm việc nhóm với Git.
Thực tế: xây dựng ứng dụng tương tự sản phẩm thương mại đơn giản.

Yêu cầu kỹ thuật

1. Công nghệ sử dụng

Android: View layout, database

Database: SQLite

Build tool: Gradle

Version control: Git (GitHub/GitLab)

Thư viện: Color, Palette

2. Chức năng chính (100% cho phiên bản cơ bản)

a. Người dùng (50%)

- Đăng ký/Đăng nhập/Đăng xuất (10%)
- Xem danh sách nhiệm vụ (10%)
- Xem chi tiết nhiệm vụ (tên, mô tả, thời gian, màu sắc) (5%)
- Thực hiện tô màu (20%)
- Xem điểm số và lịch sử (5%)

b. Quản trị viên (50%)

- Đăng nhập/Đăng xuất (5%)
- Quản lý nhiệm vụ (Thêm/Sửa/Xóa) (15%)
- Quản lý hình (Thêm/Sửa/Xóa) (15%)
- Xem danh sách người dùng (5%)
- Xem báo cáo điểm số (top 10) (10%)

Chi tiết Use Case
UC-U01 – Đăng ký

Người dùng tạo tài khoản mới.

Preconditions: App cài đặt, chưa có tài khoản

Postconditions: Tài khoản mới được tạo

Main flow: chọn Đăng ký → nhập thông tin → lưu → hiển thị thành công

Sub flow: email tồn tại → lỗi

UC-U02 – Đăng nhập

Người dùng đăng nhập bằng tài khoản đã có.

Preconditions: Tài khoản tồn tại

Postconditions: Vào giao diện chính

Main flow: nhập email & mật khẩu → xác thực → vào main

Sub flow: sai mật khẩu → lỗi

UC-U03 – Đăng xuất

Người dùng thoát hệ thống.

Preconditions: Đã đăng nhập

Postconditions: Quay lại màn hình đăng nhập

Main flow: chọn Đăng xuất → xác nhận → quay lại đăng nhập

Sub flow: chọn “Hủy” → quay lại main

UC-U04 – Xem danh sách nhiệm vụ

Xem danh sách tất cả nhiệm vụ.

Preconditions: Đã đăng nhập

Postconditions: Hiển thị danh sách

Main flow: truy vấn dữ liệu → hiển thị danh sách nhiệm vụ

UC-U05 – Tô màu

Người dùng chọn hình và thực hiện tô màu.

Preconditions: Đã đăng nhập

Postconditions: Lưu kết quả

Main flow: chọn nhiệm vụ → hiển thị hình và bảng màu → tô màu → hoàn thành → tính điểm → lưu kết quả

UC-U06 – Xem lịch sử và điểm số

Người dùng xem lại các nhiệm vụ đã hoàn thành và điểm số.

Preconditions: Đã đăng nhập

Postconditions: Hiển thị thông tin

Main flow: chọn "Lịch sử" → truy vấn → hiển thị danh sách nhiệm vụ đã hoàn thành và điểm số

UC-A01 – Đăng nhập (Admin)

Admin truy cập hệ thống.

Preconditions: Có tài khoản quản trị

Postconditions: Vào trang quản lý

Main flow: nhập email/mật khẩu → xác thực → hiển thị trang chính

Sub flow: sai mật khẩu → lỗi

UC-A02 – Đăng xuất (Admin)

Admin thoát khỏi hệ thống.

Main flow: chọn Đăng xuất → xác nhận → về đăng nhập

Sub flow: chọn “Hủy” → quay lại main

UC-A03 – Quản lý nhiệm vụ

Admin thêm, sửa, xóa nhiệm vụ.

Preconditions: Đăng nhập với quyền admin

Postconditions: Danh sách nhiệm vụ được cập nhật

Main flow: vào "Quản lý nhiệm vụ" → thêm/sửa → nhập thông tin (tên, mô tả, thời gian) → lưu

Sub flow: xóa → xác nhận → xóa khỏi database

UC-A04 – Quản lý hình

Admin quản lý danh mục hình để tô màu.

Preconditions: Đăng nhập với quyền admin

Postconditions: Danh sách hình được cập nhật

Main flow: vào "Quản lý hình" → thêm/sửa/xóa hình → lưu vào database

UC-A05 – Xem danh sách người dùng

Admin xem danh sách người dùng đã đăng ký.

Preconditions: Đăng nhập với quyền admin

Postconditions: Hiển thị danh sách

Main flow: vào "Quản lý người dùng" → truy vấn database → hiển thị danh sách

UC-A06 – Xem báo cáo điểm số

Admin xem top 10 người dùng có điểm cao nhất.

Preconditions: Đăng nhập với quyền admin

Postconditions: Hiển thị bảng xếp hạng

Main flow: vào "Báo cáo" → truy xuất dữ liệu điểm → sắp xếp → hiển thị top 10

Danh sách Use Case tổng hợp:

Người dùng:
- UC-U01 – Đăng ký
- UC-U02 – Đăng nhập
- UC-U03 – Đăng xuất
- UC-U04 – Xem danh sách nhiệm vụ
- UC-U05 – Tô màu
- UC-U06 – Xem lịch sử và điểm số

Quản trị viên:
- UC-A01 – Đăng nhập (Admin)
- UC-A02 – Đăng xuất (Admin)
- UC-A03 – Quản lý nhiệm vụ (Thêm/Sửa/Xóa)
- UC-A04 – Quản lý hình (Thêm/Sửa/Xóa)
- UC-A05 – Xem danh sách người dùng
- UC-A06 – Xem báo cáo điểm số (top 10)

---

YÊU CẦU NÂNG CAO (Tùy chọn - có thể phát triển sau):

1. Thông báo thời gian (10%)
   - Notification khi hết giờ làm nhiệm vụ
   - Background service để theo dõi thời gian
   - Bộ đếm thời gian nâng cao (count up/down)

2. Hỗ trợ đa ngôn ngữ (5%)
   - Giao diện tiếng Anh/Tiếng Việt
   - Chuyển đổi ngôn ngữ trong settings

3. Xem tác phẩm (5%)
   - Admin xem chi tiết hình người dùng đã tô
   - Hiển thị gallery các tác phẩm

Lưu ý: Các tính năng nâng cao này không bắt buộc trong phiên bản 1.0, 
có thể phát triển thêm khi có ngân sách hoặc yêu cầu mở rộng.

---

TỔNG KẾT:

Phiên bản cơ bản: 100% - Tập trung vào các chức năng core
- Hệ thống đăng ký/đăng nhập đầy đủ
- Tô màu hình cơ bản với chấm điểm
- Quản lý nhiệm vụ và hình (Admin)
- Xem lịch sử và báo cáo

Phiên bản nâng cao (tùy chọn): thêm 20%
- Thông báo và background service
- Đa ngôn ngữ
- Gallery tác phẩm