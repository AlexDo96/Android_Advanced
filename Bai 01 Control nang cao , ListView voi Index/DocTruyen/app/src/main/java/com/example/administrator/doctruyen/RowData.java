package com.example.administrator.doctruyen;
import nl.siegmann.epublib.domain.Resource;
/**
 * Created by Administrator on 4/2/2017.
 */
public class RowData {
    public String title;
    public Resource resource;

    public RowData() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public Resource getResource() {
        return resource;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}