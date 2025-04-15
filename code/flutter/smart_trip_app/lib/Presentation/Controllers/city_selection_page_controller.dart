import 'dart:convert';
import 'package:http/http.dart' as http;

//Richiede e ottiene la lista di città presenti nel database
Future<List<String>> retrieveCities(String indirizzo) async{
  String apiUrl='http://$indirizzo/luoghi/citta';

  var url = Uri.parse(apiUrl);

   final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
  );

  List<String> cities=[];

  if (response.statusCode >= 200 && response.statusCode<300) {
    cities= List<String>.from(jsonDecode(response.body));
    return cities;
    
  } else {
    return cities;
  }
}