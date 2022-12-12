class TagihanModel {
  String? tanggalBayar;
  int? jumlahTagihan;
  String? kodeAppointment;
  String? kode;
  String? tanggalTerbuat;
  bool? isPaid;

  TagihanModel(
      {this.tanggalBayar,
      this.jumlahTagihan,
      this.kodeAppointment,
      this.kode,
      this.tanggalTerbuat,
      this.isPaid});

  TagihanModel.fromJson(Map<String, dynamic> json) {
    tanggalBayar = json['tanggal_bayar'];
    jumlahTagihan = json['jumlah_tagihan'];
    kodeAppointment = json['kode_appointment'];
    kode = json['kode'];
    tanggalTerbuat = json['tanggal_terbuat'];
    isPaid = json['is_paid'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['tanggal_bayar'] = this.tanggalBayar;
    data['jumlah_tagihan'] = this.jumlahTagihan;
    data['kode_appointment'] = this.kodeAppointment;
    data['kode'] = this.kode;
    data['tanggal_terbuat'] = this.tanggalTerbuat;
    data['is_paid'] = this.isPaid;
    return data;
  }
}
