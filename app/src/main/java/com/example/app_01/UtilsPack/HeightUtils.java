package com.example.app_01.UtilsPack;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class HeightUtils {
    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        listView.post(new Runnable() {
            @Override
            public void run() {
                int total = listView.getPaddingTop() + listView.getPaddingBottom();
                int count = listAdapter.getCount();
                for (int i = 0; i < count; i++) {
                    View listItem = listAdapter.getView(i, null, listView);

                    if (listItem instanceof ViewGroup) {
                        listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    }

                    listItem.measure(
                            View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
                    );
                    total += listItem.getMeasuredHeight();
                }

                ViewGroup.LayoutParams params = listView.getLayoutParams();
                params.height = total + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
                listView.setLayoutParams(params);
                listView.requestLayout();
            }
        });
    }

    public static void setGridViewHeight(GridView gridView, int numColumns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int total = gridView.getPaddingTop() + gridView.getPaddingBottom();
        int numRow = (int) Math.ceil(listAdapter.getCount() * 1.0 / numColumns);
        for (int i = 0; i < numRow; i++) {
            View listItem = listAdapter.getView(i * numColumns, null, gridView);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            );
            total += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = total + 60;
        gridView.setLayoutParams(params);
    }
}
