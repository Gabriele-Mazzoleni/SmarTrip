import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/luogo.dart';

class LuogoEsteso{
  final TimeOfDay oraArrivo;
  final Luogo luogo;

   LuogoEsteso({
    required this.oraArrivo,
    required this.luogo,
  });

  //metodo di decoding
  LuogoEsteso.fromJSON(Map<String, dynamic> jsonMap) :
    luogo=Luogo.fromJSON(jsonMap['luogo'] ?? ""),
    oraArrivo=(jsonMap['orarioDiArrivo'] ?? "");


    //metodo di encoding
    Map<String, dynamic> toJson() {
    return {
      'luogo': luogo.toJson(),
      'orarioDiArrivo': oraArrivo,
    };
  }
    
}