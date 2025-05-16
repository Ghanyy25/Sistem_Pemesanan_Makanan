import Model.Menu;
import Model.Order;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.NumberFormat;
import java.util.Locale;


public class MainGui {
    private static List<Menu> daftarMenu = new ArrayList<>();
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static JFrame frame;
    private static JTextArea customerMenuArea = new JTextArea();
    private static JTextArea managerMenuArea = new JTextArea();

    public static void main(String[] args) {
        
        bacaMenuDariFile();
        customerMenuArea.setFont(new Font("Monospaced", Font.PLAIN, 16)); 
        managerMenuArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

       
        if (daftarMenu.isEmpty()) {
            daftarMenu.add(new Menu("Nasi Goreng", 15000));
            daftarMenu.add(new Menu("Es Teh", 5000));
            daftarMenu.add(new Menu("Ayam Bakar", 20000));
            simpanMenuKeFile(); 
        }

        frame = new JFrame("Sistem Pemesanan Makanan");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(menuUtamaPanel(), "menuUtama");
        mainPanel.add(customerPanel(), "customer");
        mainPanel.add(managerPanel(), "manager");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel menuUtamaPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel label = new JLabel("Pilih Peran:", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnCustomer = new JButton("Customer");
        btnCustomer.setFont(new Font("Arial", Font.PLAIN, 20)); 
        JButton btnManager = new JButton("Manager");
        btnManager.setFont(new Font("Arial", Font.PLAIN, 20));

        btnCustomer.addActionListener(e -> {
            updateMenuText(customerMenuArea);
            cardLayout.show(mainPanel, "customer");
        });

        btnManager.addActionListener(e -> {
            JPanel loginPanel = new JPanel(new GridLayout(2, 2));
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();

            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(usernameField);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(frame, loginPanel,
                    "Login Manager", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    updateMenuText(managerMenuArea);
                    cardLayout.show(mainPanel, "manager");
                } else {
                    JOptionPane.showMessageDialog(frame, "Login gagal. Username atau password salah.");
                }
            }
        });

        panel.add(label);
        panel.add(btnCustomer);
        panel.add(btnManager);
        return panel;
    }


    private static JPanel customerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        customerMenuArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(customerMenuArea);

        JTextField inputId = new JTextField();
        JButton btnPesan = new JButton("Masukkan ke Pesanan");
        JButton btnPreview = new JButton("Preview Pesanan");
        JButton btnBatal = new JButton("Batal Pesan");
        JButton btnSelesai = new JButton("Pesan");
        JButton btnBack = new JButton("Kembali");

        List<Menu> cart = new ArrayList<>();

        btnPesan.addActionListener(e -> {
            try {
                int nomor = Integer.parseInt(inputId.getText());
                int index = nomor - 1;
                if (index >= 0 && index < daftarMenu.size()) {
                    Menu item = daftarMenu.get(index);
                    cart.add(item);
                    JOptionPane.showMessageDialog(frame, item.getNama() + " ditambahkan.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Nomor menu tidak valid.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan nomor menu yang valid.");
            }
        });

        btnPreview.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Tidak ada pesanan.");
                return;
            }
            StringBuilder preview = new StringBuilder("Pesanan saat ini:\n");
            for (Menu item : cart) {
                preview.append("- ").append(item.getNama()).append(" (Rp").append(item.getHarga()).append(")\n");
            }
            int opsi = JOptionPane.showConfirmDialog(frame, preview.toString() + "\nApakah ingin membatalkan pesanan?", 
                                            "Preview Pesanan", JOptionPane.YES_NO_OPTION);
            if (opsi == JOptionPane.YES_OPTION) {
                cart.clear();
                JOptionPane.showMessageDialog(frame, "Pesanan dibatalkan.");
            }
        });

        btnSelesai.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Anda belum memesan apapun.");
                return;
            }

            String nama = JOptionPane.showInputDialog("Nama Anda:");
            if (nama != null && !nama.isEmpty()) {
                NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

                Map<String, Integer> jumlahPerMenu = new LinkedHashMap<>();
                Map<String, Double> hargaPerMenu = new HashMap<>();

                for (Menu item : cart) {
                    String itemNama = item.getNama();
                    jumlahPerMenu.put(itemNama, jumlahPerMenu.getOrDefault(itemNama, 0) + 1);
                    hargaPerMenu.put(itemNama, item.getHarga());
                }

                double total = 0;
                StringBuilder struk = new StringBuilder("--- Struk Pemesanan ---\n");
                struk.append("Customer: ").append(nama).append("\n\n");
                struk.append(String.format("%-5s %-20s %15s\n", "Qty", "Item", "Subtotal"));
                struk.append("==============================================\n");

                for (String itemNama : jumlahPerMenu.keySet()) {
                    int qty = jumlahPerMenu.get(itemNama);
                    double harga = hargaPerMenu.get(itemNama);
                    double subtotal = harga * qty;
                    total += subtotal;
                    struk.append(String.format("%-5d %-20s %15s\n", qty, itemNama, rupiahFormat.format(subtotal)));
                }

                struk.append("==============================================\n");
                struk.append(String.format("%-25s %15s\n", "Total", rupiahFormat.format(total)));
                struk.append("------------------------");

                JOptionPane.showMessageDialog(frame, struk.toString());
                cart.clear();
                }
            });



            btnBack.addActionListener(e -> cardLayout.show(mainPanel, "menuUtama"));

            JPanel inputPanel = new JPanel(new GridLayout(2, 1));
            inputPanel.add(new JLabel("Masukkan ID Menu:"));
            inputPanel.add(inputId);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(btnPesan);
            buttonPanel.add(btnPreview);
            buttonPanel.add(btnSelesai);
            buttonPanel.add(btnBack);

            panel.add(scroll, BorderLayout.CENTER);
            panel.add(inputPanel, BorderLayout.NORTH);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            return panel;
            }

    // Panel manager
    private static JPanel managerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        managerMenuArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(managerMenuArea);

        JButton btnTambah = new JButton("Tambah Menu");
        JButton btnHapus = new JButton("Hapus Menu");
        JButton btnBack = new JButton("Kembali");

        btnTambah.addActionListener(e -> {
            String nama = JOptionPane.showInputDialog("Nama menu:");
            String hargaStr = JOptionPane.showInputDialog("Harga:");
            try {
                double harga = Double.parseDouble(hargaStr);
                daftarMenu.add(new Menu(nama, harga));
                updateMenuText(managerMenuArea);
                simpanMenuKeFile();  
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga tidak valid.");
            }
        });

        btnHapus.addActionListener(e -> {
            String nomorStr = JOptionPane.showInputDialog("Nomor menu yang dihapus:");
            try {
                int nomor = Integer.parseInt(nomorStr);
                int index = nomor - 1;
                if (index >= 0 && index < daftarMenu.size()) {
                    daftarMenu.remove(index);
                    updateMenuText(managerMenuArea);
                    simpanMenuKeFile(); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Nomor tidak ditemukan.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Nomor tidak valid.");
            }
        });

        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "menuUtama"));

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnTambah);
        btnPanel.add(btnHapus);
        btnPanel.add(btnBack);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private static void updateMenuText(JTextArea area) {
        StringBuilder sb = new StringBuilder("--- Menu ---\n");
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu item = daftarMenu.get(i);
            sb.append((i + 1)).append(". ").append(item).append("\n");
        }
        area.setText(sb.toString());
    }

    private static void simpanMenuKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt"))) {
            for (Menu item : daftarMenu) {
                writer.write(item.getNama() + "," + item.getHarga());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Gagal menyimpan menu ke file.");
        }
    }

    private static void bacaMenuDariFile() {
        daftarMenu.clear();
        File file = new File("menu.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nama = parts[0];
                    double harga = Double.parseDouble(parts[1]);
                    daftarMenu.add(new Menu(nama, harga));
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Gagal membaca menu dari file.");
        }
    }
}
