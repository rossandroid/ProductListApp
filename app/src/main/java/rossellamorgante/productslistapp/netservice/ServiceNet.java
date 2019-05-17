package rossellamorgante.productslistapp.netservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rossellamorgante.productslistapp.model.ProductsList;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ServiceNet {

    private static ServiceNet instance;
    private EndPoint endPoint;
    private Gson gson;
    private Retrofit retrofit;

    public ServiceNet(){
        // Retrofit uses GSON to parse data
        gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.endclothing.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
        endPoint = retrofit.create(EndPoint.class);

    };

    //Singleton Pattern
    public static ServiceNet getInstance(){
        if(instance==null)
            instance=new ServiceNet();
        return instance;
    }

    public Subscription getProductList(final IService service){

        return endPoint.getProductsRetrofit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductsList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        service.onError(e.toString());
                    }

                    @Override
                    public void onNext(ProductsList response) {
                        service.onSuccess(response.products);
                    }
                });

    }


}