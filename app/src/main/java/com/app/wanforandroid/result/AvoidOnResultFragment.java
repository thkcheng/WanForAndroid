package com.app.wanforandroid.result;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by thkcheng on 2018/11/27.
 */

public class AvoidOnResultFragment extends Fragment {

    private Map<Integer, PublishSubject<ActivityResultInfo>> mSubjects = new HashMap<>();
    private Map<Integer, AvoidOnResult.Callback> mCallbacks = new HashMap<>();

    public AvoidOnResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Observable<ActivityResultInfo> startForResult(final Intent intent) {
        final PublishSubject<ActivityResultInfo> subject = PublishSubject.create();
        return subject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                int requestCode = generateRequestCode();
                mSubjects.put(requestCode, subject);
                startActivityForResult(intent, requestCode);
            }
        });
    }

    public void startForResult(Intent intent, AvoidOnResult.Callback callback) {
        int requestCode = generateRequestCode();
        mCallbacks.put(requestCode, callback);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //rxjava方式的处理
        PublishSubject<ActivityResultInfo> subject = mSubjects.remove(requestCode);
        if (subject != null) {
            subject.onNext(new ActivityResultInfo(resultCode, data));
            subject.onComplete();
        }

        //callback方式的处理
        AvoidOnResult.Callback callback = mCallbacks.remove(requestCode);
        if (callback != null) {
            callback.onActivityResult(resultCode, data);
        }
    }

    private int generateRequestCode(){
        Random random = new Random();
        for (;;){
            int code = random.nextInt(65536);
            if (!mSubjects.containsKey(code) && !mCallbacks.containsKey(code)){
                return code;
            }
        }
    }

}
