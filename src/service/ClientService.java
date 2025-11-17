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
        if (!Validation.notEmpty(c.getDni())) throw new IllegalArgumentException("El DNI no pot estar buit");
        if (!Validation.notEmpty(c.getName())) throw new IllegalArgumentException("El nom no pot estar buit");
        if (!Validation.notEmpty(c.getAddress())) throw new IllegalArgumentException("L'adreça no pot estar buida");
        if (!Validation.notEmpty(c.getCity())) throw new IllegalArgumentException("La població no pot estar buida");
        if (!Validation.notEmpty(c.getPostalCode())) throw new IllegalArgumentException("El CP no pot estar buit");
        if (!Validation.notEmpty(c.getProvince())) throw new IllegalArgumentException("La província no pot estar buida");
        if (!Validation.notEmpty(c.getPhone())) throw new IllegalArgumentException("El telèfon no pot estar buit");

        if (!Validation.validDni(c.getDni())) throw new IllegalArgumentException("DNI invàlid (8 dígits + lletra)");
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
        if (!Validation.validPhone(c.getPhone())) throw new IllegalArgumentException("Telèfon invàlid (9 dígits, començant per 6, 7, 8 o 9)");
        return repo.save(c);
    }

    public boolean deleteClient(String dni) {
        return repo.delete(dni);
    }

    /**
     * Actualiza un cliente existente
     */
    public boolean updateClient(String dni, Client newClient) throws IllegalArgumentException {
        if (!Validation.notEmpty(newClient.getDni())) throw new IllegalArgumentException("El DNI no pot estar buit");
        if (!Validation.notEmpty(newClient.getName())) throw new IllegalArgumentException("El nom no pot estar buit");
        if (!Validation.notEmpty(newClient.getAddress())) throw new IllegalArgumentException("L'adreça no pot estar buida");
        if (!Validation.notEmpty(newClient.getCity())) throw new IllegalArgumentException("La població no pot estar buida");
        if (!Validation.notEmpty(newClient.getPostalCode())) throw new IllegalArgumentException("El CP no pot estar buit");
        if (!Validation.notEmpty(newClient.getProvince())) throw new IllegalArgumentException("La província no pot estar buida");
        if (!Validation.notEmpty(newClient.getPhone())) throw new IllegalArgumentException("El telèfon no pot estar buit");

        if (!Validation.validDni(newClient.getDni())) throw new IllegalArgumentException("DNI invàlid (8 dígits + lletra)");
        if (!Validation.noForbiddenChars(newClient.getDni())) throw new IllegalArgumentException("El DNI no pot contenir el caràcter ';'");
        if (!Validation.validLength(newClient.getName(), 40)) throw new IllegalArgumentException("Nom massa llarg (40)");
        if (!Validation.noForbiddenChars(newClient.getName())) throw new IllegalArgumentException("El nom no pot contenir el caràcter ';'");
        if (!Validation.validLength(newClient.getAddress(), 40)) throw new IllegalArgumentException("Adreça massa llarga (40)");
        if (!Validation.noForbiddenChars(newClient.getAddress())) throw new IllegalArgumentException("L'adreça no pot contenir el caràcter ';'");
        if (!Validation.validLength(newClient.getCity(), 20)) throw new IllegalArgumentException("Població massa llarga (20)");
        if (!Validation.noForbiddenChars(newClient.getCity())) throw new IllegalArgumentException("La població no pot contenir el caràcter ';'");
        if (!Validation.validCP(newClient.getPostalCode())) throw new IllegalArgumentException("CP invàlid (5 dígits)");
        if (!Validation.validLength(newClient.getProvince(), 20)) throw new IllegalArgumentException("Província massa llarga (20)");
        if (!Validation.noForbiddenChars(newClient.getProvince())) throw new IllegalArgumentException("La província no pot contenir el caràcter ';'");
        if (!Validation.validPhone(newClient.getPhone())) throw new IllegalArgumentException("Telèfon invàlid (9 dígits, començant per 6, 7, 8 o 9)");
        return repo.update(dni, newClient);
    }
}
