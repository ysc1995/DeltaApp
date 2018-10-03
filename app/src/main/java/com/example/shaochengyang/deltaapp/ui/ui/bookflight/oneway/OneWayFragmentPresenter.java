package com.example.shaochengyang.deltaapp.ui.ui.bookflight.oneway;

public class OneWayFragmentPresenter implements IOneWayFragmentPresenter {
    IOneWayFragmentView iOneWayFragmentView;
    public OneWayFragmentPresenter(OneWayFragment oneWayFragment) {
        iOneWayFragmentView = oneWayFragment;

    }

    @Override
    public void minusOneTickey() {
        iOneWayFragmentView.minusOneTickey();
    }
}
