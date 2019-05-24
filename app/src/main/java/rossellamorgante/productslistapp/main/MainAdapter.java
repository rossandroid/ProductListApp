package rossellamorgante.productslistapp.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import rossellamorgante.productslistapp.R;
import rossellamorgante.productslistapp.model.Product;
import rossellamorgante.productslistapp.model.ProductsList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Product> mProduct;

    @Inject
    public MainAdapter() {


    }

    public void setListProduct(List<Product> data){
        this.mProduct = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Product c = mProduct.get(i);
        viewHolder.nameProduct.setText(c.name);
        viewHolder.price.setText(c.price);

        new DownloadImageTask(viewHolder.imgProduct)
                .execute(c.image);


    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameProduct;
        public TextView price;
        public ImageView imgProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameProduct= (TextView) itemView.findViewById(R.id.textProductName);
            price= (TextView) itemView.findViewById(R.id.textPrice);
            imgProduct= (ImageView) itemView.findViewById(R.id.imageProduct);

        }
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
