package com.hihia.controller;

import com.hihia.domain.Canteen;
import com.hihia.domain.Dish;
import com.hihia.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "菜品controller", tags = {"菜品信息模块"})
@RestController
@RequestMapping("/dish")
@CrossOrigin
public class DishController {

    @Autowired
    private DishService dishService;

    @ApiOperation(value = "获取菜品信息列表")
    @GetMapping(value = "/getDishList")
    @ResponseBody
    public Map<String, Object> getDishList() {
        List<Dish> list = dishService.getDishList();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("length", list.size());
        map.put("code", 0);
        map.put("data", data);
        map.put("msg", "获取成功");
        return map;
    }

    @ApiOperation(value = "模糊搜索菜品")
    @GetMapping(value = "/query/{query}")
    @ResponseBody
    public Map<String, Object> queryDish(@PathVariable(name = "query") String query) {
        List<Dish> list = dishService.queryDish(query);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", list);
        map.put("msg", "查询成功");
        return map;
    }

    @ApiOperation(value = "修改菜品信息")
    @PostMapping(value = "/modify")
    @ResponseBody
    public Map<String, Object> modifyDish(@RequestBody Dish dish) {
        dishService.modifyDish(dish);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "修改成功");
        return map;
    }

    @ApiOperation(value = "删除菜品信息")
    @GetMapping(value = "/delete/{dno}")
    @ResponseBody
    public Map<String, Object> deleteCanteen(@PathVariable(name = "dno") String dno) {
        dishService.deleteDish(dno);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "删除成功");
        return map;
    }

    @ApiOperation(value = "创建新的菜品信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> createDish(@RequestBody Dish dish) {
        dishService.createDish(dish);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", "");
        map.put("msg", "创建成功");
        return map;
    }
}
