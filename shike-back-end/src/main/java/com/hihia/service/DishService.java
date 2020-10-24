package com.hihia.service;

import com.hihia.domain.Dish;

import java.util.List;

public interface DishService {

    /**
     * 查询所有菜品信息
     * @return
     */
    public List<Dish> getDishList();

    /**
     * 模糊搜索菜品
     * @return
     */
    public List<Dish> queryDish(String query);

    /**
     * 修改菜品信息
     */
    public void modifyDish(Dish dish);

    /**
     * 删除菜品信息
     */
    public void deleteDish(String dno);

    /**
     * 创建菜品信息
     */
    public void createDish(Dish dish);
}
