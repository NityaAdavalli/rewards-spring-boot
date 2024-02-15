package com.retailer.rewards.service;

import com.retailer.rewards.model.CustomerRewards;
import com.retailer.rewards.model.PurchaseDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.Month;
import java.util.*;

@Service
public class RewardService {

    private BigDecimal primaryPurchaseLimit;

    private BigDecimal secondaryPurchaseLimit;

    private int rewardsForPrimaryLimit;

    private int rewardsForSecondaryLimit;


    public RewardService(@Value("${primary.purchase.limit}") BigDecimal primaryPurchaseLimit,
                         @Value("${secondary.purchase.limit}") BigDecimal secondaryPurchaseLimit,
                         @Value("${primary.purchase.rewards}") int rewardsForPrimaryLimit,
                         @Value("${secondary.purchase.rewards}") int rewardsForSecondaryLimit) {
        this.primaryPurchaseLimit = primaryPurchaseLimit;
        this.secondaryPurchaseLimit = secondaryPurchaseLimit;
        this.rewardsForPrimaryLimit = rewardsForPrimaryLimit;
        this.rewardsForSecondaryLimit = rewardsForSecondaryLimit;
    }

    public List<CustomerRewards> calculateRewards(List<PurchaseDetail> purchaseDetailList) throws Exception {
        if(CollectionUtils.isEmpty(purchaseDetailList))
            throw new RuntimeException("Purchase list is not provided");

        Map<Long, CustomerRewards> customerRewardsMap = new HashMap<>();

        for(PurchaseDetail detail : purchaseDetailList) {

            Long customerId = detail.customerId();
            Month month = detail.purchasedMonth();
            BigDecimal purchaseAmt = detail.purchaseAmt();

            // Calculate reward points for the Purchase
            long rewardsEarned = calculateRewardsbyAmount(purchaseAmt);

            CustomerRewards customerRewards = customerRewardsMap.computeIfAbsent(customerId, k -> new CustomerRewards());
            customerRewards.setCustomerId(customerId);
            customerRewards.setTotalRewards(customerRewards.getTotalRewards() + rewardsEarned);
            customerRewards.getMonthlyRewards().merge(month, rewardsEarned, Long::sum);
        }
        return new ArrayList<>(customerRewardsMap.values());
    }

    private long calculateRewardsbyAmount(BigDecimal purchaseAmt) {
        long points = 0;

        // Calculate points for dollars spent over $100
        if (purchaseAmt.compareTo(primaryPurchaseLimit) > 0) {
            points += rewardsForPrimaryLimit * (purchaseAmt.subtract(primaryPurchaseLimit).longValue());
        }

        // Calculate points for dollars spent over $50
        if (purchaseAmt.compareTo(secondaryPurchaseLimit) > 0) {
            points = points + (purchaseAmt.compareTo(primaryPurchaseLimit) < 0 ? purchaseAmt.subtract(secondaryPurchaseLimit) :
                    primaryPurchaseLimit.subtract(secondaryPurchaseLimit)).longValue() * rewardsForSecondaryLimit;
        }

        return points;
    }
}
