package com.example.talismanv01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class LobbyActivity extends AppCompatActivity {

    // Views
    private Button startGameButton;
    private TextView hostIP;

    // Server-Komponenten
    private ServerSocket serverSocket;
    private ArrayList<Socket> clientSockets = new ArrayList<>();
    private boolean isHost;
    private boolean serverRunning = true;
    private int connectedPlayers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);

        // Views initialisieren
        startGameButton = findViewById(R.id.startGameButton);
        hostIP = findViewById(R.id.hostIP);

        // Erweiterungen aus Intent abrufen und Charaktere filtern
        ArrayList<String> selectedExpansions = getIntent().getStringArrayListExtra("SELECTED_EXPANSIONS");
        if (selectedExpansions != null) {
            filterCharactersByExpansions(selectedExpansions);
        }

        // Host oder Client?
        isHost = getIntent().getBooleanExtra("isHost", false);

        if (isHost) {
            // Als Host: Server starten und IP anzeigen
            startGameButton.setEnabled(false); // Deaktivieren bis Spieler verbunden sind
            new Thread(this::startServer).start();
        } else {
            // Als Client: Anzeigen, mit welchem Host verbunden
            String hostIpAddress = getIntent().getStringExtra("HOST_IP");
            if (hostIpAddress != null) {
                hostIP.setText("Verbunden mit Host: " + hostIpAddress);
            } else {
                hostIP.setText("Keine Verbindungsinformationen verfügbar");
                Toast.makeText(this, "Verbindungsfehler", Toast.LENGTH_SHORT).show();
            }

            // Start-Button für Client ausblenden oder deaktivieren
            startGameButton.setVisibility(View.GONE);
        }

        // Button Listener für den Spielstart
        startGameButton.setOnClickListener(v -> startGame());
    }

    private String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                // Überspringe inaktive Schnittstellen
                if (!networkInterface.isUp()) continue;

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    // Filtere lokale und IPv6-Adressen aus
                    if (!address.isLoopbackAddress() && address.getHostAddress().indexOf(':') == -1) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "IP nicht gefunden";
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(5000);
            final String hostIpAddress = getLocalIpAddress();

            runOnUiThread(() -> {
                hostIP.setText("Host IP: " + hostIpAddress);
                Toast.makeText(LobbyActivity.this,
                        "Server gestartet auf " + hostIpAddress,
                        Toast.LENGTH_SHORT).show();
            });

            // Auf Spieler warten
            while (serverRunning) {
                try {
                    Socket client = serverSocket.accept();
                    clientSockets.add(client);
                    connectedPlayers++;

                    runOnUiThread(() -> {
                        Toast.makeText(LobbyActivity.this,
                                "Neuer Spieler verbunden: " + client.getInetAddress().getHostAddress(),
                                Toast.LENGTH_SHORT).show();

                        // Aktiviere den Start-Button, wenn mindestens ein Spieler verbunden ist
                        if (connectedPlayers > 0) {
                            startGameButton.setEnabled(true);
                        }
                    });
                } catch (IOException e) {
                    if (serverRunning) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            runOnUiThread(() -> {
                Toast.makeText(LobbyActivity.this,
                        "Fehler beim Starten des Servers: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
                hostIP.setText("Server konnte nicht gestartet werden");
                finish();  // Beende die Activity, wenn der Server nicht startet
            });
        }
    }

    private void closeServer() {
        serverRunning = false;

        for (Socket socket : clientSockets) {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void filterCharactersByExpansions(ArrayList<String> expansions) {
        List<GameCharacter> availableCharacters = new ArrayList<>();
        List<GameCharacter> allCharacters = CharacterManager.getAllCharacters(); // Holt alle Charaktere

        for (GameCharacter character : allCharacters) {
            if (expansions.contains(character.getExpansion()) || character.getExpansion().equals("Base")) {
                availableCharacters.add(character);
            }
        }

        // Hier kannst du die `availableCharacters`-Liste weiterverwenden, z.B. um sie an eine UI anzuzeigen.
    }

    // Methode, um das Spiel zu starten
    private void startGame() {
        Toast.makeText(LobbyActivity.this, "Spiel wird gestartet!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (isHost) {
            closeServer();
        }
        super.onDestroy();
    }
}
