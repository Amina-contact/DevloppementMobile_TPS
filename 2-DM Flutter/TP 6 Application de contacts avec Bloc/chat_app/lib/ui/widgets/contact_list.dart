import 'package:chat_app/bloc/contact_state.dart';
import 'package:flutter/material.dart';

class ContactListView extends StatelessWidget{
  final ContactState state;
  const ContactListView({Key? key,required this.state}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ListView.separated(
        separatorBuilder: (context,index)=>Divider(color: Colors.black12,height: 3,),
        itemCount: state.contacts.length,
        itemBuilder: (context,index) {
          return ListTile(
            leading: CircleAvatar(child: Text("${state.contacts[index].profile}"),),
            title: Text(state.contacts[index].name),
          );
        });
  }
}