// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.explorer.browse;

import dagger.internal.Factory;
import javax.inject.Provider;
import vn.trungkma.money.data.repository.DownloadRepository;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BrowseViewModel_Factory implements Factory<BrowseViewModel> {
  private final Provider<DownloadRepository> explorerReponsitoryProvider;

  public BrowseViewModel_Factory(Provider<DownloadRepository> explorerReponsitoryProvider) {
    this.explorerReponsitoryProvider = explorerReponsitoryProvider;
  }

  @Override
  public BrowseViewModel get() {
    return newInstance(explorerReponsitoryProvider.get());
  }

  public static BrowseViewModel_Factory create(
      Provider<DownloadRepository> explorerReponsitoryProvider) {
    return new BrowseViewModel_Factory(explorerReponsitoryProvider);
  }

  public static BrowseViewModel newInstance(DownloadRepository explorerReponsitory) {
    return new BrowseViewModel(explorerReponsitory);
  }
}
