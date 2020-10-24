package com.hihia.controller;

/**
 * @author 邓梁
 * @date 2020/7/7 10:32
 * @email 18221221@bjtu.edu.cn
 */

import com.hihia.domain.Account;
import com.hihia.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户web
 */

@Api(value = "用户账户controller", tags= {"用户账号模块"})
@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> Login(@RequestBody Account account) {
        List<Account> list = accountService.login(account.getUsername());
        Map<String, Object> map = new HashMap<>();
        if(list.isEmpty()) {
            map.put("code", 1);
            map.put("data", "");
            map.put("msg", "未找到用户名");
        } else {
            Account item = list.get(0);
            if(item.getPassword().equals(account.getPassword())) {
                map.put("code", 0);
                map.put("data", "");
                map.put("msg", "登录成功");
            } else {
                map.put("code", 2);
                map.put("data", "");
                map.put("msg", "密码错误");
            }
        }
        return map;
    }
}
