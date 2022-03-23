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
import br.com.amandareis.DeleteCustomerRequest;
import br.com.amandareis.DeleteCustomerResponse;
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
	public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req)
			throws Exception {
		Customer customer = service.findById(req.getId());
		if (customer == null) {
			throw new Exception("Invalid Customer id " + req.getId());
		}
		return convertToGetCustomerDetailResponse(customer);
	}

	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();
		resp.setCustomerDetail(convertToCustomerDetail(customer));
		return resp;
	}

	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());
		return customerDetail;
	}

	@PayloadRoot(namespace = "http://amandareis.com.br", localPart = "GetAllCustomerDetailRequest")
	@ResponsePayload
	public GetAllCustomerDetailResponse processGetAllCustomerDetailRequest(
			@RequestPayload GetAllCustomerDetailRequest req) {
		List<Customer> customers = service.findAll();
		return convertToGetAllCustomerDetailResponse(customers);
	}

	private GetAllCustomerDetailResponse convertToGetAllCustomerDetailResponse(List<Customer> customers) {
		GetAllCustomerDetailResponse resp = new GetAllCustomerDetailResponse();
		customers.stream().forEach(c -> resp.getCustomerDetail().add(convertToCustomerDetail(c)));
		return resp;
	}

	@PayloadRoot(namespace = "http://amandareis.com.br", localPart = "DeleteCustomerRequest")
	@ResponsePayload
	public DeleteCustomerResponse deleteCustomerRequest(@RequestPayload DeleteCustomerRequest req) {
		DeleteCustomerResponse resp = new DeleteCustomerResponse();
		resp.setStatus(convertStatusSoap(service.deleteById(req.getId())));
		return resp;
	}

	private br.com.amandareis.Status convertStatusSoap(
			com.amanda.soap.webservices.customeradministration.helper.Status statusService) {
		if (statusService == com.amanda.soap.webservices.customeradministration.helper.Status.FAILURE) {
			return br.com.amandareis.Status.FAILURE;
		}
		return br.com.amandareis.Status.SUCCESS;

	}
}
