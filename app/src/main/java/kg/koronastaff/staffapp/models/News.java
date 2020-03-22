package kg.koronastaff.staffapp.models;

public class News {
    int id;
    String title;
    String body;
    String created_at;

    public News(int id, String title, String body, String created_at) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.created_at = created_at;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
