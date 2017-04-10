package nichat.com.dummynews;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Activity2 extends AppCompatActivity {

    Bundle bb,b1,b2,b3,b5;
    ImageView ig;
    Intent i,ii,i1,i2,i3,i5;
    TextView newsa;
    int key;
    Drawable myDrawable;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        ig=(ImageView)findViewById(R.id.img);
        newsa=(TextView)findViewById(R.id.newsa);
        ii=getIntent();
        bb=ii.getExtras();
        key=bb.getInt("news");
        switch(key)
        {
            case 1:
                myDrawable=getResources().getDrawable(R.drawable.ecnews);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.ecnews));
                break;
            case 2:
                myDrawable=getResources().getDrawable(R.drawable.movie2);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.movie2));
                break;
            case 3:
                myDrawable=getResources().getDrawable(R.drawable.movie3);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.movie3));
                break;
            case 4:
                myDrawable=getResources().getDrawable(R.drawable.sports2);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.sports2));

                break;
            case 5:
                myDrawable=getResources().getDrawable(R.drawable.sports3);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.sports3));

                break;
            case 6:
                myDrawable=getResources().getDrawable(R.drawable.tech2);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.tech2));

                break;
            case 7:
                myDrawable=getResources().getDrawable(R.drawable.news2);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.news2));

                break;
            case 8:
                myDrawable=getResources().getDrawable(R.drawable.tech1);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.tech1));

                break;
            default:
                break;

        }
        i1 = getIntent();
        b1=i1.getExtras();
        key=bb.getInt("sports");
        switch(key) {
            case 1:
                myDrawable = getResources().getDrawable(R.drawable.sports2);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.sports2));
                break;
            case 2:
                myDrawable = getResources().getDrawable(R.drawable.sports3);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.sports3));
                break;
        }

        i2 = getIntent();
        b2=i2.getExtras();
        key=b2.getInt("movie");
        switch(key) {
            case 1:
                myDrawable = getResources().getDrawable(R.drawable.movie2);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.movie2));
                break;
            case 2:
                myDrawable = getResources().getDrawable(R.drawable.movie3);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.movie3));
                break;
        }

        i3 = getIntent();
        b3=i3.getExtras();
        key=b3.getInt("tech");
        switch(key) {
            case 1:
                myDrawable = getResources().getDrawable(R.drawable.tech1);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.tech1));
                break;
            case 2:
                myDrawable = getResources().getDrawable(R.drawable.tech2);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.tech2));
                break;
        }

        i5 = getIntent();
        b5=i5.getExtras();
        key=b5.getInt("politics");
        switch(key) {
            case 1:
                myDrawable = getResources().getDrawable(R.drawable.ecnews);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.ecnews));
                break;
            case 2:
                myDrawable = getResources().getDrawable(R.drawable.news2);
                ig.setImageDrawable(myDrawable);
                newsa.setText(getResources().getString(R.string.news2));
                break;
        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar2, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

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
    public boolean showIntent(String option)
    {

        if(option.equalsIgnoreCase("ho"))
        {
            i=new Intent(getBaseContext(),MainActivity.class);
            Bundle b=new Bundle();
            b.putString("ctgry","Main");
            i.putExtras(b);
            startActivity(i);
            finish();
        }
        if(option.equalsIgnoreCase("se"))
        {
            i=new Intent(getBaseContext(),Activity3.class);
            startActivity(i);
            finish();
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
}
