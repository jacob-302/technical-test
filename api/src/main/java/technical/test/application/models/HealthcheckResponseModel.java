package technical.test.application.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;
import technical.test.application.services.HealthcheckService;

@Configuration
public class HealthcheckResponseModel {

    private String version;
    private String description;
    private String lastCommitSha;

    public HealthcheckResponseModel() {}

    public HealthcheckResponseModel(
            String version,
            String description,
            String lastCommitSha
    ) {
        this.version = version;
        this.description = description;
        this.lastCommitSha = lastCommitSha;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }

}
