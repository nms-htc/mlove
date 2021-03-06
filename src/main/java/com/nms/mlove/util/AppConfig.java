package com.nms.mlove.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nguyen Trong Cuong
 * @since 08/26/2014
 * @version 1.0
 */
public class AppConfig implements Serializable
{

    private static final long serialVersionUID = 6615311407288469977L;
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.
            getName());
    public static final String FILE_PATH = "mlove/config.properties";
    public static final String DEFAULT_FILE = "config.properties";
    public static final String FILE_STORE_PATH_PROPERTY = "filestore.path";
    public static final String ROOT_FOLDER_NAME = ".mlovefiles";

    public static Properties props = null;

    static
    {
        try
        {
            loadProperties();
        }
        catch (Exception e)
        {
            LOGGER.log(Level.WARNING,
                    "[AppConfig] Error when try loading the 'config.properties' file.",
                    e);
        }
    }

    private static void loadProperties() throws IOException
    {
        props = new Properties();
        //InputStream is = AppConfig.class.getClassLoader().getResourceAsStream(FILE_PATH);
        try (InputStream is = new FileInputStream(new File(FILE_PATH)))
        {

            LOGGER.log(Level.INFO, "load config from " + FILE_PATH);
            props.load(is);
        }
        catch (FileNotFoundException e)
        {
            LOGGER.log(Level.INFO, "Can not find " + FILE_PATH + " get by "
                    + DEFAULT_FILE);
            try (InputStream is = AppConfig.class.getClassLoader().
                    getResourceAsStream(
                            DEFAULT_FILE))
            {
                props.load(is);
                    }
        }

    }

    public static String getFileStorePath()
    {
        String path = null;

        if (props != null)
        {
            path = props.getProperty(FILE_STORE_PATH_PROPERTY);
        }

        if (path == null)
        {
            path = getDefaultPath();
        }

        if (!path.endsWith(File.separator))
        {
            path += File.separator;
        }

        return path;
    }

    private static String getDefaultPath()
    {
        StringBuilder sb = new StringBuilder(System.getProperty("user.home"));
        sb.append(File.separator);
        sb.append(ROOT_FOLDER_NAME).append(File.separator);
        return sb.toString();
    }
}
