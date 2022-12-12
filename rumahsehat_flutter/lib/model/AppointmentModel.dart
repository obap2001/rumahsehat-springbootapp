import 'dart:convert';

AppointmentModel appointmentModelJson(String str) =>
    AppointmentModel.fromJson(json.decode(str));

String appointmentModelToJson(AppointmentModel data) => json.encode(data.toJson());

class AppointmentModel {
  String kode;
  bool isDone;
  DateTime waktuAwal;

  AppointmentModel({required this.kode, required this.isDone, required this.waktuAwal});

  factory AppointmentModel.fromJson(Map<String, dynamic> json) => AppointmentModel(
      kode: json["kode"], isDone: json["role"], waktuAwal: json['waktuAwal']);

  Map<String, dynamic> toJson() => {
    "kode": kode,
    "isDone": isDone,
    "waktuAwal": waktuAwal
  };

  String get kodeAppointment => kode;

  bool get isDoneAppointment => isDone;

  DateTime get waktuAppointment => waktuAwal;

}