import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputFolder = "C:\\Users\\ROG\\Desktop\\aidraw\\src\\main\\resources\\sample\\pic4.json";

        Knowledge bas = new Knowledge();
        bas.readMain("C:\\Users\\ROG\\Desktop\\aidraw\\src\\main\\resources\\standard");
        bas.readSec("C:\\Users\\ROG\\Desktop\\aidraw\\src\\main\\resources\\titlestore\\Second.json");
        bas.readThd("C:\\Users\\ROG\\Desktop\\aidraw\\src\\main\\resources\\titlestore\\Third.json");

        List<Map<String, Map<String, Object>>> finalExtractList;

        Input io = new Input();

        //Map<String, Object> tep = io.loadjson(inputFolder);
        JSONObject jsonData = io.readJson(inputFolder);
        Map<String, Object> tep = jsonData.toMap();

        if (tep.containsKey("检验项目")) {
            finalExtractList = JianYanProcessor.process(tep);
        }
        else{
            finalExtractList = TiJianProcessor.process(bas, tep);
        }

        //System.out.println(finalExtractList);

        List<Result> organMap = new ArrayList<>();
        bas.query(finalExtractList, organMap);
        
        for(Result re: organMap){
            System.out.println(re);
        }
    }
}