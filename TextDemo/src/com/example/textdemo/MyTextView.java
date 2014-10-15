package com.example.textdemo;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MyTextView extends View {

	private Paint m_TextPaint;
	private boolean m_bDrawSideLine = false; // 默认不采用描边

	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public MyTextView(Context context) {
		super(context);
		init();
	}

	public void init() {
		m_TextPaint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (m_bDrawSideLine) {
			// 描外层
			// super.setTextColor(Color.BLUE); // 不能直接这么设，如此会导致递归
			setTextColorUseReflection(Color.BLUE);
			m_TextPaint.setStrokeWidth(3); // 描边宽度
			m_TextPaint.setStyle(Style.FILL_AND_STROKE); // 描边种类
			m_TextPaint.setFakeBoldText(true); // 外层text采用粗体
			m_TextPaint.setShadowLayer(1, 0, 0, 0); // 字体的阴影效果，可以忽略
			super.onDraw(canvas);

			// 描内层，恢复原先的画笔

			// super.setTextColor(Color.BLUE); // 不能直接这么设，如此会导致递归
			setTextColorUseReflection(Color.RED);
			m_TextPaint.setStrokeWidth(0);
			m_TextPaint.setStyle(Style.FILL_AND_STROKE);
			m_TextPaint.setFakeBoldText(false);
			m_TextPaint.setShadowLayer(0, 0, 0, 0);
		}
		super.onDraw(canvas);
	}

	private void setTextColorUseReflection(int color) {
		Field textColorField;
		try {
			textColorField = TextView.class.getDeclaredField("mCurTextColor");
			textColorField.setAccessible(true);
//			textColorField.set(color);
			textColorField.setAccessible(false);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} /*catch (IllegalAccessException e) {
			e.printStackTrace();
		}*/
		m_TextPaint.setColor(color);
	}
}
