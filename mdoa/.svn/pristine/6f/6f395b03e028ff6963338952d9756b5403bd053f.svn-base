package com.mdoa.personnel.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdoa.base.controller.BaseController;
import com.mdoa.personnel.service.ClockService;

@RestController
@RequestMapping("/clock")
@Component
@Lazy(value=false)
public class ClockController extends BaseController{
	
	@Autowired
	private ClockService clockService;
	
	
	@Scheduled(cron = "0 1 0 * * ?")//每天上午零点零一分触发 处理前一天的打卡记录
	public void autoDeal() throws Exception{
		clockService.autoDeal();
	}
	
	@Scheduled(cron = "0 25 15 * * ?")//每天上午零点零一分触发 处理前一天的打卡记录
	public void test() throws Exception{
		clockService.test();
	}
}
