import React from 'react';
import { NativeModules, requireNativeComponent } from 'react-native';

const { PanoramaGLManager } = NativeModules;

class PanoramaView extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <RNPanoramaGL ref={ref => this.ref = ref} />
    );
  }
}

const RNPanoramaGL = requireNativeComponent('RNPanoramaGL', PanoramaView, {
  nativeOnly: {
    onChange: true
  }
});

const PanoramaGL = { PanoramaView };

module.exports = PanoramaGL;
