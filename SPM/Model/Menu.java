package Model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Menu {
    private static int nextId = 1;
    private int id;
    private String nama;
    private double harga;

    public Menu(String nama, double harga) {
        this.id = nextId++; 
        this.nama = nama;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols();
        simbol.setGroupingSeparator('.');
        simbol.setDecimalSeparator(','); 

        DecimalFormat formatter = new DecimalFormat("#,###", simbol);
        String hargaFormat = formatter.format(harga);

    return nama + " - Rp" + hargaFormat;
    }
}

