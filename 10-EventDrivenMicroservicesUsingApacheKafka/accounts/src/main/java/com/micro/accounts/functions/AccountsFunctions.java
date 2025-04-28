package com.micro.accounts.functions;

import com.micro.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/*
 message mikroservisi,accounts mikroservisi arasında mesaj alma islevlerini yapacak.
 */
@Configuration
public class AccountsFunctions {

    private static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);

    /**
     * rabbit mq nün döndürdüğü mesajı Long tipinde alıyor. rabbit mq sms ismindeki fonksiyonun çıktısı Long dur.
     * Bean definition for updating the communication status of an account.
     *
     * This Consumer takes an account number and updates its communication status
     * by using the provided IAccountsService.
     *
     * @param accountsService the service used to update the communication status
     * @return a Consumer that updates communication status for a given account number
     */
    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService) {
        return accountNumber -> {
            log.info("Updating Communication status for the account number : " + accountNumber.toString());
            accountsService.updateCommunicationStatus(accountNumber);
        };
    }

}
