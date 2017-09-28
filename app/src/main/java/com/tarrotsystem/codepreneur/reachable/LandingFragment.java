package com.tarrotsystem.codepreneur.reachable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.hbb20.CountryCodePicker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by codepreneur on 8/22/17.
 */

public class LandingFragment extends Fragment {

    @BindView(R.id.phnumber_et)
    EditText phoneEditText;

    @BindView(R.id.countryCode)
    EditText countryCode;

    @BindView(R.id.ccp)
    CountryCodePicker codePicker;

    private Unbinder unbinder;


    public LandingFragment(){}


    @OnClick(R.id.proceed_btn)
    public void proceed(){
        if (phoneEditText.length() != 0){

            Bundle bundle = new Bundle();
            bundle.putString("phoneNumber",getString(R.string.fullphonenumber_format,codePicker.getSelectedCountryCode(),String.valueOf(phoneEditText.getEditableText())
            ));

            ValidateOtpFragment validateOtpFragment = new ValidateOtpFragment();
            validateOtpFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right)
                    .replace(R.id.fragment_container, validateOtpFragment)
                    .commit();
        }else{
            phoneEditText.setError(AppUtils.getStringFromResources(getContext(),R.string.error_phone_no));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.landing_fragment,container,false);
        unbinder = ButterKnife.bind(this,rootView);

        codePicker.setOnCountryChangeListener(() -> countryCode.setText(getString(R.string.countrycode_format,codePicker.getSelectedCountryCode())));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
