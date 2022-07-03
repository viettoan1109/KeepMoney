// Generated by Dagger (https://dagger.dev).
package vn.trungkma.money;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_Lifecycle_Factory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MemoizedSentinel;
import dagger.internal.Preconditions;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import vn.trungkma.money.data.repository.ApkRepository;
import vn.trungkma.money.data.repository.ArchiveRepository;
import vn.trungkma.money.data.repository.DocumentRepository;
import vn.trungkma.money.data.repository.DownloadRepository;
import vn.trungkma.money.data.repository.FileRecentRepository;
import vn.trungkma.money.data.repository.MusicRepository;
import vn.trungkma.money.data.repository.PhotoRepository;
import vn.trungkma.money.data.repository.VideoRepository;
import vn.trungkma.money.ui.archive.ArchiveViewModel;
import vn.trungkma.money.ui.archive.ArchiveViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.base.BaseActivity;
import vn.trungkma.money.ui.base.BaseDialogFragment;
import vn.trungkma.money.ui.base.BaseFragment;
import vn.trungkma.money.ui.explorer.ExplorerViewModel;
import vn.trungkma.money.ui.explorer.ExplorerViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.explorer.apk.ApkViewModel;
import vn.trungkma.money.ui.explorer.apk.ApkViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.explorer.browse.BrowseViewModel;
import vn.trungkma.money.ui.explorer.browse.BrowseViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.explorer.doc.DocViewModel;
import vn.trungkma.money.ui.explorer.doc.DocViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.explorer.download.DownloadViewModel;
import vn.trungkma.money.ui.explorer.download.DownloadViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.explorer.music.MusicViewModel;
import vn.trungkma.money.ui.explorer.music.MusicViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.explorer.photo.PhotoViewModel;
import vn.trungkma.money.ui.explorer.photo.PhotoViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.explorer.video.VideoViewModel;
import vn.trungkma.money.ui.explorer.video.VideoViewModel_HiltModules_KeyModule_ProvideFactory;
import vn.trungkma.money.ui.main.MainViewModel;
import vn.trungkma.money.ui.main.MainViewModel_HiltModules_KeyModule_ProvideFactory;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerApp_HiltComponents_SingletonC extends App_HiltComponents.SingletonC {
  private final ApplicationContextModule applicationContextModule;

  private DaggerApp_HiltComponents_SingletonC(
      ApplicationContextModule applicationContextModuleParam) {
    this.applicationContextModule = applicationContextModuleParam;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public ActivityRetainedComponentBuilder retainedComponentBuilder() {
    return new ActivityRetainedCBuilder();
  }

  @Override
  public ServiceComponentBuilder serviceComponentBuilder() {
    return new ServiceCBuilder();
  }

  @Override
  public void injectApp(App app) {
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public App_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new DaggerApp_HiltComponents_SingletonC(applicationContextModule);
    }
  }

  private final class ActivityRetainedCBuilder implements App_HiltComponents.ActivityRetainedC.Builder {
    @Override
    public App_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl();
    }
  }

  private final class ActivityRetainedCImpl extends App_HiltComponents.ActivityRetainedC {
    private volatile Object lifecycle = new MemoizedSentinel();

    private ActivityRetainedCImpl() {

    }

    private Object lifecycle() {
      Object local = lifecycle;
      if (local instanceof MemoizedSentinel) {
        synchronized (local) {
          local = lifecycle;
          if (local instanceof MemoizedSentinel) {
            local = ActivityRetainedComponentManager_Lifecycle_Factory.newInstance();
            lifecycle = DoubleCheck.reentrantCheck(lifecycle, local);
          }
        }
      }
      return (Object) local;
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder();
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return (ActivityRetainedLifecycle) lifecycle();
    }

    private final class ActivityCBuilder implements App_HiltComponents.ActivityC.Builder {
      private Activity activity;

      @Override
      public ActivityCBuilder activity(Activity activity) {
        this.activity = Preconditions.checkNotNull(activity);
        return this;
      }

      @Override
      public App_HiltComponents.ActivityC build() {
        Preconditions.checkBuilderRequirement(activity, Activity.class);
        return new ActivityCImpl(activity);
      }
    }

    private final class ActivityCImpl extends App_HiltComponents.ActivityC {
      private ActivityCImpl(Activity activity) {

      }

      @Override
      public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
        return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(ApplicationContextModule_ProvideApplicationFactory.provideApplication(DaggerApp_HiltComponents_SingletonC.this.applicationContextModule), getViewModelKeys(), new ViewModelCBuilder(), ImmutableSet.<ViewModelProvider.Factory>of(), ImmutableSet.<ViewModelProvider.Factory>of());
      }

      @Override
      public Set<String> getViewModelKeys() {
        return ImmutableSet.<String>of(ApkViewModel_HiltModules_KeyModule_ProvideFactory.provide(), ArchiveViewModel_HiltModules_KeyModule_ProvideFactory.provide(), BrowseViewModel_HiltModules_KeyModule_ProvideFactory.provide(), DocViewModel_HiltModules_KeyModule_ProvideFactory.provide(), DownloadViewModel_HiltModules_KeyModule_ProvideFactory.provide(), ExplorerViewModel_HiltModules_KeyModule_ProvideFactory.provide(), MainViewModel_HiltModules_KeyModule_ProvideFactory.provide(), MusicViewModel_HiltModules_KeyModule_ProvideFactory.provide(), PhotoViewModel_HiltModules_KeyModule_ProvideFactory.provide(), VideoViewModel_HiltModules_KeyModule_ProvideFactory.provide());
      }

      @Override
      public ViewModelComponentBuilder getViewModelComponentBuilder() {
        return new ViewModelCBuilder();
      }

      @Override
      public FragmentComponentBuilder fragmentComponentBuilder() {
        return new FragmentCBuilder();
      }

      @Override
      public ViewComponentBuilder viewComponentBuilder() {
        return new ViewCBuilder();
      }

      @Override
      public void injectBaseActivity(BaseActivity baseActivity) {
      }

      private final class FragmentCBuilder implements App_HiltComponents.FragmentC.Builder {
        private Fragment fragment;

        @Override
        public FragmentCBuilder fragment(Fragment fragment) {
          this.fragment = Preconditions.checkNotNull(fragment);
          return this;
        }

        @Override
        public App_HiltComponents.FragmentC build() {
          Preconditions.checkBuilderRequirement(fragment, Fragment.class);
          return new FragmentCImpl(fragment);
        }
      }

      private final class FragmentCImpl extends App_HiltComponents.FragmentC {
        private FragmentCImpl(Fragment fragment) {

        }

        @Override
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
          return ActivityCImpl.this.getHiltInternalFactoryFactory();
        }

        @Override
        public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
          return new ViewWithFragmentCBuilder();
        }

        @Override
        public void injectBaseDialogFragment(BaseDialogFragment baseDialogFragment) {
        }

        @Override
        public void injectBaseFragment(BaseFragment baseFragment) {
        }

        private final class ViewWithFragmentCBuilder implements App_HiltComponents.ViewWithFragmentC.Builder {
          private View view;

          @Override
          public ViewWithFragmentCBuilder view(View view) {
            this.view = Preconditions.checkNotNull(view);
            return this;
          }

          @Override
          public App_HiltComponents.ViewWithFragmentC build() {
            Preconditions.checkBuilderRequirement(view, View.class);
            return new ViewWithFragmentCImpl(view);
          }
        }

        private final class ViewWithFragmentCImpl extends App_HiltComponents.ViewWithFragmentC {
          private ViewWithFragmentCImpl(View view) {

          }
        }
      }

      private final class ViewCBuilder implements App_HiltComponents.ViewC.Builder {
        private View view;

        @Override
        public ViewCBuilder view(View view) {
          this.view = Preconditions.checkNotNull(view);
          return this;
        }

        @Override
        public App_HiltComponents.ViewC build() {
          Preconditions.checkBuilderRequirement(view, View.class);
          return new ViewCImpl(view);
        }
      }

      private final class ViewCImpl extends App_HiltComponents.ViewC {
        private ViewCImpl(View view) {

        }
      }
    }

    private final class ViewModelCBuilder implements App_HiltComponents.ViewModelC.Builder {
      private SavedStateHandle savedStateHandle;

      @Override
      public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
        this.savedStateHandle = Preconditions.checkNotNull(handle);
        return this;
      }

      @Override
      public App_HiltComponents.ViewModelC build() {
        Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
        return new ViewModelCImpl(savedStateHandle);
      }
    }

    private final class ViewModelCImpl extends App_HiltComponents.ViewModelC {
      private volatile Provider<ApkViewModel> apkViewModelProvider;

      private volatile Provider<ArchiveViewModel> archiveViewModelProvider;

      private volatile Provider<BrowseViewModel> browseViewModelProvider;

      private volatile Provider<DocViewModel> docViewModelProvider;

      private volatile Provider<DownloadViewModel> downloadViewModelProvider;

      private volatile Provider<ExplorerViewModel> explorerViewModelProvider;

      private volatile Provider<MainViewModel> mainViewModelProvider;

      private volatile Provider<MusicViewModel> musicViewModelProvider;

      private volatile Provider<PhotoViewModel> photoViewModelProvider;

      private volatile Provider<VideoViewModel> videoViewModelProvider;

      private ViewModelCImpl(SavedStateHandle savedStateHandle) {

      }

      private ApkViewModel apkViewModel() {
        return new ApkViewModel(new ApkRepository());
      }

      private Provider<ApkViewModel> apkViewModelProvider() {
        Object local = apkViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(0);
          apkViewModelProvider = (Provider<ApkViewModel>) local;
        }
        return (Provider<ApkViewModel>) local;
      }

      private ArchiveViewModel archiveViewModel() {
        return new ArchiveViewModel(new ArchiveRepository());
      }

      private Provider<ArchiveViewModel> archiveViewModelProvider() {
        Object local = archiveViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(1);
          archiveViewModelProvider = (Provider<ArchiveViewModel>) local;
        }
        return (Provider<ArchiveViewModel>) local;
      }

      private BrowseViewModel browseViewModel() {
        return new BrowseViewModel(new DownloadRepository());
      }

      private Provider<BrowseViewModel> browseViewModelProvider() {
        Object local = browseViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(2);
          browseViewModelProvider = (Provider<BrowseViewModel>) local;
        }
        return (Provider<BrowseViewModel>) local;
      }

      private DocViewModel docViewModel() {
        return new DocViewModel(new DocumentRepository());
      }

      private Provider<DocViewModel> docViewModelProvider() {
        Object local = docViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(3);
          docViewModelProvider = (Provider<DocViewModel>) local;
        }
        return (Provider<DocViewModel>) local;
      }

      private DownloadViewModel downloadViewModel() {
        return new DownloadViewModel(new DownloadRepository());
      }

      private Provider<DownloadViewModel> downloadViewModelProvider() {
        Object local = downloadViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(4);
          downloadViewModelProvider = (Provider<DownloadViewModel>) local;
        }
        return (Provider<DownloadViewModel>) local;
      }

      private ExplorerViewModel explorerViewModel() {
        return new ExplorerViewModel(new DownloadRepository());
      }

      private Provider<ExplorerViewModel> explorerViewModelProvider() {
        Object local = explorerViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(5);
          explorerViewModelProvider = (Provider<ExplorerViewModel>) local;
        }
        return (Provider<ExplorerViewModel>) local;
      }

      private MainViewModel mainViewModel() {
        return new MainViewModel(new FileRecentRepository());
      }

      private Provider<MainViewModel> mainViewModelProvider() {
        Object local = mainViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(6);
          mainViewModelProvider = (Provider<MainViewModel>) local;
        }
        return (Provider<MainViewModel>) local;
      }

      private MusicViewModel musicViewModel() {
        return new MusicViewModel(new MusicRepository());
      }

      private Provider<MusicViewModel> musicViewModelProvider() {
        Object local = musicViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(7);
          musicViewModelProvider = (Provider<MusicViewModel>) local;
        }
        return (Provider<MusicViewModel>) local;
      }

      private PhotoViewModel photoViewModel() {
        return new PhotoViewModel(new PhotoRepository());
      }

      private Provider<PhotoViewModel> photoViewModelProvider() {
        Object local = photoViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(8);
          photoViewModelProvider = (Provider<PhotoViewModel>) local;
        }
        return (Provider<PhotoViewModel>) local;
      }

      private VideoViewModel videoViewModel() {
        return new VideoViewModel(new VideoRepository());
      }

      private Provider<VideoViewModel> videoViewModelProvider() {
        Object local = videoViewModelProvider;
        if (local == null) {
          local = new SwitchingProvider<>(9);
          videoViewModelProvider = (Provider<VideoViewModel>) local;
        }
        return (Provider<VideoViewModel>) local;
      }

      @Override
      public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
        return ImmutableMap.<String, Provider<ViewModel>>builderWithExpectedSize(10).put("vn.trungkma.money.ui.explorer.apk.ApkViewModel", (Provider) apkViewModelProvider()).put("vn.trungkma.money.ui.archive.ArchiveViewModel", (Provider) archiveViewModelProvider()).put("vn.trungkma.money.ui.explorer.browse.BrowseViewModel", (Provider) browseViewModelProvider()).put("vn.trungkma.money.ui.explorer.doc.DocViewModel", (Provider) docViewModelProvider()).put("vn.trungkma.money.ui.explorer.download.DownloadViewModel", (Provider) downloadViewModelProvider()).put("vn.trungkma.money.ui.explorer.ExplorerViewModel", (Provider) explorerViewModelProvider()).put("vn.trungkma.money.ui.main.MainViewModel", (Provider) mainViewModelProvider()).put("vn.trungkma.money.ui.explorer.music.MusicViewModel", (Provider) musicViewModelProvider()).put("vn.trungkma.money.ui.explorer.photo.PhotoViewModel", (Provider) photoViewModelProvider()).put("vn.trungkma.money.ui.explorer.video.VideoViewModel", (Provider) videoViewModelProvider()).build();
      }

      private final class SwitchingProvider<T> implements Provider<T> {
        private final int id;

        SwitchingProvider(int id) {
          this.id = id;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T get() {
          switch (id) {
            case 0: // vn.trungkma.money.ui.explorer.apk.ApkViewModel 
            return (T) ViewModelCImpl.this.apkViewModel();

            case 1: // vn.trungkma.money.ui.archive.ArchiveViewModel 
            return (T) ViewModelCImpl.this.archiveViewModel();

            case 2: // vn.trungkma.money.ui.explorer.browse.BrowseViewModel 
            return (T) ViewModelCImpl.this.browseViewModel();

            case 3: // vn.trungkma.money.ui.explorer.doc.DocViewModel 
            return (T) ViewModelCImpl.this.docViewModel();

            case 4: // vn.trungkma.money.ui.explorer.download.DownloadViewModel 
            return (T) ViewModelCImpl.this.downloadViewModel();

            case 5: // vn.trungkma.money.ui.explorer.ExplorerViewModel 
            return (T) ViewModelCImpl.this.explorerViewModel();

            case 6: // vn.trungkma.money.ui.main.MainViewModel 
            return (T) ViewModelCImpl.this.mainViewModel();

            case 7: // vn.trungkma.money.ui.explorer.music.MusicViewModel 
            return (T) ViewModelCImpl.this.musicViewModel();

            case 8: // vn.trungkma.money.ui.explorer.photo.PhotoViewModel 
            return (T) ViewModelCImpl.this.photoViewModel();

            case 9: // vn.trungkma.money.ui.explorer.video.VideoViewModel 
            return (T) ViewModelCImpl.this.videoViewModel();

            default: throw new AssertionError(id);
          }
        }
      }
    }
  }

  private final class ServiceCBuilder implements App_HiltComponents.ServiceC.Builder {
    private Service service;

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public App_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(service);
    }
  }

  private final class ServiceCImpl extends App_HiltComponents.ServiceC {
    private ServiceCImpl(Service service) {

    }
  }
}