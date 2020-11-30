package com.nearby.belinked.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Tool {
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        if (str == null) {
            return false;
        }
        if (str.length() != 11) {
            return false;
        }
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 验证密码是否是数字与字母的组合
     *
     * @param password 密码
     * @return 返回值
     */
    public static boolean isPassword(String password) {
        boolean ret;
//        Pattern p = Pattern.compile("\\w{6,16}+");

        //字母加数字组合
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z" +
                "!@#$%\\^&*(){}\\[\\]+-=|;:'<,>.?/]{6,12}$");

        //随意纯字母或者纯数字或者组合
       /* Pattern p = Pattern.compile("^[0-9A-Za-z" +
                "!@#$%\\^&*(){}\\[\\]+-=|;:'<,>.?/]{6,12}$");*/
        Matcher m = p.matcher(password);
        ret = m.matches();
        return ret;
    }

    /**
     * 验证码是否符合6位
     *
     * @param checkcodes 验证码
     * @return 返回值
     */
    public static boolean isCheckCode(String checkcodes) {
        Pattern p = Pattern
                .compile("^\\d{6}$");
        Matcher m = p.matcher(checkcodes);
        return m.matches();
    }
}
