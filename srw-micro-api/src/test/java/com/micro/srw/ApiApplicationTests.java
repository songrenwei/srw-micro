package com.micro.srw;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.micro.srw.entity.Order;
import com.micro.srw.entity.User;
import com.micro.srw.enums.SexEnum;
import com.micro.srw.mapper.OrderMapper;
import com.micro.srw.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderMapper orderMapper;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("奥特曼");
        user.setPassword("123456");
        user.setSex(SexEnum.FEMALE);
        userMapper.insert(user);
    }

    @Test
    public void test2() {
        LambdaQueryWrapper<Order> eq = Wrappers.<Order>lambdaQuery().eq(Order::getId, 5);
        Order order = orderMapper.selectOne(eq);
        System.out.println(order);

        order.setName("梨子");
        orderMapper.updateById(order);
    }

    @Test
    public void test3() {
        orderMapper.deleteById(6);
    }

    @Test
    public void test4() {
        // 单纯只更新某一个字段
        ChainWrappers.lambdaUpdateChain(orderMapper)
                .set(Order::getName, "西瓜")
                .eq(Order::getId, 4)
                .update();
    }

}
