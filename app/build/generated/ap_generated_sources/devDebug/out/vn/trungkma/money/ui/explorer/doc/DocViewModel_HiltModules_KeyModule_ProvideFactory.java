// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.ui.explorer.doc;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DocViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static DocViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(DocViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final DocViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new DocViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
