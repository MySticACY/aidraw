import org.json.JSONArray;

public class Result {
    public String title;
    public JSONArray subrep = new JSONArray();

    @Override
    public String toString() {
        return "organ='" + title + '\'' +
                ", subrep=" + subrep;
    }
}