// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.explorer;

import dagger.internal.Factory;
import javax.inject.Provider;
import vn.trungkma.money.data.repository.DownloadRepository;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ExplorerViewModel_Factory implements Factory<ExplorerViewModel> {
  private final Provider<DownloadRepository> downloadRepositoryProvider;

  public ExplorerViewModel_Factory(Provider<DownloadRepository> downloadRepositoryProvider) {
    this.downloadRepositoryProvider = downloadRepositoryProvider;
  }

  @Override
  public ExplorerViewModel get() {
    return newInstance(downloadRepositoryProvider.get());
  }

  public static ExplorerViewModel_Factory create(
      Provider<DownloadRepository> downloadRepositoryProvider) {
    return new ExplorerViewModel_Factory(downloadRepositoryProvider);
  }

  public static ExplorerViewModel newInstance(DownloadRepository downloadRepository) {
    return new ExplorerViewModel(downloadRepository);
  }
}
