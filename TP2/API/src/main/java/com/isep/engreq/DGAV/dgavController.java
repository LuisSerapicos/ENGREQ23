package com.isep.engreq.DGAV;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dgav")
public class dgavController {

    @GetMapping("/info")
    public String getExample() {
        return "Lista com informações";
    }


    @PostMapping("/verify-location")
    public responseModel verifyLocation(@RequestBody LocationModel locationRequest) {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();



        boolean isInsidePortugal = isLocationInsidePortugal(latitude, longitude);



        if (isInsidePortugal) {
            return new responseModel(true, "Localização dentro de Portugal");
        } else {
            return new responseModel(false, "Localização fora de Portugal");
        }




    }

    private boolean isLocationInsidePortugal(double latitude, double longitude) {
        // Define the geographic boundaries for Portugal
        double minLatitude = 36.961;
        double maxLatitude = 42.151;
        double minLongitude = -9.501;
        double maxLongitude = -6.189;

        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Min Latitude: " + minLatitude);
        System.out.println("Max Latitude: " + maxLatitude);
        System.out.println("Min Longitude: " + minLongitude);
        System.out.println("Max Longitude: " + maxLongitude);


        boolean result = latitude >= minLatitude && latitude <= maxLatitude &&
                longitude >= minLongitude && longitude <= maxLongitude;

        System.out.println("Result: " + result);

        return result;
    }

}