package com.ly.mymovie.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ly.mymovie.data.model.ImageMessage;
import com.ly.mymovie.ui.filmmaker.FilmmakerActivity;
import com.ly.mymovie.util.ActionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LanYang on 2017/12/20.
 * Email:568240761@qq.com
 * 文字两端对齐
 */

public class JustifyTextView extends android.support.v7.widget.AppCompatTextView {
    private int width;
    private int height;
    /**
     * 行宽
     */
    private int lineWidth;
    /**
     * 一个字所需的宽
     */
    private int textWidth;
    /**
     * 一个字所需的高度
     */
    private int textHeight;
    /**
     * 行间距
     */
    private int textLineSpaceExtra;
    /**
     * 额外的行间距数值，单位通常为dp
     */
    private int lineSpacingExtra;
    /**
     * 行间距的倍数，没有单位
     */
    private float lineSpacingMultiplier;

    private TextPaint mPaint;

    /**
     * TextView的文字内容
     */
    private String mContent;

    /**
     * 每行文字
     */
    private List<String> lineList = new ArrayList<String>();

    /**
     * 需要特别处理的信息
     */
    private List<ImageMessage> imageMessageList = new ArrayList<ImageMessage>();

    private int linkColorId;

    private List<JustifyTextViewClickEvent> eventList = new ArrayList<JustifyTextViewClickEvent>();
    /**
     * 记录包含换行符的段落
     */
    private List<Integer> numberList = new ArrayList<>();

    public JustifyTextView(Context context) {
        this(context, null);
    }

    public JustifyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustifyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int[] attributes = new int[]{android.R.attr.lineSpacingExtra, android.R.attr.lineSpacingMultiplier};
        TypedArray arr = context.obtainStyledAttributes(attrs, attributes);
        lineSpacingExtra = arr.getDimensionPixelSize(0, 0);
        lineSpacingMultiplier = arr.getFloat(1, 1.0f);
        arr.recycle();

        mPaint = getPaint();
        linkColorId = mPaint.getColor();
    }

    public void setText(String content, List<ImageMessage> list, @ColorRes int colorId) {
        mContent = content;
        imageMessageList.addAll(list);
        linkColorId = colorId;
        requestLayout();
    }

    /**
     * 重写该方法，来计算TextView宽高。
     * 主要是高；对于宽，这个项目中，都是match_parent。
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        width = widthSize;

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            if (mContent == null || mContent.length() == 0) {
                height = 0;
            } else {
                textHeight = (int) getTextSize();
                //行间距的高度
                textLineSpaceExtra = (int) (textHeight * (lineSpacingMultiplier - 1) + lineSpacingExtra);

                textWidth = (int) mPaint.measureText("爱");
                calculateLines(mContent, textWidth, width);

                if (lineList.size() == 1) {
                    height = textHeight + getPaddingBottom() + getPaddingTop();
                } else {
                    height = (textLineSpaceExtra + textHeight) * (lineList.size() - 1) + textHeight + getPaddingTop() + getPaddingBottom();
                }
            }
        }

        setMeasuredDimension(width, height);
    }

    private void calculateLines(String text, int wordWidth, int width) {
        //一行文字的宽度
        lineWidth = width - getPaddingLeft() - getPaddingRight();
        //一行几个文字
        int wordsLength = lineWidth / wordWidth;
        String[] lines = text.split("\\n");
        for (String line : lines) {
            if (line.length() == 0) {
                return;
            }
            int start = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\u3000\u3000");
            if (line.length() <= wordsLength) {
                stringBuilder.append(line.substring(start, line.length()));
                lineList.add(stringBuilder.toString());
                numberList.add(lineList.size() - 1);
                continue;
            } else {
                stringBuilder.append(line.substring(start, wordsLength - 1));
            }

            for (int i = wordsLength - 1; i < line.length(); i++) {
                if (mPaint.measureText(stringBuilder.toString()) > lineWidth) {
                    String string = stringBuilder.toString();
                    lineList.add(string);

                    start = i;

                    stringBuilder.delete(0, stringBuilder.length());

                    if ((line.length() - start) > wordsLength) {
                        stringBuilder.append(line.substring(start, start + wordsLength));
                    } else {
                        lineList.add(line.substring(start));
                        break;
                    }
                    i = i + wordsLength - 1;
                } else {
                    stringBuilder.append(line.charAt(i));
                }
            }
            if (stringBuilder.toString().length() > 0) {
                lineList.add(stringBuilder.toString());
            }
            numberList.add(lineList.size() - 1);
        }

        initLinkFont();
    }

    private void initLinkFont() {
        if (lineList.size() > 0) {
            for (int i = 0; i < lineList.size(); i++) {
                String line = lineList.get(i);
                for (ImageMessage imageMessage : imageMessageList) {
                    String name = imageMessage.getName();
                    int length = name.length();

                    //字符串的一部分在开头
                    int lastChar = line.indexOf(name.charAt(length - 1));
                    if (lastChar != -1 &&
                            (lastChar + 1) - length < 0 &&
                            name.contains(line.substring(0, lastChar + 1))) {
                        JustifyTextViewClickEvent event = new JustifyTextViewClickEvent();
                        event.setLine(i);
                        event.setStart(0);
                        event.setEnd(lastChar);
                        event.setImageMessage(imageMessage);
                        eventList.add(event);
                    }

                    if (line.contains(name)) {
                        contains(line, name, imageMessage, i);
                    }

                    //字符串的一部分在结尾
                    int firstChar = line.indexOf(name.charAt(0));
                    if (firstChar != -1 &&
                            firstChar + length + 1 > line.length() &&
                            name.contains(line.substring(firstChar, line.length()))) {
                        JustifyTextViewClickEvent event = new JustifyTextViewClickEvent();
                        event.setLine(i);
                        event.setStart(firstChar);
                        event.setEnd(line.length() - 1);
                        event.setImageMessage(imageMessage);
                        eventList.add(event);
                    }
                }
            }
        }
    }

    private void contains(String line, String name, ImageMessage imageMessage, int i) {
        int start = line.indexOf(name);

        JustifyTextViewClickEvent event = new JustifyTextViewClickEvent();

        event.setLine(i);
        event.setStart(start);

        int end = start + name.length();
        event.setEnd(end);

        event.setImageMessage(imageMessage);

        eventList.add(event);

        String text = line.substring(end);
        if (text.contains(name)) {
            contains(text, name, imageMessage, i);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (lineList.size() == 0) {
            super.onDraw(canvas);
        } else {
            int defaultColor = mPaint.getColor();

            Paint.FontMetrics fm = mPaint.getFontMetrics();
            float firstHeight = getTextSize() - (fm.bottom - fm.descent + fm.ascent - fm.top);
            for (int i = 0; i < lineList.size(); i++) {
                List<JustifyTextViewClickEvent> list = getEventList(i);

                String line = lineList.get(i);

                float drawY = i * (textHeight + textLineSpaceExtra) + firstHeight + getPaddingTop();

                //字与字之间的间距
                float interval = 0;
                if (!numberList.contains(i)) {
                    interval = (lineWidth - mPaint.measureText(line)) / (line.length() - 1);
                }
                mPaint.setColor(defaultColor);
                for (int j = 0; j < line.length(); j++) {
                    float drawX = mPaint.measureText(line.substring(0, j)) + interval * j + getPaddingLeft();
                    if (list == null || list.size() == 0) {
                        canvas.drawText(line.substring(j, j + 1), drawX, drawY, mPaint);
                    } else {
                        for (JustifyTextViewClickEvent event : list) {
                            if (j >= event.getStart() && j <= event.getEnd()) {
                                mPaint.setColor(getResources().getColor(linkColorId));
                                if (j == event.getStart()) {
                                    event.setLeft(drawX);
                                    event.setTop(drawY - firstHeight);
                                    event.setBottom(drawY + (fm.bottom - fm.descent + fm.ascent - fm.top));
                                }
                                if (j == event.getEnd()) {
                                    event.setRight(drawX + textWidth);
                                }
                            } else {
                                mPaint.setColor(defaultColor);
                            }
                            canvas.drawText(line.substring(j, j + 1), drawX, drawY, mPaint);
                        }
                    }
                }
            }
        }
    }

    private List<JustifyTextViewClickEvent> getEventList(int line) {
        if (eventList.size() == 0) {
            return null;
        }
        List<JustifyTextViewClickEvent> list = new ArrayList<>();
        for (JustifyTextViewClickEvent event : eventList) {
            if (event.getLine() == line) {
                list.add(event);
            }
        }
        return list;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                onClickEvent(x, y);
                break;
        }
        return true;
    }

    private void onClickEvent(float x, float y) {
        if (eventList.size() > 0) {
            for (JustifyTextViewClickEvent event : eventList) {
                if (x >= event.getLeft() && x <= event.getRight() &&
                        y >= event.getTop() && y <= event.getBottom()) {
                    ActionUtil.gotoActivity(getContext(), FilmmakerActivity.class,event.getImageMessage().getId());
                    break;
                }
            }
        }
    }
}
