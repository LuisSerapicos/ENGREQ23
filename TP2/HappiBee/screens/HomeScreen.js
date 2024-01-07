import React, { Component } from 'react'
import { Image, ScrollView, Text, TextInput, View } from 'react-native';
import NavOptions from "../components/NavOptions"
import { GOOGLE_MAPS_APIKEY } from "@env";
import { GooglePlacesAutocomplete } from 'react-native-google-places-autocomplete';
import { useDispatch } from 'react-redux';
import { setDestination, setOrigin } from '../slices/navSlice';

const HomeScreen = () => {
  const dispatch = useDispatch();

  return (
  <ScrollView style={{backgroundColor: "lightblue"}}>
      <Text>Some text</Text>
      <View>
          <Text style={{color: "blue"}}>Some more text</Text>
          <Image
          source={{
              uri: 'https://reactnative.dev/docs/assets/p_cat2.png',
          }}
          style={{width: 200, height: 200}}
          />
      </View>
      <TextInput
          style={{
          height: 40,
          borderColor: 'gray',
          borderWidth: 1,
          }}
          defaultValue="ESCREVE AQUI..."
      />

      <GooglePlacesAutocomplete 
        placeholder='Where from?'
        nearbyPlacesAPI='GooglePlacesSearch'
        debounce={400}
        onPress={(data, details = null) => {
          dispatch(setOrigin({
            location: details.geometry.location,
            description: data.description
          }));

          dispatch(setDestination(null));
        }}
        fetchDetails={true}
        enablePoweredByContainer={false}
        query={
          {
            key: GOOGLE_MAPS_APIKEY,
            language: "pt-PT"
          }
        }
      />

      <NavOptions />
  </ScrollView>
  )
}

export default HomeScreen
