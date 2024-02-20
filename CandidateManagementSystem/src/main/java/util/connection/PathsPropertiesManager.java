package util.connection;

import controller.Context;
import controller.Main;
import view.components.frames.CustomErrorDialogue;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

public class PathsPropertiesManager {
    private static File file;
    public static String absolutePath;
    public static final String propertiesDir = File.separator + "properties" + File.separator;
    public static final String dataBaseDir = File.separator + "database" + File.separator;
    public static final String iconDir = File.separator + "icons" + File.separator;
    public static final String flagIconDir = File.separator + "icons" + File.separator + "flags" + File.separator;
    public static final String menuIconDir = File.separator + "icons" + File.separator + "menu" + File.separator;
    private static Properties properties;

    private PathsPropertiesManager(){
    }

    static {
        try {
            absolutePath = initAbsolutePath();
            String pathToPaths = absolutePath + propertiesDir + "paths.properties";
            file = new File(pathToPaths);
            InputStream in = new FileInputStream(pathToPaths); //can't run getClass() from static method
            properties = new Properties();
            properties.load(in);
            if(properties.getProperty("dump.location") == null || properties.getProperty("dump.location").isEmpty()){
                writeToProperty("dump.domain", absolutePath + dataBaseDir + "backups");
            }
        }
        catch (IOException e) {
            new CustomErrorDialogue(true, "Failed to load PathsPropertiesManager.properties", Context.getParentFrame(), true).showDialogue();
        }
        catch (URISyntaxException e) {
            new CustomErrorDialogue(true, "PathsPropertiesManager.absolutePaths contains an unsupported symbol", Context.getParentFrame(), true).showDialogue();
        }
    }

    public static void loadCopy(File loadLocation) { //loads a copy of the DB
        if(loadLocation != null) {
            try(FileInputStream input = new FileInputStream(loadLocation); FileOutputStream output = new FileOutputStream(SQLiteConnectionManager.getDbPath())) {
                int byteToWrite;
                while((byteToWrite = input.read()) != -1) {
                    output.write(byteToWrite);
                }
            }
            catch (IOException e) {
                new CustomErrorDialogue(true, "Failed to load the copy", Context.getParentFrame(), true).showDialogue();
            }
        }
    }

    public static void createCopy() throws IOException {
        String fileName =  "Date "
                + formatTime(LocalDate.now().getDayOfMonth()) + "-"
                + formatTime(LocalDate.now().getMonthValue()) + "-"
                + formatTime(LocalDate.now().getYear())
                + " Time "
                + formatTime(LocalTime.now().getHour()) + "-"
                + formatTime(LocalTime.now().getMinute()) + "-"
                + formatTime(LocalTime.now().getSecond()) + ".sqlite";
        try(FileInputStream input = new FileInputStream(SQLiteConnectionManager.getDbPath());
            FileOutputStream output = new FileOutputStream(getPath("dump.location") + File.separator + fileName)) {
            int byteToWrite;
            while((byteToWrite = input.read()) != -1) {
                output.write(byteToWrite);
            }
        }
    }
    
    public static void writeToProperty(String key, String value) throws IOException {
        properties.setProperty(key, value);
        properties.store(new FileOutputStream(file), null);
    }

    public static void writeToProperty(String key, File f)  {
        try {
            if (f != null) {
                properties.setProperty(key, f.getPath());
                properties.store(new FileOutputStream(file), null);
            }
        }
        catch(IOException e) {
            new CustomErrorDialogue(true, "Failed to write to PathsPropertyManager.properties", Context.getParentFrame(), true).showDialogue();
        }
    }

    public static String getPath (String key) {
        return properties.getProperty(key);
    }

    private static String formatTime(int timeField) {
        String strField = "";
        switch(timeField){
            case 0,1,2,3,4,5,6,7,8,9 -> strField += "0" + timeField;
            default -> strField += timeField;
        }
        return strField;
    }

    //Directory with the jar file
    private static String initAbsolutePath() throws URISyntaxException {
        CodeSource cs = Main.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(cs.getLocation().toURI().getPath());
        return jarFile.getParentFile().getPath();
    }
}