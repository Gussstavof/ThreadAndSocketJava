package org.example.client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("started client");
        Socket socket = new Socket("localhost", 8080);

        Thread sender = new Thread(() -> {
            try (
                    Scanner sc = new Scanner(System.in);
                    PrintStream printStream = new PrintStream(socket.getOutputStream())
            ) {

                while (sc.hasNext()) {
                    String cep = sc.next();
                    printStream.println(cep);
                }

            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });

        Thread receiver = new Thread(() -> {
            try (Scanner sc = new Scanner(socket.getInputStream())) {

                while (sc.hasNext()) {
                    System.out.print(sc.next());
                }

            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });

        sender.start();
        receiver.start();

        sender.join();
        receiver.join();

        socket.close();
    }
}
