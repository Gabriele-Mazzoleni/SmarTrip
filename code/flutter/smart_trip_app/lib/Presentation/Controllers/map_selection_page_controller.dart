import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:smart_trip_app/Domain/mappa.dart';

//DA SISTEMARE, MANCA API 
Future<List<Mappa>?> retrieveUserMaps(String mail,String indirizzo) async{
  String apiUrl='http://$indirizzo/......';

  var url = Uri.parse(apiUrl);

   final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(<String, Object>{
      'mail': mail,

    }),
  );


  if (response.statusCode >= 200 && response.statusCode<300) {
    //TODO devo ancora implementare la costruzione della lista di mappe
    return null;
    
  } else {
    throw Exception('Invalid credentials');
  }
}