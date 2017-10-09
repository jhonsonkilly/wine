package com.androidyuan.frame.cores.widget;

/**
 * Created by wei on 2014/9/10.
 */

import android.content.Context;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * 功能：自定义验证码实现类
 * 博客：http://blog.csdn.net/u010538765/article/
 * 时间:2013.8.19
 *
 * @author Jacky
 */
class CheckView extends View {

    //随机生成所有的数组
    final String[] strContent = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    //验证码图片
    private Bitmap bitmap = null;

    //构造函数
    public CheckView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    //获得画干扰直线的位置
    public static int[] getLine(int height, int width) {

        int[] tempCheckNum = {0, 0, 0, 0, 0};
        for (int i = 0; i < 4; i += 2) {
            tempCheckNum[i] = (int) (Math.random() * width);
            tempCheckNum[i + 1] = (int) (Math.random() * height);
        }
        return tempCheckNum;
    }

    //获得干扰点的位置
    public static int[] getPoint(int height, int width) {

        int[] tempCheckNum = {0, 0, 0, 0, 0};
        tempCheckNum[0] = (int) (Math.random() * width);
        tempCheckNum[1] = (int) (Math.random() * height);
        return tempCheckNum;
    }

    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, paint);
        }
        else {
            paint.setColor(Color.GRAY);
            paint.setTextSize(20);
            canvas.drawText("点击换一换", 10, 30, paint);
        }
        super.draw(canvas);
    }

    /**
     * 得到验证码；设置图片
     *
     * @return 生成的验证码中的数字
     */
    public String[] getValidataAndSetImage() {
        //产生随机数
        String[] strRes = generageRadom(strContent);
        //传随机串和随机数
        bitmap = generateValidate(strContent, strRes);
        //刷新
        invalidate();

        return strRes;
    }

    /**
     * 绘制验证码并返回
     *
     * @param strContent
     * @param strRes
     * @return
     */
    private Bitmap generateValidate(String[] strContent, String[] strRes) {

        int width = 120, height = 57;

        int isRes = isStrContent(strContent);
        if (isRes == 0) {
            return null;
        }

        //创建图片和画布
        Bitmap sourceBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(sourceBitmap);
        canvas.drawColor(Color.YELLOW);
        Paint numPaint = new Paint();
        numPaint.setTextSize(40);
        numPaint.setFakeBoldText(true);
        numPaint.setColor(Color.BLACK);

        //设置每个字
        canvas.drawText(strRes[0], 10, height * 3 / 4, numPaint);
        Matrix mMatrix = new Matrix();
        mMatrix.setRotate((float) Math.random() * 25);
        canvas.setMatrix(mMatrix);

        canvas.drawText(strRes[1], 40, height * 3 / 4, numPaint);
        mMatrix.setRotate((float) Math.random() * 25);
        canvas.setMatrix(mMatrix);

        canvas.drawText(strRes[2], 70, height * 3 / 4 - 10, numPaint);
        mMatrix.setRotate((float) Math.random() * 25);
        canvas.setMatrix(mMatrix);

        canvas.drawText(strRes[3], 100, height * 3 / 4 - 15, numPaint);
        mMatrix.setRotate((float) Math.random() * 25);
        canvas.setMatrix(mMatrix);

        //设置绘制干扰的画笔
        Paint interferencePaint = new Paint();
        interferencePaint.setAntiAlias(true);
        interferencePaint.setStrokeWidth(4);
        interferencePaint.setColor(Color.BLACK);
        interferencePaint.setStyle(Paint.Style.FILL);    //设置paint的style

        //绘制直线
        int[] line;
        for (int i = 0; i < 2; i++) {
            line = getLine(height, width);
            canvas.drawLine(line[0], line[1], line[2], line[3], interferencePaint);
        }
        // 绘制小圆点
        int[] point;
        for (int i = 0; i < 100; i++) {
            point = getPoint(height, width);
            canvas.drawCircle(point[0], point[1], 1, interferencePaint);
        }

        canvas.save();
        return sourceBitmap;
    }

    private int isStrContent(String[] strContent) {

        if (strContent == null || strContent.length <= 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    /**
     * 从指定数组中随机取出4个字符(数组)
     *
     * @param strContent
     * @return
     */
    private String[] generageRadom(String[] strContent) {

        String[] str = new String[4];
        // 随机串的个数
        int count = strContent.length;
        // 生成4个随机数
        Random random = new Random();
        int randomResFirst = random.nextInt(count);
        int randomResSecond = random.nextInt(count);
        int randomResThird = random.nextInt(count);
        int randomResFourth = random.nextInt(count);

        str[0] = strContent[randomResFirst].toString().trim();
        str[1] = strContent[randomResSecond].toString().trim();
        str[2] = strContent[randomResThird].toString().trim();
        str[3] = strContent[randomResFourth].toString().trim();
        return str;
    }

    /**
     * 从指定数组中随机取出4个字符(字符串)
     *
     * @param strContent
     * @return
     */
    private String generageRadomStr(String[] strContent) {

        StringBuilder str = new StringBuilder();
        // 随机串的个数
        int count = strContent.length;
        // 生成4个随机数
        Random random = new Random();
        int randomResFirst = random.nextInt(count);
        int randomResSecond = random.nextInt(count);
        int randomResThird = random.nextInt(count);
        int randomResFourth = random.nextInt(count);

        str.append(strContent[randomResFirst].toString().trim());
        str.append(strContent[randomResSecond].toString().trim());
        str.append(strContent[randomResThird].toString().trim());
        str.append(strContent[randomResFourth].toString().trim());
        return str.toString();
    }

    /**
     * 给定范围获得随机颜色，未使用
     *
     * @param rc 0-255
     * @param gc 0-255
     * @param bc 0-255
     * @return colorValue 颜色值，使用setColor(colorValue)
     */
    public int getRandColor(int rc, int gc, int bc) {

        Random random = new Random();
        if (rc > 255)
            rc = 255;
        if (gc > 255)
            gc = 255;
        if (bc > 255)
            bc = 255;
        int r = rc + random.nextInt(rc);
        int g = gc + random.nextInt(gc);
        int b = bc + random.nextInt(bc);
        return Color.rgb(r, g, b);
    }
}