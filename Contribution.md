### Bài 5: Design Pattern - Singleton
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Singleton cho các class: AuthenticationController, HomeController, PaymentController, PlaceOrderController, ViewCartController (package controller)
- Subteam 2: Ngô Huy Thao, Đặng Đình Thọ, Hoàng Minh Tiến:
Áp dụng Singleton cho các class: AuthenticationController (package controller), AIMSDB (package entity.db), Cart (package entity.cart), Utils (package utils)

### Bài 5: Design Pattern - Template Method + Factory Method
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Template Method cho các class: CartScreenHandler, HomeScreenHandler, LoginScreenHandler, IntroScreenHandler, InvoiceScreenHandler, PaymentScreenHandler, ResultScreenHandler, ShippingScreenHandler với lớp cha là BaseScreenHandler.- Subteam 2: Hoàng Minh Tiến,Đặng Đình Thọ,Ngô Huy Thao
Áp dụng Factory Method cho Media và các lớp con: Book,CD,DVD
- Subteam 2: Hoàng Minh Tiến, Đặng Đình Thọ,Ngô Huy Thao
Tạo lớp cha PaymentMethod của CreditCard trong payment,tạo lớp cha PaymentMethodFactory và lớp con CreditCardFactory ứng với PaymentMethod và CreditCard


### Bài 6: Clean Code - Meaningful Names
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Meaningful Names cho các class: AuthenticationController, PaymentController (package controller); Cart (entity.cart); DeliveryInfo (entity.shipping); InterbankSubsystem (interbank); CartScreenHandler (views.screen.cart); MediaHandler (views.screen.home); InvoiceScreenHandler, MediaInvoiceScreenHandler (views.screen.invoice); PaymentScreenHandler (views.screen.payment); App (default package).
- Subteam2 : Hoàng Minh Tiến, Đặng Đình Thọ, Ngô Huy Thao
Áp dụng Meaningful Names trong các class sau: AuthenticationController, PaymentController, PlaceOrderController, ViewCartController,
 BookDAO, CDDAO, DVDDAO, MediaDAO, UserDAO, Cart, AIMSDB, ApplicationProgrammigInterface, MyMap, Utils


### Bài 6: Clean Code - Method Refactoring
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Method Refactoring cho các class: PaymentController, PlaceOrderController, ViewCartController (package controller); BookDAO, CDDAO, DVDDAO, MediaDAO (package dao.media); UserDAO (package dao.user); Cart (package entity.cart); AIMSDB (package entity.db); Order (package entity.order); DeliveryInfo (package entity.shipping); User (package entity.user); App (default package).


### Bài 6: Clean Code - Clean Class, Clean Test
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Clean Class cho các class: AuthenticationController, BaseController, PaymentController, PlaceOrderController, ViewCartController (package controller); Book (package entity.media). Áp dụng Clean code cho các class: MediaHandler(Searchable names) (package views.screen.home).
