package iwh.empowher;

import iwh.empowher.models.ServerRequest;
import iwh.empowher.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("db_files/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}
