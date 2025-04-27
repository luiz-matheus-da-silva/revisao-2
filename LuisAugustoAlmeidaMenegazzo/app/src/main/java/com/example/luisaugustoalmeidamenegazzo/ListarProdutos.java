package com.example.luisaugustoalmeidamenegazzo;

import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.List;

public class ListarProdutos extends AppCompatActivity {
    private TextView textListarProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_produtos);

        textListarProd = findViewById(R.id.textListarProd);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> voltarParaCadastroProd());

        ProductDatabase db = Room.databaseBuilder(getApplicationContext(),
                ProductDatabase.class, "product-database").allowMainThreadQueries().build();
        ProductDao productDao = db.productDao();

        List<Product> productList = productDao.getAllProducts();
        StringBuilder report = new StringBuilder();

        for (Product product : productList) {
            report.append("Produto: ").append(product.getNomeProd()).append("\n")
                    .append("Código: ").append(product.getCodProd()).append("\n\n");
        }

        textListarProd.setText(report.toString());
    }

    public void voltarParaCadastroProd() {
        Intent intent = new Intent(ListarProdutos.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
