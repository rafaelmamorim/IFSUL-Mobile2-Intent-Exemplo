package br.com.rafaelamorim.intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.Manifest;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
    }
    /**
     * Método para discar para um número de telefone, usando o discador do Android
     */
    public void discarViaDiscador(View view) {
        // Número de telefone a ser discado
        String phoneNumber = "+555536211190";

        // Criando a intent para fazer a discagem
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        // Inicia a atividade da discagem
        startActivity(intent);
    }

    /**
     * Método para discar para um número de telefone, SEM USAR o discador do Android
     */
    public void discarDiretamente(View view) {
        String phoneNumber = "+555536211190";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        // Verifica se a permissão CALL_PHONE foi concedida
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Solicita a permissão se não foi concedida
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }

        // Inicia a chamada telefônica
        startActivity(intent);
    }
}