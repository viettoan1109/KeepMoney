package vn.trungkma.money.ui.explorer.photo;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.data.repository.PhotoRepository;
import vn.trungkma.money.ui.base.BaseViewModel;

@HiltViewModel
public class PhotoViewModel extends BaseViewModel {

    private PhotoRepository photoRepository;
    public MutableLiveData<List<FileData>> mutableLiveDataPhoto = new MutableLiveData<>();

    @Inject
    public PhotoViewModel(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void getPhoto() {
        photoRepository.getPhoto().subscribe(new SingleObserver<List<FileData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<FileData> fileData) {
                mutableLiveDataPhoto.postValue(fileData);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

    }
}
