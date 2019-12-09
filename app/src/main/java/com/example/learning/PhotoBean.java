package com.example.learning;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zcx
 * @Copyright: 浙江集商优选电子商务有限公司
 * @CreateDate: 2019/12/3 16:19
 * @Version: 1.0.0
 */
public class PhotoBean implements Serializable {
    private static final String BASE_DOWNLOAD_URL = "https://picsum.photos/id/";

    private int id;
    private String author;
    private int width;
    private int height;
    private String url;
    @SerializedName("download_url")
    private String downloadUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getThumUrl() {
        return BASE_DOWNLOAD_URL + id + "/400/300";
    }
}
