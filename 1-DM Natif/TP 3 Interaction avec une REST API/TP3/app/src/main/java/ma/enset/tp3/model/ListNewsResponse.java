package ma.enset.tp3.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListNewsResponse {
    private int totalResults;

    @SerializedName("articles")
    private List<News> newsList;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
