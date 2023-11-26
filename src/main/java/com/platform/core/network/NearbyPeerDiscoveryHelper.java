package com.platform.core.network;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  Todo : Currently does not work.
 *
 *  1. Create Send Broadcast Method.
 *  2. Create Listener for Broadcasted messages.
 */
@Slf4j
public class NearbyPeerDiscoveryHelper {

    private static final int PORT = 12345;
    private static final int BROADCAST_INTERVAL = 500; // in milliseconds
    private static final int BROADCAST_DURATION = 2000; // in milliseconds
    private static final int WAIT_FOR_ACK_TIMEOUT = 10000; // in milliseconds
    private static final String DISCOVERY_MESSAGE = "DISCOVER"; // in milliseconds

    private final List<InetAddress> discoveredPeers = new ArrayList<>();

    public List<InetAddress> discoverPeers() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.scheduleAtFixedRate(this::sendBroadcast, 0, BROADCAST_INTERVAL, TimeUnit.MILLISECONDS);

        try {
            Thread.sleep(BROADCAST_DURATION);
        } catch (InterruptedException e) {
            log.error("Discovery interrupted: {}", e.getMessage());
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Executor service termination interrupted: {}", e.getMessage());
        }

        log.info("Discovered peers: {}", discoveredPeers);
        return discoveredPeers;
    }

    private void sendBroadcast() {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            byte[] sendData = DISCOVERY_MESSAGE.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.1.255"), PORT);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            socket.setSoTimeout(WAIT_FOR_ACK_TIMEOUT);

            while (true) {
                try {
                    socket.receive(receivePacket);
                    InetAddress peerAddress = receivePacket.getAddress();
                    int peerPort = receivePacket.getPort();
                        log.info("haddr: " + peerAddress.getHostAddress());
                    if (!discoveredPeers.contains(peerAddress + ":" + peerPort)) {
                        discoveredPeers.add(peerAddress);
                        log.info("Discovered peer: {}, {}", peerAddress, receivePacket);
                    }
                } catch (SocketTimeoutException e) {
                    // Timeout reached, no more responses expected
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Error during discovery: {}", e.getMessage());
        }
    }

}

