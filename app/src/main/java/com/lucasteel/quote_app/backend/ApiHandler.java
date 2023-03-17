package com.lucasteel.quote_app.backend;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import com.lucasteel.quote_app.MainActivityKt;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiHandler {


    public void updateQuote()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.quotable.io/").build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.getData();
        JSONConverter jsonfromatter = new JSONConverter();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 try {
                     MainActivityKt.getMainViewModel().setQuoteInfo(jsonfromatter.formatJSON(response.body().string(), "quote").get(0));
                     MainActivityKt.getMainViewModel().setAuthorInfo(jsonfromatter.result.get(1));
               } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
    }

    public void updateColor()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.colr.org/json/color/").build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.getData();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    MainActivityKt.getMainViewModel().setColorInfo(new JSONConverter().formatJSON(response.body().string(), "color").get(0));

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
    }
}

