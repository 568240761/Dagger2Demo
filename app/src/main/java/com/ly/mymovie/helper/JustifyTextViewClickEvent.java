package com.ly.mymovie.helper;

import com.ly.mymovie.data.model.ImageMessage;

/**
 * Created by LanYang on 2017/12/23.
 * Email:568240761@qq.com
 */

public class JustifyTextViewClickEvent {
    private int line;
    private int start;
    private int end;
    private ImageMessage mImageMessage;
    private float top;
    private float bottom;
    private float left;
    private float right;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ImageMessage getImageMessage() {
        return mImageMessage;
    }

    public void setImageMessage(ImageMessage imageMessage) {
        mImageMessage = imageMessage;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }
}
