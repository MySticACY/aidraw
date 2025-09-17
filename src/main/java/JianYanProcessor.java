import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JianYanProcessor {
    @SuppressWarnings("ConvertToStringSwitch")
    public static List<Map<String, Map<String, Object>>> process(Map<String, Object> data) {

        List<Map<String, Map<String, Object>>> extractList = new ArrayList<>();
        Map<String, Object> message = (Map<String, Object>) data.get("基础信息");

        String hospital = message.get("医院机构").toString();
        String time = message.get("报告时间").toString();
        String sec = message.get("申请项目").toString();
        
        for(String key: data.keySet()){
            if(key.equals("检验项目")){
                List<Map<String, Object>> items = (List<Map<String, Object>>) data.get(key);
                for(Map<String, Object> item: items){
                    Map<String, Map<String, Object>> record = new java.util.HashMap<>();
                    item.put("项目名称", sec + '$' + item.get("项目名称").toString());
                    record.put("检验项目", item);
                    record.get("检验项目").put("医院机构", hospital);
                    record.get("检验项目").put("报告时间", time);
                    extractList.add(record);
                }
            }
        }

        return extractList;
    }
}