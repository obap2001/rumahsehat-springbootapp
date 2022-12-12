// import 'dart:convert';
//
// import 'package:flutter/cupertino.dart';
// import 'package:flutter/material.dart';
// import 'package:shared_preferences/shared_preferences.dart';
// import '../model/TagihanModel.dart';
// import 'package:http/http.dart' as http;
//
//
//
// class ListTagihan extends StatefulWidget {
//   const ListTagihan({super.key});
//
//   @override
//   State<StatefulWidget> createState() => TagihanListState();
// }
//
// Future<List<TagihanModel>> getAllTagihan() async {
//   SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
//   var token = sharedPreferences.getString("token");
//   final response = await http.get(Uri.parse(
//       'http://localhost:10078/api/pasien/tagihan/viewall'),
//
//     headers: {'Authorization': 'Bearer ${token}'},
//   );
//   Map<String, dynamic> data = jsonDecode(response.body);
//   print(data);
//
//   // return TagihanModel.fromJson(jsonDecode(response.body));
//
//   final allTagihan = (response.data['list-tagihan'])
//       .map((tagihan) => TagihanModel.fromJson(tagihan))
//       .toList()
//       .cast<TagihanModel>();
//   return allTagihan;
// }
//
//
// class TagihanListState extends State<ListTagihan> {
//   late Future<List<TagihanModel>> tagihanListFuture;
//
//   @override
//   void initState() {
//     tagihanListFuture = getAllTagihan();
//     super.initState();
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(title: const Text('Tagihan')),
//       body: FutureBuilder<List<TagihanModel>>(
//         future: tagihanListFuture,
//         builder: (context, snapshot) {
//           if (snapshot.hasData) {
//             final tagihanList = snapshot.data!;
//             return ListView(
//               children: [
//                 ...tagihanList.map(
//                       (tagihan) => ListTile(
//                     leading: const Icon(Icons.receipt),
//                     title: Text(tagihan.kode.toString()),
//                     subtitle: Column(
//                       crossAxisAlignment: CrossAxisAlignment.start,
//                       children: [
//                         Text(tagihan.tanggalTerbuat.toString()),
//                         tagihan.isPaid.toString()
//                             ? const Text(
//                           'Paid',
//                           style: TextStyle(color: Colors.green),
//                         )
//                             : const Text(
//                           'NotPaid',
//                           style: TextStyle(color: Colors.red),
//                         ),
//                       ],
//                     ),
//                     trailing: ElevatedButton(
//                       child: Text('Detail'),
//                       onPressed: () {
//                         // Navigator.of(context).push(MaterialPageRoute(
//                         //   builder: (context) => DetailTagihan(tagihan.kode),
//                         // ));
//                       },
//                     ),
//                   ),
//                 ),
//               ],
//             );
//           }
//           if (snapshot.hasError) {
//             return const Center(child: Text('Something went wrong'));
//           }
//           return const Center(child: CircularProgressIndicator());
//         },
//       ),
//     );
//   }
// }
