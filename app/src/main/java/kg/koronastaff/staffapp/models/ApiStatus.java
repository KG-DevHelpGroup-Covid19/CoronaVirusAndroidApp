package kg.koronastaff.staffapp.models;

public class ApiStatus {
    String status;

    public ApiStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccessful(){
        return status.equals("ok");
    }
}
