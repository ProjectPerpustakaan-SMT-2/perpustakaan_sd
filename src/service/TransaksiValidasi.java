package service;

import data.ComboItem;
import jakarta.validation.constraints.NotNull;

public class TransaksiValidasi {

    @NotNull(message = "Pilih Buku Yang Akan Di Pinjam")
    private ComboItem buku;

    public TransaksiValidasi(ComboItem buku) {
        this.buku = buku;
    }
}
