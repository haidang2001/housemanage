package com.training0802.demo;


public class MongoDatasource {
    private String url;

    public MongoDatasource() {
    }

    public MongoDatasource(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MongoDatasource{" +
                "url='" + url + '\'' +
                '}';
    }
}
