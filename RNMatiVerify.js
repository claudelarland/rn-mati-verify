
import React, { Component } from 'react'
import { requireNativeComponent, TouchableOpacity,NativeModules ,Alert} from 'react-native'

const RNMatiVerify = requireNativeComponent('RNMatiVerify', RNMatiVerifyView)
export const RNMatiFace = NativeModules.RNMatiVerify;
export default class RNMatiVerifyView extends Component {
    constructor(props){
        super(props)
        console.log(this.props)
    }
    _onChange(event) {
    
        this.props.onCancel(event.nativeEvent.message);
    }
    render() {
        return <RNMatiVerify {...this.props} onCancel={this._onChange.bind(this)}/>

    }
}
// RNMatiVerifyView.propTypes = {
//     exampleProp: React.PropTypes.any
// }