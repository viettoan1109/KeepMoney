package vn.trungkma.money.data.repository;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.trungkma.money.data.model.FileData;


public class DownloadRepository {


    @Inject
    public DownloadRepository() {
    }

    public Single<List<FileData>> getDownload(String storagePath) {
        return Single.fromCallable(() -> DownloadRepository.this.getDownloadOnDevice(storagePath)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<FileData> getDownloadOnDevice(String storagePath) {
        List<FileData> list = new LinkedList<>();

        String path = Environment.getExternalStorageDirectory().toString() + storagePath;
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            String data = File.separator + files[i];
            File file = new File(files[i].getPath());
            long size = Long.parseLong(String.valueOf(file.length()));
            long dateTime = files[i].lastModified();
            long count = 0;
            if (files[i].isDirectory()) {
                String path1 = Environment.getExternalStorageDirectory().toString() + "/" + files[i].getName();
                File directory1 = new File(path1);
                File[] files1 = directory1.listFiles();
                if (files1 != null)
                    count = files1.length;
                //count++;

                Log.d("Fi", files[i].getName() + files[i].getAbsolutePath());
            }
            list.add(new FileData(files[i].getName(), size, data, dateTime / 1000, count));


        }
        return list;
    }


}
