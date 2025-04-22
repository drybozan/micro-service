package com.micro.accounts.service.client;

import com.micro.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{

    //istemci uygulamalara veya ağ geçitine runtime fırlatmak yerine,
    // hata durumunda bu servislerden yanıt alınamadığında null döndür
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
