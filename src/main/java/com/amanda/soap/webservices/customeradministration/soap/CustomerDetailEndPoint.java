package com.amanda.soap.webservices.customeradministration.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.amanda.soap.webservices.customeradministration.service.CustomerDetailService;

import br.com.amandareis.CustomerDetail;
import br.com.amandareis.GetCustomerDetailRequest;
import br.com.amandareis.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {
	
	@Autowired
	CustomerDetailService service;
	
	@PayloadRoot(namespace="http://amandareis.com.br", localPart="GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailrequest(@RequestPayload GetCustomerDetailRequest req) {
		GetCustomerDetailResponse response = new GetCustomerDetailResponse();
		CustomerDetail customerDetail= new CustomerDetail();		
		customerDetail.setId(1);
		customerDetail.setName("Bob");
		customerDetail.setPhone("999999999");
		customerDetail.setEmail("bob@gmail.com");
		response.setCustomerDetail(customerDetail);
		return response;
	}

}
