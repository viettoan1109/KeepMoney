// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.data.repository;

import dagger.internal.Factory;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PhotoRepository_Factory implements Factory<PhotoRepository> {
  @Override
  public PhotoRepository get() {
    return newInstance();
  }

  public static PhotoRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PhotoRepository newInstance() {
    return new PhotoRepository();
  }

  private static final class InstanceHolder {
    private static final PhotoRepository_Factory INSTANCE = new PhotoRepository_Factory();
  }
}
