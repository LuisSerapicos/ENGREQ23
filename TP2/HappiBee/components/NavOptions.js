import { useNavigation } from "@react-navigation/native";
import React from "react";
import {Image, FlatList, Pressable, Text, TouchableOpacity, View} from "react-native";
import { Icon } from 'react-native-elements';
import { useSelector } from "react-redux";
import { selectOrigin } from "../slices/navSlice";

const data = [
    {
        id: "123",
        title: "Get a ride",
        image: "https://links.papareact.com/3pn",
        screen: "MapScreen",
    },
    {
        id: "456",
        title: "Order food",
        image: "https://links.papareact.com/28w",
        screen: "EatsScreen",
    }
]

const NavOptions = () => {
    const navigation = useNavigation();
    const origin = useSelector(selectOrigin);

    return (
        <FlatList 
            data={data}
            keyExtractor={(item) => item.id}
            renderItem={({item}) => (
                <Pressable onPress={() => navigation.navigate(item.screen)} style={{backgroundColor: "gray", width: 130, margin: 15}} disabled={!origin}>
                    <View style={!origin && {opacity: 0.2}}>
                        <Text>{item.title}</Text>
                        <Image 
                            style={{width: 120, height: 120, resizeMode: "contain"}}
                            source={{uri: item.image}}
                        />
                    </View>
                    <Icon 
                        style={{backgroundColor: "black", borderRadius: 100, width: 25}}
                        name="arrowright"
                        color={"white"}
                        type="antdesign"
                    />
                </Pressable>
            )}
        />
    );
};

export default NavOptions;