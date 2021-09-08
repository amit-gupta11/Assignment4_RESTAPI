package com.lng.constants;

public class Constants {

    public static final String USER_RECORD_ALREADY_EXISTS = "User Already Exists.";
    public static final String EMPTY_FIELDS = "Required Fields are missing.";
    public static final String INVALID_IP = "Please enter a valid IP Address.";
    public static final String IP_ADDRESS_ALREADY_EXISTS = "IP Address already exists.";
    public static final String BLOCKED_IP = "IP Address is Blocked by Network Admin";
    public static final String UNAUTHORIZED_USER = "Unauthorized User.";
    public static final String USER_NOT_EXISTS = "=User not exists.";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error.";
    public static final String ASSIGN_RIGHTS_TO_USER = "Please Assign Some Rights to User.";
    public static final String VALIDATE_IP_QUAD = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";
    public static final String VALIDATE_IP_PATTERN = VALIDATE_IP_QUAD + "\\." + VALIDATE_IP_QUAD + "\\." + VALIDATE_IP_QUAD + "\\." + VALIDATE_IP_QUAD;

    public static final String INSERT_RIGHTS = "I";
    public static final String VIEW_RIGHTS = "V";
    public static final String UPDATE_RIGHTS = "U";

    public Constants() {
    }

}
