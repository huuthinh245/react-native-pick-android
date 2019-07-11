package com.reactlibrary;

import android.widget.RelativeLayout;

public class WheelOrderUpdater
{
    private final PickerView pickerView;

    WheelOrderUpdater(final PickerView v) {
        this.pickerView = v;
    }



    private RelativeLayout.LayoutParams getDefaultLayoutParams(){
        return new RelativeLayout.LayoutParams(-2, pickerView.getMeasuredHeight());
    }

}

