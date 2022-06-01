package com.haerul.interventions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface ApiInterface {

    @POST("get_interventions.php")
    Call<List<Interventions>> getInterventions();

    @FormUrlEncoded
    @POST("add_intervention.php")
    Call<Interventions> insertIntervention(
            @Field("key") String key,
            @Field("request_info") String request_info,
            @Field("request_id") String request_id,
            @Field("request_desc") String request_desc,
            @Field("requester_name") String requester_name,
            @Field("tech") int tech,
            @Field("assign_date") String assign_date,
            @Field("requester_city") String requester_city,
            @Field("requester_add1") String requester_add1,
            @Field("requester_zip") String requester_zip,
            @Field("requester_email") String requester_email,
            @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("update_intervention.php")
    Call<Interventions> updateIntervention(
            @Field("key") String key,
            @Field("id") int id,

            @Field("request_info") String request_info,
            @Field("request_id") String request_id,
            @Field("request_desc") String request_desc,
            @Field("requester_name") String requester_name,
            @Field("tech") int tech,
            @Field("assign_date") String assign_date,
            @Field("requester_city") String requester_city,
            @Field("requester_add1") String requester_add1,
            @Field("requester_zip") String requester_zip,
            @Field("requester_email") String requester_email,
            @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("delete_intervention.php")
    Call<Interventions> deleteIntervention(
            @Field("key") String key,
            @Field("id") int id);

    @FormUrlEncoded
    @POST("update_status.php")
    Call<Interventions> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);

}
