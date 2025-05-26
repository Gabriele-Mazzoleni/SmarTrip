
import 'package:flutter/material.dart';
import 'package:smart_trip_app/Domain/luogo.dart';
import 'package:smart_trip_app/Domain/luogo_esteso.dart';
import 'package:smart_trip_app/Presentation/Controllers/trip_page_controller.dart';
import 'package:smart_trip_app/Presentation/Styles/app_colors.dart';
import 'package:smart_trip_app/Presentation/Styles/font_styles.dart';
import 'package:smart_trip_app/Presentation/Styles/sizes.dart';

class LuogoEstesoWidget extends StatefulWidget {
  final LuogoEsteso luogoEst;
  final String ip;

  //FILE IN SVILUPPO, DEVO VEDERE SE E' POSSSIBILE METTERE DIFFERENTI CARATTERISTICHE PER LE CARD

  const LuogoEstesoWidget({super.key, required this.luogoEst, required this.ip});

  @override
  _LuogoEstesoWidgetState createState() => _LuogoEstesoWidgetState();
}

class _LuogoEstesoWidgetState extends State<LuogoEstesoWidget> {

  Luogo? selectedRestaurant; //viene effettivamente istanziato soltanto se il luogo è una selezione ristorante

  @override
  Widget build(BuildContext context) {
    //Tre tipi di card diversa
    if(widget.luogoEst.luogo.tipo=='Default'){
      //tipo 1: per luoghi default -> partenza/arrivo
      return Card(
        margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
        color: AppColors.red,
        child: ListTile(
          title: Text(widget.luogoEst.luogo.nome, style:FontStyles.redCardTitle),
          subtitle: Align(
            alignment: Alignment.centerLeft,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Orario previsto: ${formatTimeOfDay(widget.luogoEst.oraArrivo)}', style:FontStyles.redCardText),
              ],),
            )
      ),
      );
    }
    else if(widget.luogoEst.luogo.tipo=='Ristorante'){
      //tipo 2: per ricerca ristorante
      return Card(
        margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
        child: ListTile(
          title: Text(widget.luogoEst.luogo.nome, style:FontStyles.redCardTitle),
          subtitle: Align(
            alignment: Alignment.centerLeft,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Orario previsto: ${formatTimeOfDay(widget.luogoEst.oraArrivo)}', style:FontStyles.redCardText),
                ElevatedButton(
                    onPressed: () {
                      _showRestaurantSelectionModal(context, widget.luogoEst.luogo.latitudine, widget.luogoEst.luogo.longitudine);
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor: AppColors.red,
                      minimumSize: const Size(double.infinity, 50), // Larghezza bottone
                      shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                            ),
                    ),
                    child: (selectedRestaurant!= null)
                      ? const Icon(Icons.change_circle_outlined, color:AppColors.white, size:10.0)
                      : const Icon(Icons.add, color:AppColors.white, size:10.0),
                  ),
              ],),
            )
      ),
      );
    }
    else{
      //tipo 3: pre luoghi visitabili e di ristoro
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

  //modal per la selezione dei ristoranti
  Future<void> _showRestaurantSelectionModal(BuildContext context, double lat, double long) async {
    //recupero una lista di 4 ristoranti vicini
    List<Luogo> possibleRestaurants=await getListaRistoranti(widget.ip, lat, long);
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return StatefulBuilder(
          builder: (context, setState) {
            return AlertDialog(
              content: const SingleChildScrollView(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    SizedBox(height: Sizes.smallPaddingSpace),
                  ],
                ),
              ),
              actions: [
                TextButton(
                  style: ElevatedButton.styleFrom(
                        backgroundColor: AppColors.red,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                        ),
                      ),
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                  child: const Text('ANNULLA', style:FontStyles.buttonTextWhite),
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                        backgroundColor: AppColors.red,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(Sizes.smallRoundedCorner),
                        ),
                      ),
                  onPressed: () {
                    setState(() {
                      //imposta selectedRestaurant al ristorante selezionato nel modal, dopodichè chiude il modal
                    });
                  },
                  child:  const Text('CONFERMA', style: FontStyles.buttonTextWhite),
                ),
              ],
            );
          },
        );
      },
    );
  }

}


