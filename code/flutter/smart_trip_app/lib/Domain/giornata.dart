import 'package:flutter/material.dart';

class Giornata {
  TimeOfDay? oraInizio;
  bool devoPranzare;
  TimeOfDay? oraPranzo;
  int? tempoPranzo;
  int? pausa;
  int? tempoVisita;

  Giornata({
    this.oraInizio,
    this.devoPranzare = false,
    this.oraPranzo,
    this.tempoPranzo,
    this.pausa,
    this.tempoVisita,
  });

  // Metodo di parsing orario "HH:mm" in TimeOfDay
  static TimeOfDay? _parseTime(String? timeString) {
    if (timeString == null || timeString.isEmpty) return null;
    final parts = timeString.split(':');
    if (parts.length != 2) return null;
    final hour = int.tryParse(parts[0]);
    final minute = int.tryParse(parts[1]);
    if (hour == null || minute == null) return null;
    return TimeOfDay(hour: hour, minute: minute);
  }

  // Metodo di encoding TimeOfDay in stringa "HH:mm"
  static String? _formatTime(TimeOfDay? time) {
    if (time == null) return null;
    final hour = time.hour.toString().padLeft(2, '0');
    final minute = time.minute.toString().padLeft(2, '0');
    return '$hour:$minute';
  }

  // Metodo di decoding
  factory Giornata.fromJSON(Map<String, dynamic> jsonMap) {
    return Giornata(
      oraInizio: _parseTime(jsonMap['orarioDiInizioVisita']),
      devoPranzare: jsonMap['devoPranzare'] ?? false,
      oraPranzo: _parseTime(jsonMap['orarioPranzo']),
      tempoPranzo: jsonMap['tempoPranzo'],
      pausa: jsonMap['pausa'],
      tempoVisita: jsonMap['tempoVisita'],
    );
  }

  // Metodo di encoding
  Map<String, dynamic> toJson() {
    return {
      'orarioDiInizioVisita': _formatTime(oraInizio),
      'devoPranzare': devoPranzare,
      'orarioPranzo': _formatTime(oraPranzo),
      'tempoPranzo': tempoPranzo,
      'pausa': pausa,
      'tempoVisita': tempoVisita,
    };
  }

  @override
String toString() {
  return '''
  Giornata(
  oraInizio: $oraInizio,
  devoPranzare: $devoPranzare,
  oraPranzo: $oraPranzo,
  tempoPranzo: $tempoPranzo,
  pausa: $pausa,
  tempoVisita: $tempoVisita
)
''';
}
}
