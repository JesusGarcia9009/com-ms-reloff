/**
 * 
 */
package com.ms.reloff.utils;

import static com.ms.reloff.utils.ConstantUtil.EXCEPTION_DEPENDENCY;
import static com.ms.reloff.utils.ConstantUtil.LOG_END;
import static com.ms.reloff.utils.ConstantUtil.LOG_START;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Utils {
	
//	@Autowired
//	public PlateRepository plateRepository;
	
	String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	

	private Utils() {
	}

	public <T> T validateOpt(Optional<T> opt) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		if (!opt.isPresent()) {
			throw new Exception(EXCEPTION_DEPENDENCY);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return opt.get();
	}
	
	public String getStringDate(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
		return (localDate.getDayOfMonth() + " de "+ MES[localDate.getMonthValue()-1] +" de " + localDate.getYear());
	}
	
	public static String printCurlEquivalent(String url, HttpMethod method, HttpEntity<?> requestEntity) {
		StringBuilder curlCommand = new StringBuilder("curl");

        // Agregar la URL
        curlCommand.append(" --location '").append(url).append("' \\").append(System.lineSeparator());

        // Agregar el método HTTP
        curlCommand.append(" --request ").append(method.toString()).append(" \\").append(System.lineSeparator());

        // Agregar las cabeceras
        HttpHeaders headers = requestEntity.getHeaders();
        for (String key : headers.keySet()) {
            curlCommand.append(" --header '").append(key).append(": ").append(headers.getFirst(key)).append("' \\").append(System.lineSeparator());
        }

        // Agregar los parámetros de datos codificados de formulario
        MultiValueMap<String, String> requestBody = (MultiValueMap<String, String>) requestEntity.getBody();
        if (requestBody != null) {
            for (String key : requestBody.keySet()) {
                for (String value : requestBody.get(key)) {
                    curlCommand.append(" --data-urlencode '").append(key).append("=").append(value).append("' \\").append(System.lineSeparator());
                }
            }
        }

        return curlCommand.toString();
    }
	
	
	
	
	
	
	
	

	

}
