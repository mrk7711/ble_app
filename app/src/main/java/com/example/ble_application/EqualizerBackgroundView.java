package com.example.ble_application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class EqualizerBackgroundView extends View {
    private Paint paint;
    private int bassValue = 0;
    private int midValue = 0;
    private int trebleValue = 0;

    public EqualizerBackgroundView(Context context) {
        super(context);
        init();
    }

    public EqualizerBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.arnica2, null)); // رنگ بنفش برای نوار
        paint.setStyle(Paint.Style.FILL);
    }

    // متد برای دریافت مقادیر
    public void setValues(int bass, int mid, int treble) {
        this.bassValue = bass;
        this.midValue = mid;
        this.trebleValue = treble;
        invalidate(); // بازکشی View
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // محاسبه نقاط مثلث با استفاده از مقادیر SeekBar
        float bassX = width * 0.16f;
        float bassY = height - ((bassValue / 12.0f )* height)-135;
        float midX = width * 0.5f;
        float midY = height - ((midValue / 12f) * height)-135;
        float trebleX = width * 0.83f;
        float trebleY = height - ((trebleValue / 12.0f) * height)-135;

        // رسم مستطیل قرمز رنگ بین نقاط
        Path path = new Path();
        path.moveTo(bassX, bassY); // نقطه اول (Bass)
        path.lineTo(midX, midY);   // نقطه دوم (Mid)
        path.lineTo(trebleX, trebleY); // نقطه سوم (Treble)
        path.lineTo(trebleX, height); // پایین ترین نقطه از نوار
        path.lineTo(bassX, height); // پایین ترین نقطه از نوار
        path.close(); // مثلث بسته می‌شود
        // رسم نوار روی Canvas
        canvas.drawPath(path, paint);
    }

}
