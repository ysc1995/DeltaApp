package com.example.shaochengyang.deltaapp.ui.flightconfirmation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
    File mFile;
    SharedPreferences sharedPreferences;
    String senderEmail, recipientsEmail, text;

    /*String numofTicket;
    String busid;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        ButterKnife.bind(this);
        isFirst = getIntent().getExtras().getBoolean("isFirst");
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        senderEmail = sharedPreferences
                .getString("userEmail", "mickeyguo29@gmail.com");
        recipientsEmail = "mickeyguo29@gmail.com";
        Log.d(TAG, "onCreate: " + senderEmail + "   " + recipientsEmail);
        /*busid = getIntent().getExtras().getString("busid");

        numofTicket = getIntent().getExtras().getString("numofTicket");*/
        showFlightOnScreen();

    }

    //save img to emulator file
    private File savebitmap(Bitmap bmp) {

        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        File file = new File(extStorageDirectory, "temp" + ".png");
        Log.d(TAG, "savebitmap: " + file.toString());

        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp" + ".png");
            Log.d(TAG, "file exists: " + file.toString());
        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
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
        text = flight.getBusid() + " " + flight.getBustype() + " " + flight.getFare();
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

    }

    //build message
    private static Message buildMessage(Session session, String from, String recipients,
                                        String subject, String text, String filename)
            throws MessagingException, AddressException {
        Log.d(TAG, "buildMessage: ***********************building message");

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        message.setSubject(subject);

        BodyPart messageTextPart = new MimeBodyPart();
        messageTextPart.setText(text);

        BodyPart messageAttachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource(new File(filename));
        messageAttachmentPart.setDataHandler(new DataHandler(source));
        messageAttachmentPart.setFileName(filename);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageTextPart);
        multipart.addBodyPart(messageAttachmentPart);
        message.setContent(multipart);

        return message;
    }

    //create session
    private Session createSessionObject() {
        Log.d(TAG, "createSessionObject: session created*********************");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                //TODO: hardcode email and password
                return new PasswordAuthentication(
                        email,
                        password);
            }
        });
    }

    @OnClick({R.id.btn_fc_select_seat, R.id.btn_sendemail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_fc_select_seat:
                //numofTicket = getIntent().getExtras().getString("numofTicket");
                if (!isFirst) {
                    Intent intent = new Intent(this, EcoSeatReserveActivity.class);
                    intent.putExtra("busid", flight.getBusid());
                    intent.putExtra("numofTicket", flightTicket.getNumOfPassenger());

                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, FirstClassSeatReserveActivity.class);
                    intent.putExtra("busid", flight.getBusid());
                    intent.putExtra("numofTicket", flightTicket.getNumOfPassenger());

                    startActivity(intent);
                }
                break;
            case R.id.btn_sendemail:
                Drawable d = imgQRcode.getDrawable();
                BitmapDrawable bitDw = ((BitmapDrawable) d);
                Bitmap bitmap2 = bitDw.getBitmap();
                mFile = savebitmap(bitmap2);

                //send email
                try {
                    String subject = "Confirmation Ticket";
                    Message m = buildMessage(createSessionObject(),
                            senderEmail, recipientsEmail, subject, text, mFile.toString());
                    new SendMailTask().execute(m);
                    Log.d(TAG, "onCreate: email sending**********************");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    //async
    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(
                    ConfirmationPageActivity.this,
                    "Please wait",
                    "Sending mail",
                    true,
                    false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

//    @OnClick({R.id.btn_fc_select_seat})
//    public void onViewClicked() {
//        //numofTicket = getIntent().getExtras().getString("numofTicket");
//        if (!isFirst) {
//            Intent intent = new Intent(this, EcoSeatReserveActivity.class);
//            intent.putExtra("busid", flight.getBusid());
//            intent.putExtra("numofTicket", flightTicket.getNumOfPassenger());
//
//            startActivity(intent);
//        } else {
//            Intent intent = new Intent(this, FirstClassSeatReserveActivity.class);
//            intent.putExtra("busid", flight.getBusid());
//            intent.putExtra("numofTicket", flightTicket.getNumOfPassenger());
//
//            startActivity(intent);
//        }
//    }
}
