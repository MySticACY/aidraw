import java.util.List;

import org.json.JSONObject;

public class Same {
    public void add_result(String organ, JSONObject tep, List<Result> organMap) {
        boolean found = false;
        for (Result res : organMap) {
            if (res.title.equals(organ)) {
                found = true;
                res.subrep.put(tep);
                break;
            }
        }
        if (found) return;
        organMap.add(new Result() {
            {
                title = organ;
                subrep.put(tep);
            }
        });
    }
}
