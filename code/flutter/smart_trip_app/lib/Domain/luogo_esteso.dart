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
  factory LuogoEsteso.fromJSON(Map<String, dynamic> jsonMap) {
    final orarioStr = jsonMap['orarioDiArrivo'] as String? ?? "00:00";
    final parts = orarioStr.split(':');
    final hour = int.parse(parts[0]);
    final minute = int.parse(parts[1]);

    return LuogoEsteso(
      oraArrivo: TimeOfDay(hour: hour, minute: minute),
      luogo: Luogo.fromJSON(jsonMap['luogo']),
    );
  }

    //metodo di encoding
  Map<String, dynamic> toJson() {
    final hour = oraArrivo.hour.toString().padLeft(2, '0');
    final minute = oraArrivo.minute.toString().padLeft(2, '0');
    final orarioString = '$hour:$minute';

    return {
      'luogo': luogo.toJson(),
      'orarioDiArrivo': orarioString,
    };
  }
    
}