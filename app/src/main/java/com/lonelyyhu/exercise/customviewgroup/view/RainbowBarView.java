package com.lonelyyhu.exercise.customviewgroup.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.lonelyyhu.exercise.customviewgroup.R;

import java.util.ArrayList;

/**
 * Created by hulonelyy on 2017/10/21.
 */

public class RainbowBarView extends View{

    int barColor = Color.GREEN;
    ArrayList<Integer> barColors;

    int hSpace;
    int vSpace;

    int space;

    int startColorIdx;

    float startX = 0f;
    float delta = 10f;

    Paint mPaint;

    public RainbowBarView(Context context) {
        super(context);
        init(context, null);
    }

    public RainbowBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RainbowBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public RainbowBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.rainbowBar);

            int attrType = typedArray.getType(R.styleable.rainbowBar_barColor);

            switch (attrType) {

                case TypedValue.TYPE_REFERENCE:
                    int resourceId = typedArray.getResourceId(R.styleable.rainbowBar_barColor, 0);

                    if (resourceId != 0) {
                        String colorStr = context.getResources().getString(resourceId);
                        barColor = Color.parseColor(colorStr);
                    }

                    break;
                default:
                    barColor = typedArray.getColor(R.styleable.rainbowBar_barColor, barColor);

            }

            int colorsId = typedArray.getResourceId(R.styleable.rainbowBar_barColors, 0);
            Log.wtf("RainbowBarView", "colorsId: " + colorsId);
            String[] colors = context.getResources().getStringArray(colorsId);

            Log.wtf("RainbowBarView", "init: colors:" + colors[0]);

            barColors = new ArrayList<>();
            for (String colorStr:colors) {
                Log.wtf("RainbowBarView", "init: color=>" + colorStr);
                barColors.add(Color.parseColor(colorStr));
            }

            int dfHSpace = Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, context.getResources().getDisplayMetrics())).intValue();
            hSpace = typedArray.getDimensionPixelSize(R.styleable.rainbowBar_hSpace, dfHSpace);

            int dfVSpace = Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics())).intValue();
            vSpace = typedArray.getDimensionPixelSize(R.styleable.rainbowBar_vSpace, dfVSpace);

            typedArray.recycle();
        } else {
            hSpace = Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, context.getResources().getDisplayMetrics())).intValue();
            vSpace = Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics())).intValue();
        }

        space = Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics())).intValue();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(barColor);
        mPaint.setStrokeWidth(vSpace);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.wtf("RainbowBarView", "onDraw: run!!!");

        float screenWidth = this.getMeasuredWidth();

        if (startX >= screenWidth + (hSpace + space) - (screenWidth % (hSpace + space))) {
            startX = 0;
        } else {
            startX += delta;
        }

        float start = startX;
        int startc = startColorIdx;
        while (start < screenWidth) {
            Log.wtf("RainbowBarView", "onDraw: start" + start);

            if (barColors!=null) {
                int idx = startc % barColors.size();
                int color = barColors.get(idx);
                mPaint.setColor(color);
            }

            canvas.drawLine(start, 50, start+hSpace, 50, mPaint);
            start += hSpace + space;
            startc++;
        }

        start = startX - space;
        startc = startColorIdx - 1;
        while (start > 0) {

            if (barColors!=null) {
                Log.wtf("RainbowBarView", "onDraw: startc =>" + startc);
                int idx = startc % barColors.size();
                if (idx < 0) {
                    idx = idx + barColors.size();
                }

                Log.wtf("RainbowBarView", "onDraw: idx =>" + idx);
                int color = barColors.get(idx);
                mPaint.setColor(color);
            }


            Log.wtf("RainbowBarView", "onDraw: start" + start);
            Log.wtf("RainbowBarView", "onDraw: end" + (start-hSpace));
            canvas.drawLine(start, 50, start-hSpace, 50, mPaint);
            start -= (hSpace + space);
            Log.wtf("RainbowBarView", "onDraw: result" + start);

            startc--;
        }


        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        invalidate();

    }
}
