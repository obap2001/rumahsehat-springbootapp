import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/model/PasienModel.dart';
import 'package:rumah_sehat_flutter/screens/top_up_saldo.dart';
import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert';


class ProfilPasienPage extends StatefulWidget {
  @override
  _ProfilPasienPage createState() => _ProfilPasienPage();

}

class _ProfilPasienPage extends State<ProfilPasienPage> {
  Future<PasienModel?> fetchPasien() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    var token = sharedPreferences.getString("token");
    String url =
        "http://localhost:8080/api/pasien/data-pasien";
    var headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token',
      "Access-Control-Allow-Origin":
      "*",
    };
    print(token);
    // final response = await http.get(Uri.parse(url),
    //   headers: <String, String>{
    //     "Content-Type": "application/json",
    //     "Authorization": "Bearer $token"
    //   },
    // );
    final response = await http.get(Uri.parse(url), headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer $token',
    });
    print('Token : ${token}');
    print(response);
    // final response2 = await http.get(Uri.parse(url2), headers: headers);
    var data = jsonDecode(response.body);
    // data.addAll(jsonDecode(response2.body));

    PasienModel pasien = PasienModel.fromJson(data);

    if (response.statusCode == 200) {
      return pasien;
    } else
      return null;
  }


  @override
  Widget build(BuildContext context) {

    return Scaffold(
        appBar: AppBar(
          title: Text('Lihat Profile'),

        ),
        backgroundColor: Colors.white,
        body: Container(
          child: FutureBuilder(
            future: fetchPasien(),
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
                          'Nama Pasien',
                          style: TextStyle(
                              color: Colors.black87,
                              letterSpacing: 2,
                              fontSize: 14,
                              fontWeight: FontWeight.bold

                          ),
                        ),
                        SizedBox(height: 10),
                        Text(
                          snapshot.data.nama,
                          style: TextStyle(
                              color: Colors.greenAccent[400],
                              letterSpacing: 2,
                              fontSize: 20,
                              fontWeight: FontWeight.bold
                          ),
                        ),


                        SizedBox(height: 30),
                        Text(
                          'Username',
                          style: TextStyle(
                              color: Colors.black87,
                              letterSpacing: 2,
                              fontSize: 14,
                              fontWeight: FontWeight.bold
                          ),
                        ),
                        SizedBox(height: 10),
                        Text(
                          snapshot.data.username,
                          style: TextStyle(
                              color: Colors.greenAccent[400],
                              letterSpacing: 2,
                              fontSize: 20,
                              fontWeight: FontWeight.bold
                          ),
                        ),


                        SizedBox(height: 30),
                        Text(
                          'Email',
                          style: TextStyle(
                              color: Colors.black87,
                              letterSpacing: 2,
                              fontSize: 14,
                              fontWeight: FontWeight.bold
                          ),
                        ),
                        SizedBox(height: 10),
                        Text(
                          snapshot.data.email,
                          style: TextStyle(
                              color: Colors.greenAccent[400],
                              letterSpacing: 2,
                              fontSize: 20,
                              fontWeight: FontWeight.bold
                          ),
                        ),
                        SizedBox(height: 30),
                        Text(
                          'Umur',
                          style: TextStyle(
                              color: Colors.black87,
                              letterSpacing: 2,
                              fontSize: 14,
                              fontWeight: FontWeight.bold
                          ),
                        ),
                        SizedBox(height: 10),
                        Text(
                          snapshot.data.umur.toString() + ' tahun',
                          style: TextStyle(
                              color: Colors.greenAccent[400],
                              letterSpacing: 2,
                              fontSize: 20,
                              fontWeight: FontWeight.bold
                          ),
                        ),

                        SizedBox(height: 30),
                        Text(
                          'Saldo',
                          style: TextStyle(
                              color: Colors.black87,
                              letterSpacing: 2,
                              fontSize: 14,
                              fontWeight: FontWeight.bold
                          ),
                        ),
                        Row(
                          children: [
                            SizedBox(height: 10),
                            Text(
                              'Rp ' + snapshot.data.saldo.toString() +'  ',
                              style: TextStyle(
                                  color: Colors.greenAccent[400],
                                  letterSpacing: 2,
                                  fontSize: 20,
                                  fontWeight: FontWeight.bold
                              ),
                            ),
                            TextButton(
                              style:
                              TextButton.styleFrom(
                                  backgroundColor: Colors.blue),
                              onPressed: () {
                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                      builder: (context) => topUpSaldo(),)
                                );
                              },
                              child: Text(
                                "+Top Up",
                                style: TextStyle(
                                    color: Colors.white,
                                    letterSpacing: 2
                                ),
                              ),
                            ),
                          ],
                        )
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
