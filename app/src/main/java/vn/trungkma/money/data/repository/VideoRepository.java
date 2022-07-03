package vn.trungkma.money.data.repository;


import static vn.trungkma.money.common.Constant.PATH_CACHE;
import static vn.trungkma.money.common.Constant.PATH_COVER;
import static vn.trungkma.money.common.Constant.PATH_ICON;
import static vn.trungkma.money.common.Constant.PATH_THUMBNAILS;
import static vn.trungkma.money.common.Constant.PATH_TRASH;
import static vn.trungkma.money.common.Constant.VIDEO_3GP;
import static vn.trungkma.money.common.Constant.VIDEO_AVI;
import static vn.trungkma.money.common.Constant.VIDEO_DLVX;
import static vn.trungkma.money.common.Constant.VIDEO_FLV;
import static vn.trungkma.money.common.Constant.VIDEO_MKV;
import static vn.trungkma.money.common.Constant.VIDEO_MP4;
import static vn.trungkma.money.common.Constant.VIDEO_MPGE;
import static vn.trungkma.money.common.Constant.VIDEO_VOB;
import static vn.trungkma.money.common.Constant.VIDEO_WEBV;
import static vn.trungkma.money.common.Constant.VIDEO_WMV;

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

public class VideoRepository {
    private ArrayList<String> fileList = new ArrayList<>();

    @Inject
    public VideoRepository() {
    }

    public Single<List<FileData>> getVideo() {
        return Single.fromCallable(() -> VideoRepository.this.getVideoOnDevice()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<FileData> getVideoOnDevice() {
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

            /*File directory1 = new File(uri);
            File[] files = directory1.listFiles();
            for (int j = 0; j < files.length; j++) {
                String data = File.separator + files[i];
                long dateTime = files[i].lastModified();
                long count = 0;
                File a = new File(files[i].getPath());
                int size = Integer.parseInt(String.valueOf(a.length() / 1024));

                list.add(new FileData(files[i].getName(), convertByte(size), data, getDate(String.valueOf(dateTime / 1000)), count));
            }*/

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
                    if (file.getName().endsWith(VIDEO_3GP)
                            || file.getName().endsWith(VIDEO_AVI)
                            || file.getName().endsWith(VIDEO_DLVX)
                            || file.getName().endsWith(VIDEO_FLV)
                            || file.getName().endsWith(VIDEO_MKV)
                            || file.getName().endsWith(VIDEO_MP4)
                            || file.getName().endsWith(VIDEO_MPGE)
                            || file.getName().endsWith(VIDEO_VOB)
                            || file.getName().endsWith(VIDEO_WEBV)
                            || file.getName().endsWith(VIDEO_WMV)) {
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
