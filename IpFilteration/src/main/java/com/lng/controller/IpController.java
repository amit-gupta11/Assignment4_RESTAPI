package com.lng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lng.constants.Constants;
import com.lng.entity.IPTable;
import com.lng.entity.User;
import com.lng.exception.CustomException;
import com.lng.repository.IPTableRepository;
import com.lng.repository.UserRepository;
import com.lng.utils.RequestValidator;

@RestController
public class IpController {

    @Autowired
    IPTableRepository ipRepo;
    @Autowired
    UserRepository userRepo;

    public IpController() {

    }

    @PostMapping("/IPTable/addIp")
    public String addIp(@RequestBody IPTable ipRequest) {
        RequestValidator.validateIPRequest(ipRequest);
        User user = userRepo.findUserByUserId(ipRequest.getAddedby());
        if (user == null || !user.getRights().contains(Constants.INSERT_RIGHTS)) {
            throw new CustomException(Constants.UNAUTHORIZED_USER);
        }
        if (ipRepo.findByIp(ipRequest.getIp()) == null)
            ipRepo.save(ipRequest);
        else
            throw new CustomException(Constants.IP_ADDRESS_ALREADY_EXISTS);
        return "IP Saved Successfully.";
    }

    @GetMapping("/IPLookUp/getIpsByUserId/{userid}")
    public List<IPTable> getIpsByUserId(@PathVariable("userid") String userid) {
        User user = userRepo.findUserByUserId(userid);
        if (user == null) {
            throw new CustomException(Constants.UNAUTHORIZED_USER);
        }
        List<IPTable> table = userRepo.IsAdmin(userid) ? ipRepo.findAll() : ipRepo.findAllByAddedby(userid);
        return table;

    }
}
