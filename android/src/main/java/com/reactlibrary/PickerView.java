package com.reactlibrary;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;


import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class PickerView extends RelativeLayout {
    private WheelOrderUpdater wheelOrderUpdater;
    private NumberPickerView picker;
    private String userValue;
    private String[] stringArr;
    public PickerView() {
        super(PickerManager.context);
        View rootView = inflate(getContext(), R.layout.picker_layout,this);
        RelativeLayout wheelsWrapper  = (RelativeLayout) rootView.findViewById(R.id.wheelsWrapper);
        picker = (NumberPickerView) wheelsWrapper.findViewById(R.id.picker);
        wheelsWrapper.setWillNotDraw(false);
        picker.setDividerColor(Color.BLACK);
        picker.setOnValueChangedListener(new NumberPickerView.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
                onChange(stringArr[newVal]);
            }
        });
    }
    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            measure(
                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    public void setValueAtIndex() {
        Log.d("value  index ", getIndex() + "");
        if(getIndex() != -1) {
            picker.setValue(getIndex());
        }
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        post(measureAndLayout);
    }
    public void initData(ReadableArray readableArray) {
        stringArr = new String[readableArray.size()];;
        for (int i = 0; i < readableArray.size(); i++) {
            stringArr[i] = readableArray.getString(i);
        }
        picker.setDisplayedValues(stringArr);
        picker.setMinValue(0);
        picker.setMaxValue(stringArr.length - 1);
        setValueAtIndex();
    }
    public void setUserValue(String userValue) {
        this.userValue =  userValue;
    }
    public int getIndex () {
        List<String> stringList = new ArrayList<String>(Arrays.asList(stringArr));
        Log.d("value  index ", stringList.indexOf("t") + "");
        return stringList.indexOf(userValue);
        //return  5;
    }
    public void onChange(String data) {
        WritableMap event = Arguments.createMap();
        event.putString("data", data);
        PickerManager.context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(),"dateChange", event);
    }

}
