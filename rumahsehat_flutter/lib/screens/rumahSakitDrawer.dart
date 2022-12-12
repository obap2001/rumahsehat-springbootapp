import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/screens/add-appointment-new.dart';
import 'package:rumah_sehat_flutter/screens/lihatProfile.dart';
import 'package:rumah_sehat_flutter/screens/topUpSaldo.dart';

class RumahSehatHome extends StatefulWidget{
  @override
  State<StatefulWidget> createState(){
    return _RumahSehatHomeState();
  }
}

class _RumahSehatHomeState extends State<RumahSehatHome>{
  @override
  Widget build(BuildContext context){
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
              title: Text('Appointment'),
              onTap: (){
                Navigator.push(context, MaterialPageRoute(
                    builder: (context) => const addAppointment()));
              },
            ),
            ListTile(
              title: Text('Tagihan'),
              onTap: (){},
            ),
            ListTile(
              title: Text('Lihat Profile'),
              onTap: (){
                Navigator.push(context, MaterialPageRoute(
                    builder: (context) => ProfilPasienPage()));
              },
            )
          ],
        )
      ),
    );
  }
}