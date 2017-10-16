package com.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import zjw.wine.R;


/**
 * Created by lxs on 2016/6/1.
 */
public class CirTextView extends TextView {

    private Paint paint;

    public CirTextView(Context context) {
        super(context);
        initView();
    }
    public CirTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initView();
    }

    public CirTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs , defStyle);
        // TODO Auto-generated constructor stub
        initView();
    }

    public void initView(){
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.theme_color));
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth()/2;
        canvas.drawCircle(center,center,center,paint);
        super.onDraw(canvas);
    }
}
