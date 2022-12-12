import 'package:flutter/material.dart';
//import './editdata.dart';
import 'package:http/http.dart' as http;

class Detail extends StatefulWidget {
  List list;
  int index;
  Detail({super.key, required this.index,required this.list});
  @override
  _DetailState createState() => _DetailState();
}

class _DetailState extends State<Detail> {

  void deleteData(){
    var url="http://10.0.2.2/my_store/deleteData.php";
    http.post(Uri.parse(url), body: {
      'id': widget.list[widget.index]['id']
    });
  }

  void confirm (){
    AlertDialog alertDialog = AlertDialog(
      content: Text("Are You sure want to delete '${widget.list[widget.index]['item_name']}'"),
      actions: <Widget>[
        ElevatedButton(
          child: const Text("OK DELETE!",
            style: TextStyle(color: Colors.black),),
          //color: Colors.red,
          onPressed: (){
            deleteData();
            // Navigator.of(context).push(
            //     MaterialPageRoute(
            //       builder: (BuildContext context)=> new Home(),
            //     )
            // );
          },
        ),
        ElevatedButton(
          child: Text("CANCEL",style: TextStyle(color: Colors.black)),
          //color: Colors.green,
          onPressed: ()=> Navigator.pop(context),
        ),
      ],
    );

    //showDialog(context: context, child: alertDialog, builder: (BuildContext context) {  });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("${widget.list[widget.index]['item_name']}")),
      body: Container(
        height: 270.0,
        padding: const EdgeInsets.all(20.0),
        child: Card(
          child: Center(
            child: Column(
              children: <Widget>[

                const Padding(padding: EdgeInsets.only(top: 30.0),),
                Text(widget.list[widget.index]['item_name'], style: const TextStyle(fontSize: 20.0),),
                Text("Code : ${widget.list[widget.index]['item_code']}", style: const TextStyle(fontSize: 18.0),),
                Text("Price : ${widget.list[widget.index]['price']}", style: const TextStyle(fontSize: 18.0),),
                Text("Stock : ${widget.list[widget.index]['stock']}", style: const TextStyle(fontSize: 18.0),),
                const Padding(padding: EdgeInsets.only(top: 30.0),),

                Row(
                  mainAxisSize: MainAxisSize.min,
                  children: <Widget>[
                    // ElevatedButton(
                    //   child: const Text("EDIT"),
                    //   //color: Colors.green,
                    //   // onPressed: ()=>Navigator.of(context).push(
                    //   //     MaterialPageRoute(
                    //   //       //builder: (BuildContext context)=>EditData(list: widget.list, index: widget.index,),
                    //   //     )
                    //   // ),
                    // ),
                    ElevatedButton(
                      child: const Text("DELETE"),
                      //color: Colors.red,
                      onPressed: ()=>confirm(),
                    ),
                  ],
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}