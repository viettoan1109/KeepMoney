// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.explorer.video;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class VideoViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static VideoViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(VideoViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final VideoViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new VideoViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
