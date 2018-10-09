package com.example.shaochengyang.deltaapp.ui.upcomingflightconfirm;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.DataManager;
import com.example.shaochengyang.deltaapp.ui.data.IDataManager;
import com.example.shaochengyang.deltaapp.ui.data.model.PassenterInfo;
import com.example.shaochengyang.deltaapp.ui.data.model.TicketInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UpComingFlightConfirmActivity extends AppCompatActivity implements IDataManager.onPassenterInfoListener, IDataManager.onSeatInfoListener {

    private static final String TAG = "ConfirmationPageActivit";
    @BindView(R.id.rv_upcoming_comfirm)
    RecyclerView rvUpcomingComfirm;
    RecyclerView.Adapter adapter;
    IDataManager iDataManager;

    List<PassenterInfo> passenterInfoList;
    List<String> seatList;
    @BindView(R.id.img_QRcode)
    ImageView imgQRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_flight_confirm);
        ButterKnife.bind(this);


        String ticketID = getIntent().getExtras().getString("ticketID");
        String arrAirport = getIntent().getExtras().getString("arrAirport");
        String depAirport = getIntent().getExtras().getString("depAirport");
        String cabin = getIntent().getExtras().getString("cabin");
        String depTime = getIntent().getExtras().getString("depTime");
        String arrTime = getIntent().getExtras().getString("arrTime");
        String flightNum = getIntent().getExtras().getString("flightNum");
        String flightDuration = getIntent().getExtras().getString("flightDuration");

        iDataManager = new DataManager(this);
        iDataManager.getPassenterInfo(this, ticketID);
        iDataManager.getSeatInfo(this, ticketID);
        TicketInfo ticketInfo = new TicketInfo(ticketID, arrAirport, depAirport, cabin, depTime, arrTime, flightNum, flightDuration);


        showFlightOnScreen(ticketInfo);

    }


    private void showFlightOnScreen(TicketInfo ticketInfo) {

        String text = ticketInfo.getFlightNum() + " " + ticketInfo.getTicketID() + " " + ticketInfo.getArrAirport()+
                " " + ticketInfo.getDepAirport();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            Log.d(TAG, "showFlightOnScreen: success");
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgQRcode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            Log.d(TAG, "showFlightOnScreen: " + e.toString());
            e.printStackTrace();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUpcomingComfirm.setLayoutManager(layoutManager);
        rvUpcomingComfirm.setItemAnimator(new DefaultItemAnimator());

        adapter = new UpComingFlightConfirmAdaptor(passenterInfoList, seatList, ticketInfo, UpComingFlightConfirmActivity.this);
        rvUpcomingComfirm.setAdapter(adapter);
    }


    @Override
    public void passPassengerInfo(List<PassenterInfo> passenterInfoList) {
        this.passenterInfoList = passenterInfoList;
    }

    @Override
    public void passSeatInfo(List<String> seatList) {
        this.seatList = seatList;
    }
}
