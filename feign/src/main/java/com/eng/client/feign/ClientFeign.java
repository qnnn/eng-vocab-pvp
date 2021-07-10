package com.eng.client.feign;

import com.eng.client.config.FeignConfig;
import com.eng.client.entity.EasyWord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "resource-server",path = "resource",configuration = {FeignConfig.class})
public interface ClientFeign {

    @RequestMapping("/getCurrentUser")
    String getCurrentUser();


    @GetMapping("getCompetitor")
    public List<EasyWord> getCompetitorWord();

    @GetMapping("getCompetitorChinese")
    public List<String> getCompetitorChinese();

}
