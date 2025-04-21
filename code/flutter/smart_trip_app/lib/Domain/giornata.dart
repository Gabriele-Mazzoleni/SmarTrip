import 'package:flutter/material.dart';

class Giornata{

  final TimeOfDay oraInizio;
  final bool devoPranzare;
  final TimeOfDay oraPranzo;
  final int tempoPranzo;
  final int pausa;
  final int tempoVisita;


  Giornata({
    required this.oraInizio,
    required this.devoPranzare,
    required this.oraPranzo,
    required this.tempoPranzo,
    required this.pausa,
    required this.tempoVisita
  });


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