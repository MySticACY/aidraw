import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

// “检查”报告处理器
public class JianChaProcessor {
    public static List<JianChaItem> process(JsonNode reportJson) {
        List<JianChaItem> extractList = new ArrayList<>();
        JsonNode basicInfo = reportJson.get("基础信息");
        String hospital = basicInfo.get("医疗机构").asText();
        String time = basicInfo.get("报告日期").asText();
        String itemName = basicInfo.get("检查项目").asText();

        String conclusion = reportJson.has("检查结论") ? reportJson.get("检查结论").asText() : "";
        String findings = reportJson.has("检查所见") ? reportJson.get("检查所见").asText() : "";
        String suggestion = reportJson.has("医学建议") ? reportJson.get("医学建议").asText() : "";

        extractList.add(new JianChaItem(itemName, conclusion, findings, suggestion, time, hospital));
        return extractList;
    }
}