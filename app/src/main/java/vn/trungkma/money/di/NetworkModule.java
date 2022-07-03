package vn.trungkma.money.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.trungkma.money.common.Constant;

@Module
@InstallIn(SingletonComponent.class)
public abstract class NetworkModule {

    @Provides
    @Singleton
    public static Retrofit providesRetrofit(
            OkHttpClient okHttpClient
    ) {
        return new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public static OkHttpClient providesOkHttpClientAppVersion() {
// SSLv3, TLSv1, TLSv1.1, TLSv1.2 etc.
        // SSLv3, TLSv1, TLSv1.1, TLSv1.2 etc.
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(Constant.CONNECT_S, TimeUnit.SECONDS)
                .writeTimeout(Constant.WRITE_S, TimeUnit.SECONDS)
                .readTimeout(Constant.READ_S, TimeUnit.SECONDS);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.addNetworkInterceptor(interceptor);
        return client.build();
    }
}
