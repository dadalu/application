package com.wxc.spring.aop;

import com.wxc.spring.aop.annotation.IdempotenceAnnotation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aop/test")
public class TestController {
    @GetMapping("get/{id}")
    public String aopTest(@PathVariable("id") Integer id) {
        return "拦截了,id = "+id;
    }

    //因为设置了默认值所以不用设置参数就会使用默认值
    /*@IdempotenceAnnotation
    @ApiOperation(value = "收藏和取消收藏", notes = "参数>parkId")
    @PostMapping(value = "/app/favoriteAndUnFavorite")
    public ObjectResult<Object> favoriteAndUnFavorite(@RequestBody SpUserCollectionPark userCollectionPark) {
        userCollectionPark.setUserId(getCurrentUserId());
        spUserCollectionParkService.favoriteAndUnFavorite(userCollectionPark);
        return ObjectResultUtil.success("操作成功");
    }*/


}
