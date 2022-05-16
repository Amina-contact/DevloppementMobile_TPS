import 'package:chat_app/model/contact_model.dart';
enum RequestState{Loaded,Loading,Error,NONE}
class ContactState{
  List<Contact> contacts;
  RequestState requestState;
  String errorMessage;

  ContactState({required this.contacts,required this.requestState,required this.errorMessage});
}