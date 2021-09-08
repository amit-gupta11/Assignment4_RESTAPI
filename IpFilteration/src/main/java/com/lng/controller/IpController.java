package com.lng.controller;

import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lng.IpFilterationApplication;
import com.lng.constants.Constants;
import com.lng.dto.IpRequestDTO;
import com.lng.entity.IPTable;
import com.lng.entity.User;
import com.lng.exception.CustomException;
import com.lng.repository.IPTableRepository;
import com.lng.repository.UserRepository;
import com.lng.utils.RequestValidator;

@RestController
//@RequestMapping("/ipLookUp")
public class IpController {

	@Autowired
	IPTableRepository ipRepo;
	@Autowired
	UserRepository userRepo;
	public IpController() {

	}

	
	@PostMapping("/IPTable/addIp")
	public String addIp(@RequestBody IPTable ipRequest)
	{
		JSONObject result= new JSONObject();
		RequestValidator.validateIPRequest(ipRequest);
		User user=userRepo.findUserByUserId(ipRequest.getAddedby());
		if(user==null || !Arrays.asList(user.getRights().split(",")).contains(Constants.INSERT_RIGHTS))
		{
			throw new CustomException(Constants.UNAUTHORIZED_USER);
		}
		if(ipRepo.findByIp(ipRequest.getIp())==null)
		ipRepo.save(ipRequest);
		else
		throw new CustomException(Constants.IP_ADDRESS_ALREADY_EXISTS);	
		result.put("message","IP Saved Successfully.");
		return result.toString();
	}
	
	@PostMapping("/IPTable/updateIp")
	public String updateIp(@RequestBody IpRequestDTO updateRequest)
	{
		JSONObject result= new JSONObject();
		RequestValidator.validateUpdateIpRequest(updateRequest);
		User user=userRepo.findUserByUserId(updateRequest.getUpdatedby());
		if(user==null || !Arrays.asList(user.getRights().split(",")).contains(Constants.UPDATE_RIGHTS))
		{
			throw new CustomException(Constants.UNAUTHORIZED_USER);
		}
		IPTable record=ipRepo.findByIp(updateRequest.getIp());
		if(record!=null)
		{
	    record.setIp(updateRequest.getNewIp());
		record.setDescription(updateRequest.getDescription());
		record.setAddedby(updateRequest.getUpdatedby());
		ipRepo.save(record);
		}
		else
		throw new CustomException(Constants.IP_ADDRESS_NOT_EXISTS);	
		result.put("message","IPTable updated Successfully.");
		return result.toString();		
	}


	@GetMapping("/IPLookUp/getIpsByUserId/{userid}")
	public List<IPTable> getIpsByUserId(@PathVariable("userid") String userid)
	{
		System.out.println("userid===="+userid);
		User user=userRepo.findUserByUserId(userid);
		if(user==null)
		{
			throw new CustomException(Constants.UNAUTHORIZED_USER);
		}
		List<IPTable> table=userRepo.IsAdmin(userid)?ipRepo.findAll():ipRepo.findAllByAddedby(userid);
		return table;
		
	}
}
