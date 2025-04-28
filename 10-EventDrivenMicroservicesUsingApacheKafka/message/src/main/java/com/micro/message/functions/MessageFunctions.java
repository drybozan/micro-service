package com.micro.message.functions;

import com.micro.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    /*
   Function<T, R> , T girdi, R ise çıkışı gösterir. mail ve sms gönderme iş kodlarını yazmıyoruz şimdilik.
   accounts mikroservisi,mesaj aracıma bir mesaj gönderecek ve mesaj aracım da mail işlevlerinden birini çağıracak.
   Bu mail için girdi AccountsMsgDto olacaktır. Arka planda sms işlevini de çağırmak istediğimde  mail ve sms gibi bu iki işlevi tek bir
   mantıksal birim olarak oluşturacağım. sms işlev, AccountsMsgDto i duyduğu için aynı nesneyi mailden dönen bir tür olarak döndürmeye çalışıyorum.
   mailin döndürdüğü nesne sms için girdi görevi görecektir. account miksroservisine hesap numarası ile dönüş sağlayarak asenkron iletişimi
   tamamlamış oluyorum. Bu sayede account hesap numarası bilgisine göre veritabaında mail sms gönderildi şeklinde güncelleme yapabilir.
     */
    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email() {
        return accountsMsgDto -> {
            log.info("Sending email with the details : " + accountsMsgDto.toString());
            return accountsMsgDto;
        };
    }

    // emailsms-out-0 ismindeki exchangeye mesajı bu fonksiyon return edecektir. bu mesaj ilgili servislere akatarılacak.
    @Bean
    public Function<AccountsMsgDto, Long> sms() {
        return accountsMsgDto -> {
            log.info("Sending sms with the details : " + accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }

}
