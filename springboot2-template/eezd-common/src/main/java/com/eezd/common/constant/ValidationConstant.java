package com.eezd.common.constant;


public class ValidationConstant {

    public static final String NOT_EMPTY_MSG = "不能为空";

    public static final String EMPTY_MSG = "必须为空";

    public static final int USERNAME_MIN_LENGTH = 3;

    public static final int USERNAME_MAX_LENGTH = 20;

    public static final String USERNAME_LENGTH_MSG = "user.username.length.not.valid";

    public static final String EMAIL = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static final String EMAIL_MSG = "user.email.not.valid";

    public static final int EMAIL_MIN_LENGTH = 4;

    public static final int EMAIL_MAX_LENGTH = 40;

    public static final String EMAIL_LENGTH_MSG = "user.email.length.not.valid";


    public static final String PHONE = "^1[0-9]{10}$";

    public static final String PHONE_MSG = "user.mobile.phone.number.not.valid";


    public static final int PASSWORD_MIN_LENGTH = 5;

    public static final int PASSWORD_MAX_LENGTH = 20;

    public static final String PASSWORD_LENGTH_MSG = "user.password.length.not.valid";

    /**
     * 必须存在一个大写字母
     */
    public static final String HAS_ONE_UPPERCASE = "^(?=.*[A-Z]).+$";

    public static final String HAS_ONE_UPPERCASE_MSG = "必须存在一个大写字母";

    /**
     * 必须存在一个小写字母
     */
    public static final String HAS_ONE_LOWERCASE = "^(?=.*[a-z]).+$";

    public static final String HAS_ONE_LOWERCASE_MSG = "必须存在一个小写字母";

    /**
     * 必须存在一个数字字母
     */
    public static final String HAS_ONE_NUM = "^(?=.*[0-9]).+$";

    public static final String HAS_ONE_NUM_MSG = "必须存在一个数字";

    /**
     * 只接受 Y 或者 N
     */
    public static final String Y_OR_N = "^[YN]$";

    public static final String Y_OR_N_MSG = "config.type.not.valid";

}
