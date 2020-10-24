package com.hihia.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来测试的Controller
 */

@Api(value = "测试Controller", tags = {"测试模块"})
@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @ApiOperation(value = "负载均衡测试")
    @GetMapping(value = "/outputHost")
    @ResponseBody
    public Map<String, Object> Test() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "这里是tomcat-8888");
        map.put("msg", "测试成功");
        return map;
    }
}
