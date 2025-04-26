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