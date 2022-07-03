package vn.trungkma.money.common;

public class Constant {
    public static final String DATABASE_NAME = "money.realm";
    public static final String BASE_URL = "";
    public static final int CONNECT_S = 30;
    public static final int READ_S = 30;
    public static final int WRITE_S = 30;

    public static final int WRITE_REQUEST_CODE = 1;


    public static final int SCREEN_DOC = 2;
    public static final int SCREEN_PHOTO = 3;
    public static final int SCREEN_MUSIC = 4;
    public static final int SCREEN_DOWNLOAD = 5;
    public static final int SCREEN_VIDEO = 6;
    public static final int SCREEN_APK = 7;

    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_VN = "vi";

    public static final String STORAGE_DOWNLOAD = "/Download";
    public static final String STORAGE_ROOT = "/";

    public static final String TOAST_DELETE_SUCCESS = "Delete ";
    public static final String TOAST_RENAME_SUCCESS = "Rename the file successfully";
    public static final String TOAST_RENAME_FAILED = "Failed to rename the file";

    public static final String TYPE_PDF = "pdf";
    public static final String TYPE_DOC_X = "docx";
    public static final String TYPE_XLSX = "xlsx";
    public static final String TYPE_TXT = "txt";
    public static final String TYPE_PPTX = "pptx";
    public static final String TYPE_NULL = "null";
    public static final String TYPE_FOLDER = "FOLDER";

    public static final String TYPE_APK = "apk";
    public static final String TYPE_ZIP = "zip";
    public static final String TYPE_RAR = "rar";
    public static final String TYPE_GZIP = "Gzip";
    public static final String TYPE_7ZIP = "7zip";
    public static final String TYPE_TAR = "tar";

    public static final String TYPE_MP3 = "mp3";
    public static final String TYPE_mp4 = "mp4";

    public static final String LINK_DEFAUL_COMPRESS = "/Zazip/Compressed";
    public static final String LINK_DEFAUL_EXTRACT = "/Zazip/Extracted";

    //type document
    public static final String DOCUMENT_PDF = "pdf";
    public static final String DOCUMENT_DOC = "doc";
    public static final String DOCUMENT_DOC_X = "docx";
    public static final String DOCUMENT_XLSX = "xlsx";
    public static final String DOCUMENT_TXT = "txt";
    public static final String DOCUMENT_PPTX = "pptx";
    public static final String DOCUMENT_PUB = "pub";


    //type achiver
    public static final String ARCHIVE_ZIP = "zip";
    public static final String ARCHIVE_GZIP = "Gzip";
    public static final String ARCHIVE_RAR = "rar";
    public static final String ARCHIVE_7ZIP = "7z";
    public static final String ARCHIVE_TAR = "tar";
    public static final String ARCHIVE_CAB = "cab";
    public static final String ARCHIVE_BIN = "bin";
    public static final String ARCHIVE_JAR = "jar";
    public static final String[] ARCHIVE_TYPE = {"zip", "Gzip", "rar", "7z", "tar", "cab", "bin", "jar"};

    //type sound
    public static final String SOUND_MP3 = "mp3";
    public static final String SOUND_WMA = "wma";
    public static final String SOUND_WAV = "wav";
    public static final String SOUND_FLAC = "flac";
    public static final String SOUND_OGG = "ogg";
    public static final String SOUND_PCM = "pcm";
    public static final String SOUND_AIF = "aif";
    public static final String SOUND_ALAC = "alac";
    public static final String SOUND_AMR = "amr";
    public static final String SOUND_MIDI = "midi";
    public static final String SOUND_WMA9 = "wma9";
    public static final String SOUND_AC3 = "ac3";
    public static final String SOUND_AAC = "aac";
    public static final String SOUND_MP2 = "mp2";
    public static final String[] SOUND_TYPE = {"mp3", "wma", "wav", "flac", "ogg", "pcm", "aif", "alac", "amr", "midi", "wma9", "ac3", "aac", "mp2"};


    //type video
    public static final String VIDEO_MP4 = "mp4";
    public static final String VIDEO_MKV = "mkv";
    public static final String VIDEO_WMV = "wmv";
    public static final String VIDEO_VOB = "vob";
    public static final String VIDEO_FLV = "flv";
    public static final String VIDEO_DLVX = "dlvx";
    public static final String VIDEO_AVI = "avi";
    public static final String VIDEO_MPGE = "mpge";
    public static final String VIDEO_WEBV = "webv";
    public static final String VIDEO_3GP = "3gp";
    public static final String[] VIDEO_TYPE = {"mp4", "mkv", "wmv", "vob", "flv", "dlvx", "avi", "mpge", "webv", "3gp"};


    //type image
    public static final String IMAGE_JPG = "jpg";
    public static final String IMAGE_JPEG = "jpeg";
    public static final String IMAGE_PNG = "png";
    public static final String IMAGE_GIF = "gif";
    public static final String IMAGE_BMP = "bmp";
    public static final String IMAGE_WEBP = "webp";
    public static final String[] IMAGE_TYPE = {"jpg", "jpeg", "png", "gif", "bmp", "webp"};

    //folder hide
    public static final String PATH_THUMBNAILS = "thumbnails";
    public static final String PATH_ICON = "icon";
    public static final String PATH_COVER = "cover";
    public static final String PATH_CACHE = "cache";
    public static final String PATH_TRASH = "Trash";


    /*SharePreference constant*/
    public static final String PREF_SETTING_LANGUAGE = "pref_setting_language";

    /*switch type db, this only use in this sample*/
    public static final int DB_TYPE_ROOM = 1;


    //event bus
    public static final int EVENT_CHANGE_NAME = 1;
    public static final int EVENT_DELETE = 2;


}
