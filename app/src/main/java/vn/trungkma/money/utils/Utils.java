package vn.trungkma.money.utils;

import static android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.trungkma.money.R;
import vn.trungkma.money.data.model.FileData;

public class Utils {

    public static String subStringTypeFile(String s1) {
        return s1.substring(s1.lastIndexOf(".") + 1);
    }

    public static String getDate(String val) {
        try {
            long val1 = Long.parseLong(val);
            val1 *= 1000L;
            return new SimpleDateFormat("dd MMM yyyy - hh:mma", Locale.US).format(new java.util.Date(val1));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String convertByte(long size) {
        if (size >= 1048576) {
            return size / 1048576 + " Mb";
        } else if (size < 1024) {
            return size + " B";
        } else {
            return size / 1024 + " Kb";
        }
    }

    public static void deleteDirectory(File directory) {
        if (directory != null) {
            if (directory.exists()) {
                if (directory.isDirectory()) {
                    if (directory.listFiles() != null) {
                        if (directory.listFiles().length != 0) {
                            File[] files = directory.listFiles();
                            for (File file : files) {
                                if (file.isFile()) {
                                    file.delete();
                                } else {
                                    deleteDirectory(file);
                                }
                            }
                        }
                    }
                }
                directory.delete();
            }
        }
    }

    public static void createFolder(String path, String nameFolder) {
        File directory = new File(path + File.separator + nameFolder);
        directory.mkdirs();
    }

    public static void showInputMethod(View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }

    }

 /*   private void hideInputMethod(View view, Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }*/

    public static String addSeparatorToPath(String path) {
        if (!path.endsWith("/")) {
            return path + "/";
        }
        return path;
    }

    public static void copy(File source, File to) throws IOException {
        InputStream input = new FileInputStream(source);
        OutputStream output = new FileOutputStream(to);
        byte[] buf = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, bytesRead);
        }
        input.close();
        output.close();
    }

    public static class DateTimeComparator implements Comparator<FileData> {

        @Override
        public int compare(FileData o1, FileData o2) {
            if (o1.getFileTime() == o2.getFileTime())
                return 0;
            else if (o1.getFileTime() < o2.getFileTime())
                return 1;
            else
                return -1;
        }
    }

  /*  public static ArrayList<String> getFile(File dir, String[] type, ArrayList<String> fileList) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File file : listFile) {
                if (file.isDirectory()) {
                    getFile(file, type, fileList);
                } else {
                    for (int i = 0; i < type.length; i++){

                    }
                        if (file.getName().endsWith(fileList.get(0))
                                || file.getName().endsWith(IMAGE_JPG)
                                || file.getName().endsWith(IMAGE_JPEG)
                                || file.getName().endsWith(IMAGE_GIF)
                                || file.getName().endsWith(IMAGE_BMP)
                                || file.getName().endsWith(IMAGE_WEBP)) {
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
    }*/

    public static String readFileContent(File file) throws IOException {
        BufferedReader reader;
        StringBuffer sbf = new StringBuffer();
        reader = new BufferedReader(new FileReader(file));
        String tempStr;
        while ((tempStr = reader.readLine()) != null) {
            sbf.append(tempStr);
        }
        reader.close();
        return sbf.toString();
    }

    public static String getTimeString(long time) {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        return displaymetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        return displaymetrics.heightPixels;
    }

    public static boolean isOnline(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    public static <C> List<C> asList(SparseArray<C> sparseArray) {
        if (sparseArray == null) return null;
        List<C> arrayList = new ArrayList<C>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++)
            arrayList.add(sparseArray.valueAt(i));
        return arrayList;
    }

    public static int pxToDp(float px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static Locale getLocaleCompat() {
        Configuration configuration = Resources.getSystem().getConfiguration();
        return isAtLeastSdkVersion(Build.VERSION_CODES.N) ? configuration.getLocales().get(0) : configuration.locale;
    }

    public static boolean isAtLeastSdkVersion(int versionCode) {
        return Build.VERSION.SDK_INT >= versionCode;
    }

    public static void showDialogPermissionManageExternalStorage(Context context, String content) {
        new AlertDialog.Builder(context).setMessage(content).setPositiveButton(
                context.getResources().getString(R.string.go_to_setting), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                        context.startActivity(intent);

                    }
                }).setNegativeButton(context.getResources().getString(R.string.go_back), (dialog, which) -> dialog.dismiss()).show();
    }

    public static void showDialogPermissionWriteExternalStorage(Context context, String content) {
        new AlertDialog.Builder(context).setMessage(content).setPositiveButton(
                context.getResources().getString(R.string.go_to_setting), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                        intent.setData(uri);
                        context.startActivity(intent);
                    }
                }).setNegativeButton(context.getResources().getString(R.string.go_back), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
