import java.util.Scanner;

// Parent class
class Barang {
    // Properti Barang
    protected String kodeBarang;  // Properti yang bisa diakses oleh subclass
    protected String namaBarang;
    protected double hargaBarang;

    // Constructor Barang
    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }

    // Method untuk menampilkan informasi barang
    public void displayBarang() {
        System.out.println("Kode Barang: " + kodeBarang);
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Harga Barang: Rp " + hargaBarang);
    }
}

// Subclass (Child Class) - Menunjukkan INHERITANCE
class Penjualan extends Barang {
    // Properti tambahan untuk Penjualan
    private final String noFaktur;  // Properti baru di subclass
    private final int jumlahBeli;
    private final double total;

    // Constructor Penjualan
    public Penjualan(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        // Memanggil constructor dari parent class menggunakan `super`
        super(kodeBarang, namaBarang, hargaBarang);
        this.noFaktur = noFaktur;  // Inisialisasi properti subclass
        this.jumlahBeli = jumlahBeli;
        this.total = calculateTotal();  // Menghitung total langsung
    }

    // Method untuk menghitung total
    private double calculateTotal() {
        return hargaBarang * jumlahBeli;  // Menggunakan properti parent class
    }

    // Method untuk menampilkan data penjualan
    public void displayPenjualan() {
        System.out.println("\n--- Detail Penjualan ---");
        System.out.println("No Faktur: " + noFaktur);
        displayBarang(); // Memanggil method dari parent class (Inheritance)
        System.out.println("Jumlah Beli: " + jumlahBeli);
        System.out.println("Total: Rp " + total);
    }
}

// Custom Exception
class InvalidInputException extends Exception { // Menunjukkan CUSTOM EXCEPTION
    public InvalidInputException(String message) {
        super(message); // Mengirim pesan ke parent class Exception
    }
}

public class Main {
    public static void main(String[] args) {
        // Menggunakan try-with-resources untuk memastikan scanner ditutup otomatis
        try (Scanner scanner = new Scanner(System.in)) {
            // Input dan logika program
            System.out.print("Masukkan No Faktur: ");
            String noFaktur = scanner.nextLine();
        
            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang = scanner.nextLine();
        
            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = scanner.nextLine();
        
            System.out.print("Masukkan Harga Barang: ");
            double hargaBarang = scanner.nextDouble();
            // Exception Handling - Memastikan harga barang valid
            if (hargaBarang <= 0) {
                throw new InvalidInputException("Harga barang harus lebih dari 0!");
            }
        
            System.out.print("Masukkan Jumlah Beli: ");
            int jumlahBeli = scanner.nextInt();
            // Exception Handling - Memastikan jumlah beli valid
            if (jumlahBeli <= 0) {
                throw new InvalidInputException("Jumlah beli harus lebih dari 0!");
            }
        
            // Membuat objek Penjualan
            Penjualan penjualan = new Penjualan(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
            penjualan.displayPenjualan(); // Menampilkan data penjualan
        } catch (InvalidInputException e) { 
            // Menangkap custom exception
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Menangkap exception umum lainnya (contoh: kesalahan dalam input)
            System.out.println("Error: Harap masukkan data yang sesuai!");
        } finally {
            // Blok ini selalu dijalankan, terlepas ada atau tidaknya exception
            System.out.println("Selesai!");
        }
    }
}
