package com.micro.srw.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * XxlJob开发示例（Bean模式）
 */
@Slf4j
@Component
public class SampleXxlJob {

    @XxlJob("demoJobHandler")
    public void demoJobHandler() {
        log.info("first task ...");
        XxlJobHelper.handleSuccess();
    }

}
