package com.ly.mymovie.util.log;

import android.text.TextUtils;

import com.ly.mymovie.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.orhanobut.logger.Printer;

/**
 * Created by LanYang on 2017/11/27.
 * Email:568240761@qq.com
 * Log日志工具类
 * 调用依赖库Logger中的函数(能格式化显示xml、json格式的数据，也能将日志保存到文件中)
 */

public class LogUtil {
    //默认TAG
    private static final String DEFAULT_TAG = "MyMovie";

    private static volatile LogUtil mInstance;

    private Printer printer;

    private FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(2)     // (Optional) How many method line to show. Default 2
            .methodOffset(4)    // (Optional) Hides internal method calls up to offset. Default 5
            .tag(DEFAULT_TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build();

    //Logger中逻辑控制适配器
    private LogAdapter logAdapter = new AndroidLogAdapter(formatStrategy) {
        @Override
        public boolean isLoggable(int priority, String tag) {
            return BuildConfig.DEBUG;
        }
    };

    private LogUtil() {
        printer = new LoggerPrinter();
        printer.addAdapter(logAdapter);
    }

    public static LogUtil getSingleton() {
        if (mInstance == null) {
            synchronized (LogUtil.class) {
                if (mInstance == null) {
                    mInstance = new LogUtil();
                }
            }
        }
        return mInstance;
    }


    /**
     * 设置LogUtil中的tag
     *
     * @param tag
     */
    public void setLogTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            printer.t(tag);
        }
    }

    public void d(String message) {
        printer.d(message);
    }

    public void e(Throwable throwable) {
        e(null,throwable);
    }

    public void e(String message, Throwable throwable) {
        printer.e(throwable, message);
    }

    public void json(String json) {
        printer.json(json);
    }
}
