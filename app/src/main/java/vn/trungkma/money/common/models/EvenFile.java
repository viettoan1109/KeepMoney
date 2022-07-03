package vn.trungkma.money.common.models;

public class EvenFile {

    private boolean iSuccess;

    public EvenFile(boolean iSuccess) {
        this.iSuccess = iSuccess;
    }

    public boolean isiSuccess() {
        return iSuccess;
    }

    public void setiSuccess(boolean iSuccess) {
        this.iSuccess = iSuccess;
    }
}
