import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public interface extract {
    List<result> draw(JsonNode report);
}