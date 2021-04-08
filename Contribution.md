### Bài 5: Design Pattern - Singleton
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Singleton cho các class: AuthenticationController, HomeController, PaymentController, PlaceOrderController, ViewCartController (package controller)
- Subteam 2: Ngô Huy Thao, Đặng Đình Thọ, Hoàng Minh Tiến:
Áp dụng Singleton cho các class: AuthenticationController (package controller), AIMSDB (package entity.db), Cart (package entity.cart), Utils (package utils)

### Bài 5: Design Pattern - Template Method + Factory Method
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Template Method cho các class: CartScreenHandler, HomeScreenHandler, LoginScreenHandler, IntroScreenHandler, InvoiceScreenHandler, PaymentScreenHandler, ResultScreenHandler, ShippingScreenHandler với lớp cha là BaseScreenHandler.
- Subteam 2: Hoàng Minh Tiến,Đặng Đình Thọ,Ngô Huy Thao
Áp dụng Factory Method cho Media và các lớp con: Book,CD,DVD
Tạo lớp cha PaymentMethod của CreditCard trong payment,tạo lớp cha PaymentMethodFactory và lớp con CreditCardFactory ứng với PaymentMethod và CreditCard

### Bài 6: Clean Code - Meaningful Names
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Meaningful Names cho các class: AuthenticationController, PaymentController (package controller); Cart (entity.cart); DeliveryInfo (entity.shipping); InterbankSubsystem (interbank); CartScreenHandler (views.screen.cart); MediaHandler (views.screen.home); InvoiceScreenHandler, MediaInvoiceScreenHandler (views.screen.invoice); PaymentScreenHandler (views.screen.payment); App (default package).