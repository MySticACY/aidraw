import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    public void query(List<Map<String, Object>> ext,  List<result> organMap){
        for(Map<String, Object> zhibiao: ext){
            String[] s = zhibiao.get("项目名称").toString().split("\\$");
            if(this.sec.containsKey(s[0])){
                s[0] = this.sec.get(s[0]).toString();
            }
            if(this.thd.containsKey(s[1])){
                s[1] = this.thd.get(s[1]).toString();
            }
            zhibiao.put("项目名称", String.format("%s$%s", s[0], s[1]));

            String[] relatedOrgans = new String[]{};
            if(this.sum.containsKey(zhibiao.get("项目名称").toString())){
                relatedOrgans = (String[]) this.sum.get(zhibiao.get("项目名称").toString());
            }
            for(String organ: relatedOrgans){
                boolean found = false;
                for(result res: organMap){
                    if(res.title.equals(organ)){
                        found = true;
                        res.subrep.add(zhibiao);
                        break;
                    }
                }
                if(found) continue;
                organMap.add(new result(){
                    {
                        title = organ;
                        subrep.add(zhibiao);
                    }
                });
            }
        }
    }
}