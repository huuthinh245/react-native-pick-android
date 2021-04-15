import React from 'react';
import { requireNativeComponent, StyleProp, ViewStyle, StyleSheet, Dimensions } from 'react-native';

const NativePicker = requireNativeComponent(
  'RNPickerAndroid',
  RNPickerAndroid,
  { nativeOnly: { onChange: true } }
);

class RNPickerAndroid extends  React.Component {

  _onChange = (e) => {
    this.props.onValueChange(e.nativeEvent.data)
  }
  render() {
    return(
      <NativePicker
        style={[styles.picker, this.props.style]}
        initData={this.props.initData.toString()}
        data={this.props.data}
        onChange={this._onChange}
      />
    )
  }
}


const styles = StyleSheet.create({
  picker: {
    width: Dimensions.get('window').width,
    height: 200
  }
})

export default RNPickerAndroid;
