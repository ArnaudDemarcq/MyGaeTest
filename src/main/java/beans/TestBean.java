package beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBean {

    private static final Logger logger = LoggerFactory.getLogger(TestBean.class);
    private String testString = "initial value";

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {

        this.testString = testString;
    }

    @Override
    public String toString() {
        return testString;
    }
}
