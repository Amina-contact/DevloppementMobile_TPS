import 'dart:math';

import 'package:chat_app/model/contact_model.dart';

class ContactRepository{
  List<Contact> contacts=[Contact(id: 1, name: "Amina", group: "BDCC", profile: "AM"),
                          Contact(id: 2, name: "Fatima", group: "GLSID", profile: "FA"),
                          Contact(id: 3, name: "Mohamed", group: "BDCC", profile: "MO"),
                          Contact(id: 4, name: "Achraf", group: "GLSID", profile: "AC"),
                          Contact(id: 5, name: "Zakia", group: "BDCC", profile: "ZA")];

//Créeer des méthodes asynchrone pour communiquer avec le backend
    Future<List<Contact>> allContacts()async{
      var future = await Future.delayed(Duration(seconds: 2));
      //génerer des nombre aléatoire entre 0 et 10
      int rand=new Random().nextInt(10);
      if(rand>5){
        return contacts;
      }else{
        throw Exception("Erreur de chargement des contacts");
      }
    }

    Future<List<Contact>> contactsByGroup(String group)async{
      var future = await Future.delayed(Duration(seconds: 2));
      int rand=new Random().nextInt(10);
      if(rand>2){
        return contacts.where((element) => element.group==group).toList();
      }else{
        throw Exception("Erreur de chargement des contacts");
      }
    }
}