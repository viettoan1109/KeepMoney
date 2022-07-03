package vn.trungkma.money.ui.main;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.data.repository.ApkRepository;
import vn.trungkma.money.data.repository.FileRecentRepository;
import vn.trungkma.money.ui.base.BaseViewModel;

@HiltViewModel
public class MainViewModel extends BaseViewModel {
    private FileRecentRepository fileRecentRepository;
    private ApkRepository apkRepository;
    public MutableLiveData<List<FileData>> mutableLiveDataFileRecent = new MutableLiveData<>();

    @Inject
    public MainViewModel(FileRecentRepository fileRecentRepository) {
        this.fileRecentRepository = fileRecentRepository;
    }


    public void getFileRecent() {
        fileRecentRepository.getRecent().subscribe(new SingleObserver<List<FileData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<FileData> fileData) {
                mutableLiveDataFileRecent.postValue(fileData);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

}
