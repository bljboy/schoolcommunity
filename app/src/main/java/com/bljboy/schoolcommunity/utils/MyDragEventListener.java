package com.bljboy.schoolcommunity.utils;

import android.view.DragEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;


public class MyDragEventListener implements View.OnDragListener {
    private int mLastX, mLastY;

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;

            case DragEvent.ACTION_DROP: // 当用户放开控件时
                int x = (int) event.getX(); // 获取当前位置的横坐标
                int y = (int) event.getY(); // 获取当前位置的纵坐标

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = x;
                params.topMargin = y;
                v.setLayoutParams(params); // 设置新的布局参数

                mLastX = x; // 将控件位置保存
                mLastY = y;
                break;

            case DragEvent.ACTION_DRAG_LOCATION: // 当用户拖拽控件时
                int x1 = (int) event.getX(); // 获取当前位置的横坐标
                int y2 = (int) event.getY(); // 获取当前位置的纵坐标

                // 将控件移动到新位置
                int deltaX = x1 - mLastX;
                int deltaY = y2 - mLastY;
                v.offsetLeftAndRight(deltaX);
                v.offsetTopAndBottom(deltaY);
                break;

            default:
                break;
        }
        return true;
    }
}
