package com.example.bluenews.models;

public class News {
    private String category;
    private String content;
    private String date;
    private String headline;
    private String source;
    private String imageUrl;

    public News() {
        // Default constructor required for Firebase database
    }

    public News(String headline, String content, String date, String source, String imageUrl, String category) {
        this.headline = headline;
        this.content = content;
        this.date = date;
        this.source = source;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    // Getter and Setter methods for the fields

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
