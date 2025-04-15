import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:smart_trip_app/Domain/luogo.dart';

//Richiede e ottiene la lista di città presenti nel database
Future<List<Luogo>> retrieveLocations(String city, String indirizzo) async{
  String apiUrl='http://$indirizzo/luoghi/$city';

  var url = Uri.parse(apiUrl);

   final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    }
  );

  

  if (response.statusCode >= 200 && response.statusCode<300) {
    final responseBody = jsonDecode(response.body);
    var locations= List<Luogo>.from(responseBody.map((item) => Luogo.fromJSON(item)));

    return locations;
    
  } else {
    throw Exception('Error during location DB access');
  }
}