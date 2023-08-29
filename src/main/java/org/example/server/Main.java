package org.example.server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        RepositoryHTTPAddress repositoryHTTPAddress = new AddressService();
        System.out.println("started server");

        ServerSocket socket = new ServerSocket(8080);
        ExecutorService pool = Executors.newCachedThreadPool();
        final BlockingQueue<String> cepQueue = new ArrayBlockingQueue<>(4);

        while (true) {
            Socket client = socket.accept();
            pool.submit(() -> {
                System.out.println("Thread: " + Thread.currentThread().getName());

                try (Scanner scanner = new Scanner(client.getInputStream())) {

                    while (scanner.hasNextLine()) {
                        cepQueue.put(scanner.next());
                    }

                } catch (Exception exception) {
                    throw new RuntimeException(exception.getMessage());
                }
            });

            pool.submit(() -> {
                System.out.println("Thread: " + Thread.currentThread().getName());

                try (PrintStream printStream = new PrintStream(client.getOutputStream())) {

                    while (!cepQueue.isEmpty()) {
                        Future<Address> future = pool.submit(
                                new AddressTask(cepQueue.take(), repositoryHTTPAddress)
                        );
                        Address address = future.get();
                        printStream.println(address.toString());
                    }

                } catch (Exception exception) {
                    throw new RuntimeException(exception.getMessage());
                }
            });
        }
    }
}
