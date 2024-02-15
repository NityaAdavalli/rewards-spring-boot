package com.retailer.rewards.resource;

import com.retailer.rewards.model.CustomerRewards;
import com.retailer.rewards.model.PurchaseDetail;
import com.retailer.rewards.service.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rewards")
@AllArgsConstructor
public class RewardController {

    private RewardService rewardService;

    @GetMapping("/calculate")
    public List<CustomerRewards> calculateRewards(@RequestBody List<PurchaseDetail> purchaseDetailList) throws Exception {
        return rewardService.calculateRewards(purchaseDetailList);
    }
}
