package org.example.server;

import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class AddressTask implements Callable<Address> {
    private final String CEP;
    private final RepositoryHTTPAddress repositoryHTTPAddress;

    public AddressTask(String CEP, RepositoryHTTPAddress repositoryHTTPAddress) {
        this.CEP = CEP;
        this.repositoryHTTPAddress = repositoryHTTPAddress;
    }

    @Override
    public Address call() throws Exception {
        return repositoryHTTPAddress.searchAddress(CEP);
    }
}
