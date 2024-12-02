package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    Properties prop;

    public ConfigReader() throws IOException {


        prop = new Properties();

        File file=new File(System.getProperty("user.dir")+"/Configurations/Application.properties");
        FileInputStream fileInputStream=new FileInputStream(file);
        prop.load(fileInputStream);

    }



    public String getcreateobject()
    {
        return prop.getProperty("createobject");
    }

    public String getputobject()
    {
        return prop.getProperty("putobject");
    }
}
