import com.fasterxml.jackson.annotation.JsonProperty;

// 定义“检查”报告的JSON结构
public class JianChaItem {
    @JsonProperty("项目名称") private String itemName;
    @JsonProperty("检查结论") private String conclusion;
    @JsonProperty("检查所见") private String findings;
    @JsonProperty("医学建议") private String suggestion;
    @JsonProperty("报告时间") private String reportTime;
    @JsonProperty("医疗机构") private String hospital;

    public JianChaItem() {}

    public JianChaItem(String itemName, String conclusion, String findings, String suggestion, String reportTime, String hospital) {
        this.itemName = itemName; this.conclusion = conclusion; this.findings = findings;
        this.suggestion = suggestion; this.reportTime = reportTime; this.hospital = hospital;
    }

    // Getters and Setters
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getConclusion() { return conclusion; }
    public void setConclusion(String conclusion) { this.conclusion = conclusion; }
    public String getFindings() { return findings; }
    public void setFindings(String findings) { this.findings = findings; }
    public String getSuggestion() { return suggestion; }
    public void setSuggestion(String suggestion) { this.suggestion = suggestion; }
    public String getReportTime() { return reportTime; }
    public void setReportTime(String reportTime) { this.reportTime = reportTime; }
    public String getHospital() { return hospital; }
    public void setHospital(String hospital) { this.hospital = hospital; }
}