package com.drogbalog.server.infra.retrofit;

import com.drogbalog.server.global.exception.DrogbalogException;
import lombok.extern.log4j.Log4j2;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Log4j2
public abstract class RetrofitClient<T , E> {

    public T execute(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }

            E errorResponse = parseErrorBody(response);
            throwException(errorResponse);
            return null;
        } catch (IOException e) {
            throw new DrogbalogException(e.getMessage());
        }
    }

    abstract E parseErrorBody(Response<T> response);
    abstract void throwException(E e);
}
