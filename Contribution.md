### Design Pattern - Coupling
- Subteam 1: Lê Anh Thành, Phạm Văn Thành: Tìm Coupling trên các package : controller,dao,entity,subsystem,utils,views.screen,class App
-  Subteam 2: Ngô Huy Thao, Đặng Đình Thọ, Hoàng Minh Tiến: Tìm Coupling trên các package controller, dao, entity, subsystem, utils, views.screen, class App

### Design Pattern - Cohesion
- Subteam 1: Lê Anh Thành, Phạm Văn Thành: Tìm Cohesion lên các class: PaymentController, App, PlaceOrderController, Observable(common.interfaces), Observer(commmon.interfaces), ApplicationProgrammingInterface(java.utils), MyMap(java.utils), Utils(java.utils), AuthenticationController.
-  Subteam 2: Ngô Huy Thao, Đặng Đình Thọ, Hoàng Minh Tiến:Tìm Cohesion cho các class: PlaceOrderController, AuthenticationController, BaseController, HomeController, PaymentController, ViewCartController(java.controller), Cart(entity.cart), cartItem(entity.cart), AIMSDB(java.entity.db), Invoice(java.entity.invoice), Book,DVD,Media(java.entity.media), Order(java.entity.order), DeliveryInfo(java.entity.shipping), User, BaseScreenHandler, CartScreenHandler, HomeScreenHandler(views.screen)

### Design Pattern - SOLID
- Subteam 1: Lê Anh Thành, Phạm Văn Thành: Tìm SOLID trên các class: AuthenticationController, PaymentController, PlaceOrderController, MyMap, ApplicationProgrammingInterface, HomeController.
-  Subteam 2: Ngô Huy Thao, Đặng Đình Thọ, Hoàng Minh Tiến: Tìm SOLID cho các class: Cart, CD, CartScreenHandler, HomeScreenHandler, LoginScreenHandler, ShippingScreenHandler, Media, Order, PaymentTransaction, DeliveryInfo, AuthenticationController, PaymentController, InterbankSubsystem.

### Design Pattern - Singleton
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Singleton cho các class: AuthenticationController, HomeController, PaymentController, PlaceOrderController, ViewCartController (package controller)
- Subteam 2: Ngô Huy Thao, Đặng Đình Thọ, Hoàng Minh Tiến:
Áp dụng Singleton cho các class: AuthenticationController (package controller), AIMSDB (package entity.db), Cart (package entity.cart), Utils (package utils)

### Design Pattern - Template Method + Factory Method
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Template Method cho các class: CartScreenHandler, HomeScreenHandler, LoginScreenHandler, IntroScreenHandler, InvoiceScreenHandler, PaymentScreenHandler, ResultScreenHandler, ShippingScreenHandler với lớp cha là BaseScreenHandler.
- Subteam 2: Hoàng Minh Tiến, Đặng Đình Thọ,Ngô Huy Thao:
Áp dụng Factory Method cho Media và các lớp con: Book,CD,DVD.
Tạo lớp cha PaymentMethod của CreditCard trong payment,tạo lớp cha PaymentMethodFactory và lớp con CreditCardFactory ứng với PaymentMethod và CreditCard


### Clean Code - Meaningful Names
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Meaningful Names cho các class: AuthenticationController, PaymentController (package controller); Cart (entity.cart); DeliveryInfo (entity.shipping); InterbankSubsystem (interbank); CartScreenHandler (views.screen.cart); MediaHandler (views.screen.home); InvoiceScreenHandler, MediaInvoiceScreenHandler (views.screen.invoice); PaymentScreenHandler (views.screen.payment); App (default package).
- Subteam2 : Hoàng Minh Tiến, Đặng Đình Thọ, Ngô Huy Thao
Áp dụng Meaningful Names trong các class sau: AuthenticationController, PaymentController, PlaceOrderController, ViewCartController,
 BookDAO, CDDAO, DVDDAO, MediaDAO, UserDAO, Cart, AIMSDB, ApplicationProgrammigInterface, MyMap, Utils


### Clean Code - Method Refactoring
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Method Refactoring cho các class: PaymentController, PlaceOrderController, ViewCartController (package controller); BookDAO, CDDAO, DVDDAO, MediaDAO (package dao.media); UserDAO (package dao.user); Cart (package entity.cart); AIMSDB (package entity.db); Order (package entity.order); DeliveryInfo (package entity.shipping); User (package entity.user); App (default package).
- Subteam 2: Hoàng Minh Tiến, Đặng Đình Thọ, Ngô Huy Thao
Áp dụng Method Refactoring cho các class: BookDAO,DVDDAO,MediaDAO,UserDAO,PaymentTransaction,InterbankPayloadConverter

### Clean Code - Clean Class, Clean Test
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Clean Class cho các class: AuthenticationController, BaseController, PaymentController, PlaceOrderController, ViewCartController (package controller); Book (package entity.media). Áp dụng Clean code cho các class: MediaHandler(Searchable names) (package views.screen.home). Áp dụng Clean code để loại bỏ sự phụ thuộc trực tiếp vào ConcreteClass (cụ thể là CreditCard) để phụ thuộc vào AbstractClass (cụ thể là PaymentMethod - superclass của CreditCard) và ứng dụng cả FactoryMethod trong các class: PaymentController (package controller); InterbankInterface, InterbankSubsystem (package subsystem); InterbankPayloadConverter, InterbankSubsystemController (package subsystem.interbank); PaymentScreenHandler (package views.screen.payment).
- Subteam 2: Hoàng Minh Tiến, Đặng Đình Thọ, Ngô Huy Thao
Áp dụng Clean Class cho các class: DVDDao,MediaDao,InterbankPayloadConverter,InterbankSubsystemController

### Design Pattern - Strategy Pattern
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Strategy Method cho các class: DeliveryInfo (package entity.shipping), tạo interface CalculateMethod (package entity.shipping) để ứng dụng Strategy Method cho class trên phần tính chi phí vận chuyển (method calculateShippingFee).
- Subteam 2: Hoàng Minh Tiến, Đặng Đình Thọ, Ngô Huy Thao
Áp dụng Strategy Method cho các class: BaseScreenHandler, tạo interface Notification với 2 implementation OnScreenNotification(sử dụng thông báo trong màn hiện tại),PopupNotification(sử dụng thông báo trong cửa sổ Popup)

### Design Pattern - Observer Pattern
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Observer Pattern cho class MediaHandler - sử dụng Interface Observable, class CartScreenHandler - sử dụng Interface Observer (package views.screen.cart).
- Subteam 2: Hoàng Minh Tiến, Đặng Đình Thọ,Ngô Huy Thao 
  Áp dụng Observer Pattern cho class MediaHandler - sử dụng Interface Observable, class CartScreenHandler - sử dụng Interface Observer
  
### Design Pattern - Adapter Pattern, State Pattern
- Subteam 1: Lê Anh Thành, Phạm Văn Thành:
Áp dụng Adapter Pattern giải quyết yêu cầu số 4. Cho class DeliveryInfo (package entity.shipping) liên quan tới sự thay đổi thư viện tính toán khoảng cách. Tạo mới một interface DistanceCalculatorInterface, ba class OldDistanceCalculator, AlternativeCalculatorAdapter, NewDistanceCalculator (package entity.shipping).
- Subteam 2: Ngô Huy Thao, Đặng Đình Thọ, Hoàng Minh Tiến:
Áp dụng State Pattern giải quyết yêu cầu số 7. Cho class Order liên quan tới yêu cầu hủy đơn hàng. Tạo mới abstract class State, với các thành phần con kế thừa DefaultState, WaitingState, ApprovedState, CanceledState.
