import 'package:flutter/material.dart';

class About extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("About"),
        ),
        body: Center(
          heightFactor: 2,
          child: Padding(
            padding: const EdgeInsets.only(top: 50),
            child: Column(
              children: [
                const Padding(
                  padding: EdgeInsets.all(8.0),
                  child:  Text(
                      "Applications  with flutter which allows the addition of contacts, the search on github users, see the status of covid-19 in each country and consult weather.",
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Colors.deepPurple, fontSize: 30)),
                ),
              ],
            ),
          ),
        ));
  }
}
