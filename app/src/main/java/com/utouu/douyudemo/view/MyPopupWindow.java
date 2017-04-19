package com.utouu.douyudemo.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Create by 李俊鹏 on 2017/4/19 11:14
 * Function：
 * Desc：
 */
public class MyPopupWindow extends PopupWindow {

    public View view;


    public MyPopupWindow(Activity context, int layoutId) {
        super(context);

        view = LayoutInflater.from(context).inflate(layoutId, null);
        setContentView(view);

        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
//        this.setAnimationStyle(R.style.AppTheme);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        this.setBackgroundDrawable(dw);
        setOutsideTouchable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public View getView() {
        return view;
    }


}
