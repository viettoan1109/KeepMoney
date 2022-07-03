package vn.trungkma.money.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FileData implements Parcelable {

    private String fileName;
    private long fileSize;
    private String filePath;
    private long fileTime;
    private long folderSize;
    private boolean isChecked = false;

    public FileData(String fileName, long fileSize, String filePath, long fileTime, long folderSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.fileTime = fileTime;
        this.folderSize = folderSize;
    }

    protected FileData(Parcel in){
        fileName = in.readString();
        fileSize = in.readLong();
        filePath = in.readString();
        fileTime = in.readLong();
        folderSize = in.readLong();
    }

    public static final Creator<FileData> CREATOR = new Creator<FileData>() {
        @Override
        public FileData createFromParcel(Parcel in) {
            return new FileData(in);
        }

        @Override
        public FileData[] newArray(int size) {
            return new FileData[size];
        }
    };

    public long getFolderSize() {
        return folderSize;
    }

    public void setFolderSize(long folderSize) {
        this.folderSize = folderSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileTime() {
        return fileTime;
    }

    public void setFileTime(long fileTime) {
        this.fileTime = fileTime;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeLong(fileSize);
        dest.writeString(filePath);
        dest.writeLong(fileTime);
        dest.writeLong(folderSize);
    }
}
