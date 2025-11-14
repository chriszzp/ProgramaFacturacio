package service;

import model.Client;
import repository.ClientRepository;
import util.Validation;
import java.util.List;

public class ClientService {
    private final ClientRepository repo;

    public ClientService(String dataDir) {
        this.repo = new ClientRepository(dataDir);
    }

    public List<Client> listAll() {
        return repo.findAll();
    }

    public Client find(String dni) {
        return repo.findByDni(dni);
    }

    public boolean addClient(Client c) throws IllegalArgumentException {
        // validations per spec
        if (!Validation.validDni(c.getDni())) throw new IllegalArgumentException("DNI invàlid (ha de tenir 9 caràcters)");
        if (!Validation.noForbiddenChars(c.getDni())) throw new IllegalArgumentException("El DNI no pot contenir el caràcter ';'");
        if (!Validation.validLength(c.getName(), 40)) throw new IllegalArgumentException("Nom massa llarg (40)");
        if (!Validation.noForbiddenChars(c.getName())) throw new IllegalArgumentException("El nom no pot contenir el caràcter ';'");
        if (!Validation.validLength(c.getAddress(), 40)) throw new IllegalArgumentException("Adreça massa llarga (40)");
        if (!Validation.noForbiddenChars(c.getAddress())) throw new IllegalArgumentException("L'adreça no pot contenir el caràcter ';'");
        if (!Validation.validLength(c.getCity(), 20)) throw new IllegalArgumentException("Població massa llarga (20)");
        if (!Validation.noForbiddenChars(c.getCity())) throw new IllegalArgumentException("La població no pot contenir el caràcter ';'");
        if (!Validation.validCP(c.getPostalCode())) throw new IllegalArgumentException("CP invàlid (5 dígits)");
        if (!Validation.validLength(c.getProvince(), 20)) throw new IllegalArgumentException("Província massa llarga (20)");
        if (!Validation.noForbiddenChars(c.getProvince())) throw new IllegalArgumentException("La província no pot contenir el caràcter ';'");
        if (!Validation.validPhone(c.getPhone())) throw new IllegalArgumentException("Telèfon invàlid (9 dígits)");
        return repo.save(c);
    }

    public boolean deleteClient(String dni) {
        return repo.delete(dni);
    }
}
