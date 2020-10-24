package com.hihia.service.impl;

import com.hihia.dao.DishDao;
import com.hihia.domain.Dish;
import com.hihia.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dishService")
public class DishServiceImpl implements DishService {

    @Autowired
    private DishDao dishDao;

    @Override
    public List<Dish> getDishList() {
        return dishDao.getDishList();
    }

    @Override
    public List<Dish> queryDish(String query) {
        return dishDao.queryDish(query);
    }

    @Override
    public void modifyDish(Dish dish) {
        dishDao.modifyDish(dish);
    }

    @Override
    public void deleteDish(String dno) {
        dishDao.deleteDish(dno);
    }

    @Override
    public void createDish(Dish dish) {
        dishDao.createDish(dish);
    }
}
