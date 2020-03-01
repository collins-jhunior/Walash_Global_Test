package walashglobal.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

public class contributions extends AppCompatActivity {
    String ID = "";
    RecyclerView rv;
    Database db;
    ArrayList<String> ids, amounts, dates;
    DividerItemDecoration dividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributions);

        dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            if (extra.getString("ID") != null) {
                ID = extra.getString("ID");
            }
        }
        db = new Database(this);

        ids = new ArrayList<>();
        dates = new ArrayList<>();
        amounts = new ArrayList<>();

        Cursor cc = db.data_info_check();
        if (cc.getCount() > 0) {
            cc.moveToLast();
            do {
                ids.add(cc.getString(0));
                dates.add(cc.getString(1));
                amounts.add(cc.getString(2));
            }
            while (cc.moveToPrevious());
        }
        cc.close();

        rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new contributions_adapter(this, ids, amounts, dates));
        rv.removeItemDecoration(dividerItemDecoration);
        rv.addItemDecoration(dividerItemDecoration);
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

    public void add(View v) {
        Intent i = new Intent(this, Add_New.class);
        i.putExtra("ID", ID);
        startActivity(i);
    }
}
