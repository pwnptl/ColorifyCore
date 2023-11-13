package com.platform.core.network;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class NearbyPeerDiscoveryHelper {

    private static final int SERVICE_PORT = 12345;
    private static final int TIMEOUT = 2000; // 2 Sec

    public List<InetAddress> discoverPeers() {
        List<InetAddress> peers = new ArrayList<>();
        try (DatagramSocket socket = new DatagramSocket(SERVICE_PORT, InetAddress.getByName("0.0.0.0"))) {
            socket.setBroadcast(true);
            socket.setSoTimeout(TIMEOUT);
            System.out.println("Listening for peers on port " + SERVICE_PORT);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                try {
                    socket.receive(receivePacket);

                    InetAddress senderAddress = receivePacket.getAddress();
                    int senderPort = receivePacket.getPort();

                    System.out.println("Received packet from " + senderAddress + ":" + senderPort);

                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received message: " + receivedMessage);

                    // Add senderAddress to peers if not already present
                    if (!peers.contains(senderAddress)) {
                        peers.add(senderAddress);
                    }

                    // Add your additional processing logic here

                } catch (SocketTimeoutException ste) {
                    // Handle timeout if needed
                    break; // exit the loop when a timeout occurs
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return peers;
    }

}

