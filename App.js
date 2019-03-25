/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from 'react';
import { Platform, StyleSheet, Text, View, Alert, TouchableOpacity, Dimensions,DeviceEventEmitter } from 'react-native';
import ReactNativeMatiVerify, { RNMatiFace } from "./RNMatiVerify"

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {

  constructor(props) {
    super(props);
  }
  componentDidMount(){
   
   // RNMatiFace.init("5c8a88fc0c43f7001bbab6b0")
  }

  press = () => {
    RNMatiFace.openWindow()
    console.log('cons', typeof ReactNativeMatiVerify)
  }

  cancel=()=>{
    Alert.alert('canceled')


  }

  success=()=>{
    Alert.alert('succeed')


  }

  error=()=>{
    Alert.alert('errr')


  }

  on(event) {
    Alert.alert('js')
    RNMatiFace.show("Canceled from js")
  }
  render() {
    return (
      <View style={styles.container}>
        <Text>Bienvenu(e) dans la vérification de votre identité...</Text>
        <Text>Cliquer pour continuer</Text>
        <TouchableOpacity onPress={() => this.press()} style={{}}>
          <ReactNativeMatiVerify
            key='5c8a88fc0c43f7001bbab6b0'
            onCancel={this.cancel} 
            onSuccess={this.success} 
            title="Verify Identity" style={{ width: Dimensions.get('screen').width - 50, height: 50, alignItems: 'center', }} />
        </TouchableOpacity>

      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
