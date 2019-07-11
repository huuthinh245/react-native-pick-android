

import { Platform }  from 'react-native';
import PickerIos from './index.ios';
import PickerAndroid from './index.android';

const Picker = Platform.select({
  android: PickerAndroid,
  ios: PickerIos
})

export {
  Picker
}

