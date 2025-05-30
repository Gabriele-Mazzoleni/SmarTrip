import 'dart:convert';
import 'package:flutter/material.dart';
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
    body: jsonEncode(m.toJson()),
  );

  if (response.statusCode >= 200 && response.statusCode<300) {
    final responseBody = jsonDecode(response.body);
    final Itinerario itinerario=Itinerario.fromJson(m.idMappa,m.nomeUtente,responseBody);
    return itinerario;
    
  } else {
    throw Exception('Error during Itinerary computation');
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

String formatTimeOfDay(TimeOfDay time) {
  final hours = time.hour.toString().padLeft(2, '0');
  final minutes = time.minute.toString().padLeft(2, '0');
  return '$hours:$minutes';
}

Future<List<Luogo>> getListaRistoranti(String indirizzo, double lat, double long) async{
    String apiUrl='http://$indirizzo/luoghi/$lat/$long/4';

  var url = Uri.parse(apiUrl);

   final http.Response response = await http.post(
    url,
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    }
  );

  if (response.statusCode >= 200 && response.statusCode<300) {
    //VA SISTEMATO, RISPOSTA JSON INCLUDE ANCHE DISTANZA (NON SO IN QUALE UNITA' DI MISURA)
    final responseBody = jsonDecode(response.body);
    var locations= List<Luogo>.from(responseBody.map((item) => Luogo.fromJSON(item)));

    return locations;
    
  } else {
    throw Exception('Error during location DB access');
  }
}