package com.example.talismanv01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class LobbyClientActivity extends AppCompatActivity {
    private EditText ipInput;
    private Button joinButton;
    private Socket socket;
    private final int PORT = 5000;
    private boolean connectionEstablished = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_client_activity);

        ipInput = findViewById(R.id.ipInput);
        joinButton = findViewById(R.id.btnJoin);

        joinButton.setOnClickListener(view -> {
            String hostIp = ipInput.getText().toString().trim();
            joinLobby(hostIp);
        });
    }

    private void joinLobby(String ipAddress) {
        // Disable join button to prevent multiple attempts
        runOnUiThread(() -> joinButton.setEnabled(false));

        new Thread(() -> {
            try {
                if (ipAddress.isEmpty()) {
                    runOnUiThread(() -> {
                        Toast.makeText(LobbyClientActivity.this, "Bitte gib eine IP-Adresse ein", Toast.LENGTH_LONG).show();
                        joinButton.setEnabled(true);
                    });
                    return;
                }

                // Check if IP address is valid
                InetAddress serverAddress = InetAddress.getByName(ipAddress);

                // Set a timeout for the connection
                socket = new Socket();
                socket.connect(new InetSocketAddress(serverAddress, PORT), 5000); // 5000ms timeout

                // If we get here, the connection was successful
                connectionEstablished = true;

                runOnUiThread(() -> {
                    Toast.makeText(LobbyClientActivity.this, "Verbindung erfolgreich!", Toast.LENGTH_SHORT).show();

                    // Switch to LobbyActivity and pass host IP
                    Intent intent = new Intent(LobbyClientActivity.this, LobbyActivity.class);
                    intent.putExtra("isHost", false);
                    intent.putExtra("HOST_IP", ipAddress);
                    intent.putExtra("CONNECTION_ESTABLISHED", true);
                    startActivity(intent);
                });

                // Start a thread to handle communication with the server
                // Note: Don't close the socket here - it will be used for communication
                handleServerCommunication();

            } catch (IOException e) {
                e.printStackTrace();

                runOnUiThread(() -> {
                    Toast.makeText(LobbyClientActivity.this,
                            "Verbindungsfehler: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    joinButton.setEnabled(true);
                });

                // Close the socket only if connection failed
                closeSocket();
            } catch (Exception e) {
                e.printStackTrace();

                runOnUiThread(() -> {
                    Toast.makeText(LobbyClientActivity.this,
                            "Ein Fehler ist aufgetreten: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    joinButton.setEnabled(true);
                });

                // Close the socket only if connection failed
                closeSocket();
            }
        }).start();
    }

    private void handleServerCommunication() {
        // Implement your communication protocol here
        // This would include reading from and writing to the server socket
    }

    private void closeSocket() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeSocket();
    }
}