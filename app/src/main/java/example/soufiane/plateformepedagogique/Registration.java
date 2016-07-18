package example.soufiane.plateformepedagogique;

import android.app.ActivityOptions;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Soufiane on 23/03/2016.
 */
public class Registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    static Map<String, String> RegistrationMap = new HashMap<String, String>();

    public static final String DATEPICKER_TAG = "datepicker";
    private Toolbar toolbar;
    private EditText inputName, inputSirName,inputCity, inputEmail,inputBirthday ;
    private TextInputLayout inputLayoutName,inputLayoutSirName,inputLayoutCity, inputLayoutEmail, inputLayoutBirthday;
    private Button btnSignUp;
    //nom prenom ville date de naissance email   datepicker
//اسم المستخدم كلمة المرور    و صورة
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), isVibrate());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutSirName = (TextInputLayout) findViewById(R.id.input_layout_sirname);
        inputLayoutCity = (TextInputLayout) findViewById(R.id.input_layout_city);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutBirthday = (TextInputLayout) findViewById(R.id.input_layout_birthday);
        inputName = (EditText) findViewById(R.id.input_name);
        inputSirName = (EditText) findViewById(R.id.input_sirname);
        inputCity = (EditText) findViewById(R.id.input_city);
        inputBirthday = (EditText) findViewById(R.id.input_birthday);
        inputEmail = (EditText) findViewById(R.id.input_email);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputSirName.addTextChangedListener(new MyTextWatcher(inputSirName));
        inputCity.addTextChangedListener(new MyTextWatcher(inputCity));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputBirthday.addTextChangedListener(new MyTextWatcher(inputBirthday));
        inputBirthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(isVibrate());
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });

        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(Registration.this);
            }

        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    private boolean isVibrate() {
        return false;
    }

    private boolean isCloseOnSingleTapDay() {
        return false;
    }




    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        Toast.makeText(Registration.this, "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
        inputBirthday.setText(year + "-" + month + "-" + day);
    }


    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }
        if (!validateSirName()) {
            return;
        }
        if (!validateCity()) {
            return;
        }
        if (!validateBirthday()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }

        RegistrationMap.put("firstName",inputName.getText().toString());
        RegistrationMap.put("lastName",inputSirName.getText().toString());
        RegistrationMap.put("ville",inputCity.getText().toString());
        RegistrationMap.put("dateNaissance",inputBirthday.getText().toString());
        RegistrationMap.put("email",inputEmail.getText().toString());

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();

        Intent i2 = new Intent(getApplicationContext(), Registration2.class);
        Bundle bundle = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.move_right_in, R.anim.move_right_out).toBundle();
        startActivity(i2, bundle);
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateSirName() {
        if (inputSirName.getText().toString().trim().isEmpty()) {
            inputLayoutSirName.setError(getString(R.string.err_msg_sirname));
            requestFocus(inputSirName);
            return false;
        } else {
            inputLayoutSirName.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateCity() {
        if (inputCity.getText().toString().trim().isEmpty()) {
            inputLayoutCity.setError(getString(R.string.err_msg_city));
            requestFocus(inputCity);
            return false;
        } else {
            inputLayoutCity.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateBirthday() {
        String birthday = inputBirthday.getText().toString().trim();

        if (birthday.isEmpty() || !isValidBirthday(birthday)) {
            inputLayoutBirthday.setError(getString(R.string.err_msg_birthday));
            requestFocus(inputBirthday);
            return false;
        } else {
            inputLayoutBirthday.setErrorEnabled(false);
        }

        return true;
    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private static boolean isValidBirthday(String birthday) {
        return true;
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
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_sirname:
                    validateSirName();
                    break;
                case R.id.input_city:
                    validateCity();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_birthday:
                    validateBirthday();
                    break;
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
