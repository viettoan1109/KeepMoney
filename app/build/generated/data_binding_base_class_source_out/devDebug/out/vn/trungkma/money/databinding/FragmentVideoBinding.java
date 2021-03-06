// Generated by data binding compiler. Do not edit!
package vn.trungkma.money.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.Deprecated;
import java.lang.Object;
import vn.trungkma.money.R;

public abstract class FragmentVideoBinding extends ViewDataBinding {
  @NonNull
  public final ImageView imgVideoBack;

  @NonNull
  public final ImageView imgVideoSearch;

  @NonNull
  public final ProgressBar progressVideo;

  @NonNull
  public final RecyclerView recyclerVideo;

  @NonNull
  public final RelativeLayout rlVideoAds;

  @NonNull
  public final SearchView searchVideo;

  @NonNull
  public final TextView tvVideo;

  @NonNull
  public final TextView tvVideoCancel;

  @NonNull
  public final TextView tvVideoNoData;

  protected FragmentVideoBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView imgVideoBack, ImageView imgVideoSearch, ProgressBar progressVideo,
      RecyclerView recyclerVideo, RelativeLayout rlVideoAds, SearchView searchVideo,
      TextView tvVideo, TextView tvVideoCancel, TextView tvVideoNoData) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imgVideoBack = imgVideoBack;
    this.imgVideoSearch = imgVideoSearch;
    this.progressVideo = progressVideo;
    this.recyclerVideo = recyclerVideo;
    this.rlVideoAds = rlVideoAds;
    this.searchVideo = searchVideo;
    this.tvVideo = tvVideo;
    this.tvVideoCancel = tvVideoCancel;
    this.tvVideoNoData = tvVideoNoData;
  }

  @NonNull
  public static FragmentVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_video, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentVideoBinding>inflateInternal(inflater, R.layout.fragment_video, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentVideoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_video, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentVideoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentVideoBinding>inflateInternal(inflater, R.layout.fragment_video, null, false, component);
  }

  public static FragmentVideoBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentVideoBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentVideoBinding)bind(component, view, R.layout.fragment_video);
  }
}
