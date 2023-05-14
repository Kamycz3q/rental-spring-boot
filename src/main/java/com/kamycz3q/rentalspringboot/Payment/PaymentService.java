package com.kamycz3q.rentalspringboot.Payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final String JAKIES_TAM_POLE = "jakas wartosc";

    public String pay(Payment payment) {
        return JAKIES_TAM_POLE;
    }
}
