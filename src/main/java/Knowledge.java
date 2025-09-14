import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Knowledge {
    private final Map<String, Object> sum = new HashMap<>();
    private final Map<String, Object> sec = new HashMap<>();
    private final Map<String, Object> thd = new HashMap<>();

    public static Map<String, Object> loadjson(String filePath){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            System.err.println("加载JSON文件失败: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public void readMain(String path){
        File dir = new File(path);
        File[] subdirs = dir.listFiles();
        for(File subdir : subdirs){
            File[] files = subdir.listFiles();
            for(File file : files){
                String detailPath = file.getAbsolutePath();
                String faketitle = file.getName();
                int pos = faketitle.lastIndexOf('.');
                String realTitle = faketitle.substring(0,pos);
                Map<String, Object> tep = loadjson(detailPath);
                for(String key: tep.keySet()){
                    Object value = tep.get(key);
                    Map<String, Object> subMap = (Map<String, Object>) value;
                    Object organs = subMap.get("相关器官");
                    this.sum.put(String.format("%s$%s", realTitle, key), organs);
                }
            }
        }
    }

    public void readSec(String path){
        Map<String, Object> tep = loadjson(path);
        for(String key: tep.keySet()){
            this.sec.put(key,tep.get(key));
        }
    }

    public void readThd(String path){
        Map<String, Object> tep = loadjson(path);
        for(String key: tep.keySet()){
            this.thd.put(key,tep.get(key));
        }
    }

    public void query(){
        
    }
    

    public static void main(String[] args) {
        Knowledge kno = new Knowledge();
        kno.readMain("C:\\Users\\ROG\\Desktop\\aidraw\\src\\main\\resources\\standard");
        kno.readSec("C:\\Users\\ROG\\Desktop\\aidraw\\src\\main\\resources\\titlestore\\Second.json");
        kno.readThd("C:\\Users\\ROG\\Desktop\\aidraw\\src\\main\\resources\\titlestore\\Third.json");
        System.out.println(kno.sum);
        System.out.println(kno.sec);
        System.out.println(kno.thd);
    }
}