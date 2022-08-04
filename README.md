# Patika.dev - Logo Bootcamp Final Project
## AirplaneAndBusOnlineTicketAPI

### Proje Kapsamı ve Açıklaması
• Online uçak ve otobüs bilet satış uygulaması için gerekli API’ler yazılmıştır. 
Buna ek olarak admin üzerinden yolculuk seferlerinin sisteme girilmesi ve silinmesi için de gerekli 
API’ler yazılmıştır.  
• Proje SpringBoot ile geliştirilmiştir. Veri tabanı olarak Postgresql kullanılmıştır. Servisler için unit test yazılmıştır.
Ödeme, admin ve configuration için ana sistemden ayrı servisler bulunmaktadır. Asenkron iletişimi simgelemek adına RabbitMq'dan yararlanılmıştır. Senkron iletişim için ise Feign Kütüphanesi kullanılmıştır.

### Sistem Kabulleri
• Kullanıcılar bireysel ve kurumsal olabilir.  
• Uçak yolcu kapasitesi: 189  
• Otobüs yolcu kapasitesi: 45  
• Ödeme şekli sadece Kredi kartı ve Havale / EFT olabilir.  
• Mesaj ve Mail gönderim işlemleri Asenkron olmalıdır.  
• Ödeme Servisi işlemleri Senkron olmalıdır.  
  
### Sistem Şeması

![image](https://user-images.githubusercontent.com/97917750/182848997-847e306a-d68d-4a15-a13f-1198d0a34d9b.png)

### Sistem Modelleri ve İlişkileri

Not: Voyage modelinde status sütunu sistemden silinip silinmediğini göstermektedir.  
Voyage eklendiğinde status=true, voyage silindiğinde status=false olmaktadır.  

### Projede kullanılan Enum Değerler ve Sabit Değerler

Enum:  
• ConfigurationType: EMAIL, MESSAGE -> Bilgilendirmenin email mi mesaj mı olduğunu belirtmek için.  
• CurrencyType: TL, USD, EUR -> Ödeme şeklinin belirlenmesi için.  
• GenderType: FEMALE, MALE -> Kullanıcıların ve yolcuların cinsiyetlerinin belirlenmesi için.  
• PaymentType: CREDIT_CARD, EFT_TRANSFER -> Bilet ödeme şeklinin belirlenmesi için.  
• UserType: INDIVIDUAL, CORPORATE -> Kullanıcı tipinin bireysel mi kurumsal mı olduğunu belirtmek için.  
• VehicleType: AIRPLANE, BUS -> Seyehatin hangi tür araç ile yapılacağını belirtmek için.  
  
    
    
Sabitler:  
• MAX_INDIVIDUAL_TICKET = 5 -> Bir bireysel kullanıcının bir seyehat için alabileceği maksimum bilet sayısı.  
• MAX_CORPORATE_TICKET = 20 -> Bir kurumsal kullanıcının bir seyehat için alabileceği maksimum bilet sayısı.  
• MAX_AIRPLANE_PASSENGER = 189 -> Bir uçağın alabileceği maksimum yolcu sayısı.  
• MAX_BUS_PASSENGER = 45 -> Bir otobüsün alabileceği maksimum yolcu sayısı.  
• MAX_MALE_PASSENGER = 2 -> Bir bireysel kullanıcının tek bir siparişte bilet alabileceği maksimum erkek yolcu sayısı.  
  
    
### Projede Kullanılan Uygulamaların Port Değerleri ve Veritabanı - RabbitMQ Bağlantı Bilgileri

• Admin Service Port:  
• Ticket Payment Port:  
• Configuration Service Port:  
• Airplane And Bus Online Ticket API port: 4040  
  
  • RabbitMq port:  
  • RabbitMq user: guest  
  • RabbitMq password: guest  
  
  • Postgresql port:  
  • Postgresql user: postgres  
  • Postgresql password: postgrespw  


### Postman Kullanımı İçin Bilgiler


