import 'dart:convert';
import 'package:smart_trip_app/Domain/itinerario.dart';
import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Domain/mappa.dart';
import 'package:http/http.dart' as http;

Future<Itinerario> ottieniItinerarioDaNuovaMappa(Mappa m, String indirizzo) async {
  String apiUrl='http://$indirizzo/itinerari/add';

  var url = Uri.parse(apiUrl);
     final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: m.toJson(),
  );

  if (response.statusCode >= 200 && response.statusCode<300) {
    final responseBody = jsonDecode(response.body);
    final Itinerario itinerario=Itinerario.fromJson(m.idMappa,m.nomeUtente,responseBody);
    return itinerario;
    
  } else {
    throw Exception('Error Itinerary computation');
  }
  
}

Future<Itinerario> ottieniItinerarioDaVecchiaMappa(String indirizzo, String mapName, String userName) async {
String apiUrl='http://$indirizzo/itinerari/$mapName/$userName';

  var url = Uri.parse(apiUrl);
     final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
  );

  if (response.statusCode >= 200 && response.statusCode<300) {
    final responseBody = jsonDecode(response.body);
    final Itinerario itinerario=Itinerario.fromJson(mapName,userName,responseBody);
    return itinerario;
    
  } else {
    throw Exception('Error Itinerary computation');
  }
}


String getCityFromItineario(Itinerario i){
  
  return '';
}

List<Luogo> getLuoghiFromItinerario(Itinerario i){
  List<Luogo> luoghiSel=[];
  return luoghiSel;
}