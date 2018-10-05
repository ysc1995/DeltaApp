package com.example.shaochengyang.deltaapp.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.authentication.login.LoginActivity;
import com.example.shaochengyang.deltaapp.ui.seatreserve.ecoseat.EcoSeatReserveActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoreFragment extends Fragment {

    @BindView(R.id.tv_more_logout)
    TextView tvMoreLogout;
    Unbinder unbinder;
    @BindView(R.id.tv_more_profile)
    TextView tvMoreProfile;
    @BindView(R.id.tv_more_fstatus)
    TextView tvMoreFstatus;
    @BindView(R.id.tv_more_trackbags)
    TextView tvMoreTrackbags;
    @BindView(R.id.tv_more_skyclub)
    TextView tvMoreSkyclub;
    @BindView(R.id.tv_more_fstatus2)
    TextView tvMoreFstatus2;
    @BindView(R.id.tv_more_seat)
    TextView tvMoreSeat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_more, container, false);

        unbinder = ButterKnife.bind(this, view);

        tvMoreLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });

        tvMoreSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EcoSeatReserveActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
