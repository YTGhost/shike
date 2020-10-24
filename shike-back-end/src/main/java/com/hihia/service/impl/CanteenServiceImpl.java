package com.hihia.service.impl;

import com.hihia.dao.CanteenDao;
import com.hihia.domain.Canteen;
import com.hihia.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("canteenService")
public class CanteenServiceImpl implements CanteenService {

    @Autowired
    private CanteenDao canteenDao;

    @Override
    public List<Canteen> getCanteenList() {
        return canteenDao.getCanteenList();
    }

    @Override
    public List<Canteen> checkCname(String cname) {
        return canteenDao.checkCname(cname);
    }

    @Override
    public List<Canteen> queryCanteen(String query) {
        return canteenDao.queryCanteen(query);
    }

    @Override
    public void modifyCanteen(Canteen canteen) {
        canteenDao.modifyCanteen(canteen);
    }

    @Override
    public void deleteCanteen(String cno) {
        canteenDao.deleteCanteen(cno);
    }

    @Override
    public void createCanteen(Canteen canteen) {
        canteenDao.createCanteen(canteen);
    }
}
