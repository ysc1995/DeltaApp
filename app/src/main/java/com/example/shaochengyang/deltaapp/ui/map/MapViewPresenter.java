package com.example.shaochengyang.deltaapp.ui.map;

import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;

public class MapViewPresenter implements IMapViewPresenter, IDataManager.onDatabaseListener {

    IMapView iMapView;
    IDataManager iDataManager;

    public MapViewPresenter(MapViewFragment mapViewFragment) {
        iMapView = mapViewFragment;
        iDataManager = new DataManager(mapViewFragment.getActivity());
    }

    @Override
    public void getLocationFromDb(String departCity, String arrCity) {
        iDataManager.getCityPosition(this,departCity, arrCity);
    }

    @Override
    public void passCityPositions(String fromCityLati, String fromCityLong, String toCityLati, String toCityLong) {
        iMapView.setLocation(fromCityLati,fromCityLong,toCityLati,toCityLong);
    }


}
