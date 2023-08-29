package org.example.server;

import java.io.IOException;

public interface RepositoryHTTPAddress {
    Address searchAddress(String CEP) throws IOException, InterruptedException;
}
