package org.example.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    @JsonProperty
    private String cep;
    @JsonProperty
    private String logradouro;
    @JsonProperty
    private String bairro;
    @JsonProperty
    private String localidade;

    @Override
    public String toString() {
        return "{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                '}';
    }
}
