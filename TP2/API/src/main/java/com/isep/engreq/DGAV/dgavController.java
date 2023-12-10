package com.isep.engreq.DGAV;

import com.isep.engreq.util.verifyLocation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dgav")
public class dgavController {


    @PostMapping("/declaracao-anual")
    public ResponseEntity<Map<String, Object>> declaracaoAnual(@RequestBody ApiarioModel[] apiarioModels) {
        Map<String, Object> response = new HashMap<>();

        try {

            int apiarioCount = apiarioModels.length;
            response.put("message", "As " + apiarioCount + " colmeias foram recebidas com sucesso");
            response.put("value", true);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Erro ao processar a requisição");
            response.put("value", false);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping("/verify-location")
    public responseModel verifyLocation(@RequestBody LocationModel locationRequest) {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();

        try {
            Thread.sleep(5000);
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