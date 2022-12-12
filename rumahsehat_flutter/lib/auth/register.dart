import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/auth/login.dart';
import 'package:rumah_sehat_flutter/theme.dart';
import 'package:http/http.dart' as http;


const SERVER_IP = 'http://localhost:8080';

class RegisterPage extends StatefulWidget {
  const RegisterPage({Key? key}) : super(key: key);

  @override
  _RegisterPageState createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  // Pasien pasien = Pasien("", "", "", "", 0, false, "pasien");

  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _namaController = TextEditingController();
  final TextEditingController _umurController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  void displayDialog(context, title, text) => showDialog(
        context: context,
        builder: (context) =>
            AlertDialog(title: Text(title), content: Text(text)),
      );

  Future<int> attemptSignUp(String email, String username, String nama,
      int umur, String password) async {
    final msg = jsonEncode({
      "email": email,
      "username": username,
      "nama": nama,
      "umur": umur,
      "password": password,
      "isSso": false,
      "role": "pasien"
    });
    var res = await http.post(Uri.parse('$SERVER_IP/api/register'),
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
    return res.statusCode;
  }

  bool passwordVisible = false;
  bool passwordConfirmVisible = false;
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
                  'Selamat datang di Rumah Sehat\nRegister Pasien',
                  style: heading2.copyWith(color: textBlack),
                ),
                SizedBox(
                  height: 20,
                ),
                // Image.asset(
                //   'assets/images/accent.png',
                //   width: 99,
                //   height: 4,
                // )
              ],
            ),
            SizedBox(
              height: 32,
            ),
            Form(
                child: Column(
              children: [
                Container(
                  decoration: BoxDecoration(
                      color: textWhiteGrey,
                      borderRadius: BorderRadius.circular(14)),
                  child: TextFormField(
                    controller: _emailController,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Email is empty';
                      }
                      return null;
                    },
                    decoration: InputDecoration(
                      hintText: 'Email',
                      hintStyle: heading6.copyWith(color: textGrey),
                      border: OutlineInputBorder(borderSide: BorderSide.none),
                    ),
                  ),
                ),
                SizedBox(
                  height: 16,
                ),
                Container(
                  decoration: BoxDecoration(
                      color: textWhiteGrey,
                      borderRadius: BorderRadius.circular(14)),
                  child: TextFormField(
                    controller: _usernameController,
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
                  height: 16,
                ),
                Container(
                  decoration: BoxDecoration(
                      color: textWhiteGrey,
                      borderRadius: BorderRadius.circular(14)),
                  child: TextFormField(
                    controller: _namaController,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Nama is empty';
                      }
                      return null;
                    },
                    decoration: InputDecoration(
                        hintText: 'Nama',
                        hintStyle: heading6.copyWith(color: textGrey),
                        border:
                            OutlineInputBorder(borderSide: BorderSide.none)),
                  ),
                ),
                SizedBox(
                  height: 16,
                ),
                Container(
                  decoration: BoxDecoration(
                      color: textWhiteGrey,
                      borderRadius: BorderRadius.circular(14)),
                  child: TextFormField(
                    controller: _umurController,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Umur is empty';
                      }
                      return null;
                    },
                    decoration: InputDecoration(
                        hintText: 'Umur',
                        hintStyle: heading6.copyWith(color: textGrey),
                        border:
                            OutlineInputBorder(borderSide: BorderSide.none)),
                  ),
                ),
                SizedBox(
                  height: 16,
                ),
                Container(
                  decoration: BoxDecoration(
                      color: textWhiteGrey,
                      borderRadius: BorderRadius.circular(14)),
                  child: TextFormField(
                    controller: _passwordController,
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Password is empty';
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
                ),
                // SizedBox(
                //   height: 16,
                // ),
                // Container(
                //   decoration: BoxDecoration(
                //       color: textWhiteGrey,
                //       borderRadius: BorderRadius.circular(14)),
                //   child: TextFormField(
                //     validator: (value) {
                //       if (value!.isEmpty) {
                //         return 'Konfirmasi password is empty';
                //       }
                //       return null;
                //     },
                //     obscureText: !passwordConfirmVisible,
                //     decoration: InputDecoration(
                //         hintText: 'Konfirmasi Password',
                //         hintStyle: heading6.copyWith(color: textGrey),
                //         suffixIcon: IconButton(
                //           color: textGrey,
                //           splashRadius: 1,
                //           icon: Icon(passwordConfirmVisible
                //               ? Icons.visibility_outlined
                //               : Icons.visibility_off_outlined),
                //           onPressed: () {
                //             setState(() {
                //               passwordConfirmVisible = !passwordConfirmVisible;
                //             });
                //           },
                //         ),
                //         border:
                //             OutlineInputBorder(borderSide: BorderSide.none)),
                //   ),
                // )
              ],
            )),
            SizedBox(
              height: 16,
            ),
            ElevatedButton(
              onPressed: () async {
                // if (_formKey.currentState!.validate()) {
                //   save();
                // }
                var email = _emailController.text;
                var username = _usernameController.text;
                var nama = _namaController.text;
                var umur = int.parse(_umurController.text);
                var password = _passwordController.text;
                var res = await attemptSignUp(
                  email,
                  username,
                  nama,
                  umur,
                  password,
                );
                if(res == 200)
                    displayDialog(context, "Success", "The user was created. Log in now.");

                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => LoginPage()));
              },
              child: Text("Register"),
              // buttonColor: primaryBlue,
              // textColor: Colors.white,
            ),
            // CustomPrimaryButton(
            //   onPressed: () {
            //     if (_formKey.currentState!.validate()) {
            //       save();
            //     }
            //   },
            //   buttonColor: primaryBlue,
            //   textValue: 'Register',
            //   textColor: Colors.white,
            //   // TODO button
            // ),
            SizedBox(
              height: 32,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Sudah punya akun? ',
                  style: regular16pt.copyWith(color: textGrey),
                ),
                GestureDetector(
                  onTap: () {
                    Navigator.pop(context);
                  },
                  child: Text(
                    'Login',
                    style: regular16pt.copyWith(color: primaryBlue),
                  ),
                )
              ],
            )
          ],
        ),
      )),
    );
  }
}
