package com.drogbalog.server.infra.retrofit;

import com.drogbalog.server.global.exception.DrogbalogException;
import com.drogbalog.server.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import okhttp3.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Log4j2
public class RetrofitClient<T> {

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
