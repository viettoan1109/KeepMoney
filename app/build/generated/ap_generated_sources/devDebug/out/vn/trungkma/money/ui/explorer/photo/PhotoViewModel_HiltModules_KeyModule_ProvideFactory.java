// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.explorer.photo;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PhotoViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static PhotoViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(PhotoViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final PhotoViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new PhotoViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
