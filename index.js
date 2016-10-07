import React, { PropTypes } from 'react';
import {
  View,
  NativeModules,
  requireNativeComponent
} from 'react-native';

const { PanoramaGLManager } = NativeModules;

class PanoramaView extends React.Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <RNPanoramaGLView
        {...this.props}
        ref={ref => this.ref = ref}
      />
    )
  }
}

PanoramaView.propTypes = {
  image: React.PropTypes.string,
}

const RNPanoramaGLView = requireNativeComponent('RNPanoramaGL', PanoramaView, {
  nativeOnly: {}
});

const PanoramaGL = { PanoramaView };

module.exports = PanoramaGL;
