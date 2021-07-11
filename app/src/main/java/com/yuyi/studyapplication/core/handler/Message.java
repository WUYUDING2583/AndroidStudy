package com.yuyi.studyapplication.core.handler;

public class Message {

    Message next;
    public int what;
    long when;
    public Object obj;
    Handler target;
    Runnable callback;

    public static final Object sPoolSync=new Object();
    private static Message sPool;
    private static final int MAX_POOL_SIZE=50;
    private static int sPoolSize=0;

    public static Message obtain() {
       synchronized (sPoolSync){
           if(sPool!=null){
               Message msg=sPool;
               sPool=msg.next;
               sPoolSize--;
               msg.next=null;
               return msg;
           }
       }
       return new Message();
    }

    public void recylce() {
        what=0;
        when=0;
        target=null;
        callback=null;
        obj=null;
        if(sPoolSize<MAX_POOL_SIZE){
            next=sPool;
            sPool=this;
            sPoolSize++;
        }
    }
}
