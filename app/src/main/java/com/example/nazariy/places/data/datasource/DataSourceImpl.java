package com.example.nazariy.places.data.datasource;

import com.example.nazariy.places.BuildConfig;
import com.example.nazariy.places.data.api.Api;
import com.example.nazariy.places.data.repository.remote.RemoteRepository;
import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.places.PlaceResult;
import com.example.nazariy.places.domain.interfaces.DataSource;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSourceImpl implements DataSource {
    private RemoteRepository remoteDataSource;

    public DataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        remoteDataSource = new RemoteRepository(api);
    }

    @Override
    public Observable<PlaceResult> getPlaces(String location, int radius) {
        // check if cache empty
        // doOnNext -> saveToCache
        return remoteDataSource.getPlaces(location, radius)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PlaceDetailsResult> getPlaceDetails(String id) {
        return remoteDataSource.getPlaceDetails(id)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PhotoResult> getPhotos(String id) {
        return remoteDataSource.getPhotos(id)
                .subscribeOn(Schedulers.io());
    }

}