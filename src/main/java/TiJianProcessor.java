import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class TiJianProcessor {
    @SuppressWarnings("ConvertToStringSwitch")
    public static List<Map<String, Map<String, Object>>> process(Knowledge bas, Map<String, Object> data, List<Result> organMap) {
        Same sa = new Same();
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

                if(bas.foth.containsKey(key)){
                    String organ = bas.foth.get(key).toString();

                    Map<String, Object> tags = new java.util.HashMap<>();
                    tags.put("医疗机构", hospital);
                    tags.put("报告日期", time);
                    items.add(tags);
                    Map<String, List<Map<String, Object>>> record = new java.util.HashMap<>();
                    record.put(key, items);
                    JSONObject tep = new JSONObject(record);
                    sa.add_result(organ, tep, organMap);
                }

            }
            else{
                if(data.get(key) instanceof String) continue;
                Map<String, Object> val = (Map<String, Object>) data.get(key);
                for(String sub_key: val.keySet()){
                    if(bas.foth.containsKey(sub_key)){
                        String organ = bas.foth.get(sub_key).toString();
                        Map<String, Object> sub_val = new java.util.HashMap<>();
                        sub_val.put(sub_key, val.get(sub_key));
                        sub_val.put("医疗机构", hospital);
                        sub_val.put("报告日期", time);
                        Map<String, Map<String, Object>> record = new java.util.HashMap<>();
                        record.put(key, sub_val);
                        JSONObject tep = new JSONObject(record);
                        sa.add_result(organ, tep, organMap);
                    }
                }

                if(bas.foth.containsKey(key)){
                    String organ = bas.foth.get(key).toString();
                    val.put("医疗机构", hospital);
                    val.put("报告日期", time);
                    Map<String, Map<String, Object>> record = new java.util.HashMap<>();
                    record.put(key, val);
                    JSONObject tep = new JSONObject(record);
                    sa.add_result(organ, tep, organMap);
                }
            }
        }

        return extractList;
    }
}