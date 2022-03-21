package com.amanda.soap.webservices.customeradministration.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.amandareis.CustomerDetail;
import br.com.amandareis.GetCustomerDetailRequest;
import br.com.amandareis.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {
	
	@PayloadRoot(namespace="http://amandareis.com.br", localPart="GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailrequest(@RequestPayload GetCustomerDetailRequest requisicao) {
		GetCustomerDetailResponse response = new GetCustomerDetailResponse();
		CustomerDetail customerDetail= new CustomerDetail();		
		customerDetail.setId(1);
		customerDetail.setName("Bob");
		customerDetail.setPhone("999999999");
		customerDetail.setEmail("bob@gmail.com");
		return response;
	}

}
