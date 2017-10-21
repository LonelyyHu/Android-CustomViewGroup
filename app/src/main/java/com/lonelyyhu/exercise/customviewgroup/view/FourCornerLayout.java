package com.lonelyyhu.exercise.customviewgroup.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hulonelyy on 2017/10/19.
 */

public class FourCornerLayout extends ViewGroup {


    public FourCornerLayout(Context context) {
        super(context);
    }

    public FourCornerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FourCornerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FourCornerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        MarginLayoutParams childParams;

        int childCount = getChildCount();
        int childWidth = 0;
        int childHeight = 0;

        int leftHeight = 0;
        int rightHeight = 0;

        int topWidth = 0;
        int bottomWidth = 0;

        for (int i=0; i<childCount; i++) {
            View childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            childParams = (MarginLayoutParams) childView.getLayoutParams();


            if (i==0 || i==1) {
                topWidth += childWidth + childParams.leftMargin + childParams.rightMargin;
            }

            if (i==0 || i==2) {
                leftHeight += childHeight + childParams.topMargin + childParams.bottomMargin;
            }

            if (i==1 || i==3) {
                rightHeight += childHeight + childParams.topMargin + childParams.bottomMargin;
            }

            if (i==2 || i==3) {
                bottomWidth += childWidth + childParams.leftMargin + childParams.rightMargin;
            }

        }

        width = Math.max(topWidth, bottomWidth);
        height = Math.max(leftHeight, rightHeight);

        Log.wtf("CustomViewGroup", "onMeasure =>" + (widthMode == MeasureSpec.EXACTLY));
        Log.wtf("CustomViewGroup", "onMeasure => sizeWidth:" + sizeWidth);
        Log.wtf("CustomViewGroup", "onMeasure => width:" + width);
        Log.wtf("CustomViewGroup", "onMeasure =>" + (heightMode == MeasureSpec.EXACTLY));
        Log.wtf("CustomViewGroup", "onMeasure => sizeHeight:" + sizeHeight);
        Log.wtf("CustomViewGroup", "onMeasure => height:" + height);

        setMeasuredDimension( (widthMode == MeasureSpec.EXACTLY) ? sizeWidth : width,
                              (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : height );

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        Log.wtf("CustomViewGroup", "onLayout => getHeight:" + getHeight());
        Log.wtf("CustomViewGroup", "onLayout => getWidth:" + getWidth());

        int childCount = getChildCount();
        int childWidth = 0;
        int childHeight = 0;
        MarginLayoutParams childParams = null;

        for (int idx=0; idx < childCount; idx++) {
            View childView = getChildAt(idx);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            childParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;

            switch (idx) {
                case 0:
                    cl = childParams.leftMargin;
                    ct = childParams.topMargin;
                    break;

                case 1:
                    ct = childParams.topMargin;
                    cl = getWidth() - childWidth - childParams.rightMargin - childParams.rightMargin;
                    break;

                case 2:
                    ct = getHeight() - childHeight - childParams.topMargin - childParams.bottomMargin;
                    cl = childParams.leftMargin;
                    break;

                case 3:
                    ct = getHeight() - childHeight - childParams.topMargin - childParams.bottomMargin;
                    cl = getWidth() - childWidth - childParams.rightMargin - childParams.rightMargin;
                    break;

            }

            cr = cl + childWidth;
            cb = ct + childHeight;

            Log.wtf("CustomViewGroup", "onLayout => left:" + cl);
            Log.wtf("CustomViewGroup", "onLayout => top:" + ct);



            childView.layout(cl, ct, cr, cb);

        }


    }
}
