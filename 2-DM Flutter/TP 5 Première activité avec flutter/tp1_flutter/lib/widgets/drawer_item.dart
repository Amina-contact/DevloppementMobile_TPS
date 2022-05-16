
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class DrawerItem extends StatelessWidget{
   String route;
   String title;
   Icon icon;
   DrawerItem(this.title,this.route,this.icon);
  @override
  Widget build(BuildContext context) {
    return ListTile(
      onTap: (){
        Navigator.pushNamed(context, route);
      },
      leading: icon,
      title: Text(
        title,
        style: TextStyle(fontSize: 20, color: Colors.deepPurple),
      ),
    );
  }

}