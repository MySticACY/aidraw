import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TiJianProcessor {
    @SuppressWarnings("ConvertToStringSwitch")
    public static List<Map<String, Map<String, Object>>> process(Knowledge bas, Map<String, Object> data) {

        List<Map<String, Map<String, Object>>> extractList = new ArrayList<>();
        Map<String, Object> message = (Map<String, Object>) data.get("基础信息");

        String hospital = message.get("医疗机构").toString();
        String time = message.get("报告日期").toString();

        for(String key: data.keySet()){
            if(bas.sec.containsKey(key)){
                List<Map<String, Object>> items = (List<Map<String, Object>>) data.get(key);
                for(Map<String, Object> item: items){
                    Map<String, Map<String, Object>> record = new java.util.HashMap<>();
                    item.put("项目名称", key + '$' + item.get("项目名称").toString());
                    record.put(key, item);
                    record.get(key).put("医疗机构", hospital);
                    record.get(key).put("报告日期", time);
                    extractList.add(record);
                }
            }
        }

        return extractList;
    }
}