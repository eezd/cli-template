package com.eezd.common.constant;

public class ValidationConstant {

    public static final String NOT_EMPTY_MSG = "不能为空";

    public static final String EMPTY_MSG = "必须为空";

    public static final String EMAIL = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static final String EMAIL_MSG = "邮箱格式不正确";

    public static final String PHONE = "^1[0-9]{10}$";

    public static final String PHONE_MSG = "手机号格式不正确";

    /**
     * 用户名长度限制, 3到20位
     */
    public static final String USERNAME_LENGTH = "^.{3,20}$";

    public static final String USERNAME_LENGTH_MSG = "用户名长度限制3到20位";

    /**
     * 密码长度限制, 3到20位
     */
    public static final String PASSWORD_LENGTH = "^.{3,20}$";

    public static final String PASSWORD_LENGTH_MSG = "密码长度限制3到20位";

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

    public static final String Y_OR_N_MSG = "只接受 Y 或者 N";
}
