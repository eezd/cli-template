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

    public static final String ROLE_SORT_MSG = "角色排序必须是0到9999";

    public static final String ROLE_STATUS_MSG = "角色状态必填(0正常 1停用)";

    public static final String DEL_FLAG_MSG = "删除状态必填(0代表存在 1代表删除)";

    public static final String USER_SEX_MSG = "用户性别必填(0男 1女 2未知)";
    
    public static final String USER_STATUS_MSG = "用户状态必填(0正常 1停用)";

    public static final String USER_DEL_FLAG_MSG = "用户删除状态必填(0代表存在 1代表删除)";
}
