
import React from 'react';
import { PickerIOS, StyleProp, ViewStyle, Dimensions, StyleSheet } from 'react-native';

interface Props {
  style: StyleProp<ViewStyle>,
  onValueChange: void,
  initData: String,
  data: Array<String>
}

class RNPickerIos extends React.Component<Props> {

  _onChange = (e) => {
    this.props.onValueChange(e)
  }
  render() {
    return (
      <PickerIOS
        style={[styles.picker, this.props.style]}
        selectedValue={this.props.initData}
        onValueChange={this._onChange}
      >
        {this.props.data.map((c) => 
        <PickerIOS.Item key={c} label={c} value={c} />
        )}
      </PickerIOS>
    )
  }
}


const styles = StyleSheet.create({
  picker: {
    width: Dimensions.get('window').width,
    height: 200
  }
})

export default RNPickerIos;
