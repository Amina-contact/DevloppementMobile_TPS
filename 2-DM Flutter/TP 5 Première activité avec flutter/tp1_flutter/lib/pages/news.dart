import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;
import 'package:url_launcher/url_launcher.dart';
class News extends StatefulWidget {
  @override
  State<News> createState() => _NewsState();
}

class _NewsState extends State<News> {
  var news;

  TextEditingController textEditingController = new TextEditingController();
  TextEditingController dateEditingController = new TextEditingController();
  DateTime selectedDate = DateTime.now();

  Future<void> _selectDate(BuildContext context) async {
    final DateTime? picked = await showDatePicker(
        context: context,
        initialDate: selectedDate,
        firstDate: DateTime(2006, 1, 1),
        lastDate:
            DateTime(selectedDate.year, selectedDate.month, selectedDate.day));
    if (picked != null && picked != selectedDate) {
      setState(() {
        selectedDate = picked;
      });
    }
  }

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance?.addPostFrameCallback((timeStamp) {
      textEditingController.text = "Morocco";
      searchNews("Morocco", dateFormat(DateTime.now()));
    });
  }

  String dateFormat(DateTime dateTime) {
    return dateTime.year.toString() +
        "-" +
        dateTime.month.toString() +
        "-" +
        dateTime.day.toString();
  }

  void searchNews(keyword, date) {
    String url = "https://newsapi.org/v2/everything?q=$keyword&from=$date&sortBy=publishedAt&apiKey=bdccb2e2430844eaa5e3099ccc0b0ab5";
    http.get(Uri.parse(url)).then((response) {
      setState(() {
        news = json.decode(response.body);
      });
    }).catchError((onError) {
      print(onError);
      Fluttertoast.showToast(
          msg: "Can't query data with News Api !",
          toastLength: Toast.LENGTH_LONG,
          gravity: ToastGravity.SNACKBAR,
          timeInSecForIosWeb: 2,
          backgroundColor: Colors.red,
          textColor: Colors.white,
          fontSize: 16.0);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("News"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: Column(
          children: [
            Row(
              children: [
                Expanded(
                    child: TextFormField(
                  controller: textEditingController,
                  decoration: InputDecoration(
                      prefixIcon: const Icon(Icons.search),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(15),
                      )),
                ))
              ],
            ),
            Padding(
              padding: const EdgeInsets.only(top: 10.0),
              child: Row(
                children: [
                  Expanded(
                    child: TextButton.icon(
                      onPressed: () => _selectDate(context),
                      icon: const Icon(Icons.calendar_today_outlined,color: Colors.deepPurple),
                      label: Text("${selectedDate.toLocal()}".split(' ')[0]),
                    ),
                  ),
                  IconButton(
                    onPressed: () {
                      setState(() {
                        searchNews(textEditingController.text, dateFormat(selectedDate));
                      });
                    },
                    icon: const Icon(Icons.search, color: Colors.deepPurple),
                  )
                ],
              ),
            ),
            Expanded(
              child: Padding(
                padding: const EdgeInsets.only(
                    top: 20, left: 8, right: 8, bottom: 20),
                child: ListView.builder(
                  itemCount: news == null || news["articles"] == null
                      ? 0
                      : (news["articles"].length > 10
                          ? 10
                          : news["articles"].length),
                  itemBuilder: (context, index) {
                    String imageUrl = news["articles"][index]["urlToImage"].toString();
                    String title = news["articles"][index]["title"].toString();
                    String url = news["articles"][index]["url"].toString();
                    return GestureDetector(
                      onTap: () async {
                        if (!await launch(url)) {
                          Fluttertoast.showToast(
                              msg: "Can't open link !",
                              toastLength: Toast.LENGTH_LONG,
                              gravity: ToastGravity.SNACKBAR,
                              timeInSecForIosWeb: 2,
                              backgroundColor: Colors.red,
                              textColor: Colors.white,
                              fontSize: 16.0);
                        }
                      },
                      child: Card(
                        color: Colors.transparent,
                        child: Container(
                          height: 300,
                          width: double.infinity,
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(15.0),
                              image: DecorationImage(
                                  fit: BoxFit.cover,
                                  image: imageUrl.isNotEmpty
                                      ? FadeInImage.assetNetwork(
                                              image: imageUrl,
                                              placeholder:
                                                  "images/defaultimage.jpg")
                                          .image
                                      : Image.asset("images/defaultimage.jpg")
                                          .image)),
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              Container(
                                color: Colors.deepOrange,
                                width: double.infinity,
                                child: Padding(
                                  padding: const EdgeInsets.only(
                                      bottom: 6, left: 5, right: 5),
                                  child: Text(title,
                                      textAlign: TextAlign.justify,
                                      style: const TextStyle(
                                        fontSize: 20,
                                        fontWeight: FontWeight.bold,
                                        color: Colors.white,
                                      )),
                                ),
                              )
                            ],
                          ),
                        ),
                        margin: const EdgeInsets.only(
                            left: 5.0, right: 5.0, top: 17.0),
                      ),
                    );
                  },
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
