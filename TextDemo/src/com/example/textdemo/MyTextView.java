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
	private boolean m_bDrawSideLine = false; // Ĭ�ϲ��������

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
			// �����
			// super.setTextColor(Color.BLUE); // ����ֱ����ô�裬��˻ᵼ�µݹ�
			setTextColorUseReflection(Color.BLUE);
			m_TextPaint.setStrokeWidth(3); // ��߿��
			m_TextPaint.setStyle(Style.FILL_AND_STROKE); // �������
			m_TextPaint.setFakeBoldText(true); // ���text���ô���
			m_TextPaint.setShadowLayer(1, 0, 0, 0); // �������ӰЧ�������Ժ���
			super.onDraw(canvas);

			// ���ڲ㣬�ָ�ԭ�ȵĻ���

			// super.setTextColor(Color.BLUE); // ����ֱ����ô�裬��˻ᵼ�µݹ�
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
