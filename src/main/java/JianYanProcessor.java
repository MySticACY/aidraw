import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

// “检验”报告处理器
public class JianYanProcessor {
    public static List<JianYanItem> process(JsonNode reportJson) {
        List<JianYanItem> extractList = new ArrayList<>();
        JsonNode basicInfo = reportJson.get("基础信息");
        String hospital = basicInfo.get("医院机构").asText();
        String time = basicInfo.get("报告时间").asText();
        String sec_name = basicInfo.get("申请项目").asText();

        JsonNode jianYanItems = reportJson.get("检验项目");
        if (jianYanItems != null && jianYanItems.isArray()) {
            for (JsonNode item : jianYanItems) {
                String newName = sec_name + "$" + item.get("项目名称").asText();
                extractList.add(new JianYanItem(
                    newName, item.get("结果").asText(), item.get("单位").asText(),
                    item.get("参考范围").asText(), time, hospital
                ));
            }
        }
        return extractList;
    }
}