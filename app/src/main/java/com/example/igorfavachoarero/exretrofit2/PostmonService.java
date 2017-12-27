package com.example.igorfavachoarero.exretrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by igorfavachoarero on 27/12/17.
 */

public interface PostmonService {

    @GET("{cep}")
    Call<Endereco>getEndereco(@Path("cep") String cep);
}
