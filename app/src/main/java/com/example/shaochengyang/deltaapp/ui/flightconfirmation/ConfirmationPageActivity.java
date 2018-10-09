package com.example.shaochengyang.deltaapp.ui.flightconfirmation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.data.model.BusinformationItem;
import com.example.shaochengyang.deltaapp.ui.data.model.FlightTicket;
import com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat.EcoSeatReserveActivity;
import com.example.shaochengyang.deltaapp.ui.seatreserve.firstclassseat.FirstClassSeatReserveActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmationPageActivity extends AppCompatActivity {

    private static final String TAG = "ConfirmationPageActivit";
    FlightTicket flightTicket;
    BusinformationItem flight;

    @BindView(R.id.tv_fc_flightno)
    TextView tvFcFlightno;
    @BindView(R.id.tv_fc_cabin)
    TextView tvFcCabin;
    @BindView(R.id.tv_fc_depart_time)
    TextView tvFcDepartTime;
    @BindView(R.id.tv_fc_arrive_time)
    TextView tvFcArriveTime;
    @BindView(R.id.tv_depart_airport)
    TextView tvDepartAirport;
    @BindView(R.id.tv_arrive_airport)
    TextView tvArriveAirport;
    @BindView(R.id.tv_fc_duration)
    TextView tvFcDuration;
    @BindView(R.id.tv_fc_price)
    TextView tvFcPrice;
    @BindView(R.id.img_QRcode)
    ImageView imgQRcode;

    Boolean isFirst;
    /*String numofTicket;
    String busid;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        ButterKnife.bind(this);
        isFirst = getIntent().getExtras().getBoolean("isFirst");
        /*busid = getIntent().getExtras().getString("busid");

        numofTicket = getIntent().getExtras().getString("numofTicket");*/
        showFlightOnScreen();

    }

    private void showFlightOnScreen() {

        flightTicket = getIntent().getExtras().getParcelable("ticket");
        flight = flightTicket.getFlightDetails();
        //flight = getIntent().getExtras().getParcelable("flight_confirmation");
        tvFcFlightno.setText(flight.getBusregistrationno());
        tvFcCabin.setText(flight.getBustype());
        tvFcArriveTime.setText(flight.getBoardingtime());
        tvFcDepartTime.setText(flight.getDropingtime());
        tvFcDuration.setText("Flight Duration" + flight.getJournyduration());
        tvFcPrice.setText("$" + flight.getFare() + " USD");

        // Whatever you need to encode in the QR code
        String text= flight.getBusid() + " " + flight.getBustype() + " " + flight.getFare();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            Log.d(TAG, "showFlightOnScreen: success");
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgQRcode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            Log.d(TAG, "showFlightOnScreen: " + e.toString());
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btn_fc_select_seat)
    public void onViewClicked() {
        //numofTicket = getIntent().getExtras().getString("numofTicket");
        String ticketID = getIntent().getExtras().getString("ticketID");
        if(!isFirst) {

            Intent intent = new Intent(this, EcoSeatReserveActivity.class);
            intent.putExtra("busid", flight.getBusid());
            intent.putExtra("numofTicket", flightTicket.getNumOfPassenger());
            intent.putExtra("ticketID",ticketID);

            startActivity(intent);
        }else{
            Intent intent = new Intent(this, FirstClassSeatReserveActivity.class);
            intent.putExtra("busid", flight.getBusid());
            intent.putExtra("numofTicket", flightTicket.getNumOfPassenger());
            intent.putExtra("ticketID",ticketID);

            startActivity(intent);
        }
    }
}
