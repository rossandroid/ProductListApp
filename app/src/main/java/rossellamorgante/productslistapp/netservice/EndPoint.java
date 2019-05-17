package rossellamorgante.productslistapp.netservice;

import java.util.List;

import retrofit2.http.GET;
import rossellamorgante.productslistapp.model.ProductsList;
import rx.Observable;

public interface EndPoint {

    @GET("/media/catalog/example.json")
    Observable<ProductsList> getProductsRetrofit();

}