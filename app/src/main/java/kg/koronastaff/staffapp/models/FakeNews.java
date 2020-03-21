package kg.koronastaff.staffapp.models;

public class FakeNews {
    int id;
    String title;
    String body;
    int timestamp;

    public FakeNews(int id, String title, String body, int timestamp) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
    }

    public FakeNews() {
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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
