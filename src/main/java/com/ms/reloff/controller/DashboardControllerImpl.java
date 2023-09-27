package com.ms.reloff.controller;

import static com.ms.reloff.utils.ConstantUtil.LOG_END;
import static com.ms.reloff.utils.ConstantUtil.LOG_START;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.reloff.dto.DashboardDataDto;
import com.ms.reloff.service.DashboardService;
import com.ms.reloff.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${reloff.base-uri}/dashboard")
public class DashboardControllerImpl implements DashboardController {
	
	/**
	 * Global variables
	 */
	private final DashboardService dashboardService;
	
	/**
     * Class constructor with @autowire annotation
     * 
     * @param UsersService @see {@link UsersService}
     */
    public DashboardControllerImpl(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

	@Override
	@GetMapping("/widget")
	public ResponseEntity<DashboardDataDto> getDashboardWidgetData() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		DashboardDataDto response = dashboardService.getDashboardWidgetData();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return ResponseEntity.ok(response);
	}


	
	
}
