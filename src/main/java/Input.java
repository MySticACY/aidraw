import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Input {
    public Map<String, Object> loadjson(String filePath){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            System.err.println("加载JSON文件失败: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public JSONObject readJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonData = new JSONObject(tokener);
            
            return jsonData;
            
        } catch (IOException e) {
            System.err.println("读取JSON文件失败: " + e.getMessage());
            return new JSONObject();
        }
    }
}