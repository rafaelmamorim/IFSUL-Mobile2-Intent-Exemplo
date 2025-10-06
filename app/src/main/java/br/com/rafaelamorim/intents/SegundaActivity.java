package br.com.rafaelamorim.intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.Manifest;

/**
 * SegundaActivity fornece exemplos de uso de Intents relacionadas à discagem telefônica.
 *
 * <p>Este Activity demonstra duas abordagens:
 * <ul>
 *   <li>{@link #discarViaDiscador(View)} - abre o discador do sistema pré-preenchido com o número
 *       (Ação: {@code Intent.ACTION_DIAL}). Não requer permissão em tempo de execução.</li>
 *   <li>{@link #discarDiretamente(View)} - inicia diretamente uma chamada telefônica
 *       (Ação: {@code Intent.ACTION_CALL}). Requer a permissão {@code android.permission.CALL_PHONE}.</li>
 * </ul>
 *
 * <p>Importante:
 * <ul>
 *   <li>Para usar {@code ACTION_CALL} é necessário declarar a permissão no AndroidManifest.xml:
 *   <pre>&lt;uses-permission android:name="android.permission.CALL_PHONE" /&gt;</pre>
 *   e também solicitá-la em tempo de execução em dispositivos Android 6.0+.</li>
 *   <li>Antes de chamar {@code startActivity} com uma Intent implícita é uma boa prática verificar
 *   se existe uma Activity que pode responder usando {@code resolveActivity(getPackageManager())}.
 * </ul>
 *
 * @see Intent
 */
public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
    }

    /**
     * Abre o discador do Android com o número informado já preenchido.
     *
     * <p>Esta abordagem usa {@code Intent.ACTION_DIAL} e não requer permissões especiais. O
     * usuário precisa confirmar manualmente a chamada no discador.
     *
     * @param view View que disparou o evento (normalmente um botão na interface)
     */
    public void discarViaDiscador(View view) {
        // Número de telefone a ser discado
        String phoneNumber = "+555536211190";

        // Criando a intent para abrir o discador com o número
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        // Inicia a atividade do discador (o usuário visualiza e confirma a chamada)
        startActivity(intent);
    }

    /**
     * Inicia diretamente a chamada telefônica para o número informado usando {@code ACTION_CALL}.
     *
     * <p>Uso correto:
     * <ol>
     *   <li>Declarar a permissão no Manifest: {@code android.permission.CALL_PHONE}.</li>
     *   <li>Solicitar a permissão em tempo de execução (Android 6.0+). Se a permissão não estiver
     *       concedida, este método solicita a permissão e retorna sem iniciar a chamada.</li>
     *   <li>Se a permissão já estiver concedida, a chamada é iniciada imediatamente.</li>
     * </ol>
     *
     * @param view View que disparou o evento (normalmente um botão na interface)
     * @see ActivityCompat#requestPermissions(android.app.Activity, String[], int)
     */
    public void discarDiretamente(View view) {
        String phoneNumber = "+555536211190";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        // Verifica se a permissão CALL_PHONE foi concedida
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Solicita a permissão se não foi concedida. O resultado deve ser tratado em
            // onRequestPermissionsResult(...) pelo desenvolvedor.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }

        // Inicia a chamada telefônica
        startActivity(intent);
    }
}