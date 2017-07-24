package nichat.com.dummynews;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static nichat.com.dummynews.R.layout.item_card;


public class NationalFragment extends Fragment
{


    private static final String NEWS_REQUEST_URL="https://newsapi.org/v1/articles?source=the-hindu&sortBy=latest&apiKey=c4c45240182f4d42bfae496c15f40b5a";

    static Typeface typeface;

    ArrayList<NewsItem> news3=new ArrayList<>();
    protected ArrayList<NewsItem> news1=new ArrayList<>();
    protected ArrayList<NewsItem> news2=new ArrayList<>();
    SwipeRefreshLayout mSwipe;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ContentAdapter newsAdapter;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        news3=downloadNews(NEWS_REQUEST_URL);
        Log.i("whereIAm","I am in onCreate of National Fragment");
        DBAdapter db=new DBAdapter(getContext());
        db.open();
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"Roboto-Light.ttf");
        Log.v("Volley","SIZE of news:"+news3.size());
    }


    /*private static final String NEWS_REQUEST_URL[] = {
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
    };*/

    //This onCreateView method here is without Swipe Refresh Layout.Remember to comment the Swipe Refresh layout in XML file also,when you do not want the Swipe Refresh Layout.
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
//    {
//        Log.i("whereIAm","I am in onCreateView of NationalFragment");
//        Log.i("whatIDo","I am now creating an object of RecyclerView in National Fragment");
//        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
//        Log.i("whatIDid","I created an object of RecyclerView in NationalFragment");
//        /*SwipeRefreshLayout mSwipe = (SwipeRefreshLayout) recyclerView.findViewById(R.id.swipeRefreshLayout);
//        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                news3=downloadNews(NEWS_REQUEST_URL);
//            }
//        });
//        mSwipe.setRefreshing(false);*/
//        try
//        {
//            Log.i("whatIDo","I am now setting adapter for recyclerview in NationalFragment");
//            ContentAdapter newsAdapter=new ContentAdapter(recyclerView.getContext(),news3);
//            recyclerView.setAdapter(newsAdapter);
//            newsAdapter.notifyDataSetChanged();
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        }
//        catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
//        return recyclerView;
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        Log.i("whereIAm","I am in onCreateView of National Fragment");
        Log.i("whatIDo","I am now creating an object of RecyclerView in National Fragment");
        //recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        View v=inflater.inflate(R.layout.recycler_view,container,false);
        recyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);
        Log.i("whatIDid","I created an object of RecyclerView in National Fragment");
        mSwipe=(SwipeRefreshLayout)v.findViewById(R.id.mSwipe);
        //mSwipe.setColorSchemeResources(R.color.colorPrimary);
        try
        {
            Log.i("whatIDo","I am now setting adapter for recyclerview in National Fragment");
            layoutManager=new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            newsAdapter=new ContentAdapter(recyclerView.getContext(),news3);
            recyclerView.setAdapter(newsAdapter);
            newsAdapter.notifyDataSetChanged();
            recyclerView.setHasFixedSize(true);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipe.setRefreshing(false);
                downloadNews(NEWS_REQUEST_URL);
                newsAdapter.notifyDataSetChanged();
            }
        });

        //Adding an scroll change listener to recyclerview
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                if (isLastItemDisplaying(recyclerView)) {
//                    //Calling the method getdata again
//                    getData();
//                    progressBar.setVisibility(View.GONE);
//                }
            }
        });
        return v;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView picture;
        public TextView title;
        public TextView description;
        public ImageButton favorite_button;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(item_card, parent, false));
            Log.i("whereIAm","I am in constructor of ViewHolder of National Fragment");
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            title = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_desc);
            favorite_button=(ImageButton)itemView.findViewById(R.id.favorite_button);
            title.setTypeface(typeface);
            description.setTypeface(typeface);
            Log.i("whereIAm","I am exiting constructor of ViewHolder of National Fragment");
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder>
    {
        // Set numbers of List in RecyclerView.
        Context myContext;
        ArrayList<NewsItem> news;
        DBAdapter db=new DBAdapter(getContext());
        /*private static final String NEWS_REQUEST_URL="https://newsapi.org/v1/articles?source=the-hindu&sortBy=latest&apiKey=c4c45240182f4d42bfae496c15f40b5a";
        ArrayList<NewsItem> news=downloadNews(NEWS_REQUEST_URL);*/


        public ContentAdapter (Context context,ArrayList<NewsItem> news) throws JSONException {
            Log.i("whereIAm","I am in constrctor of ContentAdapter of NationalFragment in class ContentAdapter");
            myContext=context;
            this.news=news;
            Log.i("whereIAm","I am in exiting constructor of ContentAdapter of NationalFragment in class ContentAdapter");
        }


        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i("whereIAm","I am in onCreateViewHolder of NationalFragment in class ContentAdapter");
            ViewHolder vHolder=new ViewHolder(LayoutInflater.from(parent.getContext()),parent);
            Log.i("whereIAm","I am exiting onCreateViewHolder of NationalFragment in class ContentAdapter");
            return vHolder;
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            final NewsItem currentNews=news.get(position);
            Log.i("whereIAm","I am in onBindViewHolder of NationalFragment in class ContentAdapter");
            Glide.with(myContext).load(currentNews.getImage()).into(holder.picture);
            holder.title.setText(currentNews.getTitle());
            holder.description.setText(currentNews.getDescription());
            holder.title.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getUrl()));
                    startActivity(i);
                }
            });
            holder.favorite_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.open();
                    db.insertNews(currentNews.getAuthor(),
                            currentNews.getTitle(),
                            currentNews.getDescription(),
                            currentNews.getUrl(),
                            currentNews.getImage(),
                            currentNews.getPublishedAt()
                    );
                    Toast.makeText(getContext(),"Added to favorites", Toast.LENGTH_SHORT).show();
                }
            });
            Log.i("whereIAm","I am exiting onBindViewHolder of NationalFragment in class ContentAdapter");
        }


        public Object getItem(int position) {
            return news.get(position);
        }

        public int getViewTypeCount() { return getCount(); }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() { return news.size(); }

        public int getCount() {
            return news.size();
        }

        // Clean all elements of the recycler
//        public void clear() {
//            news.clear();
//            notifyDataSetChanged();
//        }
//
//        // Add a list of items -- change to type used
//        public void addAll(ArrayList<NewsItem> list) {
//            news.addAll(list);
//            notifyDataSetChanged();
//        }

    }

    //Method that contains volley code which makes the web request.It takes a String url as an input and outputs a ArrayList<NewsItem> which we pass on to the ContentAdapter.
    public ArrayList<NewsItem> downloadNews(String url)
    {
        Log.i("whereIAm","I am in downloadNews of NationalFragment");
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 2048 * 2048); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue= new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();
        Log.i("whatIDo","I am creating a JSON objectrequest of NationalFragment");
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            ArrayList<NewsItem> news=new ArrayList<>();
            public void onResponse(JSONObject response) {
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

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Volley","Error");
                //Toast.makeText(getContext(),"NATIONAL FRAGMENT."+error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"Please check internet connection for National news", Toast.LENGTH_LONG).show();
            }
        });
        Log.i("whatIDo","I am adding jsonObjectRequest to requestQueue of NationalFragment");
        mRequestQueue.add(jsonObjectRequest).shouldCache();
        Log.i("whatIDid","I added jsonObjectRequest to requestQueue of NationalFragment");
        Log.i("whereIAm","I am exiting a JSON objectrequest of NationalFragment");
        return news1;
    }
}
