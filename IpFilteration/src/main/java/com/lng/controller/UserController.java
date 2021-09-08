package com.lng.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lng.constants.Constants;
import com.lng.entity.User;
import com.lng.exception.CustomException;
import com.lng.repository.UserRepository;
import com.lng.utils.RequestValidator;

@RequestMapping("/")


@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    public UserController() {
        // TODO Auto-generated constructor stub
    }

    @PostMapping("/admin/addUser")
    public String addUser(@RequestBody User userRequest) {
        JSONObject message = new JSONObject();
        RequestValidator.validateCreateUserRequest(userRequest);
        if (userRepo.IsAdmin(userRequest.getAddedby())) {
            if (userRepo.findUserByUserId(userRequest.getUserId()) != null)
                throw new CustomException(Constants.USER_RECORD_ALREADY_EXISTS);
            else userRepo.save(userRequest);
        } else {
            throw new CustomException(Constants.UNAUTHORIZED_USER);
        }
        message.put("message", "User Added Successfully");
        return message.toString();
    }

}
