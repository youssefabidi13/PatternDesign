package com.youssefabidi.ecommerce.dto;

import lombok.Data;
import lombok.NonNull;

//use this class to send back a java object as json
@Data
public class PurchaseResponse {

    @NonNull
    private  final String orderTrackingNumber;
}
