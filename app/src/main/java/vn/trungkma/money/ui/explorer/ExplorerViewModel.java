package vn.trungkma.money.ui.explorer;

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
public class ExplorerViewModel extends BaseViewModel {

    private DownloadRepository downloadRepository;
    public MutableLiveData<List<FileData>> mutableLiveDataExplorer = new MutableLiveData<>();

    @Inject
    public ExplorerViewModel(DownloadRepository downloadRepository){
        this.downloadRepository = downloadRepository;
    }

    public void getExplorer(String storagePath) {
        downloadRepository.getDownload(storagePath).subscribe(new SingleObserver<List<FileData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<FileData> fileData) {
                mutableLiveDataExplorer.postValue(fileData);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
