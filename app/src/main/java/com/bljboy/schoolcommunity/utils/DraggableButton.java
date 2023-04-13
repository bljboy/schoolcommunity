package com.bljboy.schoolcommunity.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;

public class DraggableButton extends View {

    private ViewDragHelper viewDragHelper;

    public DraggableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
//        viewDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            int leftBound = getPaddingLeft();
            int rightBound = getWidth() - child.getWidth() - getPaddingRight();
            return Math.min(Math.max(left, leftBound), rightBound);
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            int topBound = getPaddingTop();
            int bottomBound = getHeight() - child.getHeight() - getPaddingBottom();
            return Math.min(Math.max(top, topBound), bottomBound);
        }
    }
}
