package com.example.admin.hw1_hulbert;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Canvas2 extends View{
    private static final float TOLERANCE = 5;
    public int width;
    public int height;
    Context context;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private float mX, mY;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Integer> colors = new ArrayList<>();
    private int nColor = Color.BLACK;

    public Canvas2(Context c) {
        super(c);
        context = c;

        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(nColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

        paths.add(mPath);
    }

    public void SetColor(int nClr){
       nColor = nClr;
        mPaint.setColor(nClr);
        colors.add(nClr);

    }

    public int GetColor(){
        return nColor;
    }
    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.WHITE);
        mCanvas.drawBitmap(getBitmap(), 0, 0, null);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the mPath with the mPaint on the canvas when onDraw
        for (Path myPath : paths){
            try {
                SetColor(nColor);
                canvas.drawPath(myPath, mPaint);
            }catch(Exception e){
                e.printStackTrace();
            }


        }
       // canvas.drawBitmap(mBitmap,0,0,mPaint);
        canvas.drawPath(mPath,mPaint);
          //  Log.i("OnDrawing","Reach on draw");

        //canvas.drawPath(mPath, mPaint);
    }

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {

        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas() {
        for(Path p: paths){
            p.reset();
        }
        mPath.reset();
        invalidate();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);
        //mCanvas.drawPath(mPath,mPaint);
        //paths.add(mPath);
        //mPath = new Path();

    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                upTouch();
                invalidate();
                break;
        }
        return true;
    }

    public Bitmap getBitmap() {
//        this.setDrawingCacheEnabled(true);
//        this.buildDrawingCache();
//        this.setDrawingCacheEnabled(false);

        return mBitmap;
    }


}
