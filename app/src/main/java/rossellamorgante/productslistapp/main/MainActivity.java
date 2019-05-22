package rossellamorgante.productslistapp.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rossellamorgante.productslistapp.R;
import rossellamorgante.productslistapp.model.Product;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMP;
    private RecyclerView recyclerView;
    private List<Product> mList=new ArrayList<>();
    private MainAdapter mAdapter;
    private int numberOfColumns=2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_menu:

                    return true;
                case R.id.navigation_bookmark:

                    return true;
                case R.id.navigation_Logo:

                    return true;
                case R.id.navigation_user:

                    return true;
                case R.id.navigation_basket:
                    ((LinearLayout)findViewById(R.id.banner_delivery)).setVisibility(View.VISIBLE);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMP = new MainPresenter(this);
        mMP.getListProdct();

        recyclerView = (RecyclerView) findViewById(R.id.r_list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        // Create the Adapter for Recycleview
        mAdapter = new MainAdapter(mList);
        // Set the Adapter to Recycleview
        recyclerView.setAdapter(mAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager mGLM = new GridLayoutManager(this, numberOfColumns);
        recyclerView.setLayoutManager(mGLM);
      //  DividerItemDecoration div = new DividerItemDecoration(recyclerView.getContext(), mGLM.getOrientation());
       // recyclerView.addItemDecoration(div);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public void closeBanner(View v){
        ((LinearLayout)findViewById(R.id.banner_delivery)).setVisibility(View.GONE);
    }

    // HomeView Override Methods
    @Override
    public void showSpinner() {
        ((ProgressBar)findViewById(R.id.spinner)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.textError)).setVisibility(View.GONE);
        ((NestedScrollView)findViewById(R.id.scroll_content)).setVisibility(View.GONE);

    }

    @Override
    public void hideSpinner() {
        ((ProgressBar)findViewById(R.id.spinner)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.textError)).setVisibility(View.GONE);
        ((NestedScrollView)findViewById(R.id.scroll_content)).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSpinnerWithError(String error) {
        ((ProgressBar)findViewById(R.id.spinner)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.textError)).setText(error);
        ((TextView)findViewById(R.id.textError)).setVisibility(View.VISIBLE);
        ((NestedScrollView)findViewById(R.id.scroll_content)).setVisibility(View.GONE);

    }

    @Override
    public void updateAdapter(List<Product> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        ((TextView)findViewById(R.id.item_size)).setText(mList.size()+" items");

    }
}
