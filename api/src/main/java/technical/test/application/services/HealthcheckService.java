package technical.test.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import technical.test.application.models.HealthcheckResponseModel;

@Service
public class HealthcheckService {

    private final HealthcheckResponseModel healthcheckResponseModel;

    @Autowired
    public HealthcheckService(
            BuildProperties buildProperties,
            @Value("${application.healthcheck.description}") String description,
            @Value("${application.healthcheck.commitSha}") String lastCommitSha
    ) {
        this.healthcheckResponseModel = new HealthcheckResponseModel(
                buildProperties.getVersion(),
                description,
                lastCommitSha
        );
    }

    public HealthcheckResponseModel getHealthcheckResponse() {
        return healthcheckResponseModel;
    }

}
