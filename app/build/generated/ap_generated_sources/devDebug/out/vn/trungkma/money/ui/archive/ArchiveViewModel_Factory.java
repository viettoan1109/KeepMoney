// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.archive;

import dagger.internal.Factory;
import javax.inject.Provider;
import vn.trungkma.money.data.repository.ArchiveRepository;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ArchiveViewModel_Factory implements Factory<ArchiveViewModel> {
  private final Provider<ArchiveRepository> fileRepositoryProvider;

  public ArchiveViewModel_Factory(Provider<ArchiveRepository> fileRepositoryProvider) {
    this.fileRepositoryProvider = fileRepositoryProvider;
  }

  @Override
  public ArchiveViewModel get() {
    return newInstance(fileRepositoryProvider.get());
  }

  public static ArchiveViewModel_Factory create(
      Provider<ArchiveRepository> fileRepositoryProvider) {
    return new ArchiveViewModel_Factory(fileRepositoryProvider);
  }

  public static ArchiveViewModel newInstance(ArchiveRepository fileRepository) {
    return new ArchiveViewModel(fileRepository);
  }
}
