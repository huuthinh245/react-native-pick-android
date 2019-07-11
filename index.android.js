import React from 'react';
import { NativeModules, requireNativeComponent, StyleProp, ViewStyle, StyleSheet, Dimensions } from 'react-native';

const NativePicker = requireNativeComponent(
  'RNPickerAndroid',
  RNPickerAndroid,
  { nativeOnly: { onChange: true } }
);
interface Props {
  style: StyleProp<ViewStyle>,
  onValueChange: void
  initialData: String
  data: Array<String>
}

class RNPickerAndroid extends  React.Component<Props> {

  _onChange = (e) => {
    this.props.onValueChange(e.nativeEvent.data)
  }
  render() {
    return(
      <NativePicker
        style={[styles.picker, this.props.style]}
        initialData={this.props.initialData}
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
