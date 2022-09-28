package com.jing.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jing.mybatis_plus.dao.UserDao;
import com.jing.mybatis_plus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusApplicationTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test1(){
        List<User> userList = userDao.selectList(null);
        userList.forEach(System.out::println);
    }
    @Test
    public void insertTest(){
        User user = new User();
        user.setName("Tom");
        user.setAge(14);
        user.setEmail("123456@qq.com");
        int result = userDao.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    public void updateTest(){
        User user = new User();
        user.setId(1L);
        user.setAge(33);
        userDao.updateById(user);
    }

    @Test
    public void test2(){
        User user = new User();
//        user.setName("李易峰");
//        user.setAge(19);
//        user.setEmail("123345@qq.com");
//        userDao.insert(user);
//        System.out.println(user);

        user = new User();
        user.setId(1575095600958984194L);
        user.setName("凡凡");
        userDao.updateById(user);
    }

    @Test //乐观锁测试
    public void test3(){
        User user = new User();
        user.setName("张三");
        user.setAge(14);
        user.setEmail("123456@qq.com");
        userDao.insert(user);
    }

    @Test
    public void test4(){
        //根据Id查找到用户信息
        User user = userDao.selectById(1575099664786546689L);

        //修改用户信息
        user.setName("张三");
        user.setAge(20);

        //修改用户信息
        userDao.updateById(user);
    }

    @Test
    public void test5(){
        List<User> userList = userDao.selectBatchIds(Arrays.asList(1, 2, 3));
        userList.forEach(System.out::println);
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",18);
        userList = userDao.selectByMap(map);
        userList.forEach(System.out::println);
    }


    @Test //分页查询
    public void test6(){
        //分页参数,每页显示多少条数据,显示第几页
        Page<User> page = new Page<>(1,5);
        page = userDao.selectPage(page, null);
        //查询的数据列表
        List<User> userList = page.getRecords();
        System.out.println("userList = " + userList);
        //总页数
        long pages = page.getPages();
        System.out.println("pages = " + pages);
        //当前第几页
        long current = page.getCurrent();
        System.out.println("current = " + current);
        //每页显示几条
        long size = page.getSize();
        System.out.println("size = " + size);
        //总记录数
        long total = page.getTotal();
        System.out.println("total = " + total);
        //是否有下一页
        boolean next = page.hasNext();
        System.out.println("next = " + next);
        //是否有上一页
        boolean previous = page.hasPrevious();
        System.out.println("previous = " + previous);
    }


    //物理删除
    @Test
    public void test7(){
        int result = userDao.deleteById(1575054374322888705L);
        System.out.println("result = " + result);

        //批量删除

        System.out.println(userDao.deleteBatchIds(Arrays.asList(6, 7)));

        Map<String,Object> map = new HashMap<>();
        map.put("name","1");
        map.put("age",18);
        userDao.deleteByMap(map);
    }

    //逻辑删除
    @Test
    public void test8(){
        System.out.println(userDao.deleteById(1L));
    }

    //查询条件
    @Test
    public void test9(){
        //查询条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","a");
        userDao.selectList(wrapper).forEach(System.out::println);
    }


}