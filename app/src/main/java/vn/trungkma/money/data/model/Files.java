package vn.trungkma.money.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Files implements Parcelable {

    private String id;

    private String nameFile;

    private String nameFolder;

    private long size;

    private String time;

    private String category;

    private String uri;

    private String fileId;

    private boolean ischecked = false;




    public Files(String nameFile, String nameFolder, long size, String time, String category, String uri, String fileId) {
        this.nameFile = nameFile;
        this.nameFolder = nameFolder;
        this.size = size;
        this.time = time;
        this.category = category;
        this.uri = uri;
        this.fileId = fileId;
    }

    protected Files(Parcel in) {
        id = in.readString();
        nameFile = in.readString();
        nameFolder = in.readString();
        size = in.readLong();
        time = in.readString();
        category = in.readString();
        uri = in.readString();
        fileId = in.readString();

    }

    public static final Creator<Files> CREATOR = new Creator<Files>() {
        @Override
        public Files createFromParcel(Parcel in) {
            return new Files(in);
        }

        @Override
        public Files[] newArray(int size) {
            return new Files[size];
        }
    };

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getNameFolder() {
        return nameFolder;
    }

    public void setNameFolder(String nameFolder) {
        this.nameFolder = nameFolder;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isChecked() {
        return ischecked;
    }

    public void setChecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nameFile);
        parcel.writeString(nameFolder);
        parcel.writeLong(size);
        parcel.writeString(time);
        parcel.writeString(category);
        parcel.writeString(uri);
    }
}
