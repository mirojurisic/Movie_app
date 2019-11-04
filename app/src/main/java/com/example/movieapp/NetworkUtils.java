package com.example.movieapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String BASE_URL ="https://api.themoviedb.org";
    private static final String API_key = "";
    private static final String API = "api_key";
    private static final String MOST_POPULAR = "/3/movie/popular";
    private static final String TOP_RATED = "/3/movie/top_rated";
    private static boolean top_rated = true;
    private static String JSON_BASE_ARRAY = "results";


    private static String getResponseFromHttpUrl() throws IOException {
        URL url = buildUrl();

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    private static URL buildUrl() {
        Uri builtUri ;
        if( top_rated)
         builtUri = Uri.parse(BASE_URL).buildUpon().path(TOP_RATED)
                .appendQueryParameter(API, API_key)
                .build();
        else
            builtUri = Uri.parse(BASE_URL).buildUpon().path(MOST_POPULAR)
                    .appendQueryParameter(API, API_key)
                    .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }
    public static List<Movie> getMovies(boolean top_rated)
    {
        NetworkUtils.top_rated =top_rated;

        return getMoviesFromJson();
    }
    private static List<Movie> getMoviesFromJson(){
        List<Movie> movieArrayList = new ArrayList<>();
        try {
            JSONObject base = new JSONObject(getResponseFromHttpUrl());
            JSONArray listArray = base.getJSONArray(JSON_BASE_ARRAY);
            if (listArray.length()>0)
            {
                for (int i=0;i<listArray.length();i++)
                {
                    JSONObject singleMovie = listArray.getJSONObject(i);
                    //    public Movie(String title, String imageUrl, String plot, double rating, String releaseDate) {
                    movieArrayList.add(new Movie(singleMovie.getString("original_title"),
                            singleMovie.getString("poster_path"),
                            singleMovie.getString("overview"),
                            singleMovie.getDouble("vote_average"),
                            singleMovie.getString("release_date")));

                }
            }

        }
        catch (IOException e)
        {
            Log.v(TAG,"cannot get JSON object");
        }
        catch (JSONException j)
        {
            Log.v(TAG,"cannot get JSON object");
        }
        return movieArrayList;
    }
}

