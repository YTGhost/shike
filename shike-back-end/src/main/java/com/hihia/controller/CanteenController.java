package com.hihia.controller;

import com.hihia.domain.Canteen;
import com.hihia.service.CanteenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "食堂controller", tags = {"食堂信息模块"})
@RestController
@RequestMapping("/canteen")
@CrossOrigin
public class CanteenController {

    @Autowired
    private CanteenService canteenService;

    @ApiOperation(value = "获取食堂信息列表")
    @GetMapping(value = "/getCanteenList")
    @ResponseBody
    public Map<String, Object> getCanteenList() {
        List<Canteen> list = canteenService.getCanteenList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", list.size());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "检测食堂是否存在")
    @GetMapping(value = "/checkCname/{cname}")
    @ResponseBody
    public Map<String, Object> checkCname(@PathVariable(name = "cname") String cname) {
        List<Canteen> list = canteenService.checkCname(cname);
        Map<String, Object> map = new HashMap<>();
        if(list.isEmpty()) {
            map.put("code", 0);
        } else {
            map.put("code", 1);
        }
        map.put("data", "");
        map.put("msg", "检测成功");
        return map;
    }

    @ApiOperation(value = "模糊搜索食堂")
    @GetMapping(value = "/query/{query}")
    @ResponseBody
    public Map<String, Object> queryCanteen(@PathVariable(name = "query") String query) {
        List<Canteen> list = canteenService.queryCanteen(query);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", list);
        map.put("msg", "查询成功");
        return map;
    }

    @ApiOperation(value = "修改食堂信息")
    @PostMapping(value = "/modify")
    @ResponseBody
    public Map<String, Object> modifyCanteen(@RequestBody Canteen canteen) {
        canteenService.modifyCanteen(canteen);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "修改成功");
        return map;
    }

    @ApiOperation(value = "删除食堂信息")
    @GetMapping(value = "/delete/{cno}")
    @ResponseBody
    public Map<String, Object> deleteCanteen(@PathVariable(name = "cno") String cno) {
        canteenService.deleteCanteen(cno);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "删除成功");
        return map;
    }

    @ApiOperation(value = "创建新的食堂信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> createStudent(@RequestBody Canteen canteen) {
        canteenService.createCanteen(canteen);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "创建成功");
        return map;
    }

}
