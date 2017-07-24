package nichat.com.dummynews;

/**
 * Created by NICHAT on 07-05-2017.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static nichat.com.dummynews.myAdapter.LOG_TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name com.example.android.quakereport.QueryUtils (and an object instance of com.example.android.quakereport.QueryUtils is not needed).
     */
    private QueryUtils() {

    }

    /**
     * Return a list of {@link NewsItem} objects that has been built up from
     * parsing a JSON response.
     */

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if(url==null)
        {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            //If the request was successful(response code = 200)
            //then read the input stream and parse response
            if(urlConnection.getResponseCode()==200)
            {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<NewsItem> extractFeatureFromJson(String newsJSON) {

        ArrayList<NewsItem> news=new ArrayList<>();
        //If the JSON string is empty or null,then return early
        if(TextUtils.isEmpty(newsJSON))
        {
            return null;
        }

        try
        {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONArray featureArray = baseJsonResponse.getJSONArray("articles");

            // If there are results in the features array
            if (featureArray.length() > 0) {
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
        }
        catch (JSONException e)
        {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return news;
    }
}