import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/model/PasienModel.dart';
import 'package:rumah_sehat_flutter/screens/top_up_saldo.dart';
import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert';

import '../model/AppointmentModel.dart';


class ListAppointments extends StatefulWidget {
  @override
  _ListAppointmentPage createState() => _ListAppointmentPage();

}

class _ListAppointmentPage extends State<ListAppointments> {
  Future<AppointmentModel?> fetchAppointment() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var token = sharedPreferences.getString("token");
    String url =
        "http://apap-078.cs.ui.ac.id/api/appointment/list-appointment";
    var headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token',
      "Access-Control-Allow-Origin":
      "*",
    };
    print(token);

    final response = await http.get(Uri.parse(url), headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer $token',
    });
    print('Token : ${token}');
    print(response);

    var data = jsonDecode(response.body);

    AppointmentModel appointment = AppointmentModel.fromJson(data);

    if (response.statusCode == 200) {
      return appointment;
    } else
      return null;
  }


  @override
  Widget build(BuildContext context) {

    return Scaffold(
        backgroundColor: Colors.grey[900],
        body: Container(
          child: FutureBuilder(
            future: fetchAppointment(),
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              if (snapshot.data == null) {
                return Container(
                    child: Center(child: Icon(Icons.error))
                );
              }
              else {
                return Container(
                    padding: EdgeInsets.fromLTRB(30, 40, 30, 0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Text(
                          'Nama Dokter',
                          style: TextStyle(
                              color: Colors.redAccent[200],
                              letterSpacing: 2
                          ),
                        ),
                        SizedBox(height: 10),
                        Text(
                          snapshot.data.dokter.nama,
                          style: TextStyle(
                              color: Colors.greenAccent[200],
                              letterSpacing: 2,
                              fontSize: 28,
                              fontWeight: FontWeight.bold
                          ),
                        ),


                        SizedBox(height: 30),
                        Text(
                          'Nama Pasien',
                          style: TextStyle(
                              color: Colors.redAccent[200],
                              letterSpacing: 2
                          ),
                        ),
                        SizedBox(height: 10),
                        Text(
                          snapshot.data.pasien.nama,
                          style: TextStyle(
                              color: Colors.greenAccent[200],
                              letterSpacing: 2,
                              fontSize: 28,
                              fontWeight: FontWeight.bold
                          ),
                        ),


                        SizedBox(height: 30),
                        Text(
                          'Waktu Awal',
                          style: TextStyle(
                              color: Colors.redAccent[200],
                              letterSpacing: 2
                          ),
                        ),
                        SizedBox(height: 10),
                        Text(
                          snapshot.data.waktuAwal.toString(),
                          style: TextStyle(
                              color: Colors.greenAccent[200],
                              fontSize: 18,
                              letterSpacing: 1
                          ),
                        ),


                        SizedBox(height: 30),
                        Text(
                          'Status',
                          style: TextStyle(
                              color: Colors.redAccent[200],
                              letterSpacing: 2
                          ),
                        ),
                        SizedBox(height: 10),
                        Text(
                          snapshot.data.isDone.toString(),
                          style: TextStyle(
                              color: Colors.greenAccent[200],
                              fontSize: 18,
                              letterSpacing: 1
                          ),
                        ),

                        SizedBox(height: 30),
                        TextButton(
                          style:
                          TextButton.styleFrom(
                              backgroundColor: Colors.amberAccent[200]),
                          onPressed: () {
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => ListAppointments(),)
                            );
                          },
                          child: Text(
                            "Detail",
                            style: TextStyle(
                                color: Colors.redAccent[200],
                                letterSpacing: 2
                            ),
                          ),
                        ),
                      ],
                    )
                );
              }
            },
          ),
        )
    );
  }
}