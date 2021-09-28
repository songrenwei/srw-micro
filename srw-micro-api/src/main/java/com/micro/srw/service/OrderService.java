package com.micro.srw.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.micro.srw.domain.Order;
import com.micro.srw.mapper.OrderMapper;
import com.micro.srw.util.CodeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/9/27 11:16
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    public List<Order> listAll() {
        return orderMapper.selectList(null);
    }

    public Page<Order> listByPage(int pageNum, int pageSize) {
        Page<Order> page = new Page<>(pageNum, pageSize);
        return orderMapper.selectPage(page, null);
    }

    public Order get(Long id) {
        LambdaQueryWrapper<Order> eq = new QueryWrapper<Order>().lambda()
                .eq(Order::getId, id);
        return orderMapper.selectOne(eq);
    }

    public Boolean create(Order order) {
        order.setCode(CodeUtils.createCodeByTime(""));
        order.setCreateBy((String)StpUtil.getSession().get("user"));
        orderMapper.insert(order);
        return Boolean.TRUE;
    }

    public Boolean update(Order order) {
        Order order1 = orderMapper.selectById(order.getId());
        order1.setName(order.getName());
        orderMapper.updateById(order1);
        return Boolean.TRUE;
    }

    public Boolean delete(Long id) {
        orderMapper.deleteById(id);
        return Boolean.TRUE;
    }

}
