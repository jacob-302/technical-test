package technical.test.application.utils;

import java.util.regex.Pattern;

public class TestData {

    public static final String DESCRIPTION = "pre-interview technical test";

    // This will match either a pattern like 0.0.1-SNAPSHOT or 0.0.1
    public static final Pattern VERSION_PATTERN = Pattern.compile(
            "^([0-9]{1,}\\.[0-9]{1,}\\.[0-9]{1,}\\-SNAPSHOT|[0-9]{1,}\\.[0-9]{1,}\\.[0-9]{1,})$"
    );

    public static final String VERSION = "0.0.1";

    public static final String COMMIT_SHA = "aaaaa";

}
