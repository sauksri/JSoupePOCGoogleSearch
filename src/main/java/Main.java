import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static final String URL = "https://www.google.com/search?q=java+forums";

    public static void main(String[] args) throws IOException {
        List<SearchData> data = new LinkedList<>();
        Document doc = Jsoup.connect(URL).get();
        System.out.println(doc.select("div.g").eachText().size());
        for(Element el : doc.select("div.yuRUbf")){
            String link = el.select("a").first().attributes().get("href");
            Element tag = el.select("h3.LC20lb.DKV0Md").first();
            data.add(new SearchData(link, tag.text()));
        }
        for(SearchData s : data){
            System.out.println(s.toString());
        }
    }
}

@Data
class SearchData {
    public SearchData(String url, String tag){
        this.url=url;
        this.tag=tag;
    }
    private String url, tag;
}