package com.ljh.lib_common.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogUtil {
    private static boolean IS_DEBUG = true;
    private static String DEFAULT_TAG = "tag";
    private static final int JSON_INDENT = 4;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void i(String message) {
        if (IS_DEBUG) {
            Log.i(DEFAULT_TAG + "-->:"+getTargetStackTraceElement(), message);
        }
    }
    public static void i(String tag, String message) {
        if (IS_DEBUG) {
            Log.i(tag + "-->:"+getTargetStackTraceElement(), message);
        }
    }

    public static void e(String message) {
        if (IS_DEBUG) {
            Log.e(DEFAULT_TAG + "-->"+getTargetStackTraceElement(), message);
        }
    }

    public static void e(String tag, String message) {
        if (IS_DEBUG) {
            Log.e(tag + "-->"+getTargetStackTraceElement(), message);
        }
    }
    public static void e(String message,Throwable tr) {
        if (IS_DEBUG) {
            Log.e(DEFAULT_TAG + "-->"+getTargetStackTraceElement(), message,tr);
        }
    }

    public static void e(String tag, String message,Throwable tr) {
        if (IS_DEBUG) {
            Log.e(tag + "-->"+getTargetStackTraceElement(), message,tr);
        }
    }

    public static void w( String message) {
        if (IS_DEBUG) {
            Log.w(DEFAULT_TAG + "-->:"+getTargetStackTraceElement(), message);
        }
    }

    public static void w(String tag, String message) {
        if (IS_DEBUG) {
            Log.w(tag + "-->:"+getTargetStackTraceElement(), message);
        }
    }
    public static void v( String message) {
        if (IS_DEBUG) {
            Log.v(DEFAULT_TAG + "-->:"+getTargetStackTraceElement(), message);
        }
    }

    public static void v(String tag, String message) {
        if (IS_DEBUG) {
            Log.v(tag + "-->:"+getTargetStackTraceElement(), message);
        }
    }

    public static void d( String message) {
        if (IS_DEBUG) {
            Log.d(DEFAULT_TAG + "-->:"+getTargetStackTraceElement(), message);
        }
    }

    public static void d(String tag, String message) {
        if (IS_DEBUG) {
            Log.d(tag + "-->:"+getTargetStackTraceElement(), message);
        }
    }
    public static void json(String msg, String headString) {
        if (IS_DEBUG) {
            String message;

            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(LogUtil.JSON_INDENT);
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(LogUtil.JSON_INDENT);
                } else {
                    message = msg;
                }
            } catch (JSONException e) {
                message = msg;
            }

            printLine(DEFAULT_TAG, true);
            message = headString + LogUtil.LINE_SEPARATOR + message;
            String[] lines = message.split(LogUtil.LINE_SEPARATOR);
            for (String line : lines) {
                Log.d(DEFAULT_TAG, "║ " + line);
            }
            printLine(DEFAULT_TAG, false);
        }

    }

    public static void json(String tag, String msg, String headString) {
        if (IS_DEBUG) {
            String message;

            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(LogUtil.JSON_INDENT);
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(LogUtil.JSON_INDENT);
                } else {
                    message = msg;
                }
            } catch (JSONException e) {
                message = msg;
            }

            printLine(tag, true);
            message = headString + LogUtil.LINE_SEPARATOR + message;
            String[] lines = message.split(LogUtil.LINE_SEPARATOR);
            for (String line : lines) {
                Log.d(tag, "║ " + line);
            }
            printLine(tag, false);
        }

    }
    private static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    private static String getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if(stackTrace.length>=4){
            targetStackTrace = stackTrace[4];
        }
        String s = "";
        if(null != targetStackTrace){
            s = "(" + targetStackTrace.getFileName() + ":"
                    + targetStackTrace.getLineNumber() + ")";
        }
        return s;
    }
}