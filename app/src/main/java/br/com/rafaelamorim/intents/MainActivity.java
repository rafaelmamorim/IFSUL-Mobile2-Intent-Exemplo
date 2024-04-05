package br.com.rafaelamorim.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirSiteIfsul(View view){
        // Criando a URI do site
        Uri uri = Uri.parse("http://www.ifsul.edu.br");

        // Criando uma Intent com a ação ACTION_VIEW
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        // Verificando se há um navegador disponível para lidar com a Intent
        startActivity(intent);
    }

    public void abrirSegundaActivity(View view) {
        Intent intent = new Intent(this, SegundaActivity.class);
        startActivity(intent);
    }

}