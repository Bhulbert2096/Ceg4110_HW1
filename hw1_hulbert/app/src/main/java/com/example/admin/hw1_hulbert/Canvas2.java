package com.example.admin.hw1_hulbert;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private Map<Path, Integer> colorsMap = new HashMap<Path, Integer>();
    private int nColor = Color.BLACK;
    private Paint tmpPaint;

    public Canvas2(Context c) {
        super(c);
        context = c;

        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        tmpPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(nColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
        colors.add(nColor);
       paths.add(mPath);
       tmpPaint.setColor(nColor);
    }

    public void SetColor(int nClr){
       nColor = nClr;
        mPaint.setColor(nClr);

    }

    public Paint GetColor(){

        return tmpPaint;
    }
    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.WHITE);
      // mCanvas.drawBitmap(mBitmap, 0, 0, null);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       canvas.drawPath(mPath,mPaint);
        int tmp = 0;
        for (Path myPath: paths) {

            //these 2 work
            mPaint.setColor(colors.get(tmp));
          canvas.drawPath(myPath, mPaint);
            tmp++;
        }
       mCanvas.drawPath(mPath,mPaint);
    }

    private void startTouch(float x, float y) {

        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

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
        int wTmp = mBitmap.getWidth();
        int hTmp = mBitmap.getHeight();
        mBitmap.recycle();
        mBitmap = Bitmap.createBitmap(wTmp, hTmp, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.WHITE);
        invalidate();
    }

    private void upTouch() {
        mPath.lineTo(mX, mY);

    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                colors.add(nColor);
                paths.add(mPath);
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
        return mBitmap;
    }


}
