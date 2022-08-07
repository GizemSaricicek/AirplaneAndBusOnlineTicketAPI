
# Patika.dev - Logo Bootcamp Final Project
## AirplaneAndBusOnlineTicketAPI

### Proje Kapsamı ve Açıklaması
• Online uçak ve otobüs bilet satış uygulaması için gerekli API’ler yazılmıştır. 
Buna ek olarak admin üzerinden yolculuk seferlerinin sisteme girilmesi ve silinmesi için de gerekli 
API’ler yazılmıştır.  
• Proje SpringBoot ile geliştirilmiştir. Veri tabanı olarak Postgresql kullanılmıştır. Servis katmanları için unit test yazılmıştır.
Ödeme, admin ve configuration için ana sistemden ayrı servisler bulunmaktadır. Asenkron iletişimi simgelemek adına RabbitMq'dan yararlanılmıştır. Senkron iletişim için ise Feign Kütüphanesi kullanılmıştır.

### Sistem Gereksinimleri
• Kullanıcılar sisteme kayıt ve login olabilmelidir.  
• Kullanıcı kayıt işleminden sonra mail gönderilmelidir.  
• Kullanıcı şifresi bir hashing algoritmasıyla database kaydedilmelidir.  
• Admin kullanıcı yeni sefer ekleyebilir, iptal edebilir, toplam bilet satışını, bu satıştan elde edilen toplam ücreti görebilir.  
• Kullanıcılar şehir bilgisi, taşıt türü(uçak & otobüs) veya tarih bilgisi ile tüm seferleri arayabilmelidir.  
• Bireysel kullanıcı aynı sefer için en fazla 5 bilet alabilir.  
• Kurumsal kullanıcı aynı sefer için en fazla 20 bilet alabilir.  
• Bireysel kullanıcı tek bir siparişte en fazla 2 erkek yolcu için bilet alabilir.  
• Satın alma işlemi başarılı ise işlem tamamlanmalı ve asenkron olarak bilet detayları kullanıcının telefona numarasına mesaj gönderilmelidir.  
• Mesaj ve mail gönderme işlemleri için sadece Database kayıt etme işlemi yapması yeterlidir. Fakat bu işlemler tek bir Servis(uygulama)   
üzerinden ve polimorfik davranış ile yapılmalıdır.  
• Kullancılar aldığı biletleri görebilmelidir.
  
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

**Enum:**  
• ConfigurationType: EMAIL, MESSAGE -> Bilgilendirmenin email mi mesaj mı olduğunu belirtmek için.  
• CurrencyType: TL, USD, EUR -> Ödeme şeklinin belirlenmesi için.  
• GenderType: FEMALE, MALE -> Kullanıcıların ve yolcuların cinsiyetlerinin belirlenmesi için.  
• PaymentType: CREDIT_CARD, EFT_TRANSFER -> Bilet ödeme şeklinin belirlenmesi için.  
• UserType: INDIVIDUAL, CORPORATE -> Kullanıcı tipinin bireysel mi kurumsal mı olduğunu belirtmek için.  
• VehicleType: AIRPLANE, BUS -> Seyehatin hangi tür araç ile yapılacağını belirtmek için.  
  
**Sabitler:**  
• MAX_INDIVIDUAL_TICKET = 5 -> Bir bireysel kullanıcının bir seyehat için alabileceği maksimum bilet sayısı.  
• MAX_CORPORATE_TICKET = 20 -> Bir kurumsal kullanıcının bir seyehat için alabileceği maksimum bilet sayısı.  
• MAX_AIRPLANE_PASSENGER = 189 -> Bir uçağın alabileceği maksimum yolcu sayısı.  
• MAX_BUS_PASSENGER = 45 -> Bir otobüsün alabileceği maksimum yolcu sayısı.  
• MAX_MALE_PASSENGER = 2 -> Bir bireysel kullanıcının tek bir siparişte bilet alabileceği maksimum erkek yolcu sayısı.  
  
    
### Projede Kullanılan Uygulamaların Port Değerleri ve Veritabanı - RabbitMQ Bağlantı Bilgileri

• Admin Service Port: 4043  
• Ticket Payment Port: 4042  
• Configuration Service Port: 4041  
• Airplane And Bus Online Ticket API port: 4040  
  
  • RabbitMq user: guest  
  • RabbitMq password: guest  
  
  • Postgresql user: postgres  
  • Postgresql password: postgrespw  
    
  • Admin Service'in kullandığı DB: online_ticket_service  
  • Ticket Payment Service'in kullandığı DB: ticket_payment  
  • Configuration Service'in kullandığı DB: ticket_config  
  • Airplane And Bus Online Ticket API'ın kullandığı DB: online_ticket_service  

### Postman Kullanımı İçin Bilgiler

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/16339239-e8b6a515-fd98-48d3-9400-98856844e8d8?action=collection%2Ffork&collection-url=entityId%3D16339239-e8b6a515-fd98-48d3-9400-98856844e8d8%26entityType%3Dcollection%26workspaceId%3D26d636c6-2fb3-4672-9cfc-e07036d55d1f)  
  
  **Airplane and bus ticket api**  
  • create new user = (POST) http://localhost:4040/users
    
  **Admin service**  
    
  • create new admin = (POST) http://localhost:4043/admins  
    
  body: {  
    "name": "gizem",  
    "surname":"sarıçiçek",  
    "email":"gizemS@gmail.com",  
    "password":"gizempw"  
  }  
    
  • login admin = (GET) http://localhost:4043/admins/login  
    
  body: {  
    "email":"admin@gmail.com",  
    "password":"adminpw"  
  }  
  
  • add new voyage = (POST) =  http://localhost:4043/voyages/{adminId}/create  
    
  body: {  
    "country": "muğla",  
    "departure":"ankara",  
    "voyageDate":"2019-11-09T11:44:44.797",  
    "amount":100.0,  
    "type":"AIRPLANE",  
    "currencyType":"TL"  
   }
     
  • get total amount of all tickets = (GET) http://localhost:4043/tickets/totalAmount  
    
  • get total amount of all tickets by currency type = (GET) http://localhost:4043/tickets/totalAmount/EUR  
    
  • get number of sold tickets = (GET) http://localhost:4043/tickets/soldTicket  
    
  • delete voyage = (PUT) = http://localhost:4043/voyages/{adminId}/delete/{voyageId}  
    
  • get all tickets = (GET) = http://localhost:4043/tickets  
      
  Not: Sistemin örnek çıktıları "Örnek Çıktı Dosyası" adli pdf'te de bulunmaktadır.
