package com.hihia.dao;

import com.hihia.domain.Canteen;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CanteenDao {

    /**
     * 获取食堂信息列表
     * @return
     */
    @Select("select * from canteen")
    public List<Canteen> getCanteenList();

    /**
     * 检测食堂是否存在
     * @return
     */
    @Select("select * from canteen where cname = #{cname}")
    public List<Canteen> checkCname(String cname);

    /**
     * 模糊搜索食堂
     * @return
     */
    @Select("Select * from canteen where cname REGEXP #{query}")
    public List<Canteen> queryCanteen(String query);

    /**
     * 修改食堂信息
     */
    @Update("update canteen set cname = #{cname} where cno = #{cno}")
    public void modifyCanteen(Canteen canteen);

    /**
     * 删除食堂信息
     */
    @Delete("delete from canteen where cno = #{cno}")
    public void deleteCanteen(String cno);

    /**
     * 创建食堂信息
     */
    @Insert("insert into canteen (cname) values (#{cname})")
    public void createCanteen(Canteen canteen);
}
