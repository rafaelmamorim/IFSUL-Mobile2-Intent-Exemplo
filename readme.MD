<p align="center">
    <a href="http://www.santana.ifsul.edu.br/" target="_blank">
    <img src="IFSul-banner.jpg">
    </a>
</p>

# Exemplo de uso de Intent (Android)

Projeto de exemplo criado para a disciplina Desenvolvimento Mobile II — Tecnólogo em Análise e Desenvolvimento de Sistemas (IFSul, Campus Santana do Livramento). O objetivo é demonstrar o funcionamento de Intents no Android: como iniciar atividades internas (intents explícitas) e como pedir ao sistema ou a outras apps que realizem ações (intents implícitas).

---

## O que é uma Intent

Uma Intent é um objeto de mensageria que permite comunicar componentes do Android (Activities, Services, BroadcastReceivers) entre si. Intents descrevem uma ação a ser executada e, opcionalmente, dados que acompanham essa ação.

Principais usos:

- Iniciar uma Activity
- Iniciar ou vincular um Service
- Enviar um Broadcast
- Pedir ao sistema ou a outra app para executar uma ação (abrir uma URL, tirar uma foto, compartilhar texto)

## Intent explícita

Uma Intent explícita especifica diretamente o componente de destino (classe) que deve tratar a requisição. É usada quando o componente de destino está na mesma aplicação ou quando você sabe exatamente qual Activity/Service deve ser iniciada.

Exemplo (Kotlin):

```kotlin
// Dentro de uma Activity
val intent = Intent(this, DetalheActivity::class.java)
intent.putExtra("EXTRA_ID", 42)
startActivity(intent)
```

Exemplo (Java):

```java
// Dentro de uma Activity
Intent intent = new Intent(this, DetalheActivity.class);
intent.putExtra("EXTRA_ID", 42);
startActivity(intent);
```

Quando usar:

- Navegar entre telas da sua própria app
- Iniciar um Service interno

Vantagens:

- Direto e sem ambiguidade: o destino é conhecido

## Intent implícita

Uma Intent implícita descreve uma ação geral a ser executada (por exemplo, ACTION_VIEW, ACTION_SEND) e pode incluir dados (URI, mime type, extras). O sistema procura componentes capazes de tratar essa ação com base em filtros declarados no `AndroidManifest.xml` (intent-filters). Se existirem várias apps que podem tratar a Intent, o sistema pode mostrar um seletor para o usuário.

Exemplo — abrir uma URL no navegador (Kotlin):

```kotlin
val url = Uri.parse("https://www.example.com")
val intent = Intent(Intent.ACTION_VIEW, url)
startActivity(intent)
```

Exemplo — abrir uma URL no navegador (Java):

```java
Uri url = Uri.parse("https://www.example.com");
Intent intent = new Intent(Intent.ACTION_VIEW, url);
startActivity(intent);
```

Exemplo — compartilhar texto (Kotlin):

```kotlin
val share = Intent(Intent.ACTION_SEND)
share.type = "text/plain"
share.putExtra(Intent.EXTRA_TEXT, "Veja este exemplo de Intent!")
startActivity(Intent.createChooser(share, "Compartilhar via"))
```

Exemplo — compartilhar texto (Java):

```java
Intent share = new Intent(Intent.ACTION_SEND);
share.setType("text/plain");
share.putExtra(Intent.EXTRA_TEXT, "Veja este exemplo de Intent!");
startActivity(Intent.createChooser(share, "Compartilhar via"));
```

Quando usar:

- Delegar ações ao sistema ou a outras apps (abrir URL, enviar e-mail, tirar foto)
- Integrar funcionalidades sem depender de implementações internas

Observações:

- Nem toda Intent implícita terá um app capaz de resolvê-la; antes de chamar `startActivity`, é
  uma boa prática verificar se existe alguma `Activity` que possa atender a Intent (usando
  `resolveActivity(packageManager) != null`) para evitar crashes.

Exemplo de verificação (Kotlin):

```kotlin
val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"))
if (intent.resolveActivity(packageManager) != null) {
    startActivity(intent)
} else {
    Toast.makeText(this, "Nenhum app encontrado para esta ação", Toast.LENGTH_SHORT).show()
}
```

Exemplo de verificação (Java):

```java
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"));
if (intent.resolveActivity(getPackageManager()) != null) {
    startActivity(intent);
} else {
    Toast.makeText(this, "Nenhum app encontrado para esta ação", Toast.LENGTH_SHORT).show();
}
```

## Como executar

1. Abra o projeto em Android Studio.
2. Conecte um dispositivo Android via USB (modo desenvolvedor) ou inicie um emulador.
3. Build e Run (play). Se necessário, ajuste o SDK/gradle conforme sua instalação local.

Dica: caso apareça uma Intent implícita no código de exemplo, use um dispositivo com apps que
suportem a ação (por exemplo, navegador para `ACTION_VIEW`, app de e-mail para `ACTION_SENDTO`).

## Screenshot

```
![Screenshot da App](app/src/main/res/drawable/screenshot.png)
```