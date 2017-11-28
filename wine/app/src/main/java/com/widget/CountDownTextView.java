package com.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海来伊份电子商务有限公司</p>
 * <p>包名:com.widget</p>
 * <p>文件名:wine</p>
 * <p>类更新历史信息</p>
 *
 * @todo <a href="mailto:zhoujiawei@laiyifen.com">vernal(周佳伟)</a>
 */
public class CountDownTextView extends TextView {

    OnFinishListener onFinishListener;

    public CountDownTextView(Context context) {
        super(context);

    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public CountDownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void startCountDown() {
        timer.start();
        setEnabled(false);

    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

            setText((millisUntilFinished / 1000) + "秒后可重发");

        }

        @Override
        public void onFinish() {
            setEnabled(true);
            setText("获取验证码");
            cancel();
            if (onFinishListener != null) {
                onFinishListener.onFinish();
            }
        }
    };

    public interface OnFinishListener {
        void onFinish();
    }

    public void setOnfinishListener(OnFinishListener onfinishListener) {
      this.onFinishListener=onfinishListener;
    }
}

