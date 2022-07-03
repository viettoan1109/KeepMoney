package vn.trungkma.money.ui.explorer.apk;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.data.repository.ApkRepository;
import vn.trungkma.money.ui.base.BaseViewModel;

@HiltViewModel
public class ApkViewModel extends BaseViewModel {
    private ApkRepository apkRepository;
    public MutableLiveData<List<FileData>> mutableLiveDataApk = new MutableLiveData<>();

    @Inject
    public ApkViewModel(ApkRepository apkRepository) {
        this.apkRepository = apkRepository;
    }


    public void getApk() {
        apkRepository.getPhoto().subscribe(new SingleObserver<List<FileData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<FileData> fileData) {
                mutableLiveDataApk.postValue(fileData);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

}
