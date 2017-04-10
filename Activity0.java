package nichat.com.dummynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Activity0 extends AppCompatActivity {

    String temp="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_0);
        Thread background =  new Thread() {// Create Thread that will sleep for 5 seconds
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2*1000);


                    File file=getBaseContext().getFileStreamPath("mydata.txt");
                    if(file.exists())
                    {
                        try
                        {
                            FileInputStream in =new FileInputStream(file);
                            InputStreamReader isr=new InputStreamReader(in);
                            char[] inputBuffer=new char[100];
                            int charRead;
                            while ((charRead = isr.read(inputBuffer))>0) {
                                String readString=String.copyValueOf(inputBuffer,0,charRead);
                                temp+= readString;
                                inputBuffer=new char[100];
                            }
                            if(temp.equals(""))
                            {
                                Intent i = new Intent(getBaseContext(),MainActivity.class);
                                Bundle b=new Bundle();
                                b.putString("ctgry","Main");
                                i.putExtras(b);
                                startActivity(i);
                            }
                            if(temp.equals("Sports"))
                            {
                                Intent i = new Intent(getBaseContext(),Activity4.class);
                                startActivity(i);
                            }
                            if(temp.equals("Entertainment"))
                            {
                                Intent i = new Intent(getBaseContext(),Activity5.class);
                                startActivity(i);
                            }
                            if(temp.equals("Technology"))
                            {
                                Intent i = new Intent(getBaseContext(),Activity6.class);
                                startActivity(i);
                            }
                            if(temp.equals("Politics"))
                            {
                                Intent i = new Intent(getBaseContext(),Activity7.class);
                                startActivity(i);
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Intent i = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(i);
                    }

                    //Remove activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // start thread
        background.start();
    }
}
