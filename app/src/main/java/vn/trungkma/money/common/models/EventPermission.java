package vn.trungkma.money.common.models;

public class EventPermission {

    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public EventPermission(boolean isShow) {
        this.isShow = isShow;
    }
}
