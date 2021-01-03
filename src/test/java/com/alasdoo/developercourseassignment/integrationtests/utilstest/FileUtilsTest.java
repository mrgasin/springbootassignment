package com.alasdoo.developercourseassignment.integrationtests.utilstest;

import com.alasdoo.developercourseassignment.integrationtests.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.testng.annotations.Ignore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Ignore
public class FileUtilsTest {

    @Test
    public void getValue(){
        String result = FileUtils.getValue("selenium.browser");
        assertEquals(result, "edge");
    }

    @Test
    public void getNoneExistingValue(){
        String result = FileUtils.getValue("not.there");
        assertNull(result);
    }

    @Test
    public void getEmptyValue(){
        String result = FileUtils.getValue("spring.datasource.password");
        assertEquals(result, "");
    }
}
