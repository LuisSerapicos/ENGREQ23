import React, { Component } from 'react'
import { Text, StyleSheet, View } from 'react-native'
import MapView, {Marker} from 'react-native-maps'
import { useSelector } from 'react-redux'
import { selectOrigin } from '../slices/navSlice'

const Map = () => {
    const origin = useSelector(selectOrigin);

    return (
        <View>
            <Text> I am a map </Text>
            <MapView
                style={styles.map}
                initialRegion={{
                    latitude: origin.location.lat,
                    longitude: origin.location.lng,
                    latitudeDelta: 0.005,
                    longitudeDelta: 0.005,
                }}
            >
                {origin?.location && (
                    <Marker 
                        coordinate={{
                            latitude: origin.location.lat,
                            longitude: origin.location.lng
                        }}
                        title='Origin'
                        description={origin.description}
                        identifier='origin'
                    />
                )}
            </MapView>
        </View>
    )
}

export default Map;

const styles = StyleSheet.create(
    {
        map: {
            width: "100%",
            height: "80%",
        }
    }
)
