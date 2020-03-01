package walashglobal.com;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Add_New extends AppCompatActivity {
    EditText date, amount;
    Database db;
    String ID = "";
    Calendar cal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener listener;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new);

        date = findViewById(R.id.date);
        amount = findViewById(R.id.amount);
        db = new Database(this);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            if (extra.getString("ID") != null) {
                ID = extra.getString("ID");
            }
        }

        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                int add = i1 + 1;
                String text, i2_;
                if (add < 10) {
                    text = "0" + add;
                } else {
                    text = Integer.toString(add);
                }

                if (i2 < 10) {
                    i2_ = "0" + i2;
                } else {
                    i2_ = Integer.toString(i2);
                }
                String new_text = i2_ + "/" + text + "/" + i;
                date.setText(new_text);
                date.setError(null);
            }
        };

        date.setFocusableInTouchMode(true);
        date.setOnTouchListener(new EditText.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                    new DatePickerDialog(Add_New.this, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();

                }
                return true;
            }
        });
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    new DatePickerDialog(Add_New.this, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
                    date.clearFocus();
                }
            }
        });

    }

    public void add(View v) {
        if (date.getText().toString().trim().equals("") && amount.getText().toString().trim().equals("")) {
            date.setError("");
            amount.setError("Please enter amount here");
            amount.requestFocus();
        } else {
            if (date.getText().toString().trim().equals("")) {
                date.setError("");
            } else if (amount.getText().toString().trim().equals("")) {
                amount.setError("Please enter amount here");
                amount.requestFocus();
            } else {
                Long i = db.insert_into_data_info(ID, date.getText().toString().trim(), amount.getText().toString().trim());
                if (i != -1) {
                    Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                    View view = this.getCurrentFocus();
                    if (view != null) {

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    finish();

                } else {
                    Toast.makeText(this, "Data Addition Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void back(View v) {
        View view = this.getCurrentFocus();
        if (view != null) {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        View view = this.getCurrentFocus();
        if (view != null) {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        finish();
    }
}
