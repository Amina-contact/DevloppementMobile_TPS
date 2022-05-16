import 'package:chat_app/bloc/contact_bloc.dart';
import 'package:chat_app/bloc/contact_state.dart';
import 'package:chat_app/repositories/contact_repository.dart';
import 'package:chat_app/ui/pages/contacts_pages.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
        providers: [BlocProvider(create: (context)=>ContactBloc(new ContactState(contacts: [], requestState: RequestState.NONE, errorMessage: ""),ContactRepository()))],
        child: MaterialApp(
          theme: ThemeData(
            primarySwatch: Colors.brown
          ),
          routes: {
            "/contacts": (context)=> ContactPage(),
          },
          initialRoute: "/contacts",
        ));
  }
}
