// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.explorer.music;

import dagger.internal.Factory;
import javax.inject.Provider;
import vn.trungkma.money.data.repository.MusicRepository;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MusicViewModel_Factory implements Factory<MusicViewModel> {
  private final Provider<MusicRepository> musicRepositoryProvider;

  public MusicViewModel_Factory(Provider<MusicRepository> musicRepositoryProvider) {
    this.musicRepositoryProvider = musicRepositoryProvider;
  }

  @Override
  public MusicViewModel get() {
    return newInstance(musicRepositoryProvider.get());
  }

  public static MusicViewModel_Factory create(Provider<MusicRepository> musicRepositoryProvider) {
    return new MusicViewModel_Factory(musicRepositoryProvider);
  }

  public static MusicViewModel newInstance(MusicRepository musicRepository) {
    return new MusicViewModel(musicRepository);
  }
}