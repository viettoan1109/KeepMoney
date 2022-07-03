package vn.trungkma.money.data.model;

public class MediaFileInfo {

    private String data;
    private String title;
    private String id;

    public MediaFileInfo() {
    }

    public MediaFileInfo(String data, String title, String id) {
        this.data = data;
        this.title = title;
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
