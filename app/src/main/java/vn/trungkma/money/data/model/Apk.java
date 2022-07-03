package vn.trungkma.money.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Apk implements Parcelable {
    private String id;

    private String nameFile;

    private String nameFolder;

    private long size;

    private String time;

    private String category;

    private String uri;

    private String fileId;

    public Apk(String id, String nameFile, String nameFolder, long size, String time, String category, String uri, String fileId) {
        this.id = id;
        this.nameFile = nameFile;
        this.nameFolder = nameFolder;
        this.size = size;
        this.time = time;
        this.category = category;
        this.uri = uri;
        this.fileId = fileId;
    }

    protected Apk(Parcel in) {
        id = in.readString();
        nameFile = in.readString();
        nameFolder = in.readString();
        size = in.readLong();
        time = in.readString();
        category = in.readString();
        uri = in.readString();
        fileId = in.readString();
    }

    public static final Creator<Apk> CREATOR = new Creator<Apk>() {
        @Override
        public Apk createFromParcel(Parcel in) {
            return new Apk(in);
        }

        @Override
        public Apk[] newArray(int size) {
            return new Apk[size];
        }
    };

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public static Creator<Apk> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nameFile);
        dest.writeString(nameFolder);
        dest.writeLong(size);
        dest.writeString(time);
        dest.writeString(category);
        dest.writeString(uri);
        dest.writeString(fileId);
    }
}
