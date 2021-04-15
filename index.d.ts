
import React from 'react';
  interface Props {
    style?: StyleProp<ViewStyle>
    onValueChange:(data: string) => void
    initData: String
    data: Array<String>
  }
  
export default class RNPickerAndroid extends React.Component<Props> {}