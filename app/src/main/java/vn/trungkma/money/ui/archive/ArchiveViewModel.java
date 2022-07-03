package vn.trungkma.money.ui.archive;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.data.repository.ArchiveRepository;
import vn.trungkma.money.ui.base.BaseViewModel;

@HiltViewModel
public class ArchiveViewModel extends BaseViewModel {
    private ArchiveRepository fileRepository;
    public MutableLiveData<List<FileData>> mutableLiveDataArchive = new MutableLiveData<>();

    @Inject
    public ArchiveViewModel(ArchiveRepository fileRepository) {
        this.fileRepository = fileRepository;
    }



    public void getArchive() {
        fileRepository.getArchive().subscribe(new SingleObserver<List<FileData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<FileData> fileData) {
                mutableLiveDataArchive.postValue(fileData);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
