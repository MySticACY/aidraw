import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Knowledge {
    public final Map<String, Object> sum = new HashMap<>();
    public final Map<String, Object> sec = new HashMap<>();
    public final Map<String, Object> thd = new HashMap<>();
    public final Map<String, Object> foth = new HashMap<>();

    Input io = new Input();

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
                Map<String, Object> tep = io.loadjson(detailPath);
                for(String key: tep.keySet()){
                    Object value = tep.get(key);
                    Map<String, Object> subMap = (Map<String, Object>) value;
                    Object organs = subMap.get("相关器官");
                    this.sum.put(realTitle + '$' + key, organs);
                }
            }
        }
    }

    public void readSec(String path){
        Map<String, Object> tep = io.loadjson(path);
        for(String key: tep.keySet()){
            this.sec.put(key,tep.get(key));
        }
    }

    public void readThd(String path){
        Map<String, Object> tep = io.loadjson(path);
        for(String key: tep.keySet()){
            this.thd.put(key,tep.get(key));
        }
    }

    public void readFoth(String path){
        Map<String, Object> tep = io.loadjson(path);
        for(String key: tep.keySet()){
            this.foth.put(key,tep.get(key));
        }
    }

    @SuppressWarnings("CollectionsToArray")
    public void query(List<Map<String, Map<String, Object>>> ext,  List<Result> organMap){
        for(Map<String, Map<String, Object>> sub: ext){
            Map<String, Object> zhibiao = sub.values().iterator().next();
            String[] s = zhibiao.get("项目名称").toString().split("\\$");
            if(this.sec.containsKey(s[0])){
                s[0] = this.sec.get(s[0]).toString();
            }
            if(this.thd.containsKey(s[1])){
                s[1] = this.thd.get(s[1]).toString();
            }
            zhibiao.put("项目名称", s[0] + '$' + s[1]);

            String[] relatedOrgans = new String[]{};
            if(this.sum.containsKey(zhibiao.get("项目名称").toString())){
                ArrayList<String> list = (ArrayList<String>)this.sum.get(zhibiao.get("项目名称").toString());
                relatedOrgans = list.toArray(new String[list.size()]);
            }
            JSONObject tep = new JSONObject(sub);  
            Same sa = new Same(); 

            for(String organ: relatedOrgans){
                sa.add_result(organ, tep, organMap);
            }
        }
    }
}