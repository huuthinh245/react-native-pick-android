declare module 'react-native-pick-android' {
  import { Component } from 'react';
  import { StyleProp, ViewStyle, StyleSheet, Dimensions } from 'react-native';
  interface Props {
    style: StyleProp<ViewStyle>,
    onValueChange: (value: any) => void,
    initData: String,
    data: Array<String>
  }
  class Picker extends Component<Props> {
  }
  export default Picker;
}
