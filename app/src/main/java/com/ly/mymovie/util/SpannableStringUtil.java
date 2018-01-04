package com.ly.mymovie.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.ly.mymovie.data.model.bean.CastsBean;
import com.ly.mymovie.data.model.bean.DirectorsBean;
import com.ly.mymovie.helper.ClickableMovementMethod;
import com.ly.mymovie.ui.filmmaker.FilmmakerActivity;
import com.ly.mymovie.ui.sort.SortActivity;

import java.util.List;

/**
 * Created by LanYang on 2017/12/22.
 * Email:568240761@qq.com
 * 文字点击工具
 */

public class SpannableStringUtil {

    public static void setClickText(@StringRes int id, @ColorRes int colorId, List<? extends Object> list, TextView textView) {
        String prefixes = textView.getResources().getString(id);
        setClickText(prefixes, colorId, list, textView);

    }

    public static void setClickText(String prefixes, final @ColorRes int colorId, List<? extends Object> list, TextView textView) {
        String str = TextUtil.isEmpty(TextUtil.splicing(list), textView.getContext());
        String content = prefixes + str;
        setClickText(content, colorId, textView, list);
    }

    public static void setClickText(String content, final @ColorRes int colorId, TextView textView, List<? extends Object> list) {
        final Resources resources = textView.getResources();
        final Context context = textView.getContext();

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);

        final int type = getType(list);

        if (type != -1) {
            for (final Object object : list) {
                String item = null;
                if (type == 0) {
                    item = (String) object;
                } else if (type == 1) {
                    item = ((DirectorsBean) object).getName();
                } else if (type == 2) {
                    item = ((CastsBean) object).getName();
                }
                int start = content.indexOf(item);
                int length = item.length();
                int end = start + length;
                spannableStringBuilder.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        if (type == 0) {
                            ActionUtil.gotoActivity(context, SortActivity.class, (String) object);
                        } else if (type == 1) {
                            ActionUtil.gotoActivity(context, FilmmakerActivity.class, ((DirectorsBean) object).getId());
                        } else if (type == 2) {
                            ActionUtil.gotoActivity(context, FilmmakerActivity.class, ((CastsBean) object).getId());
                        }
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setColor(resources.getColor(colorId));
                    }
                }, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            textView.setMovementMethod(ClickableMovementMethod.getInstance());
            textView.setFocusable(false);
            textView.setClickable(false);
            textView.setLongClickable(false);
        }
        textView.setText(spannableStringBuilder);
    }

    /**
     * 区分点击类型
     *
     * @param list
     */
    private static int getType(List<? extends Object> list) {
        if (list == null || list.size() == 0) {
            return -1;
        }

        Object object = list.get(0);
        if (object instanceof String) {
            return 0;
        } else if (object instanceof DirectorsBean) {
            return 1;
        } else if (object instanceof CastsBean) {
            return 2;
        } else {
            return -1;
        }
    }
}
