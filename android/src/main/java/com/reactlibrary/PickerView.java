package com.reactlibrary;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
    public  String userValue;
    private String[] stringArr;
    public PickerView() {
        super(PickerManager.context);
        View rootView = inflate(getContext(), R.layout.picker_layout,this);
        LinearLayout wheelsWrapper  = (LinearLayout) rootView.findViewById(R.id.wheelsWrapper);
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

    @Override
    public void requestLayout() {
        super.requestLayout();
        post(measureAndLayout);
    }

    public void initData(ReadableArray readableArray) {
        ArrayList<Object>  data = readableArray.toArrayList();
        stringArr = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            Object obj = data.get(i);
            stringArr[i] = convertData(obj);
        }
        picker.setDisplayedValues(stringArr);
        picker.setMinValue(0);
        picker.setMaxValue(stringArr.length - 1);
        picker.setWrapSelectorWheel(true);
        picker.refreshByNewDisplayedValues(stringArr);
        getIndex();
    }

    public String convertData(Object data) {
        if (data instanceof Double) {
            return String.valueOf(((Double) data).intValue());
        } else if (data instanceof Boolean) {
            return String.valueOf((Boolean) data);
        } else {
            return data.toString();
        }
    }

    public void getIndex () {
        List<String> stringList = new ArrayList<String>(Arrays.asList(stringArr));
        String data = this.userValue;
        if(data != null)  {
            int index = stringList.indexOf(data);
            picker.setValue(index);
        }
    }

    public void onChange(String data) {
        WritableMap event = Arguments.createMap();
        event.putString("data", data);
        PickerManager.context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(),"dateChange", event);
    }

}
