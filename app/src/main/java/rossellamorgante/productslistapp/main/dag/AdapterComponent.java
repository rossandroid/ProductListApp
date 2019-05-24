package rossellamorgante.productslistapp.main.dag;


import dagger.Component;
import rossellamorgante.productslistapp.main.MainActivity;



@Component
public interface AdapterComponent {

    void inject(MainActivity mainActivity);

}