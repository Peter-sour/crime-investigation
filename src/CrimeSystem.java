import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;

public class CrimeSystem {
    private List<Detective> detectives;
    private List<Case> cases;

    public CrimeSystem() {
        detectives = new ArrayList<>();
        cases = new ArrayList<>();
    }

    public Case createCase(String title) {
        Case c = new Case("C" + (cases.size() + 1), title);
        cases.add(c);
        return c;
    }

    public void assignDetective(Detective d, Case c) {
        d.addCase(c);
    }

    public void promoteDetective(Detective d) {
        d.promoteRank();
    }

    public void closeCase(Case c) {
        c.closeCase();
    }

    public void arrestSuspect(Suspect s) {
        s.arrest();
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        for (Case c : cases) {
            sb.append(c.getDetails()).append("\n");
        }
        sb.append("=== Detective Info ===\n");
        for (Detective d : detectives) {
            sb.append(d.getInfo()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CrimeSystem system = new CrimeSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Crime System Menu ---");
            System.out.println("1. Tambah Detective");
            System.out.println("2. Buat Case");
            System.out.println("3. Tambah Suspect ke Case");
            System.out.println("4. Promote Detective");
            System.out.println("5. Close Case");
            System.out.println("6. Arrest Suspect");
            System.out.println("7. Generate Laporan");
            System.out.println("0. Exit");
            System.out.print("Pilih menu: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // Tambah Detective
                    System.out.print("Nama Detective: ");
                    String name = sc.nextLine();
                    System.out.print("Rank Detective: ");
                    String rank = sc.nextLine();
                    Detective detective = new Detective(name, rank);
                    system.detectives.add(detective);
                    System.out.println("Detective berhasil ditambahkan!");
                    break;

                case 2: // Buat Case
                    System.out.print("Judul Case: ");
                    String title = sc.nextLine();
                    Case c = system.createCase(title);
                    System.out.println("Case berhasil dibuat dengan ID: " + c.getTitle());
                    break;

                case 3: // Tambah Suspect
                    if (system.cases.isEmpty()) {
                        System.out.println("Tidak ada case, buat case dulu!");
                        break;
                    }
                    System.out.println("=== Daftar Case ===");
                    for (int i = 0; i < system.cases.size(); i++) {
                        Case ca = system.cases.get(i);
                        System.out.println((i + 1) + ". " + ca.getTitle() + " (Status: " + ca.getStatus() + ")");
                    }
                    System.out.print("Pilih Case ID: ");
                    int caseIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    if (caseIndex < 0 || caseIndex >= system.cases.size()) {
                        System.out.println("Case tidak valid!");
                        break;
                    }
                    Case selectedCase = system.cases.get(caseIndex);

                    if (selectedCase.getStatus().equalsIgnoreCase("Closed")) {
                        System.out.println("Case sudah closed! Tidak bisa tambah suspect.");
                        break;
                    }

                    System.out.print("Nama Suspect: ");
                    String sName = sc.nextLine();
                    System.out.print("Usia Suspect: ");
                    int sAge = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Alibi Suspect: ");
                    String sAlibi = sc.nextLine();
                    Suspect suspect = new Suspect(sName, sAge, sAlibi);
                    selectedCase.addPerson(suspect);
                    System.out.println("Suspect berhasil ditambahkan!");
                    break;

                case 4: // Promote Detective
                    if (system.detectives.isEmpty()) {
                        System.out.println("Tidak ada detective!");
                        break;
                    }
                    System.out.println("=== Daftar Detective ===");
                    for (int i = 0; i < system.detectives.size(); i++) {
                        Detective d = system.detectives.get(i);
                        System.out.println((i + 1) + ". " + d.getName() + " (" + d.getInfo() + ")");
                    }
                    System.out.print("Pilih Detective ID: ");
                    int dIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    if (dIndex < 0 || dIndex >= system.detectives.size()) {
                        System.out.println("Detective tidak valid!");
                        break;
                    }
                    system.promoteDetective(system.detectives.get(dIndex));
                    System.out.println("Detective berhasil dipromote!");
                    break;

                case 5: // Close Case
                    if (system.cases.isEmpty()) {
                        System.out.println("Tidak ada case!");
                        break;
                    }
                    System.out.println("=== Daftar Case ===");
                    for (int i = 0; i < system.cases.size(); i++) {
                        Case ca = system.cases.get(i);
                        System.out.println((i + 1) + ". " + ca.getTitle() + " (Status: " + ca.getStatus() + ")");
                    }
                    System.out.print("Pilih Case ID: ");
                    int closeIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    if (closeIndex < 0 || closeIndex >= system.cases.size()) {
                        System.out.println("Case tidak valid!");
                        break;
                    }
                    system.closeCase(system.cases.get(closeIndex));
                    System.out.println("Case berhasil di-close!");
                    break;

                case 6: // Arrest Suspect
                    if (system.cases.isEmpty()) {
                        System.out.println("Tidak ada case!");
                        break;
                    }
                    System.out.println("=== Daftar Case ===");
                    for (int i = 0; i < system.cases.size(); i++) {
                        Case ca = system.cases.get(i);
                        System.out.println((i + 1) + ". " + ca.getTitle() + " (Status: " + ca.getStatus() + ")");
                    }
                    System.out.print("Pilih Case ID: ");
                    int arrestCaseIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    if (arrestCaseIndex < 0 || arrestCaseIndex >= system.cases.size()) {
                        System.out.println("Case tidak valid!");
                        break;
                    }
                    Case arrestCase = system.cases.get(arrestCaseIndex);

                    List<Suspect> suspects = new ArrayList<>();
                    for (Person p : arrestCase.getPeople()) {
                        if (p instanceof Suspect) suspects.add((Suspect) p);
                    }
                    if (suspects.isEmpty()) {
                        System.out.println("Tidak ada suspect di case ini!");
                        break;
                    }
                    System.out.println("=== Daftar Suspect ===");
                    for (int i = 0; i < suspects.size(); i++) {
                        Suspect s = suspects.get(i);
                        System.out.println((i + 1) + ". " + s.getPersonalInfo());
                    }
                    System.out.print("Pilih Suspect ID untuk di-arrest: ");
                    int sIndex = sc.nextInt() - 1;
                    sc.nextLine();
                    if (sIndex < 0 || sIndex >= suspects.size()) {
                        System.out.println("Suspect tidak valid!");
                        break;
                    }
                    system.arrestSuspect(suspects.get(sIndex));
                    System.out.println("Suspect berhasil di-arrest!");
                    break;

                case 7: // Generate Laporan
                    System.out.println("\n=== Laporan Kasus ===");
                    System.out.println(system.generateReport());
                    break;

                case 0: // Exit
                    System.out.println("Keluar dari program...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}
