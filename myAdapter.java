package nichat.com.dummynews;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by NICHAT on 07-05-2017.
 */

public class myAdapter extends BaseAdapter {

    Context myContext;

    ArrayList<NewsItem> news=new ArrayList<NewsItem>();


    public static final String LOG_TAG=myAdapter.class.getSimpleName();

    public myAdapter (Context context,ArrayList<NewsItem> list) throws JSONException {
        myContext=context;
        news=list;
    }


    public int getCount() {
        return news.size();

    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    public int getViewTypeCount() {

        return getCount();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    //getView() method starts
    public View getView(int i, View grid, ViewGroup parent) {

        ViewHolder viewHolder;

        LayoutInflater inflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        NewsItem currentNews= news.get(i);
        if (grid == null)
        {
            grid = new View(myContext);
            grid = inflater.inflate(R.layout.list_single, null);

            //Binding java textviews with xml textviews
            ImageView image=(ImageView)grid.findViewById(R.id.list_image);
            //TextView author = (TextView)grid.findViewById(R.id.list_author);
            TextView title = (TextView)grid.findViewById(R.id.list_title);
            //TextView description = (TextView)grid.findViewById(R.id.list_description);
            TextView time = (TextView)grid.findViewById(R.id.list_time);
            TextView date = (TextView)grid.findViewById(R.id.list_date);


            viewHolder=new ViewHolder();
            viewHolder.vh_image=image;
            //viewHolder.vh_author=author;
            viewHolder.vh_title=title;
            //viewHolder.vh_description=description;
            viewHolder.vh_time=time;
            viewHolder.vh_date=date;



            grid.setTag(viewHolder);

        }
        else
        {
            viewHolder=(ViewHolder)grid.getTag();
        }

        //Code for setting image
        Glide.with(myContext).load(currentNews.getImage()).into(viewHolder.vh_image);



        viewHolder.vh_title.setText(currentNews.getTitle());
        /*if(!currentNews.getAuthor().equals("null"))
        {
            viewHolder.vh_author.setText(currentNews.getAuthor());
        }*/


        //Date formatting code starts
        /*String dateTime=currentNews.getPublishedAt();
        String edate = "";
        String etime = "";
        int length=dateTime.length();
        if(dateTime.contains("T")) {
            int of = dateTime.indexOf("T");
            edate = dateTime.substring(0, of);
            etime = dateTime.substring(of + 2, length);
        }

        edate+=formatDate(edate);
        etime+=formatTime(etime);
        viewHolder.vh_time.setText(etime);
        viewHolder.vh_date.setText(edate);*/
        //Date formatting code ends

        return grid;


    }
    //getView() method ends


    //Method to format time
    public String formatTime(Date d)
    {
        SimpleDateFormat timeFormat=new SimpleDateFormat("h:mm a");
        return timeFormat.format(d);
    }

    //Method to format date
    public String formatDate(Date d)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM,DD,yyyy", Locale.UK);
        return dateFormat.format(d);
    }

    //Created a view holder class.This ViewHolder class holds the textviews.
    public static class ViewHolder{
        ImageView vh_image;
        TextView vh_author;
        TextView vh_title;
        TextView vh_description;
        TextView vh_time;
        TextView vh_date;
    }
}
