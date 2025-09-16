import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TiJianProcessor {
    @SuppressWarnings("ConvertToStringSwitch")
    public static List<Map<String, Map<String, Object>>> process(Knowledge bas, Map<String, Object> data) {

        List<Map<String, Map<String, Object>>> extractList = new ArrayList<>();
        String hospital = "", time = "";

        for(String key: data.keySet()){
            if(key.equals("基础信息")){
                for(String subKey: ((Map<String, Object>)data.get(key)).keySet()){
                    if(subKey.equals("医院机构")){
                        hospital = ((Map<String, Object>)data.get(key)).get(subKey).toString();
                    }
                    else if(subKey.equals("报告时间")){
                        time = ((Map<String, Object>)data.get(key)).get(subKey).toString();
                    }
                }
            }
            else if(bas.sec.containsKey(key)){
                List<Map<String, Object>> items = (List<Map<String, Object>>) data.get(key);
                for(Map<String, Object> item: items){
                    Map<String, Map<String, Object>> record = new java.util.HashMap<>();
                    item.put("项目名称", key + '$' + item.get("项目名称").toString());
                    record.put(key, item);
                    record.get(key).put("医院机构", hospital);
                    record.get(key).put("报告时间", time);
                    extractList.add(record);
                }
            }
        }

        return extractList;
    }
}