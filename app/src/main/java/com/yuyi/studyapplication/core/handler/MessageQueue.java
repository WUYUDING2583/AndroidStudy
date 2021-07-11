package com.yuyi.studyapplication.core.handler;

import android.os.SystemClock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {


    boolean mBlocked;
    Message mMessages;

    //阻塞队列
    BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(50);

//    private native void nativePollOnce(long ptr, int timeoutMillis);
//    private native static void nativeWake(long ptr);

    public Message next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
//        final long ptr = 0;//不考虑其他影响
//        int nextPollTimeoutMillis = 0;
//
//        for (; ; ) {
//
//            nativePollOnce(ptr, nextPollTimeoutMillis);
//            //不考虑被阻碍的情况
//            synchronized (this) {
//                Message msg = mMessages;
//                final long now = SystemClock.uptimeMillis();
//                if (msg != null) {
//
//                    if (now < msg.when) {
//                        //当前消息未到出队列时间
//                        //设置下次轮询时间
//                        nextPollTimeoutMillis = (int) Math.min(msg.when - now, Integer.MAX_VALUE);
//                        continue;
//                    } else {
//                        mBlocked = false;
//                        //消息出队
//                        mMessages = msg.next;
//                        msg.next = null;
//                        return msg;
//                    }
//
//                } else {
//                    //队列中没有消息
//                    nextPollTimeoutMillis = -1;
//                }
//            }
//            //不考虑处理空闲任务
//            //其他操作完成，设置下次轮询时间为立即执行
//            nextPollTimeoutMillis = 0;
//        }
    }

    public boolean enqueueMessage(Message msg, long updateMillis) {
        try {
            blockingQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
//        final long mPtr=0;
//        if (msg.target == null) {
//            throw new IllegalArgumentException("Message must have a target.");
//        }
//        synchronized (this) {
//            Message p = mMessages;
//            boolean needWake;
//            if (p == null || msg.when == 0 || p.when > msg.when) {
//                //当前消息队列无消息
//                //入队消息为即时消息
//                //入队消息触发时间早于消息队列第一条
//                mMessages = msg;
//                mMessages.next = p;
//                needWake = mBlocked;
//            } else {
//                needWake = mBlocked && p.target == null;
//                Message prev;
//                for(;;){
//                    prev=p;
//                    p=p.next;
//                    if(p==null||msg.when<p.when){
//                        break;
//                    }
//                }
//                msg.next=p;
//                prev.next=msg;
//            }
//
//            if (needWake) {
//                nativeWake(mPtr);
//            }
//        }
//        return true;
    }
}

