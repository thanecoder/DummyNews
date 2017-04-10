package nichat.com.dummynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
    Intent i,ii,i1,i2,i3,i5;
    Bundle b=new Bundle();
    String ctry="Main";
    Bundle bb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().getBooleanExtra("exit",false)){
            finish();}
        try {
            ii=getIntent();
            bb=ii.getExtras();
            ctry=bb.getString("ctgry");
            switch(ctry)
            {
                case "Sports":
                    i1 = new Intent(MainActivity.this, Activity4.class);
                    startActivity(i1);
                    finish();
                    break;
                case "Entertainment":
                    i2 = new Intent(MainActivity.this, Activity5.class);
                    startActivity(i2);
                    finish();
                    break;
                case "Technology":
                    i3 = new Intent(MainActivity.this, Activity6.class);
                    startActivity(i3);
                    finish();
                    break;
                case "Politics":
                    i5 = new Intent(MainActivity.this, Activity7.class);
                    startActivity(i5);
                    finish();
                    break;
                case "Main":
                    i1= new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i1);
                    break;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);

}
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv1:
                i = new Intent(MainActivity.this, Activity2.class);
                b.putInt("news",1);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.tv2:
                i = new Intent(MainActivity.this,Activity2.class);
                b.putInt("news",2);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.tv3:
                i = new Intent(MainActivity.this,Activity2.class);
                b.putInt("news",3);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.tv4:
                i = new Intent(MainActivity.this,Activity2.class);
                b.putInt("news",4);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.tv5:
                i = new Intent(MainActivity.this,Activity2.class);
                b.putInt("news",5);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.tv6:
                i = new Intent(MainActivity.this,Activity2.class);
                b.putInt("news",6);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.tv7:
                i = new Intent(MainActivity.this,Activity2.class);
                b.putInt("news",7);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.tv8:
                i = new Intent(MainActivity.this,Activity2.class);
                b.putInt("news",8);
                i.putExtras(b);
                startActivity(i);
                break;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.sp:
                return showIntent("sp");
            case R.id.en:
                return showIntent("en");
            case R.id.tc:
                return showIntent("tc");
            case R.id.po:
                return showIntent("po");
            case R.id.se:
                return showIntent("se");
            case R.id.ex:
                return showIntent("ex");
            default:
                return true;
        }
    }
    public boolean showIntent(String option)
    {
        if(option.equalsIgnoreCase("sp"))
        {
            i=new Intent(MainActivity.this,Activity4.class);
            startActivity(i);

        }
        if(option.equalsIgnoreCase("en"))
        {
            i=new Intent(MainActivity.this,Activity5.class);
            startActivity(i);

        }
        if(option.equalsIgnoreCase("tc"))
        {
            i=new Intent(MainActivity.this,Activity6.class);
            startActivity(i);

        }
        if(option.equalsIgnoreCase("po"))
        {
            i=new Intent(MainActivity.this,Activity7.class);
            startActivity(i);

        }
        if(option.equalsIgnoreCase("se"))
        {
            i=new Intent(MainActivity.this,Activity3.class);
            startActivity(i);

        }
        if(option.equalsIgnoreCase("ex"))
        {
            Intent i4=new Intent(getBaseContext(),MainActivity.class);
            i4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i4.putExtra("exit", true);
            startActivity(i4);
            finish();
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
