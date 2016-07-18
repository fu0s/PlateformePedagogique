package example.soufiane.plateformepedagogique;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration2 extends AppCompatActivity {


    private Toolbar toolbar;
    private EditText inputUserName, inputPassword2, inputPassword;
    private TextInputLayout inputLayoutUserName, inputLayoutPassword2, inputLayoutPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration2);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputLayoutUserName = (TextInputLayout) findViewById(R.id.input_layout_username);
        inputLayoutPassword2 = (TextInputLayout) findViewById(R.id.input_layout_password2);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputUserName = (EditText) findViewById(R.id.input_username);
        inputPassword2 = (EditText) findViewById(R.id.input_password2);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        inputUserName.addTextChangedListener(new MyTextWatcher(inputUserName));
        inputPassword2.addTextChangedListener(new MyTextWatcher(inputPassword2));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateUserName()) {
            return;
        }

        if (!validatePassword2()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        //firstName , lastName , ville , dateNaissance , email, login , password , avatar ;
        Registration.RegistrationMap.put("login",inputUserName.getText().toString());
        Registration.RegistrationMap.put("password",inputPassword.getText().toString());
        Registration.RegistrationMap.put("avatar", "1");
        sendMap();

    }

    private boolean validateUserName() {
        if (inputUserName.getText().toString().trim().isEmpty()) {
            inputLayoutUserName.setError(getString(R.string.err_msg_username));
            requestFocus(inputUserName);
            return false;
        } else {
            if (isValidUserName(inputUserName.getText().toString())){
                //find if the username is unique
            } else {
                inputLayoutUserName.setErrorEnabled(false);
            }
        }

        return true;
    }

    private boolean validatePassword2() {
        String Password = inputPassword2.getText().toString().trim();

        if (Password.isEmpty() || !isValidPassword(Password)) {
            inputLayoutPassword2.setError(getString(R.string.err_msg_password2));
            requestFocus(inputPassword2);
            return false;
        } else {
            inputLayoutPassword2.setErrorEnabled(false);
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

    private static boolean isValidUserName(String userName) {
        // a faire !!!!!!!
        return true ;
    }

    private  boolean isValidPassword(String password) {
       if (password.equals(inputPassword.getText().toString())){return true;}
        return false ;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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
                case R.id.input_username:
                    validateUserName();
                    break;
                case R.id.input_password2:
                    validatePassword2();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in, R.anim.move_left_out);
    }
    public void sendMap(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.27:3000/signup_mobile";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response

                        Toast.makeText(getApplicationContext(), "Thank You!" + response.toString(), Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error

                        Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params = Registration.RegistrationMap;

                return params;
            }
        };
        queue.add(postRequest);

    }
}