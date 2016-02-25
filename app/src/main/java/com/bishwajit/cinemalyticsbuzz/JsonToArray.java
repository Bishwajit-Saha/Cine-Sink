package com.bishwajit.cinemalyticsbuzz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bishwajit on 12/17/2015.
 */
public class JsonToArray {

    ArrayList<MovieModel> list;
    String s;
    public JsonToArray(String data)
    {
        list = new ArrayList<MovieModel>();
      if(data == null || data.length() ==0)
          list = null;
      else
      {
          MovieModel m;
          try {
              JSONArray ja = new JSONArray(data);
              for(int i=0 ; i<ja.length(); i++)
              {
                  JSONObject jo = ja.getJSONObject(i);
                  m  = new MovieModel();
                  s=jo.getString("Id");
                  m.setId(s);

                  s=jo.getString("OriginalTitle");
                  m.setOriginalTitle(s);

                  s=jo.getString("Description");
                  m.setDescription(s);

                  s=jo.getString("Genre");
                  m.setGenre(s);

                  s=jo.getString("Rating");
                  m.setRating(s);

                  s=jo.getString("ReleaseDate");
                  m.setReleaseDate(s);

                  s=jo.getString("Runtime");
                  m.setRuntime(s);

                  s=jo.getString("PosterPath");
                  m.setPosterPath(s);

                  s=jo.getString("TrailerLink");
                  m.setTrailerLink(s);

                  list.add(m);
              }
          } catch (JSONException e) {
              e.printStackTrace();
          }
      }

    }

    public ArrayList<MovieModel> getArray()
    {
        return list;
    }
}


/*
setId
setOriginalTitle
setDescription
setGenre
setRating
setReleaseDate
setRuntime
setPosterPath
setTrailerLink


 */
