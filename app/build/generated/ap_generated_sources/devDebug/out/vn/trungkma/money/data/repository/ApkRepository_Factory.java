// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.data.repository;

import dagger.internal.Factory;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApkRepository_Factory implements Factory<ApkRepository> {
  @Override
  public ApkRepository get() {
    return newInstance();
  }

  public static ApkRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ApkRepository newInstance() {
    return new ApkRepository();
  }

  private static final class InstanceHolder {
    private static final ApkRepository_Factory INSTANCE = new ApkRepository_Factory();
  }
}
