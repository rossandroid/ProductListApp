package rossellamorgante.productslistapp.netservice;

import java.util.List;

import rossellamorgante.productslistapp.model.Product;
import rossellamorgante.productslistapp.model.ProductsList;
public interface IService {

    void onError(String error);
    void onSuccess(List<Product> list);
}
