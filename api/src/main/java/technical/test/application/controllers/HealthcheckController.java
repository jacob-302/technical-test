package technical.test.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import technical.test.application.models.HealthcheckResponseModel;
import technical.test.application.services.HealthcheckService;

@RestController
public class HealthcheckController {

    @Autowired
    private HealthcheckService healthcheckService;

    @GetMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthcheckResponseModel> healthcheck() {
        return ResponseEntity.ok(healthcheckService.getHealthcheckResponse());
    }
}
