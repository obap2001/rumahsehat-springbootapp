import 'dart:convert';

class PasienModel {
  String? uuid;
  String? nama;
  String? role;
  String? username;
  String? password;
  String? email;
  bool? isSso;
  int? saldo;
  int? umur;

  PasienModel(
      {this.uuid,
        this.nama,
        this.role,
        this.username,
        this.password,
        this.email,
        this.isSso,
        this.saldo,
        this.umur});

  PasienModel.fromJson(Map<String, dynamic> json) {
    uuid = json['uuid'];
    nama = json['nama'];
    role = json['role'];
    username = json['username'];
    password = json['password'];
    email = json['email'];
    isSso = json['isSso'];
    saldo = json['saldo'];
    umur = json['umur'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['uuid'] = this.uuid;
    data['nama'] = this.nama;
    data['role'] = this.role;
    data['username'] = this.username;
    data['password'] = this.password;
    data['email'] = this.email;
    data['isSso'] = this.isSso;
    data['saldo'] = this.saldo;
    data['umur'] = this.umur;
    return data;
  }
}