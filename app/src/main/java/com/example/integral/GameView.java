package com.example.integral;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    DrawThread dt;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        dt = new DrawThread(getContext(),getHolder());
        dt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        dt.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                dt.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
