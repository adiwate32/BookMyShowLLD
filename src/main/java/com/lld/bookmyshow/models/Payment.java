package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(
        name = "payments"
)
public class Payment extends BaseModel{
    private int amount;
    private String referenceNo;
    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider paymentProvider;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    private Date timeStamp;
    @ManyToOne
    private Booking booking;
}
