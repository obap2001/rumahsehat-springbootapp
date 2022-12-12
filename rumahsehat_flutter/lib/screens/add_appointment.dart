import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/screens/lihat_profile.dart';
import 'package:rumah_sehat_flutter/screens/list_appointment.dart';
import 'package:rumah_sehat_flutter/screens/home.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';

import '../model/AppointmentModel.dart';
import '../model/DokterModel.dart';

class createAppointment extends StatefulWidget{
  @override
  State<StatefulWidget> createState(){
    return createAppointmentState();
  }
}

class createAppointmentState extends State<createAppointment> {


  Future<DokterModel?> fetchDokter() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var token = sharedPreferences.getString("token");
    String url = "http://localhost:8080/api/dokter/data-dokter";
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

    DokterModel dokter = DokterModel.fromJson(data);

    if (response.statusCode == 200) {
      return dokter;
    } else
      return null;
  }


  final minimumPadding = 5.0;
  String _date = "Not set";
  String _time = "Not set";
  //Future<DokterModel?> listDokter = fetchDokter();

  TextEditingController dokterController = TextEditingController();
  TextEditingController waktuController = TextEditingController();

  late AppointmentModel appointment;
  @override
  Widget build(BuildContext context){
    TextStyle? textStyle = Theme.of(context).textTheme.subtitle2;
    final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

    return Scaffold(
      appBar: AppBar(
        title: Text('Buat Appointment'),

      ),
      body: Form(
        key: _formKey,
        child: Padding(
          padding: EdgeInsets.all(minimumPadding*2),
          child: ListView(
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      FutureBuilder(
                        future: fetchDokter(),
                        builder: (BuildContext context, AsyncSnapshot<DokterModel?> snapshot) {
                          if (snapshot.data == null) {
                            return Container(
                                child: Center(child: Icon(Icons.error))
                            );
                          }
                          else {
                            return Container(

                            );
                          }
                        },),
                    ],
                  )
                ),
                Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    mainAxisSize: MainAxisSize.max,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                      ElevatedButton(
                        // shape: RoundedRectangleBorder(
                        //     borderRadius: BorderRadius.circular(5.0)),
                        // elevation: 4.0,
                        onPressed: () {
                          DatePicker.showDatePicker(context,
                              theme: const DatePickerTheme(
                                containerHeight: 210.0,
                              ),
                              showTitleActions: true,
                              minTime: DateTime(2000, 1, 1),
                              maxTime: DateTime(2022, 12, 31), onConfirm: (date) {
                                print('confirm $date');
                                _date = '${date.year} - ${date.month} - ${date.day}';
                                setState(() {});
                              }, currentTime: DateTime.now(), locale: LocaleType.en);
                        },
                        //color: Colors.white,
                        child: Container(
                          alignment: Alignment.center,
                          height: 50.0,
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: <Widget>[
                              Row(
                                children: <Widget>[
                                  Row(
                                    children: <Widget>[
                                      const Icon(
                                        Icons.date_range,
                                        size: 18.0,
                                        color: Colors.white,
                                      ),
                                      Text(
                                        " $_date",
                                        style: const TextStyle(
                                            color: Colors.white,
                                            fontWeight: FontWeight.bold,
                                            fontSize: 18.0),
                                      ),
                                    ],
                                  )
                                ],
                              ),
                              const Text(
                                "  Change",
                                style: TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 18.0),
                              ),
                            ],
                          ),
                        ),
                      ),
                      const SizedBox(
                        height: 20.0,
                      ),
                      ElevatedButton(
                        // shape: RoundedRectangleBorder(
                        //     borderRadius: BorderRadius.circular(5.0)),
                        // elevation: 4.0,
                        onPressed: () {
                          DatePicker.showTimePicker(context,
                              theme: const DatePickerTheme(
                                containerHeight: 210.0,
                              ),
                              showTitleActions: true, onConfirm: (time) {
                                print('confirm $time');
                                _time = '${time.hour} : ${time.minute} : ${time.second}';
                                setState(() {});
                              }, currentTime: DateTime.now(), locale: LocaleType.en);
                          setState(() {});
                        },
                        //color: Colors.white,
                        child: Container(
                          alignment: Alignment.center,
                          height: 50.0,
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: <Widget>[
                              Row(
                                children: <Widget>[
                                  Container(
                                    child: Row(
                                      children: <Widget>[
                                        const Icon(
                                          Icons.access_time,
                                          size: 18.0,
                                          color: Colors.white,
                                        ),
                                        Text(
                                          " $_time",
                                          style: const TextStyle(
                                              color: Colors.white,
                                              fontWeight: FontWeight.bold,
                                              fontSize: 18.0),
                                        ),
                                      ],
                                    ),
                                  )
                                ],
                              ),
                              const Text(
                                "  Change",
                                style: TextStyle(
                                    color: Colors.white,
                                    fontWeight: FontWeight.bold,
                                    fontSize: 18.0),
                              ),
                            ],
                          ),
                        ),
                      )
                    ],
                  ),
                ),
                ElevatedButton(onPressed: (){
                  if (_formKey.currentState!.validate()) {
                    MaterialPageRoute(builder: (BuildContext context) => RumahSehatHome());
                  }
                  MaterialPageRoute(builder: (BuildContext context) => ListAppointments());
                },
                    child: Text('Buat Appointment'))
              ]
          ),
        ),
      ),
    );
  }
  Future<http.Response> createAppointment(context, String uuid_dokter, DateTime waktuAwal) async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var token = sharedPreferences.getString("token");
    String url =
        "http://localhost:8080/api/appointment/create";
    Map data = {
      'uuid_dokter': uuid_dokter,
      'waktuAwal': waktuAwal
    };
    var body = json.encode(data);
    var res = await http.post(
        Uri.parse(url),
        headers: <String, String>{
          "Content-Type": "application/json",
          "Authorization": "Bearer $token",
          "Access-Control-Allow-Origin": "*"
        },
        body: body
    );
    if (res.statusCode == 200){
      showSuccess(context, uuid_dokter);
    }
    print(data);
    print("${res.statusCode}");
    print("${res.body}");
    return res;
  }

  showConfirmDialog(context, String uuid_dokter, DateTime waktuAwal){
    // set up the buttons
    Widget cancelButton = TextButton(
      child: Text("Batal"),
      onPressed: () {
        Navigator.of(context, rootNavigator: true).pop();
      },
    );

    Widget continueButton = TextButton(
      child: Text("Konfirmasi"),
      onPressed: () {
        createAppointment(context, uuid_dokter, waktuAwal);

      },
    );

    // set up the AlertDialog
    AlertDialog alert = AlertDialog(
      content: Text("Apakah Anda yakin ingin membuat appointment"),
      actions: [
        cancelButton,
        continueButton,
      ],
    );

    // show the dialog
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }

  showSuccess(BuildContext context, String kode) {
    // set up the buttons
    Widget cancelButton = TextButton(
        child: Text("Kembali"),
        onPressed: () {
          Navigator.of(context, rootNavigator: true).pop();

          Navigator.of(context, rootNavigator: true).pop();
          Navigator.of(context).pushAndRemoveUntil(
              MaterialPageRoute(builder: (BuildContext context) => RumahSehatHome()),
                  (Route<dynamic> route) => false
          );
        }
    );

    // set up the AlertDialog
    AlertDialog alert = AlertDialog(
      content: Text("Selamat, Appointment berhasil dibuat!"),
      actions: [
        cancelButton,
      ],
    );

    // show the dialog
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }
}