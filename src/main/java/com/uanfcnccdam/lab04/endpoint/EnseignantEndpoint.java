package com.uanfcnccdam.lab04.endpoint;

import javax.xml.namespace.QName;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.uanfcnccdam.lab04.model.Enseignant;
import com.uanfcnccdam.lab04.services.EnseignantServices;

import allapis.springbootsoap.com.AddEnseignantRequest;
import allapis.springbootsoap.com.AddEnseignantResponse;
import allapis.springbootsoap.com.DeleteEnseignantRequest;
import allapis.springbootsoap.com.DeleteEnseignantResponse;
import allapis.springbootsoap.com.EnseignantInfo;
import allapis.springbootsoap.com.GetEnseignantByIdRequest;
import allapis.springbootsoap.com.GetEnseignantResponse;
import allapis.springbootsoap.com.ServiceStatus;
import allapis.springbootsoap.com.UpdateEnseignantRequest;
import allapis.springbootsoap.com.UpdateEnseignantResponse;
import jakarta.xml.bind.JAXBElement;

@Endpoint
public class EnseignantEndpoint {

	private static final String NAMESPACE_URI = "http://com.springbootsoap.allapis";

	@Autowired
	private EnseignantServices enseignantService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEnseignantRequest")
	@ResponsePayload
	public JAXBElement<AddEnseignantResponse> addEnseignant(@RequestPayload JAXBElement<AddEnseignantRequest> request) {
		AddEnseignantResponse response = new AddEnseignantResponse();
		ServiceStatus serviceStatus = new ServiceStatus();

		Enseignant enseignant = new Enseignant();
		BeanUtils.copyProperties(request.getValue().getEnseignantInfo(), enseignant);
		enseignantService.AddEnseignant(enseignant);
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Added Successfully");
		response.setServiceStatus(serviceStatus);
		QName qName = new QName("AddEnseignantRequest");
		JAXBElement<AddEnseignantResponse> jaxbElement = new JAXBElement<AddEnseignantResponse>( qName, AddEnseignantResponse.class, response);
		return jaxbElement;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEnseignantByIdRequest")
	@ResponsePayload
	public JAXBElement<GetEnseignantResponse> getEnseignant(@RequestPayload JAXBElement<GetEnseignantByIdRequest> request) {
		GetEnseignantResponse response = new GetEnseignantResponse();
		EnseignantInfo enseignantInfo = new EnseignantInfo();
		BeanUtils.copyProperties(enseignantService.getEnseignantById(request.getValue().getEnseignantId()), enseignantInfo);
		response.setEnseignantInfo(enseignantInfo);
		QName qName = new QName("GetEnseignantResponse");
		JAXBElement<GetEnseignantResponse> jaxbElement = new JAXBElement<GetEnseignantResponse>( qName, GetEnseignantResponse.class, response);
		return jaxbElement;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEnseignantRequest")
	@ResponsePayload
	public JAXBElement<UpdateEnseignantResponse> updateEnseignant(@RequestPayload JAXBElement<UpdateEnseignantRequest> request) {
		Enseignant enseignant = new Enseignant();
		BeanUtils.copyProperties(request.getValue().getEnseignantInfo(), enseignant);
		enseignantService.updateEnseignant(enseignant);
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Updated Successfully");
		UpdateEnseignantResponse response = new UpdateEnseignantResponse();
		response.setServiceStatus(serviceStatus);
		QName qName = new QName("UpdateEnseignantResponse");
		JAXBElement<UpdateEnseignantResponse> jaxbElement = new JAXBElement<UpdateEnseignantResponse>( qName, UpdateEnseignantResponse.class, response);
		return jaxbElement;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEnseignantRequest")
	@ResponsePayload
	public JAXBElement<DeleteEnseignantResponse> deleteEnseignant(@RequestPayload JAXBElement<DeleteEnseignantRequest> request) {
		enseignantService.deleteEnseignant(request.getValue().getEnseignantId());
		ServiceStatus serviceStatus = new ServiceStatus();

		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Deleted Successfully");
		DeleteEnseignantResponse response = new DeleteEnseignantResponse();
		response.setServiceStatus(serviceStatus);
		QName qName = new QName("DeleteEnseignantResponse");
		JAXBElement<DeleteEnseignantResponse> jaxbElement = new JAXBElement<DeleteEnseignantResponse>( qName, DeleteEnseignantResponse.class, response);
		return jaxbElement;
	}

}
