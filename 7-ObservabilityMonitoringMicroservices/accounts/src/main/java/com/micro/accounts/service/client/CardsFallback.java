package com.micro.accounts.service.client;

import com.micro.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{

    //istemci uygulamalara veya ağ geçitine runtime fırlatmak yerine,
    // hata durumunda bu servislerden yanıt alınamadığında null döndür

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }

}
