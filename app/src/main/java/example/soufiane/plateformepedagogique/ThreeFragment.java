package example.soufiane.plateformepedagogique;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.aenterhy.toggleswitch.ToggleSwitchButton;

/**
 * Created by Soufiane on 22/03/2016.
 */
public class ThreeFragment extends Fragment {

    private EditText  inputEmail, inputPassword;
    private TextInputLayout  inputLayoutEmail, inputLayoutPassword;
    private Button btnSignIn;
    private ToggleSwitchButton toggle ;


    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) view.findViewById(R.id.input_layout_password);

        inputEmail = (EditText) view.findViewById(R.id.input_email_username);
        inputPassword = (EditText) view.findViewById(R.id.input_password);
        btnSignIn = (Button) view.findViewById(R.id.btn_signin);

        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogIn();
            }
        });

        toggle = (ToggleSwitchButton) view.findViewById(R.id.toggle);
        toggle.setOnTriggerListener(new ToggleSwitchButton.OnTriggerListener() {
            @Override
            public void toggledUp() {
                Toast.makeText(getActivity().getApplicationContext(), "استاذ", Toast.LENGTH_SHORT).show();



                Intent i2 = new Intent(getActivity().getApplicationContext(), Registration.class);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity().getApplicationContext(), R.anim.fade_in, R.anim.fade_out).toBundle();
                getActivity().startActivity(i2, bundle);

            }

            @Override
            public void toggledDown() {
                Toast.makeText(getActivity().getApplicationContext(), "تلميذ", Toast.LENGTH_SHORT).show();


                Intent i2 = new Intent(getActivity().getApplicationContext(), Registration.class);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getActivity().getApplicationContext(), R.anim.fade_in, R.anim.fade_out).toBundle();
                getActivity().startActivity(i2, bundle);

            }
        });

        return  view ;
    }

    /**
     * Validating form
     */
    private void LogIn() {
        if (!validateEmailUsername()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Toast.makeText(getActivity().getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
    }




    private boolean validateEmailUsername() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() ) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email_username));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.input_email_username:
                    validateEmailUsername();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }

}