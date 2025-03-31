package com.example.talismanv01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class LobbyHostActivity extends AppCompatActivity {
    private ServerSocket serverSocket;
    private ArrayList<Socket> clientSockets = new ArrayList<>();
    private TextView lobbyInfo;
    private String hostIp;
    private final int PORT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);

        lobbyInfo = findViewById(R.id.lobbyInfo);

        // Get the actual device IP address
        hostIp = getLocalIpAddress();

        if (hostIp != null) {
            lobbyInfo.setText("Your IP Address: " + hostIp);
            new Thread(this::startServer).start();
        } else {
            lobbyInfo.setText("Failed to obtain IP address. Check your network connection.");
        }
    }

    private String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    // Filter for IPv4 addresses that are not loopback
                    if (!address.isLoopbackAddress() && address.getHostAddress().contains(".")) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            runOnUiThread(() -> {
                lobbyInfo.append("\nServer started on port " + PORT);

                // Pass connection info to LobbyActivity
                Intent intent = new Intent(LobbyHostActivity.this, LobbyActivity.class);
                intent.putExtra("isHost", true);
                intent.putExtra("HOST_IP", hostIp);
                startActivity(intent);
            });

            // Keep accepting client connections
            while (!Thread.interrupted() && !serverSocket.isClosed()) {
                try {
                    Socket client = serverSocket.accept();
                    clientSockets.add(client);

                    String clientAddress = client.getInetAddress().getHostAddress();
                    runOnUiThread(() -> lobbyInfo.append("\nNew player connected: " + clientAddress));

                    // Start a new thread to handle communication with this client
                    new Thread(() -> handleClientCommunication(client)).start();
                } catch (IOException e) {
                    if (!serverSocket.isClosed()) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            runOnUiThread(() -> lobbyInfo.append("\nServer error: " + e.getMessage()));
        }
    }

    private void handleClientCommunication(Socket client) {
        // Implement your communication protocol here
        // This would include reading from and writing to the client socket
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close all connections when the activity is destroyed
        for (Socket socket : clientSockets) {
            try {
                if (!socket.isClosed()) {
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
}


