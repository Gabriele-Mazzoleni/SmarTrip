import 'package:flutter/material.dart';

class Giornata{

  TimeOfDay? oraInizio;
  bool devoPranzare=false;
  TimeOfDay? oraPranzo;
  int? tempoPranzo;
  int? pausa;
  int? tempoVisita;


  Giornata();

    //metodo di decoding
  Giornata.fromJSON(Map<String, dynamic> jsonMap) :
    oraInizio=(jsonMap['orarioDiInizioVisita'] ?? ""),
    devoPranzare=(jsonMap['devoPranzare'] ?? ""),
    oraPranzo=(jsonMap['orarioPranzo'] ?? ""),
    tempoPranzo=(jsonMap['tempoPranzo'] ?? ""),
    pausa=(jsonMap['pausa'] ?? ""),
    tempoVisita=(jsonMap['tempoVisita'] ?? "");


    //metodo di encoding
    Map<String, dynamic> toJson() {
    return {
      'orarioDiInizioVisita': oraInizio,
      'devoPranzare': devoPranzare,
      'orarioPranzo': oraPranzo,
      'tempoPranzo': tempoPranzo,
      'pausa': pausa,
      'tempoVisita': tempoVisita,
    };
  }
}