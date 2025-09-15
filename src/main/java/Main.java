import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 主程序入口
public class Main {

    public static void main(String[] args) throws IOException {
        File inputFolder = new File("C:/Users/ROG/Desktop/AI_Portrait/sample");

        List<Object> finalExtractList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        if (!inputFolder.exists() || !inputFolder.isDirectory()) {
            System.err.println("错误：输入文件夹不存在或不是一个目录: " + inputFolder.getAbsolutePath());
            return;
        }

        for (File file : Objects.requireNonNull(inputFolder.listFiles())) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                System.out.println("正在处理文件: " + file.getName());
                JsonNode rootNode = objectMapper.readTree(file);

                if (rootNode.has("检验项目")) {
                    finalExtractList.addAll(JianYanProcessor.process(rootNode));
                } else if (rootNode.has("检查所见") || rootNode.has("检查结论")) {
                    finalExtractList.addAll(JianChaProcessor.process(rootNode));
                } else {
                    System.out.println("无法识别的文件类型: " + file.getName());
                }
            }
        }

        System.out.println("\n--- 所有文件处理完成，最终的JSON列表如下 ---");
        String jsonOutput = objectMapper.writeValueAsString(finalExtractList);
        System.out.println(jsonOutput);
    }
}