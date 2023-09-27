package com.ms.reloff.service;

import static com.ms.reloff.utils.ConstantUtil.LOG_END;
import static com.ms.reloff.utils.ConstantUtil.LOG_START;

import org.springframework.stereotype.Service;

import com.ms.reloff.dto.DashboardDataDto;

import lombok.extern.slf4j.Slf4j;

/**
 * DashboardServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

	
	
	@Override
	public DashboardDataDto getDashboardWidgetData() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return null;
	}

}
