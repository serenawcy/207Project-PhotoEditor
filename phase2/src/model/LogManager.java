package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LogManager extends SerializeManager<String> {

    public LogManager(String filePath) throws ClassNotFoundException, IOException {
        super(filePath);
    }

    public ArrayList<String> getLogHistory() {
        ArrayList<String> result = new ArrayList<>();
        for (String logHistory: managerList){
            if (!Objects.equals(logHistory, " ")){
                result.add(logHistory);
            }
        }
        return result;
    }
}
