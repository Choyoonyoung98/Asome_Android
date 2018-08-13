package com.example.asome.asome_sourcerequire.Project;

public class ProjectItem {
    String title;
    String about;
    String id;
    //    //{"proj_id":"18","proj_UUID":null,"proj_name":"?????","proj_about":null,"role_name":null,"role_user_id":null,
    //                // "role_start_date":null,"role_end_date":null,"role_hour":null,"role_status":null}
    //


    public ProjectItem(String title, String about, String id) {
        this.title = title;
        this.about = about;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
