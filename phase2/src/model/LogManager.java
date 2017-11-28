package model;

import java.io.IOException;

public class LogManager extends SerializeManager<ImageFile> {

    public LogManager(String filePath) throws ClassNotFoundException, IOException {
        super(filePath);

    }

    public String getLogHistory() {
        StringBuilder result = new StringBuilder();
        for(ImageFile file: managerList){
            result.append(file.getLog()).append("\n");
        }
        return result.toString();
    }
}
