# Patika.dev - Logo Bootcamp Final Project
## AirplaneAndBusOnlineTicketAPI

### Proje Kapsamı ve Açıklaması

### Proje Kısıtları

### Sistem Şeması

![image](https://user-images.githubusercontent.com/97917750/182662896-158890cb-dbdd-4910-bd72-076e5ca6b977.png)


### Sistem Modelleri ve İlişkileri

### Projede kullanılan Enum Değerler ve Sabit Değerler

Enum:  
ConfigurationType: EMAIL, MESSAGE -> Bilgilendirmenin email mi mesaj mı olduğunu belirtmek için.  
CurrencyType: TL, USD, EUR -> Ödeme şeklinin belirlenmesi için.  
GenderType: FEMALE, MALE -> Kullanıcıların ve yolcuların cinsiyetlerinin belirlenmesi için.  
PaymentType: CREDIT_CARD, EFT_TRANSFER -> Bilet ödeme şeklinin belirlenmesi için.  
UserType: INDIVIDUAL, CORPORATE -> Kullanıcı tipinin bireysel mi kurumsal mı olduğunu belirtmek için.  
VehicleType: AIRPLANE, BUS -> Seyehatin hangi tür araç ile yapılacağını belirtmek için.  
  
    
    
Sabitler:  
MAX_INDIVIDUAL_TICKET = 5 -> Bir bireysel kullanıcının bir seyehat için alabileceği maksimum bilet sayısı.  
MAX_CORPORATE_TICKET = 20 -> Bir kurumsal kullanıcının bir seyehat için alabileceği maksimum bilet sayısı.  
MAX_AIRPLANE_PASSENGER = 189 -> Bir uçağın alabileceği maksimum yolcu sayısı.  
MAX_BUS_PASSENGER = 45 -> Bir otobüsün alabileceği maksimum yolcu sayısı.  
MAX_MALE_PASSENGER = 2 -> Bir bireysel kullanıcının tek bir siparişte bilet alabileceği maksimum erkek yolcu sayısı.  
  
    
### Projede Kullanılan Uygulamaların Port Değerleri ve Veritabanı - RabbitMQ Bağlantı Bilgileri

### Postman Kullanımı İçin Bilgiler


