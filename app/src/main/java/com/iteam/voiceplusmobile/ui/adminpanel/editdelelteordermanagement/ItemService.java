package com.iteam.voiceplusmobile.ui.adminpanel.editdelelteordermanagement;

import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface ItemService {

    @DELETE("{id}")
    Call<ResponseBody> deleteItem(@Path("id") int id);
}
