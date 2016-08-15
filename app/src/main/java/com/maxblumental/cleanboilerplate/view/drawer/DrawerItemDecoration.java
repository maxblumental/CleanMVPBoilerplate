package com.maxblumental.cleanboilerplate.view.drawer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.R.color.white;
import static android.support.v4.content.res.ResourcesCompat.getColor;

public class DrawerItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        Paint paint = new Paint();
        Resources resources = parent.getContext().getResources();
        paint.setColor(getColor(resources, white, null));

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int bottom = child.getBottom();
            int right = child.getRight();

            c.drawLine(0, bottom, right, bottom, paint);
        }
    }
}