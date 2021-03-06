package dev.jcrystal.crystalfash.ui.product;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import dev.jcrystal.crystalfash.R;
import jcrystal.mobile.entities.ProductNormal;
import jcrystal.mobile.entities.enums.Categories;
import jcrystal.mobile.net.controllers.ManagerProduct;

public class ProductActivity extends AppCompatActivity {
    private TextView txtName,txtDescription,txtCategory, txtPrice, txtOldPrice;
    private ImageView img;
    Button btnAdd2Cart;
    private ProductNormal product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        txtName = (TextView) findViewById(R.id.product_detail_name);
        txtDescription = (TextView) findViewById(R.id.product_detail_desc);
        txtCategory = (TextView) findViewById(R.id.product_detail_category);
        img = (ImageView) findViewById(R.id.product_detail_img);
        txtPrice = (TextView) findViewById(R.id.product_detail_price);
        txtOldPrice = (TextView) findViewById(R.id.product_detail_old_price);
        btnAdd2Cart = findViewById(R.id.button_add_cart);

        // Recieve data
        Intent intent = getIntent();
        long idProduct = intent.getExtras().getLong("idProduct");


        ManagerProduct.getProductById(this,idProduct, product->{
            this.product = product;
            txtName.setText(product.name());
            txtDescription.setText(product.description());
            txtCategory.setText(Categories.fromId(product.category().id).name());
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            txtPrice.setText(formatter.format(product.price()));
            txtOldPrice.setText(formatter.format(product.oldPrice()));
            Picasso.get().load(product.image()).into(img);
        }, error->{
            Toast.makeText(getApplicationContext(),error.mensaje,Toast.LENGTH_SHORT);
        });


        btnAdd2Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Toast.makeText(getApplicationContext(), "Item has been added to your cart", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

}
