package TestCases;

import Utilities.Utility;
import com.halodoc.SmartAPi;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.logging.Logger;

public class TestCaseSuite extends Utility {
    SmartAPi smartAPi;

    public TestCaseSuite() throws IOException {
        smartAPi = new SmartAPi();
    }


    @Test(priority = 1, enabled = true)
    public void validateCreateResponse() {

        Logger logger = Logger.getLogger(TestCaseSuite.class.getName());

        try
        {
            smartAPi.createObject();
            smartAPi.updateObject();
            smartAPi.validateDetails();
            test.pass("Validation Successful");

        } catch (Exception e)
        {


            test.fail(e.getMessage() +  "Response from API = 200"  + "is not matching with Excepected value" ) ;

        }
    }


    @Test(priority = 2, dependsOnMethods = {"validateCreateResponse"})
    public void validateGetResponse() {

        Logger logger = Logger.getLogger(TestCaseSuite.class.getName());
        test = reports.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());

        try {

            smartAPi.updateObject();


        } catch (Exception e) {

            test.fail(e.getMessage());

        }
    }


}
