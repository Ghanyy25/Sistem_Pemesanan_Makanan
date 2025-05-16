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

    double totalHarga = 0;

    System.out.printf("%-5s %-20s %15s\n", "Qty", "Item", "Subtotal");
    System.out.println("==============================================");

    for (String nama : jumlahPerMenu.keySet()) {
        int qty = jumlahPerMenu.get(nama);
        double hargaSatuan = hargaPerMenu.get(nama);
        double subtotal = hargaSatuan * qty;
        totalHarga += subtotal;
        System.out.printf("%-5d %-20s Rp%12.2f\n", qty, nama, subtotal);
    }

    System.out.println("==============================================");
    System.out.printf("%-25s Rp%12.2f\n", "Total", totalHarga);
    System.out.println("------------------------");
}


}


