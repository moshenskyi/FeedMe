package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.places.PlaceResult;

import io.reactivex.Observable;

public interface PlacesRepository {

    Observable<PlaceResult> getPlaces(String location, int radius);

    Observable<PlaceDetailsResult> getPlaceDetails(String id);

    Observable<PhotoResult> getPhotos(String id);

}
