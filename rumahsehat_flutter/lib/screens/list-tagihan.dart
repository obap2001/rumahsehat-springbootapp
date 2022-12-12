import 'dart:convert';


import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import 'detail-tagihan.dart';
import '../model/TagihanModel.dart';



class ListTagihan extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TagihanListState();
}

static Future<List<TagihanModel>> getAllTagihan() async {
    final jwt = await FlutterSecureStorage().read(key: 'jwt');
    final response = await Dio().get(
      'http://localhost:10078/api/pasien/tagihan/viewall',
      options: Options(
        headers: {'Authorization': jwt!},
      ),
    );
    final allTagihan = (response.data['list_tagihan'])
        .map((tagihan) => TagihanModel.fromJson(tagihan))
        .toList()
        .cast<TagihanModel>();
    return allTagihan;
  }


class TagihanListState extends State<ListTagihan> {
  late Future<List<TagihanModel>> tagihanListFuture;

  @override
  void initState() {
    tagihanListFuture = getAllTagihan();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Tagihan')),
      body: FutureBuilder<List<TagihanModel>>(
        future: tagihanListFuture,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            final tagihanList = snapshot.data!;
            return ListView(
              children: [
                ...tagihanList.map(
                  (tagihan) => ListTile(
                    leading: const Icon(Icons.receipt),
                    title: Text(tagihan.kode),
                    subtitle: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(tagihan.tanggal_terbuat.toString()),
                        tagihan.is_paid
                            ? const Text(
                                'Paid',
                                style: TextStyle(color: Colors.green),
                              )
                            : const Text(
                                'NotPaid',
                                style: TextStyle(color: Colors.red),
                              ),
                      ],
                    ),
                    trailing: ElevatedButton(
                      child: Text('Detail'),
                      onPressed: () {
                        Navigator.of(context).push(MaterialPageRoute(
                          builder: (context) => DetailTagihan(tagihan.kode),
                        ));
                      },
                    ),
                  ),
                ),
              ],
            );
          }
          if (snapshot.hasError) {
            return const Center(child: Text('Something went wrong'));
          }
          return const Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}
