package org.karabacode.shipments.credentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

@Service
public class CredencialesService {

    @Autowired
    private VaultTemplate vaultTemplate;

    public VaultResponse accessCredentials() {

        VaultResponseSupport<VaultResponse> response = vaultTemplate.read("secret/data/shipments/config", VaultResponse.class);

        return response.getData();
    }
}
