package com.example.ball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class MyThread extends Thread {
    private boolean mRun = false;
    private SurfaceHolder mSurfaceHolder;
    public Ball basket[] = new Ball[10];

    public MyThread(SurfaceHolder surfaceHolder) {

        mSurfaceHolder = surfaceHolder;
        for (int i = 0; i < 10; i++)
            basket[i] = new Ball(40);
    }

    @Override
    public void run() {
        while (mRun) {
            Canvas c = null;
            try {
                c = mSurfaceHolder.lockCanvas(null);
                c.drawColor(Color.BLACK);
                synchronized (mSurfaceHolder) {
                    for (Ball b : basket) {
                        b.paint(c);
                    }
                }
            } finally {
                if (c != null) {
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }
            }
            //try { Thread.sleep(100); } catch(InterruptedException e){}
        }
    }

    public void setRunning(boolean b) {
        mRun = b;
    }
}
