package com.micro.srw.controller;

import com.micro.srw.bean.JsonResult;
import com.micro.srw.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @RequestMapping("/sendMail")
    public JsonResult<?> sendMail() {
        mailService.sendMail();
        return JsonResult.success();
    }

}
