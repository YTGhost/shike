package com.hihia.service.impl;

import com.hihia.dao.AccountDao;
import com.hihia.domain.Account;
import com.hihia.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 邓梁
 * @date 2020/7/7 10:30
 * @email 18221221@bjtu.edu.cn
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> login(String username) {
        return accountDao.login(username);
    }
}
