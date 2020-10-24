package com.hihia.service;

import com.hihia.domain.Canteen;

import java.util.List;

public interface CanteenService {

    /**
     * 查询所有食堂信息
     * @return
     */
    public List<Canteen> getCanteenList();

    /**
     * 检测食堂是否存在
     * @return
     */
    public List<Canteen> checkCname(String cname);

    /**
     * 模糊搜索食堂
     * @return
     */
    public List<Canteen> queryCanteen(String query);

    /**
     * 修改食堂信息
     */
    public void modifyCanteen(Canteen canteen);

    /**
     * 删除食堂信息
     */
    public void deleteCanteen(String cno);

    /**
     * 创建食堂信息
     */
    public void createCanteen(Canteen canteen);
}
