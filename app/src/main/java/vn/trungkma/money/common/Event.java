package vn.trungkma.money.common;

public class Event {

    private int typeEvent;
    private String newNameFile;
    private String newPathFile;


    public int getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        this.typeEvent = typeEvent;
    }

    public String getNewNameFile() {
        return newNameFile;
    }

    public void setNewNameFile(String newNameFile) {
        this.newNameFile = newNameFile;
    }

    public String getNewPathFile() {
        return newPathFile;
    }

    public void setNewPathFile(String newPathFile) {
        this.newPathFile = newPathFile;
    }

    public Event(int typeEvent, String newNameFile, String newPathFile) {
        this.typeEvent = typeEvent;
        this.newNameFile = newNameFile;
        this.newPathFile = newPathFile;
    }


}
