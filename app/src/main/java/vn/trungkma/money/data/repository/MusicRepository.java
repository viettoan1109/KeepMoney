package vn.trungkma.money.data.repository;

import static vn.trungkma.money.common.Constant.PATH_CACHE;
import static vn.trungkma.money.common.Constant.PATH_COVER;
import static vn.trungkma.money.common.Constant.PATH_ICON;
import static vn.trungkma.money.common.Constant.PATH_THUMBNAILS;
import static vn.trungkma.money.common.Constant.PATH_TRASH;
import static vn.trungkma.money.common.Constant.SOUND_MP3;
import static vn.trungkma.money.common.Constant.SOUND_OGG;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.trungkma.money.data.model.FileData;

public class MusicRepository {

    private ArrayList<String> fileList = new ArrayList<>();

    @Inject
    public MusicRepository() {
    }

    public Single<List<FileData>> getMusic() {
        return Single.fromCallable(() -> MusicRepository.this.getMusicOnDevice()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<FileData> getMusicOnDevice() {
        List<FileData> list = new LinkedList<>();
        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        getFile(root);
        for (int i = 0; i < fileList.size(); i++) {
            String uri = fileList.get(i);
            String path = Environment.getExternalStorageDirectory().toString() + uri;
            File directory1 = new File(uri);
            String data = File.separator + directory1;
            long dateTime = directory1.lastModified();
            long count = 0;
            int size = Integer.parseInt(String.valueOf(directory1.length()));

            list.add(new FileData(directory1.getName(), size, data, dateTime / 1000, count));

        }


        return list;
    }

    public ArrayList<String> getFile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File file : listFile) {
                if (file.isDirectory()) {
                    getFile(file);
                } else {
                    if (file.getName().endsWith(SOUND_MP3)
                    || file.getName().equals(SOUND_OGG)) {
                        if (!file.getPath().contains(PATH_THUMBNAILS) && !file.getPath().contains(PATH_ICON)
                                && !file.getPath().contains(PATH_CACHE) && !file.getPath().contains(PATH_COVER) && !file.getPath().contains(PATH_TRASH)) {
                            String temp = file.getAbsolutePath();
                            if (!fileList.contains(temp)) {
                                fileList.add(temp);
                            }
                        }

                    }
                }
            }
        }
        return fileList;
    }
}
