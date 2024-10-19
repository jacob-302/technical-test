package technical.test.application.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import technical.test.application.models.HealthcheckResponseModel;
import technical.test.application.utils.TestData;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HealthcheckServiceTests extends TestData {

    @Autowired
    private HealthcheckService healthcheckService;

    @Test
    public void validateHealthcheckResponse() {
        HealthcheckResponseModel test = healthcheckService.getHealthcheckResponse();

        assertThat(test.getDescription()).contains(DESCRIPTION);
        assertThat(test.getVersion()).containsPattern(VERSION_PATTERN);
        assertThat(test.getLastCommitSha()).contains(COMMIT_SHA);
    }
}
