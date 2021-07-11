package com.yuyi.studyapplication.core.handler;

public class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    static Looper mMainLooper;
    final MessageQueue mQueue;
    final Thread mThread;

    private Looper() {
        mQueue = new MessageQueue();
        mThread = Thread.currentThread();
    }

    public static void prepareMainLooper() {
        prepare();
        synchronized (Looper.class) {
            if (mMainLooper != null) {
                throw new IllegalStateException("The main Looper has already been prepared.");
            }
            mMainLooper = myLooper();
        }
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        final Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        MessageQueue queue=me.mQueue;
        for(;;){
            Message msg=queue.next();
            msg.target.dispatchMessage(msg);
//            msg.recylce();
        }
    }
}
