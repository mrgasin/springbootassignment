package com.alasdoo.developercourseassignment.integrationtests.utilstest;

import com.alasdoo.developercourseassignment.integrationtests.utils.CaseFormat;
import org.junit.jupiter.api.Test;
import org.testng.annotations.Ignore;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Ignore
public class CaseFormatTest {
    @Test
    public void splitOnUpper(){
        String test = "SplitThisStringOnUpperCases";
        String result = CaseFormat.toLowerUnderscore(test);
        assertEquals(result, "split_this_string_on_upper_cases");
    }

    @Test
    public void splitWithNoUpper(){
        String test = "splitthisstringonuppercases";
        String result = CaseFormat.toLowerUnderscore(test);
        assertEquals(result, test);
    }

    @Test
    public void splitWithSpaces(){
        String test = "Split This String On Upper Cases";
        String result = CaseFormat.toLowerUnderscore(test);
        assertEquals(result, "split_this_string_on_upper_cases");
    }
}
