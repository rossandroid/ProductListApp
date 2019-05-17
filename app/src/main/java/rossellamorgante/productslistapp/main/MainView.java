package rossellamorgante.productslistapp.main;


import java.util.List;

import rossellamorgante.productslistapp.model.Product;

public interface MainView {

    void showSpinner();
    void hideSpinner();
    void hideSpinnerWithError(String Error);
    void updateAdapter(List<Product> list);

}
