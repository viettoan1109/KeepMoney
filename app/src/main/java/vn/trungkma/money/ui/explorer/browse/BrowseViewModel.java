package vn.trungkma.money.ui.explorer.browse;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.data.repository.DownloadRepository;
import vn.trungkma.money.ui.base.BaseViewModel;

@HiltViewModel
public class BrowseViewModel extends BaseViewModel {
    private DownloadRepository explorerReponsitory;
    public MutableLiveData<List<FileData>> mutableLiveDataBrowse = new MutableLiveData<>();

    @Inject
    public BrowseViewModel(DownloadRepository explorerReponsitory) {
        this.explorerReponsitory = explorerReponsitory;
    }

    public void getBrowse(String storagePath) {
        explorerReponsitory.getDownload(storagePath).subscribe(new SingleObserver<List<FileData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<FileData> fileData) {
                mutableLiveDataBrowse.postValue(fileData);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
