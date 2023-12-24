package com.travelAgency.travelAgency.common.filter;

import java.io.IOException;
import java.util.Enumeration;

import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogerFilter implements Filter {



	// 리퀘스트를 servlet 전에 읽어버리면 그 후로는 내용을 읽지 못하기때문에
	// 캐싱할 수 있는 레퍼클래스로 설정해야한다.
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {

		ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest)request);
		ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse)response);


		chain.doFilter(req,res);

		// request 정보
		Enumeration<String> headerNames = req.getHeaderNames();
		StringBuilder headerValues = new StringBuilder();

		headerNames.asIterator().forEachRemaining(headerKey ->{
			String headerValue = req.getHeader(headerKey);

			headerValues
				.append(headerKey)
				.append(":")
				.append(headerValue)
				.append(" , ");
		});

		String requestBody = new String(req.getContentAsByteArray());
		String uri = req.getRequestURI();
		String method = req.getMethod();

		log.info(">>>>>>>>>>>>>>>>>>>>>>>> "
				+ "URI : {}" + "method : {}"
				+ "header : {}, body: {}"
			,uri, method, headerValues, requestBody);


		//response 정보
		StringBuilder responseHeaderValues = new StringBuilder();

		res.getHeaderNames().forEach(
			headerKey ->{
				String headerValue = res.getHeader(headerKey);

				responseHeaderValues
					.append(headerKey)
					.append(":")
					.append(headerValue)
					.append(" , ");
			}
		);

		String responseBody = new String(res.getContentAsByteArray());

		log.info(">>>>>>>>>>>>>>>>>>>>>>>> "
				+ "URI : {}" + "method : {}"
				+ "header : {}, body: {}"
			,uri, method, responseHeaderValues, responseBody);

		res.copyBodyToResponse();
	}
}
