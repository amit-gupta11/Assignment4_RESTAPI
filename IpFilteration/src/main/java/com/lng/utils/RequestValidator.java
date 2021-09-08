package com.lng.utils;

import com.lng.IpFilterationApplication;
import com.lng.constants.Constants;
import com.lng.entity.IPTable;
import com.lng.entity.User;
import com.lng.exception.CustomException;

public class RequestValidator {

    public RequestValidator() {

    }

    public static void validateCreateUserRequest(User user) {
        if (user == null) {
            throw new CustomException(Constants.INTERNAL_SERVER_ERROR);
        }
        if (user.getUserId() == "" || user.getUserId() == null) {
            throw new CustomException(Constants.EMPTY_FIELDS);
        }
        if (user.getRights() == "" || user.getRights() == null) {
            throw new CustomException(Constants.ASSIGN_RIGHTS_TO_USER);
        }


    }

    public static void validateIPRequest(IPTable ipRequest) {
        if (ipRequest == null || ipRequest.getAddedby().isEmpty() || ipRequest.getAddedby() == null || ipRequest.getDescription().isEmpty() || ipRequest.getDescription() == null) {
            throw new CustomException(Constants.EMPTY_FIELDS);
        }
        if (IpFilterationApplication.IP_LIST.contains(ipRequest.getIp())) {
            throw new CustomException(Constants.BLOCKED_IP);
        }
        if (ipRequest.getIp() == null || ipRequest.getIp().isEmpty() || !ipRequest.getIp().trim().matches(Constants.VALIDATE_IP_PATTERN)) {
            throw new CustomException(Constants.INVALID_IP);
        }

    }


}
