package com.drogbalog.server.global.retrofit;

import com.drogbalog.server.global.exception.DrogbalogException;
import com.drogbalog.server.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class RetrofitClient<T> {
    private static final Logger logger = LogManager.getLogger(RetrofitClient.class);

    public T execute(Call<ResponseEntity<T>> call) {
        try {
            Response<ResponseEntity<T>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body().getBody();
            }

            ErrorResponse errorResponse = parseErrorBody(response.errorBody());
            HttpStatus status = HttpStatus.valueOf(errorResponse.getCode());

            throw new DrogbalogException(status, errorResponse.getCode(), errorResponse.getMessage());

        } catch (IOException e) {
            throw new DrogbalogException(e.getMessage());
        }
    }

    private ErrorResponse parseErrorBody(ResponseBody errorBody) {
        try {
            return new ObjectMapper().readValue(errorBody.bytes(), ErrorResponse.class);
        } catch (IOException e) {
            throw new DrogbalogException(e.getMessage());
        }
    }
}
