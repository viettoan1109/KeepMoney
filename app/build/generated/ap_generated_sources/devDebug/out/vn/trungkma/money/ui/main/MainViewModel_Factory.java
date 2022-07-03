// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.main;

import dagger.internal.Factory;
import javax.inject.Provider;
import vn.trungkma.money.data.repository.FileRecentRepository;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<FileRecentRepository> fileRecentRepositoryProvider;

  public MainViewModel_Factory(Provider<FileRecentRepository> fileRecentRepositoryProvider) {
    this.fileRecentRepositoryProvider = fileRecentRepositoryProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(fileRecentRepositoryProvider.get());
  }

  public static MainViewModel_Factory create(
      Provider<FileRecentRepository> fileRecentRepositoryProvider) {
    return new MainViewModel_Factory(fileRecentRepositoryProvider);
  }

  public static MainViewModel newInstance(FileRecentRepository fileRecentRepository) {
    return new MainViewModel(fileRecentRepository);
  }
}
