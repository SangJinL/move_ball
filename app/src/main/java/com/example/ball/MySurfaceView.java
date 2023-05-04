package com.example.ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private MyThread thread;

    public MySurfaceView(Context context){
        super(context);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        thread = new MyThread(holder);


    }

    public MyThread getThread(){
        return thread;
    }

    public void surfaceCreated(SurfaceHolder holder){

        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;

        thread.setRunning(false);
        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e){
        }
    }
}

public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
    }
    //Ball.WIDTH = width;
    //Ball.HEIGHT = height;
}


class Ball{
    int x, y, xInc = 1, yInc = 1;
    int diameter;
    static int WIDTH = 1080, HEIGHT = 1920;

    public Ball (int d){
        this.diameter = d;

        x = (int) (Math.random() * (WIDTH -d) + 3);
        y = (int) (Math.random() * (HEIGHT -d) + 3);

        xInc = (int) (Math.random() * 5 + 1);
        yInc = (int) (Math.random() * 5 + 1);
    }

    public void paint(Canvas g){
        Paint paint = new Paint();

        if(x < 0 || x > (WIDTH - diameter))
            xInc = -xInc;
        if(y < 0 || y > (WIDTH - diameter))
            yInc = -yInc;

        x += xInc;
        y += yInc;

        paint.setColor(Color.RED);
        g.drawCircle(x, y, diameter, paint);
    }
}