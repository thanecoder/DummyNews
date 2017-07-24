package nichat.com.dummynews;

/**
 * Created by NICHAT on 07-05-2017.
 */
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements AboutUs.OnFragmentInteractionListener,OfflineData.OnFragmentInteractionListener{

    public static final String LOG_TAG = MainActivity.class.getName();
    protected ArrayList<NewsItem> news1=new ArrayList<NewsItem>();
    protected ArrayList<NewsItem> news2=new ArrayList<NewsItem>();
    //private SwipeRefreshLayout swiper;


/*    private static final String NEWS_REQUEST_URL="https://newsapi.org/v1/articles?source=the-huffington-post&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a";*/

    private static final String NEWS_REQUEST_URL[] = {
            "https://newsapi.org/v1/articles?source=the-hindu&sortBy=latest&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=the-washington-post&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=bbc-sport&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=associated-press&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=reuters&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=the-guardian-uk&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a",
            "https://newsapi.org/v1/articles?source=the-huffington-post&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a"
};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("whereIAm","I have just entered onCreate of MainActivity");
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        swiper=(SwipeRefreshLayout)findViewById(R.id.swiper);
//        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
//
//                // This method performs the actual data-refresh operation.
//                // The method calls setRefreshing(false) when it's finished.
//                myUpdateOperation();
//            }
//        });
//        // Configure the refreshing colors
//        swiper.setColorSchemeResources(android.R.color.holo_blue_bright,
//                                       android.R.color.holo_green_light,
//                                       android.R.color.holo_orange_light,
//                                       android.R.color.holo_red_light);






        //Adding Toolbar to Activity
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting ViewPager for each tabs
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setCurrentItem(0);

        int icons[]={
                R.drawable.ic_dashboard,
                R.drawable.ic_home,
                R.drawable.ic_world,
                R.drawable.ic_sports,
                R.drawable.ic_tech,
                R.drawable.ic_favorite
        };

        TabLayout tabs =(TabLayout)findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tabs.setTabTextColors((getResources().getColor(R.color.primaryTextDarkColor)),(getResources().getColor(R.color.primaryTextColor)));
        for(int i=0;i<6;i++)
        {
            tabs.getTabAt(i).setIcon(icons[i]);
        }

        /*for(int i=0;i<NEWS_REQUEST_URL.length;i++)
        {
            downloadNews(NEWS_REQUEST_URL[i]);
        }*/
        Log.i("whereIAm","I am now exiting onCreate of MainActivity");
    }


    private void setupViewPager(ViewPager viewPager) {
        Log.i("whereIAm","I am in setUpViewPager of MainActivity");
        Log.i("whatIDo","I am now creating adapter object");
        Adapter adapter=new Adapter(getSupportFragmentManager());
        adapter.notifyDataSetChanged();
        Log.i("whatIDid","I created adapter object");
        Log.i("whatIDo","I am now adding HomeFragment to adapter object");
        adapter.addFragment(new HomeFragment(),"Home");
        Log.i("whatIDid","I added HomeFragment to adapter object");
        Log.i("whatIDo","I am now adding NationalFragment to adapter object");
        adapter.addFragment(new NationalFragment(),"National");
        Log.i("whatIDid","I added NationalFragment to adapter object");
        Log.i("whatIDo","I am now adding WorldFragment to adapter object");
        adapter.addFragment(new WorldFragment(),"World");
        Log.i("whatIDid","I added WorldFragment to adapter object");
        Log.i("whatIDo","I am now adding SportsFragment to adapter object");
        adapter.addFragment(new SportsFragment(),"Sports");
        Log.i("whatIDid","I added SportsFragment to adapter object");
        Log.i("whatIDo","I am now adding TechFragment to adapter object");
        adapter.addFragment(new TechFragment(),"Tech");
        Log.i("whatIDid","I added TechFragment to adapter object");
        Log.i("whatIDo","I am now adding OfflineData Fragment to adapter object");
        adapter.addFragment(new OfflineData(),"Offline");
        Log.i("whatIDid","I added OfflineData Fragment to adapter object");
        //adapter.addFragment(new BookMarksFragment(),"Bookmarks");
        Log.i("whereIAm","I am now exiting setUpViewPager of MainActivity");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //left empty
        Log.i("whereIAm","I am in onFragmentInteraction of MainActivity");
    }

    public static class Adapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList=new ArrayList<>();
        private final List<String> mFragmentTitleList=new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title)
        {
            Log.i("whereIAm","I am in addFragment of MainActivity in class Adapter");
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }



        public Fragment getItem(int position)
        {
            Log.i("whereIAm","I am in getItem of MainActivity in class Adapter");
            return mFragmentList.get(position);
        }

        public int getCount()
        {
            Log.i("whereIAm","I am in getCount of MainActivity in class Adapter");
            return mFragmentList.size();
        }

        public CharSequence getPageTitle(int position)
        {
            Log.i("whereIAm","I am in getPageTitle of MainActivity in class Adpater");
            return mFragmentTitleList.get(position);
        }
    }


    /*//Method to update the ui part.START.
    public void updateUi(ArrayList<NewsItem> news)
    {
        ListView newsListView = (ListView) findViewById(R.id.list);
        try {
            newsListView.setAdapter(new myAdapter(this, news));
            final ArrayList<NewsItem> finalNews = news;
            newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    NewsItem currentNews = finalNews.get(position);
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getUrl()));
                    startActivity(i);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to update the ui part.END.

    public void downloadNews(String url)
    {
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<NewsItem> news=new ArrayList<>();

                try
                {
                    JSONArray featureArray = response.getJSONArray("articles");

                    // If there are results in the features array
                    if (featureArray.length() > 0)
                    {
                        for(int i=0;i<featureArray.length();i++)
                        {
                            JSONObject properties = featureArray.getJSONObject(i);
                            news.add(new NewsItem(
                                    properties.getString("author"),
                                    properties.getString("title"),
                                    properties.getString("description"),
                                    properties.getString("url"),
                                    properties.getString("urlToImage"),
                                    properties.getString("publishedAt")
                            ));
                        }

                    }
                    news2 = news;
                    for (NewsItem newsItem : news2)
                    {
                        news1.add(newsItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateUi(news1);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    protected void onResume() {
        super.onResume();
        for(int i=0;i<NEWS_REQUEST_URL.length;i++)
        {
            downloadNews(NEWS_REQUEST_URL[i]);
        }
    }*/



}
