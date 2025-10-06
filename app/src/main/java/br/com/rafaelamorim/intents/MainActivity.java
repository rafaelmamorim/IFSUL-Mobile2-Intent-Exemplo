package br.com.rafaelamorim.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * MainActivity demonstra exemplos simples de uso de Intents no Android.
 *
 * <p>Contém métodos que disparam Intents implícitas e explícitas:
 * <ul>
 *   <li>{@link #abrirSiteIfsul(View)} — usa {@code Intent.ACTION_VIEW} para abrir uma URL no
 *       navegador (Intent implícita).</li>
 *   <li>{@link #abrirSegundaActivity(View)} — inicia {@code SegundaActivity} via Intent explícita.</li>
 * </ul>
 *
 * <p>Boas práticas destacadas:
 * <ul>
 *   <li>Verificar se uma Intent implícita pode ser resolvida antes de chamar
 *       {@code startActivity} para evitar crashes (usar {@code resolveActivity(getPackageManager())}).</li>
 *   <li>Utilizar {@code ACTION_DIAL} em vez de {@code ACTION_CALL} quando não for necessário iniciar
 *       a chamada diretamente, evitando a necessidade de permissões de tempo de execução.</li>
 * </ul>
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Abre o site do IFSul no navegador do dispositivo usando uma Intent implícita
     * (ação {@code Intent.ACTION_VIEW}).
     *
     * <p>É recomendável verificar se existe uma Activity que possa resolver a Intent antes de
     * chamar {@code startActivity} para evitar exceções quando não houver apps capazes de lidar com
     * a ação.
     *
     * @param view View que disparou o evento (normalmente um botão)
     */
    public void abrirSiteIfsul(View view){
        // Criando a URI do site
        Uri uri = Uri.parse("http://www.ifsul.edu.br");

        // Criando uma Intent com a ação ACTION_VIEW (intent implícita)
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        // Boa prática: verificar se existe alguma Activity que possa tratar essa Intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Nenhum app encontrado para abrir a URL", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Inicia a {@code SegundaActivity} usando uma Intent explícita.
     *
     * @param view View que disparou o evento (normalmente um botão)
     */
    public void abrirSegundaActivity(View view) {
        Intent intent = new Intent(this, SegundaActivity.class);
        startActivity(intent);
    }

}