package com.micro.srw.jobhandler;

import com.micro.srw.service.MailService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 邮件执行器
 * @Author: songrenwei
 * @Date: 2020/10/21/15:58
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MailJobHandler {

    private final MailService mailService;

    @XxlJob("SendMailHandler")
    public void sendMailHandler() {
        mailService.sendMail();
        XxlJobHelper.handleSuccess();
    }

}
