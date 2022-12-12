import 'package:flutter/material.dart';
//import './editdata.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/screens/list_appointment.dart';

class DetailAppointment extends StatefulWidget {
  String kode;
  DetailAppointment({super.key, required this.kode});

  @override
  _DetailAppointmentState createState() => _DetailAppointmentState();
}

class _DetailAppointmentState extends State<DetailAppointment> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Detail Appointment")),
      body: Container(
        height: 270.0,
        padding: const EdgeInsets.all(20.0),
        child: Card(
          child: Center(
            child: Column(
              children: <Widget>[


                ElevatedButton(
                  child: const Text("Kembali"),
                  //color: Colors.red,
                  onPressed: ()=> ListAppointments(),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}