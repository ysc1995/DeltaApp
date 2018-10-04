package com.example.shaochengyang.deltaapp.ui.bookflight.oneway;

public class OneWayFragmentPresenter implements IOneWayFragmentPresenter {
    IOneWayFragmentView iOneWayFragmentView;
    public OneWayFragmentPresenter(OneWayFragment oneWayFragment) {
        iOneWayFragmentView = oneWayFragment;

    }

    @Override
    public void minusOneTicket() {
        iOneWayFragmentView.minusOneTicket();
    }

    @Override
    public void plusOneTicket() {
        iOneWayFragmentView.plusOneTicket();
    }
}
