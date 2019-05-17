package rossellamorgante.productslistapp.netservice;

import java.util.List;

import rossellamorgante.productslistapp.model.Product;

public interface IService {

    void onError(String error);
    void onSuccess(List<Product> list);
}
