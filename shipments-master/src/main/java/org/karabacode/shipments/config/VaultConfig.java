package org.karabacode.shipments.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.KubernetesAuthentication;
import org.springframework.vault.authentication.KubernetesAuthenticationOptions;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

import java.io.*;
import java.net.URI;
import java.util.function.Supplier;

@Configuration
public class VaultConfig extends AbstractVaultConfiguration {

    @Value("${vault.url}")
    private String vaultUri;

    @Value("${vault.jwt_path}")
    private String vaultJwtPath;

    @Override
    public VaultEndpoint vaultEndpoint() {
        return VaultEndpoint.from(URI.create(vaultUri));
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        Supplier<String> jwtSupplier = () -> {
            try {
                return this.getJWT();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        KubernetesAuthenticationOptions options = KubernetesAuthenticationOptions.builder()
                .role("shipments").jwtSupplier(jwtSupplier).build();
        return new KubernetesAuthentication(options, restOperations());
    }

    private String getJWT() throws IOException {
        InputStream is = new FileInputStream(vaultJwtPath);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while(line != null){
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        return sb.toString();
    }
}
