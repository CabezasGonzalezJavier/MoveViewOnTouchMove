package com.thedeveloperworldisyours.moveviewontouchmove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    ImageView mImageView;
    ViewGroup mRoot;
    private int mXDelta;
    private int mYDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRoot = (RelativeLayout)findViewById(R.id.content_main_relative_layout);
        mImageView = (ImageView) findViewById(R.id.content_main_image_view);
        mImageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) mImageView.getLayoutParams();
                mXDelta = x - lParams.leftMargin;
                mYDelta = y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mImageView.getLayoutParams();
                layoutParams.leftMargin = x - mXDelta;
                layoutParams.topMargin = y - mYDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                mImageView.setLayoutParams(layoutParams);
                break;
        }
        mRoot.invalidate();
        return true;
    }
}
