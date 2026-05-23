# PHÂN TÍCH BÀI TOÁN ĐO HIỆU NĂNG BẰNG AOP

## Việc chèn code tính thời gian thủ công vi phạm nguyên tắc thiết kế nào?

Việc chèn các đoạn code:

```java
long startTime = System.currentTimeMillis();

long endTime = System.currentTimeMillis();

System.out.println(...);
```

vào bên trong mỗi phương thức service đã vi phạm nguyên tắc:

## Single Responsibility Principle (SRP)

Trong SOLID.

---

## Giải thích

Theo nguyên tắc SRP:

> Một class hoặc method chỉ nên có một trách nhiệm duy nhất.

Trong bài toán này:

### Nhiệm vụ chính của TransactionService

* Xử lý giao dịch thanh toán
* Thực hiện business logic

Nhưng hiện tại method còn phải:

* Đo thời gian thực thi
* In log hiệu năng

Điều này làm cho business logic bị trộn với logging/performance logic.

---

## Hậu quả

### 1. Code Tangling (Rối mã)

Code xử lý nghiệp vụ và code đo hiệu năng nằm lẫn vào nhau.

Khó đọc:

```java
long startTime = ...

// business logic

long endTime = ...
```

---

### 2. Duplicate Code

Các đoạn đo thời gian bị copy-paste ở nhiều method khác nhau.

Nếu có 100 method service:

* Phải viết lại 100 lần
* Rất khó bảo trì

---

### 3. Khó mở rộng

Nếu muốn:

* đổi format log
* lưu log xuống database
* gửi log sang monitoring system

thì phải sửa hàng loạt method.

---

## Giải pháp tốt hơn: AOP

Dùng Spring AOP với `@Around Advice` để:

* Tách riêng logic đo hiệu năng
* Tự động áp dụng cho toàn bộ service package
* Không sửa business logic

Ví dụ:

```java
@Around("execution(* com.bank.digital.transaction.service.*.*(..))")
```

Aspect sẽ tự động:

* ghi thời gian bắt đầu
* thực thi method
* ghi thời gian kết thúc
* in log

---

## Kết luận

Việc đo thời gian thủ công vi phạm nguyên tắc:

* Single Responsibility Principle (SRP)

vì method vừa xử lý nghiệp vụ vừa xử lý logging/performance.

AOP giúp tách cross-cutting concern ra khỏi business logic, làm code:

* sạch hơn
* dễ bảo trì hơn
* dễ mở rộng hơn
* đúng kiến trúc hơn.
