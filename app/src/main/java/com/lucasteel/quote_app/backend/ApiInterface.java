package com.lucasteel.quote_app.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("random?maxLength=170")
    Call<ResponseBody> getData();

}
