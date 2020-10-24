package com.hihia.dao;

import com.hihia.domain.Dish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishDao {

    /**
     * 获取菜品信息列表
     * @return
     */
    @Select("select dish.dno, dish.dname, dish.cno, canteen.cname, dish.price from (dish left join canteen on canteen.cno = dish.cno)")
    public List<Dish> getDishList();

    /**
     * 模糊搜索菜品
     * @return
     */
    @Select("Select dish.dno, dish.dname, dish.cno, canteen.cname, dish.price from (dish left join canteen on dish.dname REGEXP #{query} and canteen.cno = dish.cno)")
    public List<Dish> queryDish(String query);

    /**
     * 修改菜品信息
     */
    @Update("update dish set dname = #{dname}, cno = #{cno}, price = #{price} where dno = #{dno}")
    public void modifyDish(Dish dish);

    /**
     * 删除菜品信息
     */
    @Delete("delete from dish where dno = #{dno}")
    public void deleteDish(String dno);

    /**
     * 创建菜品信息
     */
    @Insert("insert into dish (dname, cno, price) values (#{dname}, #{cno}, #{price})")
    public void createDish(Dish dish);
}
