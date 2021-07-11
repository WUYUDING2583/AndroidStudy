package com.yuyi.studyapplication.core.handler;

import android.os.SystemClock;
import android.util.Log;

public class Handler {

    private final Looper mLooper;
    private final MessageQueue mQueue;

    public Handler() {
        mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    /**
     * 使用者必须重写此方法
     */
    public void handleMessage(Message msg) {

    }

    public void dispatchMessage(Message msg) {
        if (msg.callback != null) {
            //使用post发送消息
            handleCallback(msg);
        } else {
            //使用sendMessage发送消息
            handleMessage(msg);
        }
    }

    private void handleCallback(Message msg) {
        msg.callback.run();
    }

    public final boolean sendMessage(Message msg) {
        return sendMessageDelayed(msg, 0);
    }

    private final boolean sendMessageDelayed(Message msg, int delayedMillis) {
        if (delayedMillis < 0) {
            delayedMillis = 0;
        }
        return sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayedMillis);
    }

    private final boolean sendMessageAtTime(Message msg, long updateMillis) {
        MessageQueue queue = mQueue;
        if (queue == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            Log.w("Looper", e.getMessage(), e);
            return false;
        }
        return enqueueMessage(queue, msg, updateMillis);
    }

    private boolean enqueueMessage(MessageQueue queue, Message msg, long updateMillis) {
        msg.target = this;
        return queue.enqueueMessage(msg, updateMillis);
    }

}

