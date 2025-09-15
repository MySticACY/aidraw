import com.fasterxml.jackson.annotation.JsonProperty;

// 定义“检验”指标的JSON结构
public class JianYanItem {
    @JsonProperty("项目名称") private String itemName;
    @JsonProperty("结果") private String result;
    @JsonProperty("单位") private String unit;
    @JsonProperty("参考范围") private String referenceRange;
    @JsonProperty("报告时间") private String reportTime;
    @JsonProperty("医疗机构") private String hospital;

    public JianYanItem() {}

    public JianYanItem(String itemName, String result, String unit, String referenceRange, String reportTime, String hospital) {
        this.itemName = itemName; this.result = result; this.unit = unit;
        this.referenceRange = referenceRange; this.reportTime = reportTime; this.hospital = hospital;
    }
    
    // Getters and Setters
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getReferenceRange() { return referenceRange; }
    public void setReferenceRange(String referenceRange) { this.referenceRange = referenceRange; }
    public String getReportTime() { return reportTime; }
    public void setReportTime(String reportTime) { this.reportTime = reportTime; }
    public String getHospital() { return hospital; }
    public void setHospital(String hospital) { this.hospital = hospital; }
}