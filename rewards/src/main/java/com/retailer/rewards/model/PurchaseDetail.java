package com.retailer.rewards.model;

import java.math.BigDecimal;
import java.time.Month;


public record PurchaseDetail(BigDecimal purchaseAmt, Long customerId, Month purchasedMonth)  {

}
