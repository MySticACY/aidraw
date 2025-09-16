import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InputOutput {
    public Map<String, Object> loadjson(String filePath){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            System.err.println("加载JSON文件失败: " + e.getMessage());
            return new HashMap<>();
        }
    }
}