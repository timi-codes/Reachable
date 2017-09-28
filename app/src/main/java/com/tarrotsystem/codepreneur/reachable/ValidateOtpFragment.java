package com.tarrotsystem.codepreneur.reachable;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.OtpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by codepreneur on 8/22/17.
 */

public class ValidateOtpFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.phnumber_tv)
    TextView phnumber_tv;

    @BindView(R.id.broadcast_elapsetime)
    TextView broadcast_elapsetime;

    @BindView(R.id.verify)
    ImageView verify;

    @BindView(R.id.button)
    LinearLayout button;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private static final int BROADCAST_DURATION = 49000;
    private static final int BROADCAST_ENDTIME = 2000;


    @OnClick(R.id.editPhoneNumber)
    public void editPhoneNumber() {

        LandingFragment landingFragment = new LandingFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left,
                        R.anim.exit_to_right,
                        R.anim.enter_from_right,
                        R.anim.exit_to_left)
                .replace(R.id.fragment_container, landingFragment)
                .commit();
    }

    @OnClick(R.id.resendOtp)
    void resendOtp() {
        //TODO: resends otp from server
    }


    @OnClick(R.id.button)
    void verifyOtp() {
        if (button.isEnabled()){
            //TODO: verifies otp from server
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }


    public ValidateOtpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.validating_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();
        String phoneNumber = bundle.getString("phoneNumber");
        phnumber_tv.setText(phoneNumber);
        button.setEnabled(false);
        startBroadcastReceived_ElapsedTime();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void startBroadcastReceived_ElapsedTime(){
        new CountDownTimer(BROADCAST_DURATION, BROADCAST_ENDTIME) {

            public void onTick(long millisUntilFinished) {
                broadcast_elapsetime.setText(getString(R.string.timeelapsed_format,millisUntilFinished/1000));
            }

            public void onFinish() {
                broadcast_elapsetime.setVisibility(View.GONE);
                verify.setVisibility(View.VISIBLE);
                button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                button.setEnabled(true);
                progressBar.setVisibility(View.GONE);
            }

        }.start();
    }
}
