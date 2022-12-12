import 'dart:convert';

import 'package:rumah_sehat_flutter/model/DokterModel.dart';
import 'package:rumah_sehat_flutter/model/PasienModel.dart';

AppointmentModel appointmentModelJson(String str) =>
    AppointmentModel.fromJson(json.decode(str));

String appointmentModelToJson(AppointmentModel data) => json.encode(data.toJson());

class AppointmentModel {
  String kode;
  bool isDone;
  DateTime waktuAwal;
  String uuid_dokter;
  String uuid_pasien;

  AppointmentModel({required this.kode, required this.isDone, required this.waktuAwal, required this.uuid_dokter, required this.uuid_pasien});

  factory AppointmentModel.fromJson(Map<String, dynamic> json) => AppointmentModel(
      kode: json["kode"],
      isDone: json["role"],
      waktuAwal: json['waktuAwal'],
      uuid_dokter: json['uuid_dokter'],
      uuid_pasien: json['uuid_pasien']
  );

  Map<String, dynamic> toJson() => {
    "kode": kode,
    "isDone": isDone,
    "waktuAwal": waktuAwal,
    "uuid_dokter": uuid_dokter,
    "uuid_pasien": uuid_pasien
  };

  String get kodeAppointment => kode;

  bool get isDoneAppointment => isDone;

  DateTime get waktuAppointment => waktuAwal;

  String get uuidDokter => uuid_dokter;

  String get uuidPasien => uuid_pasien;

}