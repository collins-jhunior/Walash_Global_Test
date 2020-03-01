package walashglobal.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Database db;
    EditText editText;
    String[] dummy_ids = {"22136", "88215", "93401", "74820"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(this);
        for (String dummy_id : dummy_ids) {
            Cursor check = db.id_check(dummy_id);
            if (check.getCount() == 0) {
                db.insert_id(dummy_id);
            }
        }

        editText = findViewById(R.id.editText);
    }

    public void next(View v) {
        if (editText.getText().toString().trim().equals("")) {
            editText.setError("Please enter your id here");
            editText.requestFocus();
        } else {
            Cursor check = db.id_check(editText.getText().toString().trim());
            if (check.getCount() == 0) {
                editText.setError("Unknown ID");
                editText.requestFocus();
                editText.setSelection(editText.getText().length());
            } else {
                Intent i = new Intent(this, contributions.class);
                i.putExtra("ID", editText.getText().toString().trim());
                startActivity(i);
                Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void back(View view) {
    }

    public void add(View view) {
    }
}
