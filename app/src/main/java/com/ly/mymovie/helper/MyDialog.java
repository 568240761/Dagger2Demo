package com.ly.mymovie.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.ly.mymovie.R;

/**
 * Created by LanYang on 2017/12/30.
 * Email:568240761@qq.com
 */

public class MyDialog {
    public interface IPositiveOnClick{
        void click();
    }
    public static void showDialog(Activity activity, String message, final IPositiveOnClick positiveOnClick){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setNegativeButton("否",null);
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positiveOnClick.click();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        int color = activity.getResources().getColor(R.color.font_link);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(color);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(color);
    }
}
