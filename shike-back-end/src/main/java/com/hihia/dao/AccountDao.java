package com.hihia.dao;

/**
 * @author 邓梁
 * @date 2020/7/7 10:27
 * @email 18221221@bjtu.edu.cn
 */

import com.hihia.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户dao接口
 */
@Repository
public interface AccountDao {

    /**
     * 登录
     * @return
     */
    @Select("select password from account where username = #{username}")
    public List<Account> login(String username);
}
