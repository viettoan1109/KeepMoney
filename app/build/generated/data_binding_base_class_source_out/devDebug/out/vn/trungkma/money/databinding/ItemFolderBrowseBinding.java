// Generated by data binding compiler. Do not edit!
package vn.trungkma.money.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import vn.trungkma.money.R;

public abstract class ItemFolderBrowseBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout constraintBrowseItem;

  @NonNull
  public final ImageView imgBrowseCheck;

  @NonNull
  public final ImageView imgBrowseFolder;

  @NonNull
  public final TextView tvBrowseContent;

  @NonNull
  public final TextView tvBrowseTitle;

  protected ItemFolderBrowseBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ConstraintLayout constraintBrowseItem, ImageView imgBrowseCheck, ImageView imgBrowseFolder,
      TextView tvBrowseContent, TextView tvBrowseTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.constraintBrowseItem = constraintBrowseItem;
    this.imgBrowseCheck = imgBrowseCheck;
    this.imgBrowseFolder = imgBrowseFolder;
    this.tvBrowseContent = tvBrowseContent;
    this.tvBrowseTitle = tvBrowseTitle;
  }

  @NonNull
  public static ItemFolderBrowseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_folder_browse, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemFolderBrowseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemFolderBrowseBinding>inflateInternal(inflater, R.layout.item_folder_browse, root, attachToRoot, component);
  }

  @NonNull
  public static ItemFolderBrowseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_folder_browse, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemFolderBrowseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemFolderBrowseBinding>inflateInternal(inflater, R.layout.item_folder_browse, null, false, component);
  }

  public static ItemFolderBrowseBinding bind(@NonNull View view) {
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
  public static ItemFolderBrowseBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemFolderBrowseBinding)bind(component, view, R.layout.item_folder_browse);
  }
}
