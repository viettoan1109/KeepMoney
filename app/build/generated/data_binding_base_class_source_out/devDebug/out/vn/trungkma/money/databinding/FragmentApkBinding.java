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

public abstract class FragmentApkBinding extends ViewDataBinding {
  @NonNull
  public final ImageView imgApkBack;

  @NonNull
  public final ImageView imgSearchApk;

  @NonNull
  public final ProgressBar progressApk;

  @NonNull
  public final RecyclerView recyclerApk;

  @NonNull
  public final RelativeLayout rlApkAds;

  @NonNull
  public final SearchView searchApk;

  @NonNull
  public final TextView tvApk;

  @NonNull
  public final TextView tvApkCancel;

  @NonNull
  public final TextView tvApkNoData;

  protected FragmentApkBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView imgApkBack, ImageView imgSearchApk, ProgressBar progressApk,
      RecyclerView recyclerApk, RelativeLayout rlApkAds, SearchView searchApk, TextView tvApk,
      TextView tvApkCancel, TextView tvApkNoData) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imgApkBack = imgApkBack;
    this.imgSearchApk = imgSearchApk;
    this.progressApk = progressApk;
    this.recyclerApk = recyclerApk;
    this.rlApkAds = rlApkAds;
    this.searchApk = searchApk;
    this.tvApk = tvApk;
    this.tvApkCancel = tvApkCancel;
    this.tvApkNoData = tvApkNoData;
  }

  @NonNull
  public static FragmentApkBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_apk, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentApkBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentApkBinding>inflateInternal(inflater, R.layout.fragment_apk, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentApkBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_apk, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentApkBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentApkBinding>inflateInternal(inflater, R.layout.fragment_apk, null, false, component);
  }

  public static FragmentApkBinding bind(@NonNull View view) {
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
  public static FragmentApkBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentApkBinding)bind(component, view, R.layout.fragment_apk);
  }
}