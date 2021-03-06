// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvidesOkHttpClientAppVersionFactory implements Factory<OkHttpClient> {
  @Override
  public OkHttpClient get() {
    return providesOkHttpClientAppVersion();
  }

  public static NetworkModule_ProvidesOkHttpClientAppVersionFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OkHttpClient providesOkHttpClientAppVersion() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.providesOkHttpClientAppVersion());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvidesOkHttpClientAppVersionFactory INSTANCE = new NetworkModule_ProvidesOkHttpClientAppVersionFactory();
  }
}
