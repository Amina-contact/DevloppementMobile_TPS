import 'package:chat_app/ui/widgets/button_contact.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../bloc/contact_bloc.dart';
import '../../bloc/contact_event.dart';

class ButtonsBar extends StatelessWidget{
  const ButtonsBar({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        ButtonContact(contactEvent: LoadAllContactsEvent(), text: "All"),
        ButtonContact(contactEvent: LoadBDCCContactsEvent(), text: "BDCC"),
        ButtonContact(contactEvent: LoadGLSIDContactsEvent(), text: "GLSID"),
      ],
    );
  }

}