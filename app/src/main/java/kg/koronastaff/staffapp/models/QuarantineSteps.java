package kg.koronastaff.staffapp.models;

import java.util.ArrayList;

public class QuarantineSteps {
    int id;
    String title;
    String body;

    public QuarantineSteps(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public QuarantineSteps() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
