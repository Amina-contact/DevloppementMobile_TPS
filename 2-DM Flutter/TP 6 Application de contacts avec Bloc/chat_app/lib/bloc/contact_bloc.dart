import 'package:chat_app/bloc/contact_event.dart';
import 'package:chat_app/bloc/contact_state.dart';
import 'package:chat_app/model/contact_model.dart';
import 'package:chat_app/repositories/contact_repository.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class ContactBloc extends Bloc<ContactEvent,ContactState>{
  ContactRepository contactRepository;
  //late pour l'initialiser après
  String lastEvent="";
  ContactBloc(ContactState contactState,this.contactRepository):super(contactState){
      //l'entré est event et la sortie est state
        on<ContactEvent>((event, emit)async{
            if(event is LoadAllContactsEvent){
              lastEvent="All";
                emit(ContactState(contacts: [],requestState: RequestState.Loading, errorMessage: ""));
                try {
                  List<Contact> contacts = await contactRepository.allContacts();
                  emit(ContactState(contacts: contacts, requestState: RequestState.Loaded, errorMessage: ""));
                }catch(e){
                  emit(ContactState(contacts: [],requestState: RequestState.Error, errorMessage: e.toString()));
                }
            }else if(event is LoadBDCCContactsEvent){
              lastEvent="BDCC";
                  emit(ContactState(contacts: [],requestState: RequestState.Loading, errorMessage: ""));
                  try {
                    List<Contact> contacts = await contactRepository.contactsByGroup("BDCC");
                    emit(ContactState(contacts: contacts, requestState: RequestState.Loaded, errorMessage: ""));
                  }catch(e){
                    emit(ContactState(contacts: [],requestState: RequestState.Error, errorMessage: e.toString()));
                  }
              }else if(event is LoadGLSIDContactsEvent){
              lastEvent="GLSID";
                    emit(ContactState(contacts: [],requestState: RequestState.Loading, errorMessage: ""));
                    try {
                      List<Contact> contacts = await contactRepository.contactsByGroup("GLSID");
                      emit(ContactState(contacts: contacts, requestState: RequestState.Loaded, errorMessage: ""));
                    }catch(e){
                      emit(ContactState(contacts: [],requestState: RequestState.Error, errorMessage: e.toString()));
                    }
            }
        });
  }
}