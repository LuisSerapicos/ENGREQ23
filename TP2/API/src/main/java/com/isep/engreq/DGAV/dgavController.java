package com.isep.engreq.DGAV;

import com.isep.engreq.util.verifyLocation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dgav")
public class dgavController {


    @PostMapping("/declaracao-anual")
    public ResponseEntity<Map<String, String>> declaracaoAnual(@RequestBody ApiarioModel[] apiarioModels) {

        int apiarioCount = apiarioModels.length;
        Map<String, String> response = new HashMap<>();
        response.put("message", "As " + apiarioCount + " colmeias foram recebidas com sucesso");
        response.put("status", "success");

        return ResponseEntity.ok(response);
    }




    @PostMapping("/verify-location")
    public responseModel verifyLocation(@RequestBody LocationModel locationRequest) {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();

        // 5-second delay
        try {
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        boolean isInsidePortugal = verifyLocation.isInsidePortugal(latitude, longitude);

        if (isInsidePortugal) {
            return new responseModel(true, "A DGAV aprovou a localização");
        } else {
            return new responseModel(false, "A DGAV não aprovou a localização");
        }

    }

}