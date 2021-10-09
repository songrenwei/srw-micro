package com.micro.srw.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.micro.srw.annotation.Log;
import com.micro.srw.bean.JsonResult;
import com.micro.srw.entity.Order;
import com.micro.srw.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/9/27 11:15
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/listAll")
    public JsonResult<List<Order>> listAll() {
        return JsonResult.success(orderService.listAll());
    }

    @GetMapping("/listByPage")
    public JsonResult<Page<Order>> listByPage(int pageNum, int pageSize) {
        return JsonResult.success(orderService.listByPage(pageNum, pageSize));
    }

    @Log
    @GetMapping("/get/{id}")
    public JsonResult<Order> get(@PathVariable("id") Long id) {
        return JsonResult.success(orderService.get(id));
    }

    @Log
    @PostMapping("/create")
    public JsonResult<Boolean> create(@RequestBody Order brand) {
        return JsonResult.success(orderService.create(brand));
    }

    @Log
    @PostMapping("/update")
    public JsonResult<Boolean> update(@RequestBody Order brand) {
        return JsonResult.success(orderService.update(brand));
    }

    @Log
    @PostMapping("/delete")
    public JsonResult<Boolean> delete(Long id) {
        return JsonResult.success(orderService.delete(id));
    }

}
