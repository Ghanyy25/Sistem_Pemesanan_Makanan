import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private List<MenuItem> cart;

    public Customer(String username) {
        super(username);
        this.cart = new ArrayList<>();
    }

    public void lihatMenu(List<MenuItem> menu) {
        System.out.println("\n=== Daftar Menu ===");
        for (MenuItem item : menu) {
            System.out.println(item.getId() + ". " + item.getNama() + " - Rp" + item.getHarga());
        }
    }

    public void pesan(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            lihatMenu(menu);
            System.out.print("\nMasukkan ID menu yang ingin dipesan (0 untuk selesai): ");
            int id = scanner.nextInt();
            if (id == 0) break;

            boolean found = false;
            for (MenuItem item : menu) {
                if (item.getId() == id) {
                    cart.add(item);
                    System.out.println(item.getNama() + " ditambahkan ke keranjang.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Menu dengan ID tersebut tidak ditemukan.");
            }
        }

        System.out.println("\n=== Keranjang Anda ===");
        for (MenuItem item : cart) {
            System.out.println("- " + item.getNama() + " - Rp" + item.getHarga());
        }
    }

    public List<MenuItem> getCart() {
        return cart;
    }
}
