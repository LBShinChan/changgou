package com.changgou.goods.feign;

import com.changgou.entity.Result;
import com.changgou.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "goods")
public interface SkuFeign {

    @GetMapping("/sku/spu/{spuId}")
    List<Sku> findSkuListBySpuId(@PathVariable("spuId") String spuId);

    @GetMapping("/sku/{id}")
    public Result<Sku> findById(@PathVariable String id);

    @PostMapping(value = "/sku/decr/count")
    public Result decrCount(@RequestParam("username") String username);

    @RequestMapping("/sku/resumeStockNum")
    public Result resumeStockNum(@RequestParam("skuId") String skuId, @RequestParam("num")Integer num);
}
