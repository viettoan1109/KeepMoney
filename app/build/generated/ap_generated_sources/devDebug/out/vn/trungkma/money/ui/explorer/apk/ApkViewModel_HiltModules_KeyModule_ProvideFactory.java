// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.explorer.apk;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApkViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static ApkViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(ApkViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final ApkViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ApkViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}