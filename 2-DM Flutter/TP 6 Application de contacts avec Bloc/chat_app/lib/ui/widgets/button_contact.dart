import 'package:chat_app/bloc/contact_bloc.dart';
import 'package:chat_app/bloc/contact_event.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class ButtonContact extends StatelessWidget{
  final ContactEvent contactEvent;
  final String text;
  const ButtonContact({Key? key,required this.contactEvent,required this.text}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ElevatedButton(onPressed: (){
      context.read<ContactBloc>().add(contactEvent);
    }, child: Text(text),
      style: ElevatedButton.styleFrom(primary: (context.read<ContactBloc>().lastEvent==text)? Colors.amberAccent :Colors.brown),);
  }
}