package Model;

import java.util.*;

public class Customer extends User {
    private List<Menu> cart;

    public Customer(String username) {
        super(username);
        cart = new ArrayList<>();
    }

    public void lihatMenu(List<Menu> menu) {
        System.out.println("\n--- Daftar Menu ---");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }

    public void pesan(List<Menu> menu) {
    Scanner scanner = new Scanner(System.in);
    int pilih;
    do {
        System.out.print("Masukkan nomor menu yang ingin dipesan (0 untuk selesai): ");
        pilih = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (pilih >= 0 && pilih < menu.size()) {
            cart.add(menu.get(pilih));
            System.out.println(menu.get(pilih).getNama() + " ditambahkan ke pesanan.");
        } else if (pilih != -1) {
            System.out.println("Nomor tidak valid.");
        }
    } while (pilih >= 0);

    if (cart.isEmpty()) {
        System.out.println("Tidak ada pesanan yang dibuat.");
        return;
    }

    System.out.println("\nPesanan Anda:");
    for (int i = 0; i < cart.size(); i++) {
        System.out.println((i + 1) + ". " + cart.get(i));
    }

    System.out.println("\nPilih opsi:");
    System.out.println("1. Batal pesan");
    System.out.println("2. Lanjut pesan");

    int opsi;
    do {
        System.out.print("Masukkan pilihan (1/2): ");
        opsi = scanner.nextInt();
        scanner.nextLine();
        if (opsi != 1 && opsi != 2) {
            System.out.println("Pilihan tidak valid, coba lagi.");
        }
    } while (opsi != 1 && opsi != 2);

    if (opsi == 1) {
        cart.clear();
        System.out.println("Pesanan dibatalkan.");
    } else {
        Order order = new Order(username, cart);
        order.printStruk();
    }
}

}