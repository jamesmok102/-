package cn.minishop.util.validate;

import javax.servlet.http.HttpServletRequest;

public class ValidateUtil {
    /**
     * 验证数据是否为空
     * @param data 数据
     * @return 不空返回true,否则返回false
     */
    public static boolean validateEmpty(String data) {
        if (data == null || "".equals(data)) {
            return false;
        }
        return true;
    }

    /**
     * 对数据进行正则操作验证
     * @param data 数据
     * @param regex 正则表达式
     * @return 通过返回true, 否则返回false
     */
    public static boolean validateRegex(String data, String regex) {
        if (validateEmpty(data)) {
            return data.matches(regex); //正则
        }
        return false;
    }

    /**
     * 两个数据是否相同，不区分大小写
     * @param dataa 第一个数据
     * @param datab 第二个数据
     * @return 相同返回true,否则返回false
     */
    public static boolean validateSame(String dataa, String datab) {
        if(validateEmpty(dataa) && validateEmpty(datab)) {
            return dataa.equalsIgnoreCase(datab);
        }
        return false;
    }
}
