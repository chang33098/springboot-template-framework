package com.example.boot.springboottemplatecommon;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转拼音的工具类
 *
 * @author Chang
 * @date 2019/10/7 17:54
 */
@Slf4j
public class PinYinUtils {

    private PinYinUtils() {
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     *
     * @param chinese 中文
     * @return 转化后的拼音
     */
    @SuppressWarnings("unused")
    public static String getChinesePinYin(String chinese) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = chinese.trim().toCharArray();
        StringBuilder output = new StringBuilder();

        try {
            for (char anInput : input) {
                if (Character.toString(anInput).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(anInput, format);
                    output.append(temp[0]);
                } else
                    output.append(Character.toString(anInput));
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error(e.getMessage(), e);
        }
        return output.toString();
    }

    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    @SuppressWarnings("unused")
    public static String getFirstSpell(String chinese) {
        StringBuilder builder = new StringBuilder();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char anArr : arr) {
            if (anArr > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(anArr, defaultFormat);
                    if (temp != null) {
                        builder.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                builder.append(anArr);
            }
        }
        return builder.toString().replaceAll("\\W", "").trim();
    }

    public static void main(String[] args) {
        System.out.println(getChinesePinYin("测试模块2"));
        System.out.println(getChinesePinYin("测试——aaaA的Adfd_模块2"));
        System.out.println(getChinesePinYin("测试胡代发货——十多个互动_4545dsd十点多+模块2"));
    }
}
