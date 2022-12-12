import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/auth/register.dart';
import 'package:rumah_sehat_flutter/theme.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/screens/home.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:shared_preferences/shared_preferences.dart';


import '../screens/list-tagihan.dart';
import '../web/responses/login_response.dart';

const SERVER_IP = 'http://localhost:8080';
final storage = FlutterSecureStorage();

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  void displayDialog(context, title, text) => showDialog(
        context: context,
        builder: (context) =>
            AlertDialog(title: Text(title), content: Text(text)),
      );

  Future<String?> attemptLogIn(String username, String password) async {
    final msg = jsonEncode({"username": username, "password": password});
    var res = await http.post(Uri.parse("$SERVER_IP/api/auth/login/pasien"),
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin":
              "*", // Required for CORS support to work
          "Access-Control-Allow-Credentials":
              "true", // Required for cookies, authorization headers with HTTPS
          "Access-Control-Allow-Headers":
              "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        },
        body: msg);

    if (res.statusCode == 200) {
      final parsedJson = jsonDecode(res.body);
      final loginResponse = LoginResponse.fromJson(parsedJson);

      return loginResponse.token;
    }
    return null;
  }

  bool passwordVisible = false;
  void togglePassword() {
    setState(() {
      passwordVisible = !passwordVisible;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: Padding(
          padding: EdgeInsets.fromLTRB(24, 40, 24, 0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Selamat datang di Rumah Sehat\nLogin Pasien',
                    style: heading2.copyWith(color: textBlack),
                  ),
                  SizedBox(
                    height: 20,
                  )
                ],
              ),
              SizedBox(
                height: 48,
              ),
              Form(
                  child: Column(
                children: [
                  Container(
                    decoration: BoxDecoration(
                        color: textWhiteGrey,
                        borderRadius: BorderRadius.circular(14)),
                    child: TextFormField(
                      controller: _usernameController,
                      // onChanged: (val) {
                      //   user.username = val;
                      // },
                      validator: (value) {
                        if (value!.isEmpty) {
                          return 'Username is empty';
                        }
                        return null;
                      },
                      decoration: InputDecoration(
                          hintText: 'Username',
                          hintStyle: heading6.copyWith(color: textGrey),
                          border:
                              OutlineInputBorder(borderSide: BorderSide.none)),
                    ),
                  ),
                  SizedBox(
                    height: 32,
                  ),
                  Container(
                    decoration: BoxDecoration(
                        color: textWhiteGrey,
                        borderRadius: BorderRadius.circular(14)),
                    child: TextFormField(
                      controller: _passwordController,
                      // onChanged: (val) {
                      //   user.password = val;
                      // },
                      validator: (value) {
                        if (value!.isEmpty) {
                          return 'Username is empty';
                        }
                        return null;
                      },
                      obscureText: !passwordVisible,
                      decoration: InputDecoration(
                          hintText: 'Password',
                          hintStyle: heading6.copyWith(color: textGrey),
                          suffixIcon: IconButton(
                            color: textGrey,
                            splashRadius: 1,
                            icon: Icon(passwordVisible
                                ? Icons.visibility_outlined
                                : Icons.visibility_off_outlined),
                            onPressed: togglePassword,
                          ),
                          border:
                              OutlineInputBorder(borderSide: BorderSide.none)),
                    ),
                  )
                ],
              )),
              SizedBox(
                height: 32,
              ),
              ElevatedButton(
                onPressed: () async {
                  SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
                  var username = _usernameController.text;
                  var password = _passwordController.text;
                  var jwt = await attemptLogIn(username, password);
                  if (jwt != null) {
                    sharedPreferences.setString("token", jwt);
                    storage.write(key: "token", value: jwt);
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) =>
                                ListTagihan()));
                  } else {
                    displayDialog(context, "Login Gagal",
                        "Harap masukkan username dan password yang sesuai!");
                  }
                },
                child: Text("Login"),
              ),
              SizedBox(
                height: 50,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    'Belum punya akun? ',
                    style: regular16pt.copyWith(color: textGrey),
                  ),
                  GestureDetector(
                    onTap: () {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => RegisterPage()));
                    },
                    child: Text(
                      'Register',
                      style: regular16pt.copyWith(color: primaryBlue),
                    ),
                  )
                ],
              )
            ],
          ),
        ),
      ),
    );
  }
}
