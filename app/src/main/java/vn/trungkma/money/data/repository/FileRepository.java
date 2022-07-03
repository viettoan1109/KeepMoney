package vn.trungkma.money.data.repository;

import static vn.trungkma.money.utils.Utils.getDate;
import static vn.trungkma.money.utils.Utils.subStringTypeFile;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.trungkma.money.data.model.Files;

public class FileRepository {
    @Inject
    public FileRepository() {
    }

    public Single<List<Files>> getFile(Context context, String orderBy, String type) {
        return Single.fromCallable(() -> FileRepository.this.getFileOnDevice(context, orderBy, type)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<Files> getFileOnDevice(Context context, String orderBy, String type) {
        List<Files> list = new LinkedList();
        String[] projection = {
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATA
        };

        String whereClause = null;
        Uri uri = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            uri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
//            uri = MediaStore.Files.getContentUri("external");
            uri = Uri.fromFile(Environment.getExternalStorageDirectory());
        }
        Cursor cursor = context.getContentResolver().query(uri,
                projection,
                whereClause,
                null,
                orderBy);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (type.equals("apk")) {
                    String typeFile = subStringTypeFile(cursor.getString(4));
                    if (typeFile.equals("apk")) {
                        Files file = new Files(cursor.getString(4), cursor.getString(5)
                                , cursor.getInt(6)
                                , getDate(cursor.getString(2))
                                , subStringTypeFile(cursor.getString(4)), cursor.getString(7), cursor.getString(0));
                        list.add(file);
                    }
                } else if (type.equals("document")) {
                    String typeFile = subStringTypeFile(cursor.getString(4));
                    if (typeFile.equals("pptx") || typeFile.equals("docx") || typeFile.equals("xlsx") ||
                            typeFile.equals("pdf") || typeFile.equals("txt")) {
                        Files file = new Files(cursor.getString(4), cursor.getString(5)
                                , cursor.getInt(6)
                                , getDate(cursor.getString(2))
                                , subStringTypeFile(cursor.getString(4)), cursor.getString(7), cursor.getString(0));
                        list.add(file);
                    }
                } else if (type.equals("music")) {
                    String typeFile = subStringTypeFile(cursor.getString(4));
                    if (typeFile.equals("mp3")) {
                        Files file = new Files(cursor.getString(4), cursor.getString(5)
                                , cursor.getInt(6)
                                , getDate(cursor.getString(2))
                                , subStringTypeFile(cursor.getString(4)), cursor.getString(7), cursor.getString(0));
                        list.add(file);
                    }
                } else if (type.equals("image")) {
                    String typeFile = subStringTypeFile(cursor.getString(4));
                    if (typeFile.equals("jpg") || typeFile.equals("png")) {
                        Files file = new Files(cursor.getString(4), cursor.getString(5)
                                , cursor.getInt(6)
                                , getDate(cursor.getString(2))
                                , subStringTypeFile(cursor.getString(4)), cursor.getString(7), cursor.getString(0));
                        list.add(file);
                    }
                } else if (type.equals("video")) {
                    String typeFile = subStringTypeFile(cursor.getString(4));
                    if (typeFile.equals("mp4") || typeFile.equals("mkv")) {
                        Files file = new Files(cursor.getString(4), cursor.getString(5)
                                , cursor.getInt(6)
                                , getDate(cursor.getString(2))
                                , subStringTypeFile(cursor.getString(4)), cursor.getString(7), cursor.getString(0));
                        list.add(file);
                    }
                } else if (type.equals("archive")) {
                    String typeFile = subStringTypeFile(cursor.getString(4));
                    if (typeFile.equals("rar") || typeFile.equals("zip") || typeFile.equals("7zip") || typeFile.equals("Gzip") || typeFile.contains(".")) {
                        Files file = new Files(cursor.getString(4), cursor.getString(5)
                                , cursor.getInt(6)
                                , getDate(cursor.getString(2))
                                , subStringTypeFile(cursor.getString(4)), cursor.getString(7), cursor.getString(0));
                        list.add(file);
                    }
                } else {
                    Files file = new Files(cursor.getString(4), cursor.getString(5)
                            , cursor.getInt(6)
                            , getDate(cursor.getString(2))
                            , subStringTypeFile(cursor.getString(4)), cursor.getString(7), cursor.getString(0));
                    list.add(file);
                }

            }
            cursor.close();
        }
        return list;
    }

    private void getData(Cursor cursor){

    }
}
