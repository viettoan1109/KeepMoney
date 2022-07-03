package vn.trungkma.money.ui.explorer.music;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import vn.trungkma.money.data.model.FileData;
import vn.trungkma.money.data.repository.MusicRepository;
import vn.trungkma.money.ui.base.BaseViewModel;

@HiltViewModel
public class MusicViewModel extends BaseViewModel {

    private MusicRepository musicRepository;
    public MutableLiveData<List<FileData>> mutableLiveDataMusic = new MutableLiveData<>();

    @Inject
    public MusicViewModel(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }


    public void getMusic() {
        musicRepository.getMusic().subscribe(new SingleObserver<List<FileData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<FileData> fileData) {
                mutableLiveDataMusic.postValue(fileData);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
