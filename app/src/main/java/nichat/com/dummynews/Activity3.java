package nichat.com.dummynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Activity3 extends AppCompatActivity {

        RadioButton rb1,rb2,rb3,rb4;
        RadioGroup rg;
        Button b1;
        String category;
        int id;
        Intent i;
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        rg=(RadioGroup)findViewById(R.id.rg);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);
        rb3=(RadioButton)findViewById(R.id.rb3);
        rb4=(RadioButton)findViewById(R.id.rb4);
        b1=(Button)findViewById(R.id.b1);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try
                    {
                        FileOutputStream fout = openFileOutput("mydata.txt",MODE_PRIVATE);//Write to the file what category the user has selelcted
                        OutputStreamWriter osw=new OutputStreamWriter(fout);
                        id=rg.getCheckedRadioButtonId();
                        if(id==R.id.rb1)
                        {
                            category="Sports";
                        }
                        if(id==R.id.rb2)
                        {
                            category="Entertainment";
                        }
                        if(id==R.id.rb3)
                        {
                            category="Technology";
                        }
                        if(id==R.id.rb4)
                        {
                            category="Politics";
                        }
                        osw.write(category);
                        osw.flush();
                        osw.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    i=new Intent(Activity3.this,MainActivity.class);
                    Bundle b=new Bundle();
                    b.putString("ctgry",category);
                    i.putExtras(b);
                    startActivity(i);
                    finish();
                }
            });

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
