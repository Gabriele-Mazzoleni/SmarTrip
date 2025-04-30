  import 'package:flutter/material.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';

String formattaDurata(int secondiTotali) {
  final ore = secondiTotali ~/ 3600; // divisione intera
  final minuti = (secondiTotali  ~/ 60) - (ore*60);

  final parteOre = ore > 0 ? '$ore ${ore == 1 ? "ora" : "ore"}' : '';
  final parteMinuti = minuti > 0 ? '$minuti ${minuti == 1 ? "minuto" : "minuti"}' : '';

  if (parteOre.isNotEmpty && parteMinuti.isNotEmpty) {
    return '$parteOre e $parteMinuti';
  } else if (parteOre.isNotEmpty) {
    return parteOre;
  } else if (parteMinuti.isNotEmpty) {
    return parteMinuti;
  } else {
    return '0 minuti';
  }
}

//metodo per modificare l'aspetto dei TimePicker
Future<TimeOfDay?> showCustomTimePicker({
  required BuildContext context,
  required TimeOfDay initialTime,
}) {
  return showTimePicker(
    context: context,
    initialTime: initialTime,
    builder: (context, child) {
      return Theme(
        data: Theme.of(context).copyWith(
          timePickerTheme: const TimePickerThemeData(
            backgroundColor: AppColors.white,
            hourMinuteTextColor: AppColors.black,
            dialHandColor: AppColors.red,
            entryModeIconColor: AppColors.red,
          ),
          colorScheme: const ColorScheme.light(
            primary: AppColors.red, // Colore dei pulsanti "OK" e "ANNULLA"
            onPrimary: Colors.white, // Testo dei pulsanti
            surface: Colors.white, // Sfondo principale
            onSurface: Colors.black, // Testo secondario
          ),
          textButtonTheme: TextButtonThemeData(
            style: TextButton.styleFrom(
              foregroundColor: AppColors.red, // Colore del testo nei bottoni
            ),
          ),
        ),
        child: child!,
      );
    },
  );
}
