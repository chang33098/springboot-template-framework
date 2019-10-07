package com.example.boot.springboottemplatecommon;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 汉字转拼音的工具类
 *
 * @author Chang
 * @date 2019/10/7 17:54
 */
@SuppressWarnings("unused")
public class PinYinUtils {

    private PinYinUtils() {
    }

    /**
     * 获取汉字首字母的方法。如： 张三 --> ZS
     * <p>
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     *
     * @param chinese 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getChineseInitials(String chinese) {
        String result = null;
        if (null != chinese && !"".equals(chinese)) {
            char[] charArray = chinese.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char ch : charArray) {
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != stringArray) {
                    builder.append(stringArray[0].charAt(0));
                }
            }
            if (builder.length() > 0) result = builder.toString().toUpperCase();
        }
        return result;
    }

    /**
     * 获取汉字拼音的方法。如： 张三 --> zhangsan
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     *
     * @param chinese 汉子字符串
     * @return 汉字拼音; 如果都转换失败,那么返回null
     */
    public static String getChinesePinYin(String chinese) {
        String result = null;
        if (null != chinese && !"".equals(chinese)) {
            char[] charArray = chinese.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char ch : charArray) {
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch); //逐个汉字进行转换, 每个汉字返回值为一个String数组(因为多音字的存在)
                if (null != stringArray) {
                    builder.append(stringArray[0].replaceAll("\\d", "")); // 把第几声这个数字给去掉
                }
            }
            if (builder.length() > 0) result = builder.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getChinesePinYin("系统模块"));
        System.out.println(getChinesePinYin("系统用户"));
    }
}
