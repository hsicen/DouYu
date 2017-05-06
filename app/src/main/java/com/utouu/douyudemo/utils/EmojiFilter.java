package com.utouu.douyudemo.utils;

import android.text.InputFilter;
import android.text.Spanned;

import com.utouu.douyudemo.application.DYApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者： poeticAndroid
 * 邮箱： codinghuang@163.com
 * 时间： 2017/4/30 15:55
 * 作用：表情过滤
 * 描述：
 */
public class EmojiFilter implements InputFilter {

    //过滤表情的正则表达式
    Pattern  emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]" +
            "|[\ud83d\udc00-\ud83d\udfff]|[\ud83e\udd00-\ud83e\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            ToastUtils.showShort(DYApplication.getContext(),"不支持Emoji输入");
            return "";
        }
        return null;
    }
}
