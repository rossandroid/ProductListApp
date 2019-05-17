package rossellamorgante.productslistapp.main;

import java.util.List;

import rossellamorgante.productslistapp.model.Product;
import rossellamorgante.productslistapp.model.ProductsList;
import rossellamorgante.productslistapp.netservice.IService;
import rossellamorgante.productslistapp.netservice.ServiceNet;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainPresenter {

    private MainView mMainView;
    private ServiceNet serviceNet;
    private CompositeSubscription subscriptions;

    public MainPresenter(MainView v){
        mMainView=v;
        subscriptions = new CompositeSubscription();
        serviceNet = ServiceNet.getInstance();
    }

    void getListProdct(){
        mMainView.showSpinner();
        Subscription subscription = serviceNet.getProductList(new IService() {
            @Override
            public void onError(String error) {
                mMainView.hideSpinnerWithError(error);
            }

            @Override
            public void onSuccess(List<Product> list) {
                mMainView.updateAdapter(list);
                mMainView.hideSpinner();
            }
        });
        subscriptions.add(subscription);
    }
}
