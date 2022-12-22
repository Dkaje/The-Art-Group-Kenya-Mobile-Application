package com.example.artgroup.vide;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HttpService {
    @Multipart
    @POST("artgroup/mile/upload_api.php")
    Call<FileModel>callUploadApi(@Part MultipartBody.Part image);

    @Multipart
    @POST("artgroup/mile/multi_upload.php")
    Call<FileModel>callFileModelCall(@Part List<MultipartBody.Part> image);

}
