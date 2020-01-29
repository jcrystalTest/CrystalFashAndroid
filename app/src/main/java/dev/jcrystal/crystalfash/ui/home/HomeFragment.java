package dev.jcrystal.crystalfash.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.jcrystal.crystalfash.R;
import jcrystal.mobile.entities.CartNormal;
import jcrystal.mobile.entities.ProductNormal;
import jcrystal.mobile.entities.enums.Categories;
import jcrystal.mobile.net.controllers.ManagerCart;
import jcrystal.mobile.net.controllers.ManagerProduct;
import jcrystal.mobile.net.utils.OnErrorListener;
import jcrystal.mobile.net.utils.RequestError;

public class HomeFragment extends Fragment implements OnErrorListener {

    private List<ProductNormal> lstProducts;
    private CartNormal cart;
    private List<String> categories;
    ProductAdapter myAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ManagerCart.createCart(this,(cartNormal)->{
            this.cart = cartNormal;
            System.out.println(this.cart.id());
        });
        lstProducts = new ArrayList<>();
        categories = new ArrayList<>();
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView myrv = (RecyclerView) root.findViewById(R.id.recyclerview_id);
        myAdapter = new ProductAdapter(getContext(),lstProducts);
        myrv.setLayoutManager(new GridLayoutManager(getContext(),2));
        myrv.setAdapter(myAdapter);

        ManagerProduct.getProducts(this,(products)->{
            lstProducts = products;
            myAdapter.updateList(lstProducts);
            myAdapter.notifyDataSetChanged();

        });

        RecyclerView rv_category = (RecyclerView) root.findViewById(R.id.recyclerview_category_id);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(),categories, this);
        rv_category.setLayoutManager(new GridLayoutManager(getContext(),3));
        rv_category.setAdapter(categoryAdapter);

        ManagerProduct.getCategories(this,strings -> {
            categories = strings;
            categoryAdapter.updateList(categories);
            categoryAdapter.notifyDataSetChanged();
        });



        return root;
    }

    public void onError(RequestError error){
        Toast.makeText(getContext(),error.mensaje,Toast.LENGTH_SHORT);
    }

    public void updateProductsByCategory(Categories category){
        ManagerProduct.filterProductsByCategory(this,category,(products)->{
            lstProducts = products;
            myAdapter.updateList(lstProducts);
            myAdapter.notifyDataSetChanged();
        },error -> {
            System.out.println(error.mensaje);
        });
    }
}