package com.lonelyyhu.exercise.customviewgroup.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lonelyyhu.exercise.customviewgroup.R;

/**
 * Created by hulonelyy on 2017/10/20.
 */

public class MoveableBallView extends View {

    Paint p = new Paint();
    public float currentX = 100;
    public float currentY = 100;
    public int textColor;

    public MoveableBallView(Context context) {
        super(context);
        init(context, null);
    }

    public MoveableBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MoveableBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MoveableBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs == null) {
            textColor = Color.BLACK;
        } else {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.moveableBallView);
            textColor = typedArray.getColor(R.styleable.moveableBallView_TextColor, Color.BLACK);
            typedArray.recycle();
        }

        Log.wtf("MoveableBallView", "init: " + Color.BLACK);
        Log.wtf("MoveableBallView", "init: " + textColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p.setColor(Color.BLUE);
        canvas.drawCircle(currentX, currentY, 100, p);

        p.setColor(textColor);
        p.setTextSize(50);
        canvas.drawText("Hello", currentX-30, currentY+100, p);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        currentY = event.getY();
        invalidate();
        return true;
    }
}
