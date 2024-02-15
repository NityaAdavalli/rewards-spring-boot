package com.retailer.rewards.model;

import lombok.Data;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Data
public class CustomerRewards {

   private Long customerId;

   private Long totalRewards = 0l;

   private Map<Month,Long> monthlyRewards = new HashMap<>();

}
