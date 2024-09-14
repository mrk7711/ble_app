
package com.example.ble_application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class EqualizerGraphView extends View {

    private Paint paint;
    private Path path;

    // Values for the sliders (could be updated dynamically)
    private float bassLevel = 0.2f;  // Example level for Bass (normalized between 0.0 and 1.0)
    private float midLevel = 0.8f;   // Example level for Mid
    private float trebleLevel = 0.2f; // Example level for Treble

    public EqualizerGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Initialize the paint object to define the red fill
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        // Initialize the path object to draw the shape
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // X positions of sliders (left: Bass, center: Mid, right: Treble)
        float bassX = width * 0.2f;  // 20% from the left
        float midX = width * 0.5f;   // 50% from the left
        float trebleX = width * 0.8f; // 80% from the left

        // Y positions based on the slider values (0.0 at bottom, 1.0 at top)
        float bassY = height * (1 - bassLevel);
        float midY = height * (1 - midLevel);
        float trebleY = height * (1 - trebleLevel);

        // Clear the previous path
        path.reset();

        // Move to the bass point
        path.moveTo(bassX, bassY);

        // Draw a line to the mid point
        path.lineTo(midX, midY);

        // Draw a line to the treble point
        path.lineTo(trebleX, trebleY);

        // Close the path by drawing lines to the bottom of the view
        path.lineTo(trebleX, height);
        path.lineTo(bassX, height);
        path.close();

        // Draw the path (red area) on the canvas
        canvas.drawPath(path, paint);
    }

    // Method to update the levels dynamically (called from the Activity)
    public void setLevels(float bass, float mid, float treble) {
        this.bassLevel = bass;
        this.midLevel = mid;
        this.trebleLevel = treble;
        invalidate();  // Redraw the view with updated levels
    }
}
