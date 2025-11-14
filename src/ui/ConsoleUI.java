package ui;

import model.*;
import service.*;
import util.Validation;

import java.time.LocalDate;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class ConsoleUI {
    private final ClientService clientSvc;
    private final ArticleService articleSvc;
    private final InvoiceService invoiceSvc;
    private final ConfigService configSvc;

    public ConsoleUI(ClientService cs, ArticleService as, InvoiceService is, ConfigService cfg) {
        this.clientSvc = cs;
        this.articleSvc = as;
        this.invoiceSvc = is;
        this.configSvc = cfg;
    }

    public void run() {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8.name());
        boolean running = true;
        while (running) {
            System.out.println("\n--- Programa de facturació (consola) ---");
            System.out.println("1. Gestió de clients");
            System.out.println("2. Gestió d'articles");
            System.out.println("3. Gestió de factures");
            System.out.println("4. Configuració (IVA actual: " + configSvc.getIva() + "%)");
            System.out.println("5. Sortir");
            System.out.print("Tria una opció: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": manageClients(sc); break;
                case "2": manageArticles(sc); break;
                case "3": manageInvoices(sc); break;
                case "4": manageConfig(sc); break;
                case "5": running = false; break;
                default: System.out.println("Opció no vàlida");
            }
        }
        System.out.println("Fins aviat.");
    }

    private void manageClients(Scanner sc) {
        while (true) {
            System.out.println("\n-- Gestió de clients --");
            System.out.println("1. Alta client");
            System.out.println("2. Llistar clients");
            System.out.println("3. Consultar per DNI");
            System.out.println("4. Tornar enrere");
            System.out.print("Opció: ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                try {
                    System.out.print("DNI: "); String dni = sc.nextLine().trim();
                    System.out.print("Nom: "); String name = sc.nextLine().trim();
                    System.out.print("Adreça: "); String addr = sc.nextLine().trim();
                    System.out.print("Població: "); String city = sc.nextLine().trim();
                    System.out.print("CP: "); String cp = sc.nextLine().trim();
                    System.out.print("Província: "); String prov = sc.nextLine().trim();
                    System.out.print("Telèfon: "); String phone = sc.nextLine().trim();
                    Client c = new Client(dni, name, addr, city, cp, prov, phone);
                    boolean ok = clientSvc.addClient(c);
                    if (ok) System.out.println("Client desat."); else System.out.println("No s'ha pogut desar (potser ja existeix el DNI)");
                } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
            } else if (opt.equals("2")) {
                List<Client> all = clientSvc.listAll();
                System.out.println("Clients: ");
                for (Client c : all) System.out.println(c);
            } else if (opt.equals("3")) {
                System.out.print("DNI a buscar: "); String dni = sc.nextLine().trim();
                Client c = clientSvc.find(dni);
                if (c == null) System.out.println("Client no trobat"); else System.out.println(c);
            } else if (opt.equals("4")) return; else System.out.println("Opció no vàlida");
        }
    }

    private void manageArticles(Scanner sc) {
        while (true) {
            System.out.println("\n-- Gestió d'articles --");
            System.out.println("1. Alta article");
            System.out.println("2. Llistar articles");
            System.out.println("3. Consultar per nom");
            System.out.println("4. Tornar enrere");
            System.out.print("Opció: ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                try {
                    System.out.print("Nom article: "); String name = sc.nextLine().trim();
                    System.out.print("Preu (ex. 12.50): "); String price = sc.nextLine().trim();
                    Article a = new Article(name, price);
                    boolean ok = articleSvc.addArticle(a);
                    if (ok) System.out.println("Article desat."); else System.out.println("No s'ha pogut desar (potser ja existeix)");
                } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
            } else if (opt.equals("2")) {
                List<Article> all = articleSvc.listAll();
                System.out.println("Articles: ");
                for (Article a : all) System.out.println(a);
            } else if (opt.equals("3")) {
                System.out.print("Nom a buscar: "); String name = sc.nextLine().trim();
                Article a = articleSvc.find(name);
                if (a == null) System.out.println("Article no trobat"); else System.out.println(a);
            } else if (opt.equals("4")) return; else System.out.println("Opció no vàlida");
        }
    }

    private void manageInvoices(Scanner sc) {
        while (true) {
            System.out.println("\n-- Gestió de factures --");
            System.out.println("1. Nova factura");
            System.out.println("2. Llistar factures");
            System.out.println("3. Consultar factura per Id");
            System.out.println("4. Tornar enrere");
            System.out.print("Opció: ");
            String opt = sc.nextLine().trim();
            if (opt.equals("1")) {
                try {
                    System.out.print("DNI client: "); String dni = sc.nextLine().trim();
                    Client c = clientSvc.find(dni);
                    if (c == null) { System.out.println("Client no existeix"); continue; }
                    List<InvoiceLine> lines = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        System.out.print("Afegir línia? (s/n): "); String yn = sc.nextLine().trim();
                        if (!yn.equalsIgnoreCase("s")) break;
                        System.out.print("Quantitat: "); String q = sc.nextLine().trim();
                        if (!Validation.validQuantity(q)) { System.out.println("Quantitat invàlida"); i--; continue; }
                        int qty = Integer.parseInt(q);
                        System.out.print("Nom article: "); String name = sc.nextLine().trim();
                        Article a = articleSvc.find(name);
                        String priceStr;
                        if (a != null) {
                            System.out.print("Vols usar el preu de l'article (" + a.getPrice() + ")? (s/n): ");
                            String use = sc.nextLine().trim();
                            if (use.equalsIgnoreCase("s")) priceStr = a.getPrice(); else { System.out.print("Preu unitari: "); priceStr = sc.nextLine().trim(); }
                        } else {
                            System.out.print("Article no trobat. Indica preu unitari: "); priceStr = sc.nextLine().trim();
                        }
                        if (!Validation.validPrice(priceStr)) { System.out.println("Preu invàlid"); i--; continue; }
                        InvoiceLine line = new InvoiceLine(null, qty, name, priceStr);
                        lines.add(line);
                    }
                    if (lines.isEmpty()) { System.out.println("La factura ha de tenir almenys 1 línia"); continue; }
                    String date = LocalDate.now().toString();
                    Invoice inv = invoiceSvc.createInvoice(date, dni, lines, configSvc.getIva());
                    InvoiceService.Totals totals = invoiceSvc.calculateTotals(lines, configSvc.getIva());
                    System.out.println("Factura creada: " + inv.getId());
                    System.out.println("Base: " + totals.base + "  IVA: " + totals.iva + "  Total: " + totals.total);
                } catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
            } else if (opt.equals("2")) {
                List<Invoice> all = invoiceSvc.listAll();
                for (Invoice i : all) System.out.println(i);
            } else if (opt.equals("3")) {
                System.out.print("Id factura: "); String id = sc.nextLine().trim();
                Invoice i = invoiceSvc.find(id);
                if (i == null) { System.out.println("Factura no trobada"); continue; }
                System.out.println(i);
                List<InvoiceLine> lines = invoiceSvc.findLines(id);
                for (InvoiceLine l : lines) System.out.println(l);
                InvoiceService.Totals totals = invoiceSvc.calculateTotals(lines, i.getIva());
                System.out.println("Base: " + totals.base + "  IVA: " + totals.iva + "  Total: " + totals.total);
            } else if (opt.equals("4")) return; else System.out.println("Opció no vàlida");
        }
    }

    private void manageConfig(Scanner sc) {
        System.out.println("IVA actual: " + configSvc.getIva() + "%");
        System.out.print("Nou IVA (sense canvi = Enter): ");
        String n = sc.nextLine().trim();
        if (n.isEmpty()) return;
        if (configSvc.setIva(n)) System.out.println("IVA actualitzat a " + n + "%"); else System.out.println("Valor d'IVA invàlid");
    }
}
