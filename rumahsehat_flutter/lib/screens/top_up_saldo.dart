import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/screens/lihat_profile.dart';
import 'package:rumah_sehat_flutter/screens/home.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;

class topUpSaldo extends StatefulWidget{
  @override
  State<StatefulWidget> createState(){
    return topUpSaldoState();
  }
}

class topUpSaldoState extends State<topUpSaldo>{
  final minimumPadding = 5.0;
  TextEditingController firstController = TextEditingController();
  TextEditingController lastController = TextEditingController();
  @override
  Widget build(BuildContext context){
    TextStyle? textStyle = Theme.of(context).textTheme.subtitle2;
    final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

    return Scaffold(
      appBar: AppBar(
        title: Text('Top Up Saldo'),

      ),
      body: Form(
        key: _formKey,
        child: Padding(
          padding: EdgeInsets.all(minimumPadding*2),
          child: ListView(
            children: <Widget>[
              Padding(padding: EdgeInsets.only(top: minimumPadding, bottom: minimumPadding),
                child: TextFormField(
                  style: textStyle,
                  controller: lastController,
                  validator: (String? value){
                    if(value!.isEmpty){
                      return 'please enter the top up nominal';
                    }else{
                      showConfirmDialog(context, value);
                    }
                  },
                  decoration: InputDecoration(
                    labelText: 'Nominal Top Up',
                    hintText: 'enter the top up nominal',
                    labelStyle: textStyle,
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(5.0))),
              )),
             ElevatedButton(onPressed: (){
               if (_formKey.currentState!.validate()) {
                 MaterialPageRoute(builder: (BuildContext context) => RumahSehatHome());
               }
               MaterialPageRoute(builder: (BuildContext context) => ProfilPasienPage());
             },
                 child: Text('Top Up'))
            ]
          ),
        ),
      ),
    );
  }
  Future<http.Response> topUp(context, String saldo) async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var token = sharedPreferences.getString("token");
    String url =
        "http://apap-078.cs.ui.ac.id/api/pasien/topup-saldo";
    Map data = {
      'saldo': saldo
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
      showSuccess(context, saldo);
    }
    print(data);
    print("${res.statusCode}");
    print("${res.body}");
    return res;
  }

  showConfirmDialog(context, String saldo){
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
        topUp(context, saldo);

      },
    );

    // set up the AlertDialog
    AlertDialog alert = AlertDialog(
      content: Text("Apakah Anda yakin ingin melakukan TopUp sebesar Rp" + saldo+ "?"),
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
      content: Text("Selamat, Top Up Saldo Anda Sukses!"),
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
