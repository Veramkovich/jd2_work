package web.crawler.model;

import java.util.HashMap;
import java.util.Map;

public class ResultItemDto {

    private String searchUrl;
    private Map<String, Integer> termCountMap =
            new HashMap<>();

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public Map<String, Integer> getTermCountMap() {
        return termCountMap;
    }

    public void setTermCountMap(Map<String, Integer> termCountMap) {
        this.termCountMap = termCountMap;
    }
}
