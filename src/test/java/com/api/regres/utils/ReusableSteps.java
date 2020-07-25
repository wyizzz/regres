package com.api.regres.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

public class ReusableSteps {

    public static String getDataFromFile(String fileName) throws Exception {
        String filePath= "src/test/resources/DataFiles";
        return readFileAsString(filePath+"/"+fileName);
    }

    private static String readFileAsString(String fileName)throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static String getCurrentDateString(String dateFormat) {
        Date d = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        formatDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatDate.format(d);
    }
}
