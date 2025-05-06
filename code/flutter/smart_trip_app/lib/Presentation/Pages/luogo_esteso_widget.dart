
import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/luogo_esteso.dart';
import 'package:smart_trip_app/Presentation/Controllers/trip_page_controller.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';

class LuogoEstesoWidget extends StatefulWidget {
  final LuogoEsteso luogoEst;

  //FILE IN SVILUPPO, DEVO VEDERE SE E' POSSSIBILE METTERE DIFFERENTI CARATTERISTICHE PER LE CARD

  LuogoEstesoWidget({required this.luogoEst});

  @override
  _LuogoEstesoWidgetState createState() => _LuogoEstesoWidgetState();
}

class _LuogoEstesoWidgetState extends State<LuogoEstesoWidget> {
  // Stato di espansione della Card
  bool _isExpanded = false; 

  // Cambia lo stato di espansione
  void _toggleExpand() {
    setState(() {
      _isExpanded = !_isExpanded; 
    });
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      child: ListTile(
        title: Text(widget.luogoEst.luogo.nome, style:FontStyles.cardTitle),
        subtitle: Align(
          alignment: Alignment.centerLeft,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(widget.luogoEst.luogo.indirizzo, style:FontStyles.cardText),
              Text('Orario di arrivo prevista: ${formatTimeOfDay(widget.luogoEst.oraArrivo)}', style:FontStyles.cardText),
              Text('Durata media visita: ${widget.luogoEst.luogo.tempoVisita/60} minuti', style:FontStyles.cardText),
          ],),
        )
      ),
    );
  }
}


class PercentageCircle extends StatelessWidget {
  final int percentage; // Percentuale

  PercentageCircle({required this.percentage});

  @override
  Widget build(BuildContext context) {
    return Stack(
      alignment: Alignment.center, // Posiziona il testo al centro del cerchio
      children: [
        SizedBox(
          width: 50.0,
          height: 50.0,
          child: CircularProgressIndicator(
            value: percentage / 100, // La percentuale viene divisa per 100
            strokeWidth: 6.0, // Spessore del cerchio
            backgroundColor: AppColors.white, // Colore di sfondo del cerchio
            valueColor: const AlwaysStoppedAnimation<Color>(AppColors.black), // Colore del cerchio riempito
          ),
        ),
        Text(
          '${percentage}%', // Testo della percentuale
          style:FontStyles.cardText,
        ),
      ],
    );
  }
}

