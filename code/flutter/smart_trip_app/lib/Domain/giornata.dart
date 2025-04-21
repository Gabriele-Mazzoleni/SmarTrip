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
}