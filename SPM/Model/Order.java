package Model;

import java.util.*;

public class Order {
    private String namaCustomer;
    private List<Menu> items;
    private double totalHarga;

    public Order(String namaCustomer, List<Menu> items) {
        this.namaCustomer = namaCustomer;
        this.items = new ArrayList<>(items);
        hitungTotalHarga();
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void hitungTotalHarga() {
        totalHarga = 0;
        for (Menu item : items) {
            totalHarga += item.getHarga();
        }
    }

    public void printStruk() {
    System.out.println("\n--- Struk Pemesanan ---");
    System.out.println("Customer: " + namaCustomer);

    Map<String, Integer> jumlahPerMenu = new LinkedHashMap<>();
    Map<String, Double> hargaPerMenu = new HashMap<>();

    for (Menu item : items) {
        String nama = item.getNama();
        jumlahPerMenu.put(nama, jumlahPerMenu.getOrDefault(nama, 0) + 1);
        hargaPerMenu.put(nama, item.getHarga());
    }

    for (String nama : jumlahPerMenu.keySet()) {
        int qty = jumlahPerMenu.get(nama);
        double hargaSatuan = hargaPerMenu.get(nama);
        System.out.println("- " + nama + " x" + qty + " (Rp" + hargaSatuan + " each) = Rp" + (hargaSatuan * qty));
    }

    System.out.println("Total Harga: Rp" + totalHarga);
    System.out.println("------------------------");
}

}


