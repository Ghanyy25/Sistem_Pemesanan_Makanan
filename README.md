## 🍽️ Sistem Pemesanan Makanan - Java OOP

Sistem ini merupakan implementasi program pemesanan makanan sederhana berbasis console **(Main.java)** dan versi GUI **(MainGUI.java)** menggunakan konsep **Pemrograman Berorientasi Objek (OOP)** dalam bahasa Java.

---

### 📁 Struktur Folder & Kelas

```
Model/
├── User.java           // Abstract class untuk Manager & Customer
├── Manager.java        // Untuk mengelola menu
├── Customer.java       // Untuk melakukan pemesanan
├── Menu.java           // Representasi item makanan
├── Order.java          // Menyimpan dan mencetak data pesanan

Main.java               // Program berbasis teks
MainGUI.java            // Program berbasis GUI (Swing atau JavaFX)
```

---

### 📦 Penjelasan Tiap Kelas

| Kelas      | Deskripsi                                                                                                                   |
| ---------- | --------------------------------------------------------------------------------------------------------------------------- |
| `User`     | Kelas abstrak sebagai dasar dari `Manager` dan `Customer`, menyimpan `username`.                                            |
| `Manager`  | Turunan dari `User`, dapat menambah, mengedit, menghapus, dan melihat daftar menu. Memiliki password default `1234`.        |
| `Customer` | Turunan dari `User`, dapat melihat menu, memilih item ke keranjang (cart), dan melakukan pemesanan.                         |
| `Menu`     | Menyimpan data makanan/minuman: nama, harga, dan ID unik. Memformat harga dengan `DecimalFormat`.                           |
| `Order`    | Mewakili pesanan yang dibuat oleh customer. Menghitung total harga dan mencetak struk pembelian.                            |
| `Main`     | Program utama berbasis teks. Menyediakan interface untuk login sebagai Manager atau Customer.                               |
| `MainGUI`  | Versi GUI dari program menggunakan Java Swing/JavaFX (jika tersedia). Menyediakan tampilan visual untuk menu dan pemesanan. |

---

### 🧠 Konsep OOP yang Digunakan

* **Abstraksi**: `User` sebagai superclass abstract
* **Inheritance**: `Manager` dan `Customer` mewarisi dari `User`
* **Encapsulation**: Data disembunyikan dengan `private` dan diakses melalui getter/setter
* **Polymorphism**: Method diturunkan dan dimodifikasi di class turunan

---

### 👨‍🍳 Fitur Manager

* Tambah menu makanan
* Edit menu (nama dan harga)
* Hapus menu
* Lihat semua menu

> 💡 Default password Manager: `1234`

---

### 👤 Fitur Customer

* Lihat daftar menu
* Memesan beberapa makanan sekaligus
* Cetak struk pesanan
* Bisa batal atau lanjutkan pesanan

---

### 🧾 Contoh Struk

```
--- Struk Pemesanan ---
Customer: Andi

- Ayam Goreng x2 (Rp15.000 each) = Rp30.000
- Es Teh x1 (Rp5.000 each) = Rp5.000
Total Harga: Rp35.000
------------------------
```

---

### ▶️ Cara Menjalankan Program

#### ✅ Jalankan Versi Teks (Main.java)

1. **Compile semua file**:

   ```bash
   javac Model/*.java Main.java
   ```

2. **Jalankan program**:

   ```bash
   java Main
   ```

---

#### ✅ Jalankan Versi GUI (MainGUI.java)

1. **Pastikan GUI class sudah lengkap** (menggunakan Java Swing/JavaFX).

2. **Compile semua file**:

   ```bash
   javac Model/*.java MainGUI.java
   ```

3. **Jalankan GUI**:

   ```bash
   java MainGUI
   ```

> ⚠️ Jika kamu menggunakan JavaFX, tambahkan VM options:
> `--module-path PATH_TO_FX --add-modules javafx.controls,javafx.fxml`

---

## 👥 Pembagian Tugas - Kelompok 7

**Jumlah Anggota:** 4 Orang
**Nama Kelompok:** Kelompok 7

| No. | Nama      | Tugas                                                                                                                                                               |
| --- | --------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1.  | Yusuf | - Membuat class `MenuItem` dan `Order`  <br> - Menentukan atribut, constructor, method, dan format struk <br> - Menghitung total harga pemesanan                    |
| 2.  | Maisyah | - Membuat class abstrak `User`  <br> - Mengembangkan class `Customer` (inherit dari `User`) <br> - Menambahkan fitur lihat menu dan pemesanan makanan oleh customer |
| 3.  | Hilmy | - Membuat class `Manager` (inherit dari `User`) <br> - Menambahkan fitur tambah, edit, hapus, dan lihat menu <br> - Menyusun menu interaktif untuk manager          |
| 4.  | Ghani     | - Membuat class `Main` sebagai menu utama <br> - Menyusun logika pemilihan peran (Customer / Manager) <br> - Integrasi seluruh class ke dalam satu alur sistem      |

---

### 👨‍💻 Dibuat Oleh

* **Kelompok 7**
* **Kelas / Prodi**: Kelas A / Sistem Informasi
* **Mata Kuliah**: Pemrograman Berorientasi Objek
* **Dosen**: Dr. Eng. Supri Bin Hj Amir, S.Si., M.Eng
* **Anggota**:  * Bismillah Ghaniyyu Putra D 
                * Hilmy Affayad Akbar 
                * Moch. Syech Yusuf. M
                * Maisyah Mahdiyyah
