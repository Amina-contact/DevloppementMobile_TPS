import 'package:flutter/material.dart';
import 'package:tp1_flutter/widgets/app_drawer.dart';

class Home extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
       drawer: AppDrawer(),
        appBar: AppBar(
          title: Text("Home"),
        ),
        body:Center(
          child: ElevatedButton(
            onPressed: (){
              Navigator.pushNamed(context, "/contacts");
            },
            child: Text("Contacts"),
          ),
        )

    );
  }

}