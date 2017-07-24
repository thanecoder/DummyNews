package nichat.com.dummynews;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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


public class HomeFragment extends Fragment
{

    private static final String NEWS_REQUEST_URL="https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=c4c45240182f4d42bfae496c15f40b5a";

    static Typeface typeface;

    ArrayList<NewsItem> news3=new ArrayList<>();
    protected ArrayList<NewsItem> news1=new ArrayList<>();
    protected ArrayList<NewsItem> news2=new ArrayList<>();


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        news3=downloadNews(NEWS_REQUEST_URL);
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        /*SwipeRefreshLayout mSwipe = (SwipeRefreshLayout)recyclerView.findViewById(R.id.swipeRefreshLayout);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                news3=downloadNews(NEWS_REQUEST_URL);
            }
        });*/
        try
        {
            recyclerView.setAdapter(new ContentAdapter(recyclerView.getContext(),news3));
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView picture;
        public TextView title;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(item_card, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            title = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_desc);
            title.setTypeface(typeface);
            description.setTypeface(typeface);
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

        /*private static final String NEWS_REQUEST_URL="https://newsapi.org/v1/articles?source=the-hindu&sortBy=latest&apiKey=c4c45240182f4d42bfae496c15f40b5a";
        ArrayList<NewsItem> news=downloadNews(NEWS_REQUEST_URL);*/


        public ContentAdapter (Context context,ArrayList<NewsItem> news) throws JSONException {
            myContext=context;
            this.news=news;
        }


        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder vHolder=new ViewHolder(LayoutInflater.from(parent.getContext()),parent);
            return vHolder;
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            final NewsItem currentNews=news.get(position);
            Glide.with(myContext).load(currentNews.getImage()).into(holder.picture);
            holder.title.setText(currentNews.getTitle());
            holder.description.setText(currentNews.getDescription());
            holder.title.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getUrl()));
                    startActivity(i);
                }
            });
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

    }

    //Method that contains volley code which makes the web request.It takes a String url as an input and outputs a ArrayList<NewsItem> which we pass on to the ContentAdapter.
    public ArrayList<NewsItem> downloadNews(String url)
    {
        // Instantiate the cache
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 2048 * 2048); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue= new RequestQueue(cache, network);

        // Start the queue
        mRequestQueue.start();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

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
                Toast.makeText(getContext(),"HOME FRAGMENT."+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
        return news1;
    }
}
