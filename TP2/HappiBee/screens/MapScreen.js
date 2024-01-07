import React, { Component } from 'react'
import { Text, View } from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'
import Map from '../components/Map'
import MapView from 'react-native-maps'

const MapScreen = () => {
  return (
    <View>
      <Text> textInComponent </Text>

      <View>
        <Text> textInComponent </Text>
        <Map />
      </View>

      <View>
        <Text> textInComponent </Text>
      </View>
    </View>
  )
}

export default MapScreen
