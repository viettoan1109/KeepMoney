package vn.trungkma.money.ui.explorer;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import vn.trungkma.money.R;

public class ExplorerFragmentDirections {
  private ExplorerFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionExplorerFragmentToDocFragment() {
    return new ActionOnlyNavDirections(R.id.action_explorerFragment_to_docFragment);
  }

  @NonNull
  public static NavDirections actionExplorerFragmentToPhotoFragment() {
    return new ActionOnlyNavDirections(R.id.action_explorerFragment_to_photoFragment);
  }

  @NonNull
  public static NavDirections actionExplorerFragmentToMusicFragment() {
    return new ActionOnlyNavDirections(R.id.action_explorerFragment_to_musicFragment);
  }

  @NonNull
  public static NavDirections actionExplorerFragmentToDownloadFragment() {
    return new ActionOnlyNavDirections(R.id.action_explorerFragment_to_downloadFragment);
  }

  @NonNull
  public static NavDirections actionExplorerFragmentToVideoFragment() {
    return new ActionOnlyNavDirections(R.id.action_explorerFragment_to_videoFragment);
  }

  @NonNull
  public static NavDirections actionExplorerFragmentToApkFragment() {
    return new ActionOnlyNavDirections(R.id.action_explorerFragment_to_apkFragment);
  }
}
