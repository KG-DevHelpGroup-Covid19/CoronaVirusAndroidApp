package kg.koronastaff.staffapp.models;

public class Tips {
    String title;
    String body;
    String imgurl;

    public Tips(String title, String body, String imgurl) {
        this.title = title;
        this.body = body;
        this.imgurl = imgurl;
    }

    public Tips() {
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
