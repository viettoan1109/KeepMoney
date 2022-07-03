package vn.trungkma.money;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vn.trungkma.money.databinding.ActivityMainBindingImpl;
import vn.trungkma.money.databinding.FragmentApkBindingImpl;
import vn.trungkma.money.databinding.FragmentArchiveBindingImpl;
import vn.trungkma.money.databinding.FragmentBrowseBindingImpl;
import vn.trungkma.money.databinding.FragmentDocBindingImpl;
import vn.trungkma.money.databinding.FragmentDownloadBindingImpl;
import vn.trungkma.money.databinding.FragmentExplorerBindingImpl;
import vn.trungkma.money.databinding.FragmentHomeBindingImpl;
import vn.trungkma.money.databinding.FragmentMusicBindingImpl;
import vn.trungkma.money.databinding.FragmentPhotoBindingImpl;
import vn.trungkma.money.databinding.FragmentPlayPhotoBindingImpl;
import vn.trungkma.money.databinding.FragmentPolicyBindingImpl;
import vn.trungkma.money.databinding.FragmentSelectBindingImpl;
import vn.trungkma.money.databinding.FragmentVideoBindingImpl;
import vn.trungkma.money.databinding.ItemArchiveFilesBindingImpl;
import vn.trungkma.money.databinding.ItemFileBindingImpl;
import vn.trungkma.money.databinding.ItemFolderBrowseBindingImpl;
import vn.trungkma.money.databinding.ItemMediaBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_FRAGMENTAPK = 2;

  private static final int LAYOUT_FRAGMENTARCHIVE = 3;

  private static final int LAYOUT_FRAGMENTBROWSE = 4;

  private static final int LAYOUT_FRAGMENTDOC = 5;

  private static final int LAYOUT_FRAGMENTDOWNLOAD = 6;

  private static final int LAYOUT_FRAGMENTEXPLORER = 7;

  private static final int LAYOUT_FRAGMENTHOME = 8;

  private static final int LAYOUT_FRAGMENTMUSIC = 9;

  private static final int LAYOUT_FRAGMENTPHOTO = 10;

  private static final int LAYOUT_FRAGMENTPLAYPHOTO = 11;

  private static final int LAYOUT_FRAGMENTPOLICY = 12;

  private static final int LAYOUT_FRAGMENTSELECT = 13;

  private static final int LAYOUT_FRAGMENTVIDEO = 14;

  private static final int LAYOUT_ITEMARCHIVEFILES = 15;

  private static final int LAYOUT_ITEMFILE = 16;

  private static final int LAYOUT_ITEMFOLDERBROWSE = 17;

  private static final int LAYOUT_ITEMMEDIA = 18;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(18);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_apk, LAYOUT_FRAGMENTAPK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_archive, LAYOUT_FRAGMENTARCHIVE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_browse, LAYOUT_FRAGMENTBROWSE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_doc, LAYOUT_FRAGMENTDOC);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_download, LAYOUT_FRAGMENTDOWNLOAD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_explorer, LAYOUT_FRAGMENTEXPLORER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_music, LAYOUT_FRAGMENTMUSIC);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_photo, LAYOUT_FRAGMENTPHOTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_play_photo, LAYOUT_FRAGMENTPLAYPHOTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_policy, LAYOUT_FRAGMENTPOLICY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_select, LAYOUT_FRAGMENTSELECT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.fragment_video, LAYOUT_FRAGMENTVIDEO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.item_archive_files, LAYOUT_ITEMARCHIVEFILES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.item_file, LAYOUT_ITEMFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.item_folder_browse, LAYOUT_ITEMFOLDERBROWSE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(vn.trungkma.money.R.layout.item_media, LAYOUT_ITEMMEDIA);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTAPK: {
          if ("layout/fragment_apk_0".equals(tag)) {
            return new FragmentApkBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_apk is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTARCHIVE: {
          if ("layout/fragment_archive_0".equals(tag)) {
            return new FragmentArchiveBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_archive is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBROWSE: {
          if ("layout/fragment_browse_0".equals(tag)) {
            return new FragmentBrowseBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_browse is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDOC: {
          if ("layout/fragment_doc_0".equals(tag)) {
            return new FragmentDocBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_doc is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDOWNLOAD: {
          if ("layout/fragment_download_0".equals(tag)) {
            return new FragmentDownloadBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_download is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTEXPLORER: {
          if ("layout/fragment_explorer_0".equals(tag)) {
            return new FragmentExplorerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_explorer is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMUSIC: {
          if ("layout/fragment_music_0".equals(tag)) {
            return new FragmentMusicBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_music is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPHOTO: {
          if ("layout/fragment_photo_0".equals(tag)) {
            return new FragmentPhotoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_photo is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPLAYPHOTO: {
          if ("layout/fragment_play_photo_0".equals(tag)) {
            return new FragmentPlayPhotoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_play_photo is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPOLICY: {
          if ("layout/fragment_policy_0".equals(tag)) {
            return new FragmentPolicyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_policy is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSELECT: {
          if ("layout/fragment_select_0".equals(tag)) {
            return new FragmentSelectBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_select is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTVIDEO: {
          if ("layout/fragment_video_0".equals(tag)) {
            return new FragmentVideoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_video is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMARCHIVEFILES: {
          if ("layout/item_archive_files_0".equals(tag)) {
            return new ItemArchiveFilesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_archive_files is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFILE: {
          if ("layout/item_file_0".equals(tag)) {
            return new ItemFileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_file is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFOLDERBROWSE: {
          if ("layout/item_folder_browse_0".equals(tag)) {
            return new ItemFolderBrowseBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_folder_browse is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMMEDIA: {
          if ("layout/item_media_0".equals(tag)) {
            return new ItemMediaBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_media is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(18);

    static {
      sKeys.put("layout/activity_main_0", vn.trungkma.money.R.layout.activity_main);
      sKeys.put("layout/fragment_apk_0", vn.trungkma.money.R.layout.fragment_apk);
      sKeys.put("layout/fragment_archive_0", vn.trungkma.money.R.layout.fragment_archive);
      sKeys.put("layout/fragment_browse_0", vn.trungkma.money.R.layout.fragment_browse);
      sKeys.put("layout/fragment_doc_0", vn.trungkma.money.R.layout.fragment_doc);
      sKeys.put("layout/fragment_download_0", vn.trungkma.money.R.layout.fragment_download);
      sKeys.put("layout/fragment_explorer_0", vn.trungkma.money.R.layout.fragment_explorer);
      sKeys.put("layout/fragment_home_0", vn.trungkma.money.R.layout.fragment_home);
      sKeys.put("layout/fragment_music_0", vn.trungkma.money.R.layout.fragment_music);
      sKeys.put("layout/fragment_photo_0", vn.trungkma.money.R.layout.fragment_photo);
      sKeys.put("layout/fragment_play_photo_0", vn.trungkma.money.R.layout.fragment_play_photo);
      sKeys.put("layout/fragment_policy_0", vn.trungkma.money.R.layout.fragment_policy);
      sKeys.put("layout/fragment_select_0", vn.trungkma.money.R.layout.fragment_select);
      sKeys.put("layout/fragment_video_0", vn.trungkma.money.R.layout.fragment_video);
      sKeys.put("layout/item_archive_files_0", vn.trungkma.money.R.layout.item_archive_files);
      sKeys.put("layout/item_file_0", vn.trungkma.money.R.layout.item_file);
      sKeys.put("layout/item_folder_browse_0", vn.trungkma.money.R.layout.item_folder_browse);
      sKeys.put("layout/item_media_0", vn.trungkma.money.R.layout.item_media);
    }
  }
}
