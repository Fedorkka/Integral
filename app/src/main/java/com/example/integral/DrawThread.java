package com.example.integral;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.Random;

public class DrawThread extends Thread {
    private boolean flag = true;
    SurfaceHolder surfaceHolder;
    DrawThread(Context context, SurfaceHolder holder){
        surfaceHolder = holder;
    }

    public void requestStop(){
        flag = false;
    }

    @Override
    public void run(){
        int d=1;
        while(flag){
            d+=5;
            Canvas canvas = surfaceHolder.lockCanvas();
            if(canvas != null){
                try {
                    canvas.drawColor(Color.WHITE);
                    Paint p = new Paint();
                    p.setColor(Color.RED);
                    Bitmap b = Info.drawableToBitmap(Info.d_img);
                    Matrix matrix = new Matrix();
                    matrix.reset();
                    matrix.postTranslate(-b.getWidth() / 2, -b.getHeight() / 2); // Centers image
                    matrix.postRotate(d);
                    matrix.postTranslate((Info.width/2), (Info.height/2));
                    canvas.drawBitmap(b, matrix, p);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
