package nichat.com.dummynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;


public class Activity4 extends AppCompatActivity implements View.OnClickListener {

    TableRow tr1, tr2;
    Intent i;
    Bundle b = new Bundle();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        tr1 = (TableRow) findViewById(R.id.tr1);
        tr2 = (TableRow) findViewById(R.id.tr2);
        tr1.setOnClickListener(this);
        tr2.setOnClickListener(this);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ho:
                return showIntent("ho");
            case R.id.se:
                return showIntent("se");
            case R.id.ex:
                return showIntent("ex");
            default:
                return true;
        }
    }

    public boolean showIntent(String option) {

        if (option.equalsIgnoreCase("ho")) {
            i = new Intent(getBaseContext(), MainActivity.class);
            Bundle b=new Bundle();
            b.putString("ctgry","Main");
            i.putExtras(b);
            startActivity(i);
            finish();
        }
        if (option.equalsIgnoreCase("se")) {
            i = new Intent(getBaseContext(), Activity3.class);
            startActivity(i);
            finish();
        }
        if (option.equalsIgnoreCase("ex")) {
            Intent i4 = new Intent(getBaseContext(), MainActivity.class);
            i4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i4.putExtra("exit", true);
            startActivity(i4);
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tr1:
                i = new Intent(getBaseContext(), Activity2.class);
                b.putInt("sports", 1);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.tr2:
                i = new Intent(getBaseContext(), Activity2.class);
                b.putInt("sports", 2);
                i.putExtras(b);
                startActivity(i);
                break;
        }
    }
}
