package com.bishwajit.cinemalyticsbuzz;

/**
 * Created by bishwajit on 12/17/2015.
 */
public class UrlBuilder {

    String url = "http://api.cinemalytics.com/v1/movie/title/?value=";
    private final String auth_token = "&auth_token=6843156245E0AD2B4A7F64D6EFDD1D5C";
    private final String space = "%20";
    StringBuilder sb;

    public UrlBuilder(String search)
    {
        char c;
        sb = new StringBuilder();
        if((search.compareTo(""))!=0)
        {
            sb.append(url);

            for (int i = 0; i < search.length(); i++) {
                c = search.charAt(i);
                if (c == ' ')
                    sb.append(space);

                else
                    sb.append(c);
            }
            sb.append(auth_token);
        }
    }

    public String getUrl()
    {
        return sb.toString();
    }

}
