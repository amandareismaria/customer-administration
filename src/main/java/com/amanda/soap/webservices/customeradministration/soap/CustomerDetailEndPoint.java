package com.amanda.soap.webservices.customeradministration.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.amanda.soap.webservices.customeradministration.bean.Customer;
import com.amanda.soap.webservices.customeradministration.service.CustomerDetailService;

import br.com.amandareis.CustomerDetail;
import br.com.amandareis.GetAllCustomerDetailRequest;
import br.com.amandareis.GetAllCustomerDetailResponse;
import br.com.amandareis.GetCustomerDetailRequest;
import br.com.amandareis.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {

	@Autowired
	CustomerDetailService service;

	@PayloadRoot(namespace = "http://amandareis.com.br", localPart = "GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailrequest(@RequestPayload GetCustomerDetailRequest req)
			throws Exception {
		Customer customer = service.findById(req.getId());
		if (customer == null) {
			throw new Exception("Invalid Customer id" + req.getId());
		}
		return convertToGetCustomerDetailResponse(customer);
	}

	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();
		resp.setCustomerDetail(convertToGetCustomerDetailResponse(customer));
		return resp;

	}

	private CustomerDetail convertToCustomerDetails(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());
		return customerDetail;
	}

	@PayloadRoot(namespace = "http://amandareis.com.br", localPart = "GetAllCustomerDetailRequest")
	@ResponsePayload
	public GetAllCustomerDetailResponse processGetAllCustomerDetailResponse(
			@RequestPayload GetAllCustomerDetailRequest req) {
		List<Customer> customers = service.findAll();
		return convertToGetAllCustomerDetailResponse(customers);

	}

	private GetAllCustomerDetailResponse convertToGetAllCustomerDetailResponse(List<Customer> customers) {
		GetAllCustomerDetailResponse resp = new GetAllCustomerDetailResponse();
		customers.stream().forEach(c -> resp.getCustomerDetail().add(convertToCustomerDetails(c)));
		return resp;
	}

}
