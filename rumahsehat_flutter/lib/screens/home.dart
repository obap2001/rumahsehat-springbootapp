import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/auth/login.dart';
import 'package:rumah_sehat_flutter/screens/add_appointment_new.dart';
import 'package:rumah_sehat_flutter/screens/lihat_profile.dart';
import 'package:rumah_sehat_flutter/screens/top_up_saldo.dart';
import 'package:rumah_sehat_flutter/screens/list-tagihan.dart';

class RumahSehatHome extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _RumahSehatHomeState();
  }
}

class _RumahSehatHomeState extends State<RumahSehatHome> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Rumah Sehat'),
      ),
      body: Center(child: Text('Welcome to Rumah Sehat')),
      drawer: Drawer(
          child: ListView(
        padding: EdgeInsets.only(top: 5, bottom: 5),
        children: [
          DrawerHeader(
            child: Text('Rumah Sehat'),
            decoration: BoxDecoration(
              color: Colors.blue,
            ),
          ),
          ListTile(
            title: Text('Add Appointment'),
            onTap: () {
              Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => const addAppointment()));
            },
          ),
          ListTile(
            title: Text('Detail Appointment'),
            onTap: () {},
          ),
          ListTile(
            title: Text('List-Tagihan'),
            onTap: () {
              // Navigator.push(
              //     context,
              //     MaterialPageRoute(
              //         builder: (context) => const ListTagihan()));
            },
          ),
          ListTile(
            title: Text('Lihat Profile'),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => ProfilPasienPage()));
            },
          ),
          ListTile(
            title: Text('Logout'),
            onTap: () async {
              await storage.delete(key: "token");
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => LoginPage()));
            },
          ),
        ],
      )),
    );
  }
  
  void logout() {}
}
