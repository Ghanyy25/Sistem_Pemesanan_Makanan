## 🍽️ Sistem Pemesanan Makanan - Java OOP

Sistem ini merupakan implementasi program pemesanan makanan sederhana berbasis console menggunakan konsep **Pemrograman Berorientasi Objek (OOP)** dalam bahasa Java. Program ini memungkinkan seorang **Manager** untuk mengelola menu makanan, dan seorang **Customer** untuk melihat dan memesan makanan.

---

### 📁 Struktur Package dan Class

```
Model/
│
├── User.java           // Abstract class induk untuk Manager & Customer
├── Manager.java        // Class untuk pengguna bertipe Manager
├── Customer.java       // Class untuk pengguna bertipe Customer
├── Menu.java           // Representasi objek menu makanan
├── Order.java          // Menyimpan data pesanan customer
```

---

### 🧠 Konsep OOP yang Digunakan

* **Abstraksi**: `User` adalah abstract class yang menjadi superclass dari `Manager` dan `Customer`.
* **Inheritance**: `Manager` dan `Customer` mewarisi dari `User`.
* **Encapsulation**: Atribut-atribut dibuat private/protected dengan getter/setter.
* **Polymorphism**: Pemanfaatan method overriding dalam class turunan.

---

### 👨‍🍳 Fitur Manager

Manager dapat:

1. Menambahkan menu baru
2. Mengedit nama atau harga menu yang sudah ada
3. Menghapus menu
4. Melihat seluruh daftar menu

**Password default untuk manager**: `1234`

---

### 👤 Fitur Customer

Customer dapat:

1. Melihat daftar menu makanan
2. Memesan beberapa item sekaligus
3. Melihat struk pesanan lengkap dengan total harga
4. Membatalkan pesanan jika diperlukan

---

### 🧾 Contoh Output Struk

```
--- Struk Pemesanan ---
Customer: Budi

- Nasi Goreng x2 (Rp12000 each) = Rp24000
- Teh Manis x1 (Rp5000 each) = Rp5000
Total Harga: Rp29000
------------------------
```

---

### 🔧 Cara Menjalankan Program

1. Pastikan JDK telah terinstall (Java 8+).
2. Compile semua file di folder `Model/`:

   ```bash
   javac Model/*.java
   ```
3. Buatlah class `Main` di luar folder `Model` untuk menjalankan program. Contoh:

```java
import Model.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Menu> menuList = new ArrayList<>();
        Manager manager = new Manager("admin");
        Customer customer = new Customer("user");

        int pilihan;
        do {
            System.out.println("\n=== Sistem Pemesanan Makanan ===");
            System.out.println("1. Login sebagai Manager");
            System.out.println("2. Login sebagai Customer");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan password manager: ");
                    scanner.nextLine();
                    String pass = scanner.nextLine();
                    if (pass.equals(manager.pass)) {
                        manager.menuManager(menuList);
                    } else {
                        System.out.println("Password salah!");
                    }
                    break;
                case 2:
                    customer.lihatMenu(menuList);
                    customer.pesan(menuList);
                    break;
            }
        } while (pilihan != 0);

        System.out.println("Terima kasih telah menggunakan sistem!");
    }
}
```

### 👨‍💻 Dibuat Oleh

* **Kelompok 7**
* **Prodi**: \Sistem Informasi
* **Mata Kuliah**: Pemrograman Berorientasi Objek
* **Anggota**: \-Bismillah Ghaniyyu Putra D
                -Hilmy Affayad Akbar
                -Moch. Syech Yusuf. M
                -Maisyah Mahdiyyah
