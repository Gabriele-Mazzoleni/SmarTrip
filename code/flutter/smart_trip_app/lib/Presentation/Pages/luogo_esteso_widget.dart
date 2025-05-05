
import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/luogo_esteso.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

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
    return GestureDetector(
      onTap: _toggleExpand, // Cambia lo stato quando la Card viene toccata
      child: AnimatedContainer(
        duration: const Duration(milliseconds: 300), // Durata dell'animazione
        curve: Curves.easeInOut, // Curva dell'animazione
        margin: const EdgeInsets.symmetric(vertical: 8.0, horizontal: 16.0),
        padding: const EdgeInsets.all(16.0),
        decoration: BoxDecoration(
          color: AppColors.white,
          borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
        ),
        width: double.infinity,
        // Lascio che l'altezza sia determinata dal contenuto
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              widget.luogoEst.luogo.nome,
              style: FontStyles.cardTitle,
            ),
            const SizedBox(height: Sizes.stdPaddingSpace),
            !_isExpanded
            ? const Icon(Icons.add_box_outlined, color:AppColors.black) 
            : 
            
            // Animazione della lista delle competenze richieste
            AnimatedOpacity(
              opacity: _isExpanded ? 1.0 : 0.0, // Cambia opacità in base allo stato di espansione
              duration: const Duration(milliseconds: 300), // Durata dell'animazione dell'opacità
              curve: Curves.easeInOut, // Curva dell'animazione
              child: _isExpanded 
                  ? const Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Competenze richieste per questa carriera:',
                          style: FontStyles.cardText,
                        ),
                        
                        SizedBox(height: Sizes.stdPaddingSpace),
                      ],
                    )
                  : const SizedBox.shrink(), // Placeholder se non espanso
            ),
            // Percentuale di affinità alla carriera
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Text('Percentuale di Affinità:', style: FontStyles.cardText,),
                PercentageCircle(percentage: 50),
              ],
            ),
          ],
        ),
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

