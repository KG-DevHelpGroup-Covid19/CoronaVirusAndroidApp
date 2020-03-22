package kg.koronastaff.staffapp.models;

public class ApiResponse<T> {
    T results;
    Object next;
    Object previous;
    int count;

    public ApiResponse() {
    }

    public ApiResponse(T results, Object next, Object previous, int count) {
        this.results = results;
        this.next = next;
        this.previous = previous;
        this.count = count;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
