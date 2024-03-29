package com.reactlibrary;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nonnull;

public class PickerManager extends SimpleViewManager<PickerView> {
    public static final String REACT_CLASS = "RNPickerAndroid";
    public static ThemedReactContext context;
    @Nonnull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "data")
    public void init(PickerView view, ReadableArray data) {
        view.initData(data);
    }

    @ReactProp(name = "initData")
    public void setData(PickerView view, String data) {
        view.userValue = data;
    }


    @Nonnull
    @Override
    protected PickerView createViewInstance( ThemedReactContext reactContext) {
        PickerManager.context = reactContext;
        return  new PickerView();
    }
    @SuppressWarnings("unchecked")
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("dateChange", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onChange")
                        )
                ).build();
    }
}
