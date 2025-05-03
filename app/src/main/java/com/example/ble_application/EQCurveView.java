package com.example.ble_application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
public class EQCurveView extends View {
    private Paint paint;
    private int bassValue = 0;
    private int midValue = 0;
    private int trebleValue = 0;

    public void setValues(int bass, int mid, int treble) {
        this.bassValue = bass;
        this.midValue = mid;
        this.trebleValue = treble;
        invalidate();  // بازنقاشی
    }

    public EQCurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.arnica2, null));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        int width = getWidth();
        int height = getHeight();

        float bassX = width * 0.165f;
        float bassY = height - ((bassValue / 10.0f )* height)-470;
        float midX = width * 0.5f;
        float midY = height - ((midValue / 10.0f) * height)-470;
        float trebleX = width * 0.83f;
        float trebleY = height - ((trebleValue / 10.0f) * height)-470;

        Path path = new Path();
        path.moveTo(bassX, bassY); // نقطه اول (Bass)
        path.lineTo(midX, midY);   // نقطه دوم (Mid)
        path.lineTo(trebleX, trebleY); // نقطه سوم (Treble)
        path.lineTo(trebleX, height); // پایین ترین نقطه از نوار
        path.lineTo(bassX, height); // پایین ترین نقطه از نوار
        path.close(); // مثلث بسته می‌شود

        canvas.drawPath(path, paint);
    }
}

